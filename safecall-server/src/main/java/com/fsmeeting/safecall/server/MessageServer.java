package com.fsmeeting.safecall.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.server.handler.MessageChannelPiplineFactory;
import com.fsmeeting.safecall.utils.PropertiesUtils;

public class MessageServer {

	private static final Logger logger = LoggerFactory.getLogger(MessageServer.class);

	private ChannelFactory factory;

	public static ChannelGroup channelGroup = new DefaultChannelGroup();
	private static final String IP = PropertiesUtils.getString("serverIP");
	private static final int PORT = PropertiesUtils.getInteger("serverPort", 8000);

	public static void main(String[] args) throws Exception {
		MessageServer server = new MessageServer();
		try {
			server.start();
			while (true) {
				TimeUnit.SECONDS.sleep(20);
				logger.info("channelGroup.size() ：" + channelGroup.size());

				for (Channel channel : channelGroup) {
					logger.info("channel.toString() ：" + channel.toString());
				}

			}
		} finally {
			server.stop();
		}
	}

	public void start() {
		factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), // boss线程池
				Executors.newCachedThreadPool(), // worker线程池
				8); // worker线程数

		ServerBootstrap bootstrap = new ServerBootstrap(factory);

		bootstrap.setOption("reuseAddress", true);

		// 对每一个连接（channel），server都会调用
		// ChannelPipelineFactory为该连接创建一个ChannelPipeline
		MessageChannelPiplineFactory channelPiplineFactory = new MessageChannelPiplineFactory();
		bootstrap.setPipelineFactory(channelPiplineFactory);

		// 这里绑定服务端监听的IP和端口
		bootstrap.bind(new InetSocketAddress(IP, PORT));
		// Server.channelGroup.add(channel);

		logger.info("Server is started...");
	}

	public void stop() {
		ChannelGroupFuture channelGroupFuture = MessageServer.channelGroup.close();
		channelGroupFuture.awaitUninterruptibly();
		factory.releaseExternalResources();
		logger.info("Server is stopped.");
	}
}