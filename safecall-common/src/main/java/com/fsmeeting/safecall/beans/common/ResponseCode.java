
package com.fsmeeting.safecall.beans.common;

/**
 * 
 * 
 * @author yicai.liu<moon>
 * @version 2016年10月17日
 * @see 返回编码
 * @since
 */
public enum ResponseCode {
	/**
	 * 服务响应超时
	 */
	SERVER_TIMEOUT(10),
	/**
	 * 成功
	 */
	OK(20),
	/**
	 * 客户端请求超时
	 */
	CLIENT_TIMEOUT(30),
	/**
	 * 请求不合法
	 */
	BAD_REQUEST(40),
	/**
	 * 响应不合法
	 */
	BAD_RESPONSE(50),
	/**
	 * 服务未找到
	 */
	SERVICE_NOT_FOUND(60),
	/**
	 * 服务出错
	 */
	SERVICE_ERROR(70),
	/**
	 * 服务端错误
	 */
	SERVER_ERROR(80),
	/**
	 * 客户端错误
	 */
	CLIENT_ERROR(90),
	/**
	 * 登录失败：用户名or密码错误
	 */
	LOGIN_FAIL(100),
	/**
	 * 不在线
	 */
	OFFLINE(110);
	private int code;

	private ResponseCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
