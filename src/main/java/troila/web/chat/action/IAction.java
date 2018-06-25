package troila.web.chat.action;

import io.netty.channel.Channel;

/**
 * 聊天业务处理类
 * @author limingliang
 *
 */
public interface IAction {
	void execute(Channel channel,String message);
}
