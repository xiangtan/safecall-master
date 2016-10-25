package com.fsmeeting.safecall.server.handler;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.session.Session;
import com.fsmeeting.safecall.session.manager.SessionManager;

/**
 * Channel空闲监控
 * 
 * @author yicai.liu<moon>
 *
 */
public class IdleHandler extends IdleStateAwareChannelHandler {
	private static final Logger logger = LoggerFactory.getLogger(IdleHandler.class);

	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {

		if (e.getState() == IdleState.ALL_IDLE) {
			Channel channel = ctx.getChannel();
			logger.info("----读写空闲----close channel:" + channel);
			Object obj = channel.getAttachment();
			if (obj != null && obj instanceof Session) {
				Session session = (Session) obj;
				logger.info("Remove Session:" + session);
				SessionManager.getInstance().remove(session.getUserInfo().getUsername());
			}
			channel.close();
		} else {
			super.channelIdle(ctx, e);
		}

	}

}