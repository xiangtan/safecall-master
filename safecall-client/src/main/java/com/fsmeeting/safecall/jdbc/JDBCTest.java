package com.fsmeeting.safecall.jdbc;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.fsmeeting.safecall.beans.UserInfo;
import com.fsmeeting.safecall.utils.IDGenerator;

public class JDBCTest {
	public static void main(String[] args) throws SQLException {
		UserInfo user = new UserInfo();
		user.setIMEI("FGYHJKLJ001");
		user.setMobile("18676780050");
		user.setNickname("moon");
		user.setPwd("888");
		user.setUsername(IDGenerator.generate(8));
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "insert into t_userinfo(IMEI,username,nickname,pwd,mobile,create_time,update_time) values(?,?,?,?,?,sysdate(),sysdate())";
		int count = runner.update(sql, new Object[] { user.getIMEI(), user.getUsername(), user.getNickname(),
				user.getPwd(), user.getMobile() });

		System.out.println(count);

		String countUserSql = "select * from t_userinfo where username = ?";
		UserInfo info = runner.query(countUserSql, new BeanHandler<>(UserInfo.class), user.getUsername());
		System.out.println(info);

	}
}
