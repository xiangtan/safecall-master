package com.fsmeeting.safecall.beans;

import java.io.Serializable;

import com.fsmeeting.safecall.beans.common.AnswerCode;

/**
 * 呼叫业务对象
 * 
 * @author yicai.liu<moon>
 *
 */
public class CallInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 通话ID
	 */
	private String callID;
	/**
	 * 主叫号码
	 */
	private String callerNumber;

	/**
	 * 被叫号码
	 */
	private String calleeNumber;

	/**
	 * 加密串
	 */
	private String encryptKey;

	/**
	 * 主叫人
	 */

	private String callerName;
	/**
	 * 被叫人
	 */

	private String calleeName;

	/**
	 * 应答
	 */
	private String answer = AnswerCode.YES.getCode();

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCallID() {
		return callID;
	}

	public void setCallID(String callID) {
		this.callID = callID;
	}

	public String getCallerNumber() {
		return callerNumber;
	}

	public void setCallerNumber(String callerNumber) {
		this.callerNumber = callerNumber;
	}

	public String getCalleeNumber() {
		return calleeNumber;
	}

	public void setCalleeNumber(String calleeNumber) {
		this.calleeNumber = calleeNumber;
	}

	public String getEncryptKey() {
		return encryptKey;
	}

	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}

	public String getCallerName() {
		return callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}

	public String getCalleeName() {
		return calleeName;
	}

	public void setCalleeName(String calleeName) {
		this.calleeName = calleeName;
	}

}
