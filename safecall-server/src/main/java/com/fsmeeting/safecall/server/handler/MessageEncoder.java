
package com.fsmeeting.safecall.server.handler;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.fsmeeting.safecall.beans.common.Header;
import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.utils.Bytes;
import com.fsmeeting.safecall.utils.Constants;

/**
 * 消息编码
 * 
 * <pre>
 * version(0-1)+Serialization id(2-6)+Req/Res(7)+cmd(8-15)+data+length(16-31)+data(32~)
 * </pre>
 * 
 * @author yicai.liu<moon>
 *
 */
public class MessageEncoder extends OneToOneEncoder {

	private static final Logger logger = Logger.getLogger(MessageEncoder.class);

	// 消息头长度
	protected static final int HEADER_LENGTH = 4;

	// 版本位，默认0000 0000
	private static final byte DEFAULT_VER = (byte) 0x00;

	// 版本的掩码位
	private static final byte MASK_VER = (byte) 0x03;

	// 序列位，默认0000 0100 (可动态扩展)
	private static final byte MASK_SERIALIZATION = (byte) 0x1f;

	// 序列位，默认1000 0000
	private static final byte MASK_REQUEST = (byte) 0x80;

	// 数据负载，32K
	public static final int DEFAULT_PAYLOAD = 1 << 10 << 5;

	@Override
	public Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {

		logger.info("LocalAddress:" + channel.getLocalAddress());

		if (msg instanceof Message) {

			byte[] array = encode(ctx, channel, (Message) msg);
			return ChannelBuffers.wrappedBuffer(array);

		} else {
			throw new RuntimeException("消息类型不支持");
		}
	}

	protected byte[] encode(ChannelHandlerContext ctx, Channel channel, Message msg) throws Exception {
		
		Header header = msg.getHeader();
		Object data = msg.getData();
		if (header == null) {
			throw new IllegalArgumentException("header is not null!");
		}

		byte[] payload = null;
		byte[] messages = null;

		if (data == null) {
			return encodeHeader(header);
		}

		payload = Constants.SERIALIZER.serialize(data);
		header.setLength((short) payload.length);

		messages = new byte[HEADER_LENGTH + payload.length];

		System.arraycopy(encodeHeader(header), 0, messages, 0, HEADER_LENGTH);
		System.arraycopy(payload, 0, messages, HEADER_LENGTH, payload.length);
		
		logger.info("Response Message:" + msg);
		logger.info("Response Message Hex:" + Bytes.bytes2hex(messages));
		return messages;
	}

	/**
	 * 
	 * @param header
	 *            头信息
	 * @return 二进制
	 */
	private byte[] encodeHeader(Header header) {

		checkPayload(header.getLength());

		byte[] encodes = new byte[HEADER_LENGTH];

		byte head = DEFAULT_VER;

		head = (byte) (head | (header.getVer() & MASK_VER));

		header.setSer((byte) (Constants.SERIALIZER_ID & MASK_SERIALIZATION));

		head = (byte) ((header.getSer() << 2) | head);

		if (header.isReq()) {
			head = (byte) (head | MASK_REQUEST);
		}

		encodes[0] = head;
		encodes[1] = header.getCmd();

		byte[] bLength = Bytes.short2bytes(header.getLength());

		encodes[2] = bLength[0];
		encodes[3] = bLength[1];

		return encodes;

	}

	private void checkPayload(short size) {
		if (size >= DEFAULT_PAYLOAD) {
			throw new IllegalArgumentException("payload is too big.");
		}
	}

}
