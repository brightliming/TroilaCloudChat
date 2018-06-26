/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  ProtobufCodc.java   
 * @Package troila.web.codec   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月26日 上午9:18:46   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.codec;

import troila.web.chat.proto.ChatProto;
import troila.web.chat.proto.ChatProto.Message;

/**   
 * @ClassName:  ProtobufCodc   
 * @Description:TODO(protobuf编解码类)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月26日 上午9:18:46   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class ProtobufCodc implements ICodec{

	/**   
	 * <p>Title: encode</p>   
	 * <p>Description: </p>   
	 * @param c
	 * @return   
	 * @see troila.web.codec.ICodec#encode(java.lang.Object)   
	 */
	@Override
	public Object encode(troila.web.chat.proto.Message c) {
		// TODO Auto-generated method stub
		
		
		return ChatProto.Message.newBuilder().setHeader(c.getHeader()).setBody(c.getBody()).build();
	}

	/**   
	 * <p>Title: decode</p>   
	 * <p>Description: </p>   
	 * @param c
	 * @return   
	 * @see troila.web.codec.ICodec#decode(java.lang.Object)   
	 */
	@Override
	public Object decode(Object c) {
		// TODO Auto-generated method stub
		return (ChatProto.Message)c;
	}

	
}
