/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  ErrorType.java   
 * @Package troila.web.chat.exception   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月22日 上午9:59:40   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.exception;

/**   
 * @ClassName:  ErrorType   
 * @Description:TODO(异常错误类型)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月22日 上午9:59:40   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public enum ErrorType {
	UNKNOW("未知错误"),
	
	DATA("数据错误"),
	
	VALIDATE("校验错误");
	
    private String message;//异常信息
	
	private ErrorType(String str){
		this.message = str;
	}

	public String getMessage() {
		return message;
	}

}
