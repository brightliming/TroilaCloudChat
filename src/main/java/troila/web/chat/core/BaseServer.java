package troila.web.chat.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public abstract class BaseServer implements Server {
	

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected String host = "localhost";
    protected int port = 8099;

    protected DefaultEventLoopGroup defLoopGroup;
    protected NioEventLoopGroup bossGroup;
    protected NioEventLoopGroup workGroup;
    protected NioServerSocketChannel ssch;
    protected ChannelFuture cf;
    protected ServerBootstrap b;
	
    public void init() {
    	
    	
    	
    }
	

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

}
