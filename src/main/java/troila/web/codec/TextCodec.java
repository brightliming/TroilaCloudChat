/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  TextCodec.java   
 * @Package troila.web.codec   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月26日 上午9:18:57   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.codec;

import com.alibaba.fastjson.JSON;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import troila.web.chat.proto.Message;

/**   
 * @ClassName:  TextCodec   
 * @Description:TODO(文本消息转码类)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月26日 上午9:18:57   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class TextCodec implements ICodec{

	/**   
	 * <p>Title: decode</p>   
	 * <p>Description: </p>   
	 * @param c
	 * @return   
	 * @see troila.web.codec.ICodec#decode(java.lang.Object)   
	 */
	@Override
	public Object decode(Object c) {
			return JSON.parseObject(((TextWebSocketFrame)c).text(), Message.class);
	}

	/**   
	 * <p>Title: encode</p>   
	 * <p>Description: </p>   
	 * @param c
	 * @return   
	 * @see troila.web.codec.ICodec#encode(java.lang.Object)   
	 */
	@Override
	public Object encode(Message c) {
		return new TextWebSocketFrame(JSON.toJSONString(c));
	}


}
