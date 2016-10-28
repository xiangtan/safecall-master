package com.fsmeeting.safecall.server.handler;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.beans.common.Header;
import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.context.BusinessContext;
import com.fsmeeting.safecall.context.factory.HandlerFactory;
import com.fsmeeting.safecall.context.handler.IHandler;
import com.fsmeeting.safecall.server.MessageServer;
import com.fsmeeting.safecall.session.Session;
import com.fsmeeting.safecall.session.manager.SessionManager;

// SimpleChannelHandler提供了很多基本的handler方法用来重写  
// 通常情况下足够使用了  
public class MessageLogicHandler extends SimpleChannelHandler {

	private static final Logger logger = LoggerFactory.getLogger(MessageLogicHandler.class);

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		MessageServer.channelGroup.add(e.getChannel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		e.getCause().printStackTrace();
		Channel ch = e.getChannel();
		ch.close();
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

		Message message = (Message) e.getMessage();
		Header header = message.getHeader();
		IHandler handler = HandlerFactory.getInstance().getHandler(header.getCmd(), header.isReq());
		BusinessContext context = new BusinessContext();
		context.setChannel(ctx.getChannel());
		handler.handle(context, message.getData());
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		super.channelOpen(ctx, e);
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		super.channelDisconnected(ctx, e);
	}

	@Override
	public void channelUnbound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		super.channelUnbound(ctx, e);
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		logger.info("Close channel:" + ctx.getChannel());
		Object obj = ctx.getChannel().getAttachment();
		if (obj != null && obj instanceof Session) {
			Session session = (Session) obj;
			logger.info("Remove Session:" + session);
			SessionManager.getInstance().remove(session.getUserInfo().getUsername());
		}
	}

}