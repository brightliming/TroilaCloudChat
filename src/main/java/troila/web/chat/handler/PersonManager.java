/**  
 * All rights Reserved, Designed By www.troila.com
 * @Title:  PersonManager.java   
 * @Package troila.web.chat.handler   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 天源迪科技     
 * @date:   2018年6月21日 下午5:32:20   
 * @version V1.0 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package troila.web.chat.handler;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.LoggerFactory;

/**   
 * @ClassName:  PersonManager   
 * @Description:TODO(用户信息管理类，可以通过信道，反向查询用户以及其房间信息)   
 * @author: 卓朗科技_limingliang 
 * @date:   2018年6月21日 下午5:32:20   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class PersonManager {
	
  private static final LoggerFactory logger = LoggerFactory.getLogger(PersonManager.class);

  private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

  private static ConcurrentMap<Channel, Person> userInfos = new ConcurrentHashMap<>();
}
