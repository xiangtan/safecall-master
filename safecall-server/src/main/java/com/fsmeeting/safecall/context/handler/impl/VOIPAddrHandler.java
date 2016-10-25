package com.fsmeeting.safecall.context.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.beans.ServerInfo;
import com.fsmeeting.safecall.beans.common.CommandCode;
import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.beans.common.Response;
import com.fsmeeting.safecall.beans.common.ResponseCode;
import com.fsmeeting.safecall.context.BusinessContext;
import com.fsmeeting.safecall.context.handler.IHandler;
import com.fsmeeting.safecall.session.manager.SessionManager;
import com.fsmeeting.safecall.utils.Constants;
import com.fsmeeting.safecall.utils.PropertiesUtils;

/**
 * 获取VOIP业务
 * 
 * @author yicai.liu<moon>
 *
 */
public class VOIPAddrHandler implements IHandler {

	private static final Logger logger = LoggerFactory.getLogger(VOIPAddrHandler.class);

	public void handle(BusinessContext context, Object data) throws Exception {

		Message message = new Message();
		message.setCmd(CommandCode.GETVOIPADDR.getCode());
		message.setReq(false);
		Response resp = new Response();
		try {
			if (!SessionManager.validate(context.getSession())) {
				logger.info("会话超时,需要重新登录!");
				resp.setCode(ResponseCode.CLIENT_TIMEOUT.getCode());
			} else {
				String voipAdrress = PropertiesUtils.getString(Constants.VOIP_ADDRESS);
				logger.info("成功获取VOIP地址：" + voipAdrress);
				ServerInfo server = new ServerInfo("VOIP", voipAdrress);
				resp.setData(server);
			}

		} catch (Exception e) {
			logger.error("获取VOIP地址失败:", e);
			resp.setCode(ResponseCode.SERVICE_ERROR.getCode());
		}
		message.setData(resp);
		context.sendResponse(message);
	}

}
