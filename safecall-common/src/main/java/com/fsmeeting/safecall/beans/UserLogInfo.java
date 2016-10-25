package com.fsmeeting.safecall.beans;

import java.io.Serializable;

/**
 * 用户日志
 * 
 * @author yicai.liu<moon>
 *
 */
public class UserLogInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 你猜
	 */
	private String IMEI;

	/**
	 * 用户名(八位)
	 */
	private String username;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 品牌(exp:iphone)
	 */
	private String brand;
	/**
	 * 品牌版本(exp:6S)
	 */
	private String brandVersion;

	/**
	 * 操作系统(exp:ios)
	 */
	private String os;

	/**
	 * 操作系统(exp:3.4.0)
	 */
	private String osVersion;

	/**
	 * 手机型号(exp:MG482ZP/A)
	 */
	private String model;

	/**
	 * 手机使用语言(exp:zh-CN)
	 */
	private String language;

	/**
	 * 分辨率(exp:1334 x 750)
	 */
	private String resolution;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrandVersion() {
		return brandVersion;
	}

	public void setBrandVersion(String brandVersion) {
		this.brandVersion = brandVersion;
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	@Override
	public String toString() {
		return "UserLogInfo [IMEI=" + IMEI + ", username=" + username + ", mobile=" + mobile + ", brand=" + brand
				+ ", brandVersion=" + brandVersion + ", os=" + os + ", osVersion=" + osVersion + ", model=" + model
				+ ", language=" + language + ", resolution=" + resolution + "]";
	}

}
