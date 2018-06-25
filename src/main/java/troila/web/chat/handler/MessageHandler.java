package troila.web.chat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import troila.web.chat.action.factory.ActionFactory;
import troila.web.chat.proto.ActionCode;
import troila.web.chat.proto.ChatProto;


public class MessageHandler extends SimpleChannelInboundHandler<Object> {

	private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object mess)
            throws Exception {
    	ChatProto.Message message = (ChatProto.Message)mess;
    	if(!ActionCode.codeList().contains(message.getHeader())) {
    		logger.info("Unsupport message code number:"+message.getHeader());
    		return; 
    	}
    	ActionFactory.getAction(message.getHeader()).execute(ctx.channel(),message.getBody());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        RoomManager.removePerson(ctx.channel());
        super.channelUnregistered(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("connection error and close the channel", cause);
        RoomManager.removePerson(ctx.channel());
    }

}
