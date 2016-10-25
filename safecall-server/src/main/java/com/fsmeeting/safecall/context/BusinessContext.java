/**   
* @Title: BusinessContext.java
* @Package com.fastonz.safecall.context
* @Description: 业务上下文对象
* @author zhangxt  
* @date 2016-10-17 下午2:23:04
*/
package com.fsmeeting.safecall.context;

import org.jboss.netty.channel.Channel;

import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.session.Session;

/**
 * @ClassName BusinessContext
 * @Description 业务上下文对象
 * @author zhangxt
 * @Date 2016-10-17 下午2:23:04
 */
public class BusinessContext {

	// tcp通道
	private Channel channel;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void sendResponse(Message message) {
		channel.write(message);
	}

	public Session getSession() {
		return (Session) channel.getAttachment();
	}

	public void attachSession(Session session) {
		channel.setAttachment(session);
	}

	public void removeSession(Session session) {
		channel.setAttachment(null);
	}
}
