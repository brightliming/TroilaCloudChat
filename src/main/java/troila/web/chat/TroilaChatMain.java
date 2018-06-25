package troila.web.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.troila.redis.utils.ConfigureUtils;

import troila.web.chat.utils.Conf;

public class TroilaChatMain {
	 private static final Logger logger = LoggerFactory.getLogger(TroilaChatMain.class);
	    public static void main(String[] args) {
	        final TroilaChatServer server = new TroilaChatServer(Conf.SERVER_PORT);
	        server.init();
	        server.start();
	        // 注册进程钩子，在JVM进程关闭前释放资源
	        Runtime.getRuntime().addShutdownHook(new Thread(){
	            @Override
	            public void run(){
	                server.shutdown();
	                logger.warn(">>>>>>>>>> jvm process shutdown");
	                System.exit(0);
	            }
	        });
	    }
}
