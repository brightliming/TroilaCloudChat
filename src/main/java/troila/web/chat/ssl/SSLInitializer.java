package troila.web.chat.ssl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.troila.redis.utils.ConfigureUtils;

import troila.web.chat.utils.Conf;

public class SSLInitializer {
	private static final Logger logger = LoggerFactory.getLogger(SSLInitializer.class);
	
	public static SSLEngine sslEngine = getInstant();
	
	private SSLInitializer() {
		
	}
	
	private static SSLEngine getInstant(){
		try {
			KeyStore ks = KeyStore.getInstance(Conf.SSL_CERTIFICATE_TYPE);
			InputStream ksInputStream;
			ksInputStream = new FileInputStream(Conf.SSL_CERTIFICATE);
			// 证书存放地址
			ks.load(ksInputStream, Conf.SSL_PRIVATEKEY.toCharArray());
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(ks,  Conf.SSL_PRIVATEKEY.toCharArray());
			SSLContext sslContext = SSLContext.getInstance("TLS");
			SSLEngine sslEngine = sslContext.createSSLEngine();
			sslContext.init(kmf.getKeyManagers(), null, null);
			sslEngine.setUseClientMode(false);
			sslEngine.setNeedClientAuth(false);
			return sslEngine;
		} catch (Exception e) {
			logger.error("证书加载失败");
			e.printStackTrace();
		}
		return null;
	}
	
}
