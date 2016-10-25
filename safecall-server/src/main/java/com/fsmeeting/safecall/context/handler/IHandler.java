/**   
* @Title: IRequestHandle.java
* @Package com.fastonz.safecall.context.handle
* @Description: 处理器类
* @author zhangxt  
* @date 2016-10-17 下午2:30:04
*/
package com.fsmeeting.safecall.context.handler;

import com.fsmeeting.safecall.context.BusinessContext;

/**
 * @ClassName IRequestHandle
 * @Description 处理器类
 * @author zhangxt
 * @Date 2016-10-17 下午2:30:04
 */
public interface IHandler {

	/**
	 * 处理业务
	 * 
	 * @param context
	 * @param data
	 * @throws Exception
	 */
	void handle(BusinessContext context, Object data) throws Exception;
}
