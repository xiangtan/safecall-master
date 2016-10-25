package com.fsmeeting.safecall.beans;

import java.io.Serializable;

/**
 * 服务器业务对象
 * 
 * @author yicai.liu<moon>
 *
 */
public class ServerInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String address;

	public ServerInfo() {

	}

	public ServerInfo(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
