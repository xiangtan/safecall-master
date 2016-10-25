/**   
* @Title: LoginHandle.java
* @Package com.fastonz.safecall.context.handle.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author zhangxt  
* @date 2016-10-17 下午2:49:36
*/
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
import com.fsmeeting.safecall.session.Session;
import com.fsmeeting.safecall.session.manager.SessionManager;
import com.fsmeeting.safecall.utils.Cipher;

/**
 * @ClassName LoginHandle
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author zhangxt
 * @Date 2016-10-17 下午2:49:36
 */
public class LoginHandler implements IHandler {

	private static final Logger logger = LoggerFactory.getLogger(LoginHandler.class);

	public void handle(BusinessContext context, Object data) throws Exception {

		Message message = new Message();
		message.setCmd(CommandCode.LOGIN.getCode());
		message.setReq(false);
		Response resp = new Response();

		try {

			UserInfo user = (UserInfo) data;
			logger.info("登录用户信息：" + user);

			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			user.setPwd(Cipher.encrypt(user.getUsername(), user.getPwd()));

			String countUserSql = "select * from t_userinfo where username = ? and pwd = ? ";
			user = runner.query(countUserSql, new BeanHandler<>(UserInfo.class), user.getUsername(), user.getPwd());

			if (null == user) {
				logger.info("登录失败，用户名或密码不正确!");
				resp.setCode(ResponseCode.LOGIN_FAIL.getCode());
			} else {
				logger.info("登录成功！");
				user.setPwd(null);
				bindingSession(context, user);
				resp.setData(user);
			}

		} catch (Exception e) {
			logger.error("登录失败:", e);
			resp.setCode(ResponseCode.SERVICE_ERROR.getCode());
		}

		message.setData(resp);
		context.sendResponse(message);

	}

	/**
	 * 绑定Session
	 * 
	 * @param context
	 * @param user
	 */
	private Session bindingSession(BusinessContext context, UserInfo user) {
		Session session = new Session();
		session.setUserInfo(user);
		session.setChannel(context.getChannel());
		context.attachSession(session);

		SessionManager.getInstance().addSession(user.getUsername(), session);
		return session;
	}

}
