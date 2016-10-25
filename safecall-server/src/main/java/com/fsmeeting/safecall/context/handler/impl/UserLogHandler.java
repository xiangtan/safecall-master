package com.fsmeeting.safecall.context.handler.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.beans.UserLogInfo;
import com.fsmeeting.safecall.context.BusinessContext;
import com.fsmeeting.safecall.context.handler.IHandler;
import com.fsmeeting.safecall.jdbc.JdbcUtils;

/**
 * 用户日志记录
 * 
 * @author yicai.liu<moon>
 *
 */
public class UserLogHandler implements IHandler {

	private static final Logger logger = LoggerFactory.getLogger(UserLogHandler.class);

	@Override
	public void handle(BusinessContext context, Object data) throws Exception {

		try {

			UserLogInfo userlog = (UserLogInfo) data;
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());

			String userlogSql = "insert into t_userloginfo ('imei','username','mobile','brand','brand_version','os','os_version','model','language','resolution') values (?,?,?,?,?,?,?,?,?,?) ";
			Object result = runner.insert(userlogSql, new ScalarHandler<>(), userlog.getIMEI(), userlog.getUsername(),
					userlog.getMobile(), userlog.getBrand(), userlog.getBrandVersion(), userlog.getOs(),
					userlog.getOsVersion(), userlog.getModel(), userlog.getLanguage(), userlog.getResolution());

			logger.info("Add Userlog Result:" + result);

		} catch (Exception e) {
			logger.error("日志记录失败:", e);
		}

	}

}
