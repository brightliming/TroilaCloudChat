//package troila.web.chat.handler;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//public class MessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
//
//	private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame)
//            throws Exception {
//        UserInfo userInfo = UserManager.getUserInfo(ctx.channel());
//        if (userInfo != null && userInfo.isAuth()) {
//            JSONObject json = JSONObject.parseObject(frame.text());
//            // 广播返回用户发送的消息文本
//            UserManager.broadcastMess(userInfo.getUserId(), userInfo.getNick(), json.getString("mess"));
//        }
//    }
//
//    @Override
//    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        UserManager.removeChannel(ctx.channel());
//        UserManager.broadCastInfo(ChatCode.SYS_USER_COUNT,UserManager.getAuthUserCount());
//        super.channelUnregistered(ctx);
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        logger.error("connection error and close the channel", cause);
//        UserManager.removeChannel(ctx.channel());
//        UserManager.broadCastInfo(ChatCode.SYS_USER_COUNT, UserManager.getAuthUserCount());
//    }
//
//}
