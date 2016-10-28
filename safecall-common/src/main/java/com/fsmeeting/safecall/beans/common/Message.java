package com.fsmeeting.safecall.beans.common;

import java.io.Serializable;

/**
 *
 * 请求信息
 * 
 * @author yicai.liu<moon>
 * @version 2016年10月14日
 * @see Message
 * @since
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 消息头
	 */
	private Header header;

	/**
	 * 业务数据
	 */
	private Object data;

	public Message() {
		header = new Header();
	}

	public byte getVer() {
		return header.getSer();
	}

	public void setVer(byte ver) {
		header.setVer(ver);
	}

	public byte getSer() {
		return header.getSer();
	}

	public void setSer(byte ser) {
		header.setSer(ser);
	}

	public boolean isReq() {
		return header.isReq();
	}

	public void setReq(boolean req) {
		header.setReq(req);
	}

	public byte getCmd() {
		return header.getCmd();
	}

	public void setCmd(byte cmd) {
		header.setCmd(cmd);
	}

	public short getLength() {
		return header.getLength();
	}

	public void setLength(short length) {
		header.setLength(length);
	}

	public Object getData() {
		return data;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Message [header=" + header + ", data=" + data + "]";
	}

}
