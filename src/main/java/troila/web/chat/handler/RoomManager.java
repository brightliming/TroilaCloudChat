package troila.web.chat.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.troila.httpclient.TroilaHttpClientUtil;

import io.netty.channel.Channel;
import troila.web.chat.proto.Message;
import troila.web.chat.proto.NormalMessage;
import troila.web.chat.proto.Person;
import troila.web.chat.proto.ResponseCode;
import troila.web.chat.proto.Room;
import troila.web.chat.utils.Conf;
import troila.web.chat.utils.MessageConstants;
import troila.web.chat.utils.NettyUtil;
import troila.web.codec.CodecContext;

/**
 * 
 * @ClassName: RoomManager
 * @Description:TODO(房间信息管理类，维护房间下面所有的用户信道)
 * @author: 卓朗科技
 * @date: 2018年6月21日 下午5:30:48
 * 
 * @Copyright: 2018 www.troila.com Inc. All rights reserved.
 *             注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class RoomManager {
	private static final Logger logger = LoggerFactory.getLogger(RoomManager.class);

	private static final ExecutorService executor = Executors.newCachedThreadPool();

	private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);
	/**
	 * 通道对应的人员信息
	 */
	private static ConcurrentMap<Channel, Person> userInfos = new ConcurrentHashMap<>();
	/**
	 * 房间通道信息
	 */
	private static ConcurrentMap<String, List<Person>> roomInfos = new ConcurrentHashMap<>();
	
	

	/**
	 * 
	 * @Title: addPerson @Description: TODO(新用户进入直播间) @param: @param
	 * channel @param: @param person @return: void @throws
	 */
	public static void addPerson(Person person) {
		// 将人员加入到房间
		boolean newUser = addChannel(person);
		executor.execute(new Runnable() {
			@Override
			public void run() {
				// 返回房间信息给当前登录用户
				sendRoomInfo(person);
			}
		});
		/**
		 * 如果是新用户则通知房间其他用户，如果是老用户重复登录，则不通知
		 */
		if(newUser) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					// 广播用户登录消息给当前房间用户
					broadCastInfo(person.getRoomId(), ResponseCode.INROOM, person);
				}
			});
		}
	}

	/**
	 * 人员离开直播间 @Title: removePerson @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param channel @return: void @throws
	 */
	public static void removePerson(Channel channel) {
		try {
			logger.warn("channel will be remove, address is :{}", NettyUtil.parseChannelRemoteAddr(channel));
			rwLock.writeLock().lock();
			channel.close();
			Person person = userInfos.get(channel);
			if (person != null) {
				Person tmp = userInfos.remove(channel);
				if (tmp != null) {
					if(roomInfos.get(tmp.getRoomId()) != null && roomInfos.get(tmp.getRoomId()).size() != 0) {
						roomInfos.get(tmp.getRoomId()).remove(tmp);
					}
				}
			}
			executor.execute(new Runnable() {
				@Override
				public void run() {
					//向房间所有人员广播人员离开消息
					if(person != null) {
						broadCastInfo(person.getRoomId(),ResponseCode.OUTROOM,person);
					}					
				}
			});
			
		} finally {
			rwLock.writeLock().unlock();
		}

	}
	
	
	 /**
     * 广播普通消息
     *
     * @param message
     */
    public static void boardCastUserMessage(Channel channel, String message) {
        if (!StringUtils.isBlank(message)) {
            try {
                rwLock.readLock().lock();
                Person person  = userInfos.get(channel);
                if(person != null) {
                	NormalMessage normalMessage = new NormalMessage();
                	normalMessage.setNickName(person.getNickName());
                	normalMessage.setMessage(message);
                	broadCastInfo(person.getRoomId(),ResponseCode.NORMAL,normalMessage);
                }
            } finally {
                rwLock.readLock().unlock();
            }
        }
    }
    
    /**
     * 禁言消息
     * @Title: mutePerson   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @param channel
     * @param: @param message      
     * @return: void      
     * @throws
     */
    public static void mutePerson(Channel channel, String message) {
    	try {
			rwLock.readLock().lock();
			Person person = JSON.parseObject(message,Person.class);
			if(StringUtils.isBlank(person.getId()) || StringUtils.isBlank(person.getRoomId())) {
				logger.error("禁言消息格式错误：{}",message);
				channel.writeAndFlush(CodecContext.encode(new Message(ResponseCode.FAIL.getCode(),MessageConstants.MUTE_MESSAGE_ERROR+message)));
			}
			if(roomInfos.get(person.getRoomId()) != null && roomInfos.get(person.getRoomId()).size() != 0) {
				for(Person ps : roomInfos.get(person.getRoomId())) {
					if(ps.getId().equals(person.getId())) {
						ps.getChannel().writeAndFlush(CodecContext.encode(new Message(ResponseCode.MUTE.getCode())));
					}
				}
				channel.writeAndFlush(CodecContext.encode(new Message(ResponseCode.SUCCESS.getCode(),MessageConstants.MUTE_SUCCESS)));
			}
			
		}finally {
			rwLock.readLock().unlock();
		}
    }
    /**
     * 
     * @Title: unMutePerson   
     * @Description: TODO(解禁言)   
     * @param:       
     * @return: void      
     * @throws
     */
    public static void unMutePerson(Channel channel, String message) {
    	try {
			rwLock.readLock().lock();
			Person person = JSON.parseObject(message,Person.class);
			if(StringUtils.isBlank(person.getId()) || StringUtils.isBlank(person.getRoomId())) {
				logger.error("解禁言消息格式错误：{}",message);
				channel.writeAndFlush(CodecContext.encode(new Message(ResponseCode.FAIL.getCode(),MessageConstants.UNMUTE_MESSAGE_ERROR+message)));
			}
			if(roomInfos.get(person.getRoomId()) != null && roomInfos.get(person.getRoomId()).size() != 0) {
				for(Person ps : roomInfos.get(person.getRoomId())) {
					if(ps.getId().equals(person.getId())) {
						ps.getChannel().writeAndFlush(CodecContext.encode(new Message(ResponseCode.UNMUTE.getCode())));
					}
				}
				channel.writeAndFlush(CodecContext.encode(new Message(ResponseCode.SUCCESS.getCode(),MessageConstants.UNMUTE_SUCCESS)));
			}
			
		}finally {
			rwLock.readLock().unlock();
		}
    }

    /**
     * 
     * @Title: kickPerson   
     * @Description: TODO(踢人接口)   
     * @param: @param channel
     * @param: @param message      
     * @return: void      
     * @throws
     */
    public static void kickPerson(Channel channel, String message) {
    	try {
			rwLock.writeLock().lock();
			Person person = JSON.parseObject(message, Person.class);
			if(StringUtils.isBlank(person.getId()) || StringUtils.isBlank(person.getRoomId())) {
				logger.error("踢人消息格式错误：{}",message);
				channel.writeAndFlush(CodecContext.encode(new Message(ResponseCode.FAIL.getCode(),MessageConstants.KICK_MESSAGE_ERROR)));
			}
			//调用踢人接口，给被踢人员断掉拉流
			if(!Conf.TEST) {
				Map<String, Object> param = new HashMap<String,Object>();
				param.put("liveRoomId", person.getRoomId());
				param.put("userId",person.getId());
				try {
					TroilaHttpClientUtil.httpPostExecute(Conf.LIVEAPI_ADDR+Conf.KICK_INTERFACE,param);
				} catch (Exception e) {
					logger.error(e.getMessage());
					channel.writeAndFlush(CodecContext.encode(new Message(ResponseCode.FAIL.getCode(),MessageConstants.KICK_ERROR)));
				}
			}			
			Person tmp = null;
			//直播间广播踢人消息
			if(roomInfos.get(person.getRoomId()) != null && roomInfos.get(person.getRoomId()).size() != 0) {
				for(Person ps : roomInfos.get(person.getRoomId())) {
					if(ps.getId().equals(person.getId())) {
						tmp = ps;
					}
				}
			}
			if(tmp != null) {
				//告诉用户自己被踢
				tmp.getChannel().writeAndFlush(CodecContext.encode(new Message(ResponseCode.KICK.getCode())));
				//移除用户
				removePerson(tmp.getChannel());
			}	
			//给主播反馈踢人成功消息
			channel.writeAndFlush(CodecContext.encode(new Message(ResponseCode.SUCCESS.getCode(),MessageConstants.KICK_SUCCESS)));
		}finally {
			rwLock.writeLock().unlock();
		}
    }
    
    /**
     * 
     * @Title: updateUserTime   
     * @Description: TODO(更新用户登录状态)   
     * @param: @param channel      
     * @return: void      
     * @throws
     */
    public static void updateUserTime(Channel channel) {
    	 Person person = userInfos.get(channel);
         if (person != null) {
        	 person.setTime(System.currentTimeMillis());
         }
    }
    /**
     * 
     * @Title: broadCastPing   
     * @Description: TODO(定时给用户发送ping消息)   
     * @param:       
     * @return: void      
     * @throws
     */
    public static void broadCastPing() {
        try {
        	logger.info("===========发送ping消息==========");
            rwLock.readLock().lock();
            Set<Channel> keySet = userInfos.keySet();
            for (Channel ch : keySet) {
                Person person = userInfos.get(ch);
                if (person == null) continue;
                ch.writeAndFlush(CodecContext.encode(new Message(ResponseCode.PING.getCode())));
            }
        } finally {
            rwLock.readLock().unlock();
        }
    }
    /**
     * 
     * @Title: scanNotActiveChannel   
     * @Description: TODO(关闭失效的客户端)   
     * @param:       
     * @return: void      
     * @throws
     */
    public static void scanNotActiveChannel() {
            Set<Channel> keySet = userInfos.keySet();
            for (Channel ch : keySet) {
                Person person = userInfos.get(ch);
                if (person == null) continue;
                if ((System.currentTimeMillis() - person.getTime()) > 10000) {
                    removePerson(ch);
                }
            }
    }
	/**
	 * 用户已经存在房间  false  新用户true
	 * @Title: addChannel @Description: TODO(将通道加入房间) @param: @param
	 * roomId @param: @param channel @return: void @throws
	 */
	private static boolean addChannel(Person person) {
		try {
			rwLock.writeLock().lock();
			boolean newUser = true;
			// 人员与通道对应
			userInfos.put(person.getChannel(), person);
			// room与人员对应
			if (roomInfos.containsKey(person.getRoomId())) {
				List<Person> persons = roomInfos.get(person.getRoomId());
				//如果用户已经在该房间，则踢出之前进入的用户并发送通知给他
				Person tmp = null;
				for(Person ps : persons) {
					if(ps.getId().equals(person.getId()) && ps.getRoomId().equals(person.getRoomId())) {
						ps.getChannel().writeAndFlush(CodecContext.encode(new Message(ResponseCode.REPEATLOGIN.getCode(),MessageConstants.REPEAT_LOGIN)));	
						tmp = ps;						
						break;
					}
				}
				if(tmp != null) {
					newUser = false;
					userInfos.remove(tmp.getChannel());
					tmp.getChannel().close();
					persons.remove(tmp);
				}
				
			} else {
				List<Person> personList = new ArrayList<>();
				personList.add(person);
				roomInfos.put(person.getRoomId(), personList);
			}
			return newUser;
		} finally {
			rwLock.writeLock().unlock();
		}

	}

	/**
	 * 
	 * @Title: broadCastInfo @Description: TODO(给当前房间用户广播消息) @param: @param
	 * roomId @param: @param code @param: @param mess @return: void @throws
	 */
	private static void broadCastInfo(String roomId, ResponseCode code, Object mess) {
		logger.info("广播消息，roomID：{}，事件：[{}:{}]，信息：{}", roomId, code.getCode(), code.getValue(),
				JSON.toJSONString(mess));
		try {
			rwLock.readLock().lock();
			List<Person> persons = roomInfos.get(roomId);
			for (Person ps : persons) {
				if (ps == null || ps.getChannel() == null || !ps.getChannel().isActive())
					continue;
				ps.getChannel().writeAndFlush(CodecContext.encode(new Message(code.getCode(),JSON.toJSONString(mess))));
			}
		} finally {
			rwLock.readLock().unlock();
		}
	}

	/**
	 * 
	 * @Title: sendRoomInfo @Description: TODO(给用户发送房间信息) @param: @param
	 * person @return: void @throws
	 */
	private static void sendRoomInfo(Person person) {
		try {
			rwLock.readLock().lock();
			List<Person> persons = roomInfos.get(person.getRoomId());
			Room room = new Room();
			room.setId(person.getRoomId());
			room.setPersonList(persons);
			room.setCount(persons.size());
			uniCastInfo(person, ResponseCode.ROOM, room);
		} finally {
			rwLock.readLock().unlock();
		}
	}

	/**
	 * 
	 * @Title: uniCastInfo @Description: TODO(给人员发送信息) @param: @param
	 * person @return: void @throws
	 */
	private static void uniCastInfo(Person person, ResponseCode code, Object mess) {
		logger.info("单播消息，接收人：{}，事件：[{}:{}]，信息：{}", person.getNickName(), code.getCode(), code.getValue(),
				JSON.toJSONString(mess));
		Channel channel = person.getChannel();
		if (channel == null || !channel.isActive()) {
			logger.warn("用户通道已经关闭：{}", person.getNickName());
		}
		channel.writeAndFlush(CodecContext.encode(new Message(code.getCode(),JSON.toJSONString(mess))));
	}
}
