package troila.web.chat.proto;

/**
 * 
 * @author limingliang
 *
 */
public enum ActionCode {
	/**客户端消息定义**/
	NORMAL("1000","普通消息"),
	KICK("1001","踢人消息"),
	MUTE("1002","禁言消息"),
	UNMUTE("1003","解禁言消息"),
	INROOM("1004","用户进入直播间消息"),
	TURNANCHOR("1005","转换主播"),
	TURNAUDIENCE("1006","转换用户"),
	PONG("1003","客户端PONG消息");
	
	private String code;
	
	private String value;
	
	private ActionCode(String code,String value) {
		this.code = code;
		this.value = value;
	}
}
