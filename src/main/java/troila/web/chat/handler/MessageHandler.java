package troila.web.chat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import troila.web.chat.action.factory.ActionFactory;
import troila.web.chat.proto.ActionCode;
import troila.web.chat.proto.ChatProto;
import troila.web.chat.proto.Message;
import troila.web.codec.CodecContext;


public class MessageHandler extends SimpleChannelInboundHandler<Object> {

	private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object mess)
            throws Exception {
    		Object message = CodecContext.decode(mess);
        	if(message instanceof ChatProto.Message) {
        		ChatProto.Message bean = (ChatProto.Message)message;
            	if(!ActionCode.codeList().contains(bean.getHeader())) {
            		logger.warn("Unsupport message code number:"+bean.getHeader());
            		return; 
            	}
            	ActionFactory.getAction(bean.getHeader()).execute(ctx.channel(),bean.getBody());
        	}else if(message instanceof Message){
        		Message bean = (Message)message;
            	if(!ActionCode.codeList().contains(bean.getHeader())) {
            		logger.warn("Unsupport message code number:"+bean.getHeader());
            		return; 
            	}
            	ActionFactory.getAction(bean.getHeader()).execute(ctx.channel(),bean.getBody());
        	}else {
        		logger.warn("Unsupport message");
        		return; 
        	}	    	
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
