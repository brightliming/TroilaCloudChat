/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  Message.java   
 * @Package troila.web.chat.proto   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月26日 上午9:11:29   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.proto;

/**   
 * @ClassName:  Message   
 * @Description:TODO(消息实体类)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月26日 上午9:11:29   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class Message {
	private String header;
	
	private String body;
	public Message() {
		
	}
	
	public Message(String header) {
		this();
		this.header = header;
	}
	
	public Message(String header,String body) {
		this(header);
		this.body = body;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
