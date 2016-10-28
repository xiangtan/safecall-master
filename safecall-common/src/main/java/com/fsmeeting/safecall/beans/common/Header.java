/**   
 * @Title: Header.java
 * @Package com.fsmeeting.safecall.beans.common
 * @Description: 消息头
 * @author zhangxt  
 * @date 2016-10-18 下午5:07:32
 */
package com.fsmeeting.safecall.beans.common;

import java.io.Serializable;

/**
 * @ClassName Header
 * @Description 消息头
 * @author zhangxt
 * @Date 2016-10-18 下午5:07:32
 */
public class Header implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 版本
	 */
	private byte ver;

	/**
	 * 序列化方式
	 */
	private byte ser;

	/**
	 * 请求or响应(1/0):true/false
	 */
	private boolean req = true;

	/**
	 * 指令
	 */
	private byte cmd;

	/**
	 * 数据长度
	 */
	private short length;

	public Header() {

	}

	public Header(byte ver, byte ser, boolean req) {
		this.ver = ver;
		this.ser = ser;
		this.req = req;
	}

	public byte getVer() {
		return ver;
	}

	public void setVer(byte ver) {
		this.ver = ver;
	}

	public byte getSer() {
		return ser;
	}

	public void setSer(byte ser) {
		this.ser = ser;
	}

	public boolean isReq() {
		return req;
	}

	public void setReq(boolean req) {
		this.req = req;
	}

	public byte getCmd() {
		return cmd;
	}

	public void setCmd(byte cmd) {
		this.cmd = cmd;
	}

	public short getLength() {
		return length;
	}

	public void setLength(short length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "Header [ver=" + ver + ", ser=" + ser + ", req=" + req + ", cmd=" + cmd + ", length=" + length + "]";
	}

}
