package troila.web.chat.core;

/**
 * 服务器接口
 * @author limingliang
 *
 */
public interface Server {
	/**
	 * 启动服务
	 */
	public void start();
	
	/**
	 * 关闭服务
	 */
	public void shutdown();
}
