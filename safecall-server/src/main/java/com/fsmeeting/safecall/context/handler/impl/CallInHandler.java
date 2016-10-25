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
 * 被叫应答处理
 * 		1 	接收被叫响应
 * 		2	响应主叫
 * </pre>
 * 
 * @author yicai.liu<moon>
 *
 */
public class CallInHandler implements IHandler {

	private static final Logger logger = LoggerFactory.getLogger(CallInHandler.class);

	public void handle(BusinessContext context, Object data) throws Exception {

		Message message = new Message();
		message.setCmd(CommandCode.CALL.getCode());
		message.setReq(false);
		Response resp = new Response();
		try {
			CallInfo callInfo = (CallInfo) data;
			String caller = callInfo.getCallerNumber();
			Session session = SessionManager.getInstance().getSession(caller);
			logger.info("服务器回传数据给主叫:" + caller + ",Session:" + session);
			if (SessionManager.validate(session))// 在线
			{
				logger.info("主叫用户" + caller + "在线！");
				resp.setData(callInfo);
				message.setData(resp);
				context.sendResponse(message);
				session.getChannel().write(message);
			} else {// 不在线
				logger.info("主叫用户" + caller + "不在线！");
				resp.setCode(ResponseCode.OFFLINE.getCode());
				resp.setData(data);
				message.setData(resp);
				context.sendResponse(message);
			}

		} catch (Exception e) {
			logger.error("呼入异常:", e);
			resp.setCode(ResponseCode.SERVICE_ERROR.getCode());
			message.setData(resp);
			context.sendResponse(message);
		}

	}

}
