/**   
* @Title: SessionManager.java
* @Package com.fastonz.safecall.session.manager
* @Description: 会话管理器类
* @author zhangxt  
* @date 2016-10-17 下午3:42:25
*/
package com.fsmeeting.safecall.session.manager;

import java.util.HashMap;
import java.util.Map;

import com.fsmeeting.safecall.session.Session;

/**
 * @ClassName SessionManager
 * @Description 会话管理器类
 * @author zhangxt
 * @Date 2016-10-17 下午3:42:25
 */
public class SessionManager {

	// 建立用户与会话之间的映射
	private Map<String, Session> sessions = new HashMap<String, Session>();

	private static class Holder {
		private static final SessionManager INSTANCE = new SessionManager();
	}

	private SessionManager() {

	}

	public static SessionManager getInstance() {
		return Holder.INSTANCE;
	}

	public synchronized void addSession(String key, Session value) {
		sessions.put(key, value);
	}

	public synchronized Session getSession(String key) {
		return sessions.get(key);
	}

	public synchronized Session remove(String key) {
		return sessions.remove(key);
	}

	/**
	 * 校验Session的合法性
	 * 
	 * @param session
	 * @return
	 */
	public static boolean validate(Session session) {
		return null != session;
	}
}
