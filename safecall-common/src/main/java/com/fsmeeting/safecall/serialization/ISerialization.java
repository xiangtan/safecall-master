package com.fsmeeting.safecall.serialization;

import java.io.IOException;

/**
 * 序列化接口
 * 
 * @author yicai.liu<moon>
 *
 */
public interface ISerialization {
	/**
	 * 序列化类型,范围[Ox04,0x08,0x10,0x20,0x40]
	 * 
	 * @return
	 */
	byte getContentTypeId();

	/**
	 * 序列化
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	byte[] serialize(Object obj) throws IOException;

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	Object deserialize(byte[] bytes) throws IOException;
}
