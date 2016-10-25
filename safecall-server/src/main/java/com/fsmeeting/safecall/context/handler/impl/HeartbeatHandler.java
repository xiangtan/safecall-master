package com.fsmeeting.safecall.context.handler.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.context.BusinessContext;
import com.fsmeeting.safecall.context.handler.IHandler;
import com.fsmeeting.safecall.session.Session;
import com.fsmeeting.safecall.session.manager.SessionManager;

/**
 * 心跳包处理
 * 
 * @author yicai.liu<moon>
 *
 */
public class HeartbeatHandler implements IHandler {

	private static final Logger logger = LoggerFactory.getLogger(HeartbeatHandler.class);

	@Override
	public void handle(BusinessContext context, Object data) throws Exception {

		// 更新Session
		Session curSession = context.getSession();
		logger.info("Current Session:" + curSession);
		
		Date date = new Date();
		if (null != curSession) {
			context.getSession().setLastAccessTime(date);
			Session globalSession = SessionManager.getInstance().getSession(curSession.getUserInfo().getUsername());
			if (null != globalSession) {
				logger.info("Global Session:" + globalSession);
				globalSession.setLastAccessTime(date);
			}
		}
		
		// 写心跳response
		Message response = new Message();
		response.setReq(false);
		context.sendResponse(response);
	}

}
