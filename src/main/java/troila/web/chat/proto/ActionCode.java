package troila.web.chat.proto;

import java.util.ArrayList;
import java.util.List;

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
	TURNANCHOR("1004","转换主播"),
	TURNAUDIENCE("1005","转换用户"),
	PONG("1006","客户端PONG消息");
	
	private String code;
	

	private String value;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static List<String> codeList(){
		List<String> codeList = new  ArrayList<>();
		for(ActionCode code:values()) {
			codeList.add(code.getCode());
		}
		return codeList;
	}
	
	private ActionCode(String code,String value) {
		this.code = code;
		this.value = value;
	}
}
