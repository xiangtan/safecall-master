
package com.fsmeeting.safecall.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

/**
 * 
 * 系列化工具类（hessian）
 * 
 * @author yicai.liu<moon>
 * @version 2016年10月12日
 * @see SerializeUtils
 * @since
 */
public class SerializeUtils {

	public static byte[] serialize(Object obj) throws IOException {
		if (obj == null)
			throw new NullPointerException();

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HessianOutput ho = new HessianOutput(os);
		ho.writeObject(obj);
		return os.toByteArray();
	}

	public static Object deserialize(byte[] by) throws IOException {
		if (by == null)
			throw new NullPointerException();

		ByteArrayInputStream is = new ByteArrayInputStream(by);
		HessianInput hi = new HessianInput(is);
		return hi.readObject();
	}

}
