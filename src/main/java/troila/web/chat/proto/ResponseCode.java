package troila.web.chat.proto;

public enum ResponseCode {
	/**服务端响应消息定义**/
	SUCCEUSS("2000","成功"),
	ROOM("2001","直播间信息"),
	MUTE("2002","禁言消息"),
	UNMUTE("2003","解禁言消息"),
	KICK("2004","踢人消息"),
	INROOM("2005","用户进入直播间消息"),
	OUTROOM("2006","用户离开直播间消息"),
	TURNANCHOR("2007","转换主播"),
	TURNAUDIENCE("2008","转换用户"),
	PING("2009","服务端PING消息"),
	
	/**异常信息**/
	ERROR("4000","服务器异常");
	
	private String code;
	
	private String value;
	
	private ResponseCode(String code,String value) {
		this.code = code;
		this.value = value;
	}
}
