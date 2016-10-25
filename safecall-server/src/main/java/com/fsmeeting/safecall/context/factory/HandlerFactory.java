/**   
* @Title: HandleFactory.java
* @Package com.fastonz.safecall.context.factory
* @Description: 命令分发器
* @author zhangxt  
* @date 2016-10-17 下午2:37:39
*/
package com.fsmeeting.safecall.context.factory;

import java.util.HashMap;
import java.util.Map;

import com.fsmeeting.safecall.beans.common.CommandCode;
import com.fsmeeting.safecall.context.handler.IHandler;
import com.fsmeeting.safecall.context.handler.IHandlerFactory;
import com.fsmeeting.safecall.context.handler.impl.CallHandler;
import com.fsmeeting.safecall.context.handler.impl.CallInHandler;
import com.fsmeeting.safecall.context.handler.impl.HeartbeatHandler;
import com.fsmeeting.safecall.context.handler.impl.LoginHandler;
import com.fsmeeting.safecall.context.handler.impl.LogoutHandler;
import com.fsmeeting.safecall.context.handler.impl.RegisterHandler;
import com.fsmeeting.safecall.context.handler.impl.VOIPAddrHandler;

/**
 * @ClassName HandleFactory
 * @Description 命令分发器，获取指定命令的处理函数
 * @author zhangxt
 * @Date 2016-10-17 下午2:37:39
 */
public class HandlerFactory implements IHandlerFactory {

	// 映射请求命令跟处理器的关系，key:命令，value:处理器类
	private Map<Byte, IHandler> requests = new HashMap<Byte, IHandler>();

	// 映射响应命令跟处理器的关系，key:命令，value:处理器类
	private Map<Byte, IHandler> responses = new HashMap<Byte, IHandler>();

	private static class Holder {
		private static final HandlerFactory INSTANCE = new HandlerFactory();
	}

	private HandlerFactory() {
		init();
	}

	/**
	 * @Description 初始化函数
	 */
	private void init() {
		requests.put(CommandCode.HEARTBEAT.getCode(), new HeartbeatHandler());
		requests.put(CommandCode.LOGIN.getCode(), new LoginHandler());
		requests.put(CommandCode.REGISTER.getCode(), new RegisterHandler());
		requests.put(CommandCode.CALL.getCode(), new CallHandler());
		requests.put(CommandCode.GETVOIPADDR.getCode(), new VOIPAddrHandler());
		requests.put(CommandCode.LOGOUT.getCode(), new LogoutHandler());

		responses.put(CommandCode.CALLIN.getCode(), new CallInHandler());
	}

	/**
	 * @Description 获取单例对象
	 * @return
	 */
	public static HandlerFactory getInstance() {
		return Holder.INSTANCE;
	}

	public IHandler getHandler(byte cmd, boolean request) {

		if (request) {
			return requests.get(cmd);
		}
		return responses.get(cmd);
	}

	public void registerHandler(boolean request, IHandler handler) {

	}

}
