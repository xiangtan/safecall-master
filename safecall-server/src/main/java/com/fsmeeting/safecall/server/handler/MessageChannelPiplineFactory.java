package com.fsmeeting.safecall.server.handler;

import java.util.concurrent.TimeUnit;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;

public class MessageChannelPiplineFactory implements ChannelPipelineFactory {

	public ChannelPipeline getPipeline() throws Exception {
		MessageDecoder messageDecode = new MessageDecoder();
		MessageLogicHandler messageLogicHandler = new MessageLogicHandler();
		MessageEncoder messageEncoder = new MessageEncoder();
		ChannelPipeline channelPipeline = Channels.pipeline();
		channelPipeline.addLast("messageEncoder", messageEncoder);
		channelPipeline.addLast("messageDecode", messageDecode);
		channelPipeline.addLast("messageLogicHandler", messageLogicHandler);
		channelPipeline.addLast("timeout", new IdleStateHandler(new HashedWheelTimer(), 0, 0, 5, TimeUnit.MINUTES));
		channelPipeline.addLast("idle", new IdleHandler());
		return channelPipeline;
	}

}