/**   
* @Title: FileUtil.java
* @Package com.fsmeeting.utils
* @Description: 文件目录工具类
* @author zhangxt  
* @date 2016-7-26 下午4:21:02
*/
package com.fsmeeting.safecall.utils;

import java.net.URL;

/**
 * @ClassName FileUtil
 * @Description 文件目录工具类
 * @author zhangxt
 * @Date 2016-7-26 下午4:21:02
 */
public class FileUtil {
	
	/**
	 * @Description 获取工程目录下文件的绝对路径 
	 * @param relativePath
	 * @return
	 */
	public static String getFilePath(String relativePath) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader == null) {
			classLoader = FileUtil.class.getClassLoader();
		}

		URL path = classLoader.getResource(relativePath);
		
		String absolutePath = null;
		
		if (path != null) {
			absolutePath = path.getPath();
		}

		return absolutePath;

	}
}
