/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  CodecContext.java   
 * @Package troila.web.codec   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月26日 上午9:14:50   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.codec;

import troila.web.chat.proto.Message;
import troila.web.chat.utils.Conf;

/**   
 * @ClassName:  CodecContext   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月26日 上午9:14:50   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class CodecContext {
	private static ICodec codec;
	
	static {
		if(Conf.USE_PROTO) {
			codec = new ProtobufCodc();
		}else{
			codec = new TextCodec();
		}
	}
	
	
	public static Object decode(Object c) {
		try {
			return codec.decode(c);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Object encode(Message c) {
		return codec.encode(c);
	} 
}
