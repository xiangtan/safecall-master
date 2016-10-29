package com.fsmeeting.safecall.client;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.WriteCompletionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.client.Operation.Call;
import com.fsmeeting.safecall.client.Operation.Heartbeat;
import com.fsmeeting.safecall.client.Operation.Login;
import com.fsmeeting.safecall.client.Operation.Logout;
import com.fsmeeting.safecall.client.Operation.Register;

public class MessageClientLogicHandler extends SimpleChannelHandler {

	private static final Logger logger = LoggerFactory.getLogger(MessageClientLogicHandler.class);

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("######channelConnected");
		MessageClient.channel = e.getChannel();
		Login.channel = e.getChannel();
		Register.channel = e.getChannel();
		Call.channel = e.getChannel();
		Heartbeat.channel = e.getChannel();
		Logout.channel = e.getChannel();
	}

	@Override
	public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
		logger.info("######writeComplete");
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		logger.info("######messageReceived");

		Message msg = (Message) e.getMessage();
		logger.info("The message gotten from server is : " + msg);

		// ChannelFuture channelFuture = e.getChannel().close();
		// channelFuture.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		e.getCause().printStackTrace();
		Channel ch = e.getChannel();
		ch.close();
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		logger.info("Close channel:" + ctx.getChannel());
		ctx.getChannel().close();
	}

}