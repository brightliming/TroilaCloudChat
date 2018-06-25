/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  RedisService.java   
 * @Package troila.web.chat.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 卓朗科技_limingliang     
 * @date:   2018年6月22日 上午9:55:07   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.service;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.apache.commons.lang.math.RandomUtils;

import com.troila.redis.JedisInterface;
import com.troila.redis.factory.JedisFactory;
import com.troila.redis.utils.ConfigureUtils;

import troila.web.chat.proto.User;
import troila.web.chat.utils.BeanUtil;
import troila.web.chat.utils.Conf;

/**   
 * @ClassName:  RedisService   
 * @Description:TODO(redis操作Service)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月22日 上午9:55:07   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class RedisService {
	private final static JedisInterface redisUtils = JedisFactory.getJedis(ConfigureUtils.getConfigs());
	
	public boolean existKey(String key) {
		return redisUtils.exists(key);
	}
	/**
	 * 
	 * @Title: getUserByToken   
	 * @Description: TODO(通过token从redis中获得用户信息)   
	 * @param: @param token
	 * @param: @return      
	 * @return: User      
	 * @throws
	 */
	public User getUserByToken(String token) {
		if(!Conf.TEST) {
			return (User)BeanUtil.unserialize(redisUtils.getByte(token.getBytes()));
		}else {
			User user = new User();
			user.setUserId(RandomUtils.nextInt());
			user.setUserName("用户"+user.getUserId());
			return user;
		}
		
	}
	
	
}
