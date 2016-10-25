package com.fsmeeting.safecall.beans.common;

/**
 * 应答编码
 * 
 * @author yicai.liu<moon>
 *
 */
public enum AnswerCode {

	/**
	 * 允许
	 */
	YES("yes"),
	/**
	 * 拒绝
	 */
	NO("no");
	private String code;

	private AnswerCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
