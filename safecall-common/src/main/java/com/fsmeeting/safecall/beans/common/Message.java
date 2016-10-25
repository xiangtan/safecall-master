package com.fsmeeting.safecall.beans.common;

import java.io.Serializable;

import com.fsmeeting.safecall.beans.UserInfo;
import com.fsmeeting.safecall.serialization.impl.Hessian2;
import com.fsmeeting.safecall.utils.Bytes;
import com.fsmeeting.safecall.utils.SerializeUtils;

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
		header.setSer((byte) 1);
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

	/**
	 * 
	 * @Description 消息编码
	 * @return
	 * @throws Exception
	 */
	public byte[] encode() throws Exception {

		if (header == null) {
			throw new IllegalArgumentException("header is not null!");
		}

		byte[] payload = null;
		byte[] messages = null;

		if (this.data == null) {
			return header.encode();
		}

		payload = SerializeUtils.serialize(this.data);
		header.setLength((short) payload.length);

		messages = new byte[Header.HEADER_LENGTH + payload.length];

		System.arraycopy(header.encode(), 0, messages, 0, Header.HEADER_LENGTH);
		System.arraycopy(payload, 0, messages, Header.HEADER_LENGTH, payload.length);
		
		return messages;
	}

	@Override
	public String toString() {
		return "Message [header=" + header + ", data=" + data + "]";
	}

	public static void main(String[] args) throws Exception {
		// 编码
		
		Message message = new Message();

		message.setCmd((byte) 1);
		message.setReq(false);
		message.setSer((byte) 1);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1);
		userInfo.setIMEI("dsdsddddd");
		userInfo.setMobile("15987362145");
		
		message.setData(userInfo);

		byte[] data = message.encode();

		System.out.println("message:" + message);
		System.out.println("bytes:" + Bytes.bytes2hex(data));
		
		// 解码
		byte[] head = new byte[4];
		System.arraycopy(data, 0, head, 0, 4);
		Header header = new Header();
		header.decode(head);
		System.out.println("header:" + header);
		byte[] payload = new byte[data.length-4];
		System.arraycopy(data, 4, payload, 0, payload.length);
		
		Hessian2 hessian2 = new Hessian2();
		
		UserInfo user = (UserInfo)hessian2.deserialize(payload);
		System.out.println("user：" + user);
		
		Message response = new Message();
		response.setHeader(header);
		response.setData(user);
		
		System.out.println("response:" + response);
		
	}

}
