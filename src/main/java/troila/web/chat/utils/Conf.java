package troila.web.chat.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.troila.redis.utils.ConfigureUtils;

public class Conf {
	private static final Logger logger = LoggerFactory.getLogger(Conf.class);
	
	static {
		//初始化配置信息
		new ConfigureUtils().init();
	}
	public final static int SERVER_PORT = Integer.parseInt(ConfigureUtils.getConfig("port"));
	public final static boolean TEST = ConfigureUtils.getConfig("test").equals("true");
	public final static boolean USE_PROTO = ConfigureUtils.getConfig("userproto").equals("true");
	
	public final static String WEBSOCKET_URI = ConfigureUtils.getConfig("websocket.url");
	public final static String CONTEXT = ConfigureUtils.getConfig("context");
	
	public final static boolean SSL_OPEN = ConfigureUtils.getConfig("ssl.open").equals("true");
	public final static String SSL_CERTIFICATE_TYPE = ConfigureUtils.getConfig("ssl.certificate.type");
	public final static String SSL_CERTIFICATE = ConfigureUtils.getConfig("ssl.certificate.localtion");
	public final static String SSL_PRIVATEKEY = ConfigureUtils.getConfig("ssl.privateKey");
	
	
	public final static String LIVEAPI_ADDR =  ConfigureUtils.getConfig("liveapi.address");
	public final static String KICK_INTERFACE =  ConfigureUtils.getConfig("liveapi.kickperson");
	
}
