package com.fsmeeting.safecall.beans.common;

import java.io.Serializable;

/**
 * 
 * 返回消息
 * 
 * @author yicai.liu<moon>
 * @version 2016年10月17日
 * @see Response
 * @since
 */
public class Response implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 响应码
	 */
	private int code;

	/**
	 * 业务对象
	 */
	private Object data;

	public Response() {
		this(ResponseCode.OK.getCode(), null);
	}

	public Response(Object data) {
		this(ResponseCode.OK.getCode(), data);
	}

	/**
	 * @param code
	 * @param msg
	 */
	public Response(int code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", data=" + data + "]";
	}

}
