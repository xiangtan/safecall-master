package com.fsmeeting.safecall.context.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.beans.CallInfo;
import com.fsmeeting.safecall.beans.common.CommandCode;
import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.beans.common.Response;
import com.fsmeeting.safecall.beans.common.ResponseCode;
import com.fsmeeting.safecall.context.BusinessContext;
import com.fsmeeting.safecall.context.handler.IHandler;
import com.fsmeeting.safecall.session.Session;
import com.fsmeeting.safecall.session.manager.SessionManager;

/**
 * <pre>
 * 主叫处理
 * 		1 	接收主叫请求
 * 		2	检测被呼叫在线状态
 * 		3	(不在线)响应主叫
 * 		3	(在线)请求被叫
 * </pre>
 * 
 * @author yicai.liu<moon>
 *
 */
public class CallHandler implements IHandler {

	private static final Logger logger = LoggerFactory.getLogger(CallHandler.class);

	public void handle(BusinessContext context, Object data) throws Exception {

		Message message = new Message();
		Response resp = new Response();
		try {
			CallInfo callInfo = (CallInfo) data;
			String callee = callInfo.getCalleeNumber();
			Session session = SessionManager.getInstance().getSession(callee);
			logger.info(callInfo.getCallerNumber() + "呼叫:" + callee + ",Session:" + session);
			if (!SessionManager.validate(session))// 不在线
			{
				message.setCmd(CommandCode.CALL.getCode());
				message.setReq(false);
				logger.info("被叫用户" + callee + "不在线！");
				resp.setCode(ResponseCode.OFFLINE.getCode());
				message.setData(resp);
				context.sendResponse(message);
			} else {// 在线
				logger.info("被叫用户" + callee + "在线！");
				message.setCmd(CommandCode.CALLIN.getCode());
				message.setReq(true);
				resp.setData(callInfo);
				message.setData(resp);
				session.getChannel().write(message);
			}

		} catch (Exception e) {
			logger.error("呼出异常:", e);
			resp.setCode(ResponseCode.SERVICE_ERROR.getCode());
			message.setData(resp);
			context.sendResponse(message);
		}

	}

}
