/**   
* @Title: PropertiesUtils.java
* @Package com.fsmeeting.safecall.utils
* @Description: 属性文件工具类
* @author zhangxt  
* @date 2016-10-18 下午1:43:52
*/
package com.fsmeeting.safecall.utils;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName PropertiesUtils
 * @Description 属性文件工具类
 * @author zhangxt
 * @Date 2016-10-18 下午1:43:52
 */
public class PropertiesUtils {

	private static final String CONFIG_FILE = "system.properties";

	private static Properties properties = null;

	private static ClassLoader classLoader;

	private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtils.class);

	static {
		classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = PropertiesUtils.class.getClassLoader();
		}

		loadProperties();
	}

	public static void loadProperties() {
		if (classLoader.getResource(CONFIG_FILE) == null) {
			LOG.warn("ERROR: system.properties NOT found in the classpath. ");

			throw new IllegalArgumentException("system.properties NOT found in the classpath");
		}

		InputStream input = classLoader.getResourceAsStream(CONFIG_FILE);
		properties = new Properties();
		try {
			properties.load(input);
		} catch (Exception e) {
			LOG.warn("catch an exception when loading properties. ", e);

			throw new IllegalArgumentException("fail when load properties");
		}
	}

	public static String getString(String key) {
		return properties.getProperty(key);
	}

	public static String getString(String key, String defaultValue) {
		String value = properties.getProperty(key);

		return (value != null ? value : defaultValue);
	}

	public static Integer getInteger(String key, Integer defaultValue) {
		String value = properties.getProperty(key);

		return (value != null ? Integer.valueOf(value) : defaultValue);
	}

	public static void main(String[] args) {
		System.out.println(getString(Constants.VOIP_ADDRESS));
	}

}
