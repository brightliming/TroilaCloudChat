package troila.web.chat.proto;

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
}
