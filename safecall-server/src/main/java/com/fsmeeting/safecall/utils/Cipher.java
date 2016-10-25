package com.fsmeeting.safecall.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.utils.security.DesUtil;

/**
 * 
 * 用户加解密
 * 
 * 重要：需要变更时，请联系作者。
 * 
 * @author yicai.liu<moon>
 *
 */
public class Cipher {

	private static final Logger logger = LoggerFactory.getLogger(Cipher.class);

	/**
	 * 加密过程
	 * 
	 * @param salting
	 *            颜值
	 * @param message
	 *            处理信息
	 * @return
	 */
	public static String encrypt(String salting, String message) {
		try {

			DesUtil des = new DesUtil();
			String plain = des.decrypt(message);
			logger.info("解出明文：" + plain);

			String saltingPlain = salting(salting, plain);
			logger.info("颜值：" + saltingPlain);

			return Bytes.bytes2hex(Bytes.getMD5(saltingPlain));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 加盐
	 * 
	 * @param message
	 */
	private static String salting(String salting, String message) {
		return Bytes.bytes2hex(Bytes.getMD5(salting)).concat(message);
	}

	public static void main(String[] args) {
		// 6cc720d0a34d0ea09a0f450a89d1ca45
		System.out.println(encrypt("18858501", "25b7f5afee0d962a"));
	}
}
