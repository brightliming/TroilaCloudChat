package troila.web.chat.proto;

import io.netty.channel.Channel;

/**
 *
 * @ClassName:  Person   
 * @Description:TODO(用户信息实体类)   
 * @author: 卓朗科技 _limingliang
 * @date:   2018年6月21日 下午5:37:47   
 *     
 * @Copyright: 2018 www.troila.com Inc. All rights reserved. 
 * 注意：本内容仅限于天津卓朗科技信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Person {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * 用户ID
	 */
	private String id;
	/**
	 * 用户昵称
	 */
	private String nickName;
	
	/**
	 * 当前所在房间ID
	 */
	private String roomId;
	
	/**
	 * 用户ip地址
	 */
	private String addr;
	
	/**
	 * 用户通道
	 */
	private Channel channel;
	

	/**
	 * 用户访问时间
	 */
	private Long time = 0L;

	
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}
}
