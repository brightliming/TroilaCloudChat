package troila.web.chat.handler;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
/**
 * 
 * @ClassName:  RoomManager   
 * @Description:TODO(房间信息管理类，维护房间下面所有的用户信道)   
 * @author: 卓朗科技 
 * @date:   2018年6月21日 下午5:30:48   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class RoomManager {
	private static final Logger logger = LoggerFactory.getLogger(RoomManager.class);
	
	private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);
	
	private static ConcurrentMap<String, List<Channel>> roomInfos = new ConcurrentHashMap<>();
	
}
