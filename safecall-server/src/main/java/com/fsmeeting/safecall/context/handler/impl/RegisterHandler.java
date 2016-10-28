package com.fsmeeting.safecall.context.handler.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.beans.UserInfo;
import com.fsmeeting.safecall.beans.common.CommandCode;
import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.beans.common.Response;
import com.fsmeeting.safecall.beans.common.ResponseCode;
import com.fsmeeting.safecall.context.BusinessContext;
import com.fsmeeting.safecall.context.handler.IHandler;
import com.fsmeeting.safecall.jdbc.JdbcUtils;
import com.fsmeeting.safecall.utils.Cipher;
import com.fsmeeting.safecall.utils.IDGenerator;

/**
 * 注册业务
 * 
 * @author yicai.liu<moon>
 *
 */
public class RegisterHandler implements IHandler {

	private static final Logger logger = LoggerFactory.getLogger(RegisterHandler.class);

	public void handle(BusinessContext context, Object data) throws Exception {

		Message message = new Message();
		Response resp = new Response();
		try {

			UserInfo user = (UserInfo) data;
			logger.info("注册用户信息：" + user);

			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String username = IDGenerator.generate(8);
			String countUserSql = "select * from t_userinfo where username = ?";
			while (null != runner.query(countUserSql, new BeanHandler<>(UserInfo.class), username)) {
				username = IDGenerator.generate(8);
			}

			user.setUsername(username);
			user.setPwd(Cipher.encrypt(user.getUsername(), user.getPwd()));
			String sql = "insert into t_userinfo(IMEI,username,nickname,pwd,mobile,create_time,update_time) values(?,?,?,?,?,sysdate(),sysdate())";

			int count = runner.update(sql, new Object[] { user.getIMEI(), user.getUsername(), user.getNickname(),
					user.getPwd(), user.getMobile() });

			if (count > 0) {
				message.setCmd(CommandCode.REGISTER.getCode());
				message.setReq(false);
				logger.info("注册成功:" + user.getUsername());
				resp.setData(user);
				message.setData(resp);
			} else { // 天上掉下个林妹妹
				logger.error("注册失败！");
				resp.setCode(ResponseCode.SERVICE_ERROR.getCode());
			}

		} catch (Exception e) {
			logger.error("注册异常:", e);
			resp.setCode(ResponseCode.SERVICE_ERROR.getCode());
		}

		context.sendResponse(message);
	}

}
