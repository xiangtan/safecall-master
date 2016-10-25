package com.fsmeeting.safecall.protobuf.bean;

import java.util.List;

public class UserVO {
	private String name;
	private int age;
	private long phone;

	private List<UserVO> friends;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public List<UserVO> getFriends() {
		return friends;
	}

	public void setFriends(List<UserVO> friends) {
		this.friends = friends;
	}

}
