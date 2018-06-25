/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  BeanUtil.java   
 * @Package troila.web.chat.utils   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月25日 上午10:13:32   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.utils;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * @ClassName: BeanUtil
 * @Description:TODO(Bean工具类)
 * @author: 卓朗科技 _limingliang
 * @date: 2018年6月25日 上午10:13:32
 * 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved.
 *             注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class BeanUtil {
	/**
	 * 反序列化对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {

		}
		return null;
	}
}
