
package com.fsmeeting.safecall.beans.common;

/**
 * 
 * 
 * @author yicai.liu<moon>
 * @version 2016年10月17日
 * @see 命令编码
 * @since
 */
public enum CommandCode {
	/**
	 * 心跳
	 */
	HEARTBEAT(0),
	/**
	 * 注册
	 */
	REGISTER(1),
	/**
	 * 登录
	 */
	LOGIN(2),
	/**
	 * 获取VOIP地址
	 */
	GETVOIPADDR(3),
	/**
	 * 呼入
	 */
	CALL(4),
	/**
	 * 
	 */
	CALLIN(5),
	/**
	 * 退出登录
	 */
	LOGOUT(6);
	/**
	 * 范围 0——127
	 */
	private byte code;

	private CommandCode(int code) {
		if (code < 0 || code > 127) {
			throw new RuntimeException("命令编码超出范围：[0,127]" + code);
		}
		this.code = (byte) code;
	}

	private CommandCode(byte code) {
		this.code = code;
	}

	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}

}
