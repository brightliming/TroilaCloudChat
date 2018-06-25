package troila.web.chat;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import troila.web.chat.core.BaseServer;
import troila.web.chat.handler.MessageHandler;
import troila.web.chat.handler.RoomManager;
import troila.web.chat.handler.UserAuthHandler;
import troila.web.chat.proto.ChatProto;
import troila.web.chat.ssl.SSLInitializer;
import troila.web.chat.utils.Conf;

/**
 * 聊天室服务器类
 * 
 * @author limingliang
 *
 */
public class TroilaChatServer extends BaseServer {

	private ScheduledExecutorService executorService;

	public TroilaChatServer(int port) {
		this.port = port;
		executorService = Executors.newScheduledThreadPool(2);
	}
	@Override
	public void start() {
		b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true)
				.option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.SO_BACKLOG, 1024)
				.localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline =ch.pipeline();
						if(Conf.SSL_OPEN) {
							pipeline.addLast(new SslHandler(SSLInitializer.sslEngine));
						}
						ch.pipeline().addLast(defLoopGroup,
								new HttpServerCodec(), // 请求解码器
								new HttpObjectAggregator(65536), // 将多个消息转换成单一的消息对象
								new ChunkedWriteHandler(), // 支持异步发送大的码流，一般用于发送文件流
								new IdleStateHandler(60, 0, 0), // 检测链路是否读空闲
								new ProtobufVarint32FrameDecoder(),
								new ProtobufDecoder(ChatProto.Message.getDefaultInstance()),
								new ProtobufVarint32LengthFieldPrepender(),
								new ProtobufEncoder(),
								new UserAuthHandler(),// 处理握手和认证
								new MessageHandler() // 处理消息的发送
						);
					}
				});

		try {
			cf = b.bind().sync();
			InetSocketAddress addr = (InetSocketAddress) cf.channel().localAddress();
			logger.info("TroilaChatServer start success, port is:{}", addr.getPort());

			// 定时扫描所有的Channel，关闭失效的Channel
			executorService.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					logger.info("scanNotActiveChannel --------");
					RoomManager.scanNotActiveChannel();
				}
			}, 3, 60, TimeUnit.SECONDS);

			// 定时向所有客户端发送Ping消息
			executorService.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					RoomManager.broadCastPing();
				}
			}, 3, 50, TimeUnit.SECONDS);
			
		} catch (InterruptedException e) {
			logger.error("TroilaChatServer start fail,", e);
		}

	}

	@Override
	public void shutdown() {
		if (executorService != null) {
			executorService.shutdown();
		}
		super.shutdown();
	}
	
}
