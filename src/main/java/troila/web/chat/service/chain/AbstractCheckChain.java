/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  AbstractCheckChain.java   
 * @Package troila.web.chat.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月22日 上午10:22:16   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.service.chain;

/**   
 * @ClassName:  AbstractCheckChain   
 * @Description:TODO(抽象校验类)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月22日 上午10:22:16   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public abstract class AbstractCheckChain {
	
	protected AbstractCheckChain next ;    
	        
    public abstract void checkRequest(String request) throws Exception;    
    
    //设置下一个处理请求的人     
    public void setNextHandler(AbstractCheckChain checker) {    
        this.next = checker;    
    }
    
    public boolean hasNext() {
    	return next != null;
    }
}
