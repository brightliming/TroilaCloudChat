package troila.web.chat.action;

import io.netty.channel.ChannelHandlerContext;

/**
 * 聊天业务处理类
 * @author limingliang
 *
 */
public interface IAction {
	void execute(String message,ChannelHandlerContext ctx,String type);
}
