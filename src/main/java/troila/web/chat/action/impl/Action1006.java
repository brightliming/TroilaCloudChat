/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  Action1006.java   
 * @Package troila.web.chat.action.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月25日 下午4:07:18   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.action.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import troila.web.chat.action.IAction;
import troila.web.chat.handler.RoomManager;
import troila.web.chat.utils.NettyUtil;

/**   
 * @ClassName:  Action1006   
 * @Description:TODO(处理用户PONG消息)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月25日 下午4:07:18   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class Action1006 implements IAction{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**   
	 * <p>Title: execute</p>   
	 * <p>Description: 处理用户PONG消息</p>   
	 * @param channel
	 * @param message   
	 * @see troila.web.chat.action.IAction#execute(io.netty.channel.Channel, java.lang.String)   
	 */
	@Override
	public void execute(Channel channel, String message) {
		RoomManager.updateUserTime(channel);
        logger.info("receive pong message, address: {}",NettyUtil.parseChannelRemoteAddr(channel));
	}

}
