package com.fsmeeting.safecall.serialization.impl;

import java.io.IOException;

import com.fsmeeting.safecall.serialization.ISerialization;

/**
 * Protobuf 序列化
 * 
 * @author yicai.liu<moon>
 *
 */
public class Protobuf implements ISerialization {

	public static final byte ID = 0x04;

	@Override
	public byte getContentTypeId() {
		return ID;
	}

	@Override
	public byte[] serialize(Object obj) throws IOException {

		return null;
	}

	@Override
	public Object deserialize(byte[] bytes) throws IOException {
		return null;
	}

}
