/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  ICodec.java   
 * @Package troila.web.codec   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月26日 上午8:50:42   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.codec;

import troila.web.chat.proto.Message;

/**   
 * @ClassName:  ICodec   
 * @Description:TODO(编解码接口类)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月26日 上午8:50:42   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface ICodec {
	Object encode(Message c);
	Object decode(Object c);
}
