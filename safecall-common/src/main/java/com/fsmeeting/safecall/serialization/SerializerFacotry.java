package com.fsmeeting.safecall.serialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.beans.common.SerializerCode;
import com.fsmeeting.safecall.serialization.impl.Hessian2;
import com.fsmeeting.safecall.serialization.impl.JSON;
import com.fsmeeting.safecall.serialization.impl.Protobuf;

/**
 * 序列化工厂
 * 
 * @author yicai.liu<moon>
 *
 */
public class SerializerFacotry {

	private static final Logger logger = LoggerFactory.getLogger(SerializerFacotry.class);

	/**
	 * 根据序列位，获取解码方式
	 * 
	 * @param id
	 *            序列位
	 * @return 解码方式
	 */
	public static ISerialization create(byte id) {
		switch (id) {
		case Hessian2.ID:
			logger.info("Serializer:hessian2!");
			return new Hessian2();
		case JSON.ID:
			logger.info("Serializer:json!");
			return new JSON();
		case Protobuf.ID:
			logger.info("Serializer:protobuf!");
			return new Protobuf();
		default:
			logger.error("Unsupported Serialize ID!");
			return null;
		}
	}

	/**
	 * 从配置文件获取编码方式
	 * 
	 * @param type
	 *            序列化方式
	 * @return 解码方式
	 */
	public static ISerialization create(String type) {

		if (SerializerCode.HESSIAN2.getCode().equals(type)) {
			logger.info("Serializer:hessian2!");
			return new Hessian2();
		} else if (SerializerCode.JSON.getCode().equals(type)) {
			logger.info("Serializer:json!");
			return new JSON();
		} else if (SerializerCode.PROTOBUF.getCode().equals(type)) {
			logger.info("Serializer:protobuf!");
			return new Protobuf();
		} else {
			logger.error("Unsupported Serialize Type!");
			return null;
		}
	}

}
