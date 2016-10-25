/**   
* @Title: IHandleFactory.java
* @Package com.fastonz.safecall.context.handle
* @Description: 处理器工厂类
* @author zhangxt  
* @date 2016-10-17 下午2:34:04
*/
package com.fsmeeting.safecall.context.handler;

/**
 * @ClassName IHandleFactory
 * @Description 处理器工厂类
 * @author zhangxt
 * @Date 2016-10-17 下午2:34:04
 */
public interface IHandlerFactory {

	/**
	 * 
	 * @Description 获取处理器类
	 * @param cmd
	 * @return
	 */
	IHandler getHandler(byte cmd, boolean request);

	/**
	 * 
	 * @Description 注册处理类
	 * @param request
	 * @param handle
	 */
	void registerHandler(boolean request, IHandler handler);

}
