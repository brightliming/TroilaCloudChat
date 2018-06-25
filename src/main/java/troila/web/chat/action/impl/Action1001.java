/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  Action1001.java   
 * @Package troila.web.chat.action.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月25日 下午3:16:27   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.action.impl;

import io.netty.channel.Channel;
import troila.web.chat.action.IAction;
import troila.web.chat.handler.RoomManager;

/**   
 * @ClassName:  Action1001   
 * @Description:TODO(踢人消息处理)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月25日 下午3:16:27   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class Action1001 implements IAction{

	/**   
	 * <p>Title: execute</p>   
	 * <p>Description: 踢人消息</p>   
	 * @param channel
	 * @param message   
	 * @see troila.web.chat.action.IAction#execute(io.netty.channel.Channel, java.lang.String)   
	 */
	@Override
	public void execute(Channel channel, String message) {
		//广播踢人消息，并想主播反馈成功与否
		RoomManager.kickPerson(channel,message);
	}
	
}
