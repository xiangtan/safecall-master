package com.fsmeeting.safecall.beans.common;

/**
 * 序列化方式
 * 
 * @author yicai.liu<moon>
 *
 */
public enum SerializerCode {

	/**
	 * hession2
	 */
	HESSIAN2("hessian2"),

	/**
	 * json
	 */
	JSON("json"),

	/**
	 * google protobuf
	 */
	PROTOBUF("protobuf");

	private String code;

	private SerializerCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
