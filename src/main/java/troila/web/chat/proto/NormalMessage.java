/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  NormalMessage.java   
 * @Package troila.web.chat.proto   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月25日 下午2:37:56   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.proto;

/**   
 * @ClassName:  NormalMessage   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月25日 下午2:37:56   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class NormalMessage {
	private String nickName;
	
	private String message;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
