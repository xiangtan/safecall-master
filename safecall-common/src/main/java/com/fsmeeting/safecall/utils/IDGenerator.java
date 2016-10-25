package com.fsmeeting.safecall.utils;

import java.util.Random;

public class IDGenerator {

	/**
	 * 生成定长的ID串
	 * 
	 * @param len
	 * @return
	 */
	public /* synchronized */ static String generate(int len) {

		Random r = new Random();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < len; i++) {
			if (0 == i) {
				builder.append(1);
			} else {
				builder.append(r.nextInt(10));
			}
		}

		return builder.toString();

	}

	public static void main(String[] args) {
		System.out.println(generate(8));
	}
}