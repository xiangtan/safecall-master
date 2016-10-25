/**   
 * @Title: Header.java
 * @Package com.fsmeeting.safecall.beans.common
 * @Description: 消息头
 * @author zhangxt  
 * @date 2016-10-18 下午5:07:32
 */
package com.fsmeeting.safecall.beans.common;

import java.io.Serializable;

import com.fsmeeting.safecall.utils.Bytes;

/**
 * @ClassName Header
 * @Description 消息头
 * @author zhangxt
 * @Date 2016-10-18 下午5:07:32
 */
public class Header implements Serializable {

	private static final long serialVersionUID = 1L;

	// 消息头长度
	public static final int HEADER_LENGTH = 4;

	// 版本位，默认0000 0000
	private static final byte DEFAULT_VER = (byte) 0x00;
	
	// 版本的掩码位
	private static final byte MARK_VER = (byte) 0x03;
	
	// 序列位，默认0000 0100 (可动态扩展)
	private static final byte MARK_SERIALIZATION = (byte) 0x1f;

	// 序列位，默认1000 0000
	private static final byte MARK_REQUEST = (byte) 0x80;
	
	// 数据负载，32K
	public static final int DEFAULT_PAYLOAD = 1 << 10 << 5;

	/**
	 * 版本
	 */
	private byte ver = DEFAULT_VER;

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

	public byte[] encode() {
		
		checkPayload(length);
		
		byte[] encodes = new byte[HEADER_LENGTH];

		byte head = DEFAULT_VER;
		
		head = (byte)(head | (this.ver & MARK_VER)); 
		
		this.ser = (byte)(this.ser & MARK_SERIALIZATION);
		
		head = (byte)((this.ser << 2) | head);
		
		if (req) {
			head = (byte)(head | MARK_REQUEST);
		}
		
		encodes[0] = head;
		encodes[1] = cmd;
		
		byte[] bLength = Bytes.short2bytes(length);
		
		encodes[2] = bLength[0];
		encodes[3] = bLength[1];
		
		return encodes;
	}

	public Header decode(byte[] data) {
		
		if (data == null | data.length != HEADER_LENGTH) {
			throw new IllegalArgumentException("data illegal");
		}
		
		byte head = data[0];
		
		this.ver = (byte)(head & MARK_VER);
		this.ser = (byte)((head >> 2) & MARK_SERIALIZATION);
		
		byte request = (byte) (head & MARK_REQUEST);
		
		if (request == 0) {
			this.req = false;
		}
		
		this.cmd = data[1];
		
		byte[] bLength = new byte[2];
		bLength[0] = data[2];
		bLength[1] = data[3];
		
		this.length = Bytes.bytes2short(bLength);
		
		return this;

	}
	
	private  void checkPayload(short size) {
		if (size >= DEFAULT_PAYLOAD) {
			throw new IllegalArgumentException("payload is too big.");
		}
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

	public static void main(String[] args) {
		
		Header header = new Header();
		
		header.setCmd((byte)1);
		header.setLength((short)256);
		header.setReq(true);
		header.setSer((byte)1);
		header.setVer((byte)0);
		
		System.out.println("encode:" + header);
		byte [] encoders = header.encode();
		System.out.println("bytes:" + Bytes.bytes2hex(encoders));
		
		Header raw = new Header();
		
		Header decode = raw.decode(encoders);
		System.out.println("decode:" + decode);
		
	}
	
	

}
