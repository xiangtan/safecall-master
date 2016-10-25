package com.fsmeeting.safecall.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * 连接池管理
 * 
 * @author yicai.liu<moon>
 *
 */
public class JdbcUtils {

	private static DataSource ds = null;
	static {
		try {
			// 默认 c3p0-config.xml
			ds = new ComboPooledDataSource();

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 
	 * @return 获取连接
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	/**
	 * 释放资源
	 * 
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void release(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取数据源
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return ds;
	}
}