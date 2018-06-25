/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  AuthCheckService.java   
 * @Package troila.web.chat.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月22日 上午9:54:19   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.service;

import troila.web.chat.service.chain.AbstractCheckChain;
import troila.web.chat.service.chain.DataChecker;
import troila.web.chat.service.chain.TokenChecker;
import troila.web.chat.utils.Conf;

/**   
 * @ClassName:  AuthCheckService   
 * @Description:TODO(用户权限验证Service)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月22日 上午9:54:19   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class AuthCheckService {
	/**
	 * 
	 * @Title: checkUserAuth   
	 * @Description: TODO(验证用户权限)   
	 * @param: @param uri      
	 * @return: void      
	 * @throws
	 */
	public void checkUserAuth(String request) throws Exception{
		AbstractCheckChain checker = new DataChecker();
		if(!Conf.TEST) {
			checker.setNextHandler(new TokenChecker());
		}
		checker.checkRequest(request);
	}
	
}
