package com.fsmeeting.safecall.context.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.beans.common.CommandCode;
import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.beans.common.Response;
import com.fsmeeting.safecall.beans.common.ResponseCode;
import com.fsmeeting.safecall.context.BusinessContext;
import com.fsmeeting.safecall.context.handler.IHandler;
import com.fsmeeting.safecall.session.Session;
import com.fsmeeting.safecall.session.manager.SessionManager;

/**
 * 退出登录处理
 * 
 * @author yicai.liu<moon>
 *
 */
public class LogoutHandler implements IHandler {

	private static final Logger logger = LoggerFactory.getLogger(LogoutHandler.class);

	public void handle(BusinessContext context, Object data) throws Exception {

		Message message = new Message();
		message.setCmd(CommandCode.LOGOUT.getCode());
		message.setReq(false);
		Response resp = new Response();
		try {
			freeSession(context);
		} catch (Exception e) {
			logger.error("退出登录异常:", e);
			resp.setCode(ResponseCode.SERVICE_ERROR.getCode());
		}
		message.setData(resp);
		context.sendResponse(message);
		context.getChannel().close();
	}

	/**
	 * 解除会话
	 * 
	 * @param context
	 */
	private void freeSession(BusinessContext context) {
		Session session = context.getSession();
		if (null != session && null != session.getUserInfo()) {
			String username = session.getUserInfo().getUsername();
			logger.info("退出登录,删除用户:" + username + "会话:" + session);
			context.removeSession(session);
			SessionManager.getInstance().remove(username);
		}

	}

}
