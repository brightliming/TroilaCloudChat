/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  TokenChecker.java   
 * @Package troila.web.chat.service.chain   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月22日 下午1:15:46   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.service.chain;

import troila.web.chat.exception.ChatException;
import troila.web.chat.service.AuthCheckService;
import troila.web.chat.service.RedisService;

/**   
 * @ClassName:  TokenChecker   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月22日 下午1:15:46   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class TokenChecker extends AbstractCheckChain{

	/**   
	 * <p>Title: checkRequest</p>   
	 * <p>Description: 校验token的正确性</p>   
	 * @param request
	 * @throws Exception   
	 * @see troila.web.chat.service.chain.AbstractCheckChain#checkRequest(java.lang.String)   
	 */
	@Override
	public void checkRequest(String request) throws Exception {
		// TODO Auto-generated method stub
		String token = request.split("/")[3];
		if(!new RedisService().existKey(token)) {
			throw new ChatException("用户权限校验失败，断开连接!");
		}
	}

}
