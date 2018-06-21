package troila.web.chat.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseServer implements Server {
	

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected String host = "localhost";
    protected int port = 5588;

    protected DefaultEventLoopGroup defLoopGroup;
    protected NioEventLoopGroup bossGroup;
    protected NioEventLoopGroup workGroup;
    protected NioServerSocketChannel ssch;
    protected ChannelFuture cf;
    protected ServerBootstrap b;
	
    public void init() {
<<<<<<< HEAD
    	 defLoopGroup = new DefaultEventLoopGroup(8, new ThreadFactory() {
             private AtomicInteger index = new AtomicInteger(0);

             @Override
             public Thread newThread(Runnable r) {
                 return new Thread(r, "DEFAULTEVENTLOOPGROUP_" + index.incrementAndGet());
             }
         });
         bossGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(), new ThreadFactory() {
             private AtomicInteger index = new AtomicInteger(0);

             @Override
             public Thread newThread(Runnable r) {
                 return new Thread(r, "BOSS_" + index.incrementAndGet());
             }
         });
         workGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 10, new ThreadFactory() {
             private AtomicInteger index = new AtomicInteger(0);

             @Override
             public Thread newThread(Runnable r) {
                 return new Thread(r, "WORK_" + index.incrementAndGet());
             }
         });

         b = new ServerBootstrap();
=======
        defLoopGroup = new DefaultEventLoopGroup(8, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"DEFAULTEVENTLOOPGROUP_"+index.incrementAndGet());
            }
        });

        bossGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(), new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"BOSS_"+index.incrementAndGet());
            }
        });

        workGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(), new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"WORK_"+index.incrementAndGet());
            }
        });
    	
    	b = new ServerBootstrap();
>>>>>>> 720432df92e9d91ea4570b79289efe3c70cd131f
    }
	

	@Override
	public void shutdown() {
<<<<<<< HEAD
		 if (defLoopGroup != null) {
	            defLoopGroup.shutdownGracefully();
	        }
	        bossGroup.shutdownGracefully();
	        workGroup.shutdownGracefully();
=======
		// TODO Auto-generated method stub
        if(defLoopGroup != null){
            defLoopGroup.shutdownGracefully();
        }
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();

>>>>>>> 720432df92e9d91ea4570b79289efe3c70cd131f
	}

}
