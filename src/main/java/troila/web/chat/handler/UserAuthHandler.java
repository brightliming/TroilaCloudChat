package troila.web.chat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import troila.web.chat.proto.ChatProto;
import troila.web.chat.proto.Person;
import troila.web.chat.proto.User;
import troila.web.chat.service.AuthCheckService;
import troila.web.chat.service.RedisService;
import troila.web.chat.utils.Conf;
import troila.web.chat.utils.NettyUtil;

/**
 *
 * @ClassName:  UserAuthHandler   
 * @Description:TODO(处理HTTP握手请求，校验token，升级协议成websocket)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月22日 上午9:24:53   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class UserAuthHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = LoggerFactory.getLogger(UserAuthHandler.class);

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else{
            handleWebSocket(ctx, msg);
        }
    }
    /**
     * 
     * <p>Title: userEventTriggered</p>   
     * <p>Description: 判断Channel是否长时间读空闲，是则移除Channel</p>   
     * @param ctx
     * @param evt
     * @throws Exception   
     * @see io.netty.channel.ChannelInboundHandlerAdapter#userEventTriggered(io.netty.channel.ChannelHandlerContext, java.lang.Object)
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent evnet = (IdleStateEvent) evt;
            // 判断Channel是否读空闲, 读空闲时移除Channel
            if (evnet.state().equals(IdleState.READER_IDLE)) {
                final String remoteAddress = NettyUtil.parseChannelRemoteAddr(ctx.channel());
                logger.warn("NETTY SERVER PIPELINE: IDLE exception [{}]", remoteAddress);
                RoomManager.removePerson(ctx.channel());
            }
        }
        ctx.fireUserEventTriggered(evt);
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        if (!request.decoderResult().isSuccess() || !"websocket".equals(request.headers().get("Upgrade"))) {
            logger.warn("protobuf don't support websocket");
            ctx.channel().close();
            return;
        }
        //验证权限
        try {
        	new AuthCheckService().checkUserAuth(request.getUri());
        }catch(Exception ex){
        	logger.error(ex.getMessage());
        	ctx.close();
        	return;
        }
        //验证通过进行握手
        WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory(
                Conf.WEBSOCKET_URI, null, true);
        handshaker = handshakerFactory.newHandshaker(request);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            // 动态加入websocket的编解码处理
            handshaker.handshake(ctx.channel(), request);
            // 握手成功，获取用户信息
            User user = new RedisService().getUserByToken(request.getUri().split("/")[3]);
            
            Person person = new Person(); 
            person.setId(""+user.getUserId());
            person.setNickName(user.getUserName());
            person.setRoomId(request.getUri().split("/")[2]);
            person.setAddr(NettyUtil.parseChannelRemoteAddr(ctx.channel()));
            person.setChannel(ctx.channel());
            person.setTime(System.currentTimeMillis());
            // 新人员进入直播间
            RoomManager.addPerson(person);
        }
    }

    private void handleWebSocket(ChannelHandlerContext ctx, Object frame) {
        // 判断是否关闭链路命令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), ((CloseWebSocketFrame) frame).retain());
            RoomManager.removePerson(ctx.channel());
            return;
        }
        // 判断是否Ping消息
        if (frame instanceof PingWebSocketFrame) {
            logger.info("ping message:{}", ((PingWebSocketFrame)frame).content().retain());
            ctx.writeAndFlush(new PongWebSocketFrame( ((PingWebSocketFrame)frame).content().retain()));
            return;
        }
        // 判断是否Pong消息
        if (frame instanceof PongWebSocketFrame) {
            logger.info("pong message:{}",  ((PongWebSocketFrame)frame).content().retain());
            ctx.writeAndFlush(new PingWebSocketFrame(((PongWebSocketFrame)frame).content().retain()));
            return;
        }
        if(Conf.USE_PROTO) {
        	 // 文本消息
            if (frame instanceof TextWebSocketFrame) {
            	logger.info("text message:{}",  ((TextWebSocketFrame)frame).text());
            	return;
            }
            // protobuf
            if (!(frame instanceof ChatProto.Message)) {
                throw new UnsupportedOperationException(frame.getClass().getName() + " frame type not supported");
            } 
            //后续消息交给MessageHandler处理
            ctx.fireChannelRead((ChatProto.Message)frame);
        }else {
            // 文本消息
            if (!(frame instanceof TextWebSocketFrame)) {
                throw new UnsupportedOperationException(frame.getClass().getName() + " frame type not supported");
            } 
            //后续消息交给MessageHandler处理
            ctx.fireChannelRead(((TextWebSocketFrame)frame).retain());
        }
    }
}
