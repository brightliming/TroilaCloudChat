/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  DataChecker.java   
 * @Package troila.web.chat.service.chain   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月22日 下午1:14:30   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.service.chain;

/**   
 * @ClassName:  DataChecker   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月22日 下午1:14:30   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class DataChecker extends AbstractCheckChain {

	/**   
	 * <p>Title: checkRequest</p>   
	 * <p>Description:校验传送过来的数据的完整性 </p>   
	 * @param request
	 * @throws Exception   
	 * @see troila.web.chat.service.chain.AbstractCheckChain#checkRequest(java.lang.String)   
	 */
	@Override
	public void checkRequest(String request) throws Exception {
		
		
		// TODO Auto-generated method stub
		if(this.hasNext()) {
			this.next.checkRequest(request);
		}	
	}

}
