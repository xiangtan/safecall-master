package com.fsmeeting.safecall.utils;

import com.fsmeeting.safecall.serialization.ISerialization;
import com.fsmeeting.safecall.serialization.SerializerFacotry;

/**
 * 常量定义
 * 
 * @author yicai.liu<moon>
 *
 */
public class Constants {

	/**
	 * 服务器IP配置
	 */
	public static final String SERVER_IP_STRING = "server.ip";

	/**
	 * 服务器端口配置
	 */
	public static final String SERVER_PORT_STRING = "server.port";

	/**
	 * 服务器IP
	 */
	public static final String SERVER_IP = PropertiesUtils.getString(SERVER_IP_STRING);

	/**
	 * 服务器端口
	 */
	public static final int SERVER_PORT = PropertiesUtils.getInteger(SERVER_PORT_STRING, 8000);

	/**
	 * VOIP 配置地址
	 */
	public static final String VOIP_ADDRESS = "VOIPAddress";

	/**
	 * 序列化方式
	 */
	public static final String SERIALIZER_TYPE = "serializer.type";

	/**
	 * 序列化工具
	 */
	public static final ISerialization SERIALIZER = SerializerFacotry
			.create(PropertiesUtils.getString(SERIALIZER_TYPE));

	/**
	 * 序列化位
	 */
	public static final byte SERIALIZER_ID = SERIALIZER.getContentTypeId();
}
