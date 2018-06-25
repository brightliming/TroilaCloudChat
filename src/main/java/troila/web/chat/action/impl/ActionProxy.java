/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  ActionProxy.java   
 * @Package troila.web.chat.action.impl   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月25日 下午1:12:18   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.action.impl;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import troila.web.chat.action.IAction;
import troila.web.chat.handler.RoomManager;
import troila.web.chat.utils.NettyUtil;

/**   
 * @ClassName:  ActionProxy   
 * @Description:TODO(Actin代理类)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月25日 下午1:12:18   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class ActionProxy implements IAction{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private IAction action;

	public ActionProxy(IAction action) {
		this.action = action;
	}
	/**   
	 * <p>Title: execute</p>   
	 * <p>Description:聊天业务代理类 </p>   
	 * @param message
	 * @param ctx
	 * @param type   
	 * @see troila.web.chat.action.IAction#execute(java.lang.String, io.netty.channel.ChannelHandlerContext, java.lang.String)   
	 */
	@Override
	public void execute(Channel channel,String message) {
		logger.info("{} 进行业务处理:{}",NettyUtil.parseChannelRemoteAddr(channel),action.getClass().getSimpleName());
		action.execute(channel,message);
		logger.info("{} 进行业务处理成功:{}",NettyUtil.parseChannelRemoteAddr(channel),action.getClass().getSimpleName());
	}
	
}
