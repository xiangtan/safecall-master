package com.fsmeeting.safecall.beans;

import java.io.Serializable;

/**
 * 用户业务对象
 * 
 * @author yicai.liu<moon>
 *
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private int id;

	/**
	 * 你猜
	 */
	private String IMEI;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 手机型号
	 */
	private String model;

	/**
	 * 操作系统
	 */
	private String os;

	/**
	 * 操作系统版本
	 */
	private String osVersion;

	/**
	 * 手机厂商
	 */
	private String manufacturer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIMEI() {
		return IMEI;
	}

	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", IMEI=" + IMEI + ", username=" + username + ", nickname=" + nickname + ", pwd="
				+ pwd + ", mobile=" + mobile + ", model=" + model + ", os=" + os + ", osVersion=" + osVersion
				+ ", manufacturer=" + manufacturer + "]";
	}

}
