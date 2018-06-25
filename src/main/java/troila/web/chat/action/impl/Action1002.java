/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  Action2002.java   
 * @Package troila.web.chat.action.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月25日 下午2:53:47   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.action.impl;

import io.netty.channel.Channel;
import troila.web.chat.action.IAction;
import troila.web.chat.handler.RoomManager;

/**   
 * @ClassName:  Action1002   
 * @Description:TODO(禁言消息处理类)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月25日 下午2:53:47   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class Action1002 implements IAction{

	/**   
	 * <p>Title: execute</p>   
	 * <p>Description: </p>   
	 * @param channel
	 * @param message   
	 * @see troila.web.chat.action.IAction#execute(io.netty.channel.Channel, java.lang.String)   
	 */
	@Override
	public void execute(Channel channel, String message) {
		//向相关人员发送禁言消息，并反馈给主播，操作成功或失败
		RoomManager.mutePerson(channel,message);
	}

}
