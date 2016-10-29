package com.fsmeeting.safecall.client.Operation;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.fsmeeting.safecall.beans.UserInfo;
import com.fsmeeting.safecall.beans.common.CommandCode;
import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.client.MessageClientLogicHandler;
import com.fsmeeting.safecall.server.handler.MessageDecoder;
import com.fsmeeting.safecall.server.handler.MessageEncoder;

public class Heartbeat {

	public static Channel channel;

	public static void main(String[] args) throws IOException {
		Heartbeat client = new Heartbeat();
		client.start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		client.login();
		client.heartbeat();

	}

	public void start() {
		final ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool(), 8);

		ClientBootstrap bootstrap = new ClientBootstrap(factory);
		bootstrap.setOption("tcpNoDelay", true);

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline channelPipeline = Channels.pipeline(new MessageEncoder(), new MessageDecoder(),
						new MessageClientLogicHandler());
				System.out.println(channelPipeline.hashCode());
				return channelPipeline;
			}
		});

		// 这里连接服务端绑定的IP和端口
		bootstrap.connect(new InetSocketAddress("192.168.5.157", 8000));
		System.out.println("Client is started...");
	}

	public void heartbeat() throws IOException {
		Message message = new Message();
		message.setCmd(CommandCode.HEARTBEAT.getCode());
		message.setReq(true);
		channel.write(message);
	}

	public void login() throws IOException {
		UserInfo user = new UserInfo();
		user.setUsername("74887857");
		user.setPwd("000000");
		Message message = new Message();
		message.setCmd(CommandCode.LOGIN.getCode());
		message.setData(user);
		message.setReq(true);
		channel.write(message);
	}
}
