/**   
 * @Title: Session.java
 * @Package com.fastonz.safecall.session
 * @Description: 业务会话类
 * @author zhangxt  
 * @date 2016-10-17 下午3:19:25
 */
package com.fsmeeting.safecall.session;

import java.util.Date;

import org.jboss.netty.channel.Channel;

import com.fsmeeting.safecall.beans.UserInfo;
import com.fsmeeting.safecall.beans.common.Message;

/**
 * @ClassName Session
 * @Description 业务会话类
 * @author zhangxt
 * @Date 2016-10-17 下午3:19:25
 */
public class Session {
	// 用户信息
	private UserInfo user;

	// tcp通道
	private Channel channel;

	// 最后访问时间
	private Date lastAccessTime;

	public Session() {
		this.lastAccessTime = new Date();
	}

	public UserInfo getUserInfo() {
		return user;
	}

	public void setUserInfo(UserInfo user) {
		this.user = user;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void sendResponse(Message message) {
		channel.write(message);
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	@Override
	public String toString() {
		return "Session [user=" + user + ", channel=" + channel + ", lastAccessTime=" + lastAccessTime + "]";
	}

}
