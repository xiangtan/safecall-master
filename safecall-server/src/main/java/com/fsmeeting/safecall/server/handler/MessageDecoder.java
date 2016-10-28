package com.fsmeeting.safecall.server.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.beans.common.Header;
import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.serialization.SerializerFacotry;
import com.fsmeeting.safecall.utils.Bytes;

/**
 * <pre>
 * 消息解码：
 * version(0-1)+Serialization id(2-6)+Req/Res(7)+cmd(8-15)+dataLength(16-31)+data(32~)
 * </pre>
 * 
 * @author yicai.liu<moon>
 *
 */
public class MessageDecoder extends FrameDecoder {

	private static final Logger logger = LoggerFactory.getLogger(MessageDecoder.class);

	// 消息头长度
	protected static final int HEADER_LENGTH = 4;

	// 版本的掩码位
	private static final byte MASK_VER = (byte) 0x03;

	// 序列位，默认0000 0100 (可动态扩展)
	private static final byte MASK_SERIALIZATION = (byte) 0x1f;

	// 序列位，默认1000 0000
	private static final byte MASK_REQUEST = (byte) 0x80;

	// 数据负载，32K
	public static final int DEFAULT_PAYLOAD = 1 << 10 << 5;

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {

		// 获取报文头
		logger.info("LocalAddress:" + channel.getLocalAddress());
		if (buffer.readableBytes() < HEADER_LENGTH) {
			logger.info("NEED_MORE_INPUT");
			return null;
		}
		buffer.markReaderIndex();
		Header header = new Header();
		byte[] hData = new byte[HEADER_LENGTH];
		buffer.readBytes(hData, 0, HEADER_LENGTH);

		// 校验报文头
		if (!verifyHeader(hData)) {
			logger.error("报文头信息不合法！");
			buffer.resetReaderIndex();
			return null;
		}

		// 报文头解码
		decodeHeader(header, hData);

		// 报文体解码
		short totalBodySize = header.getLength();
		if (buffer.readableBytes() < totalBodySize) {
			buffer.resetReaderIndex();
			logger.info("NEED_MORE_INPUT");
			return null;
		}

		byte[] body = new byte[totalBodySize];

		buffer.readBytes(body);
		Message message = new Message();
		message.setHeader(header);

		if (0 != body.length) {
			// 报文体 负载检测
			checkPayload(body.length);
			logger.info("Request Message Body Hex:" + Bytes.bytes2hex(body));
			Object data = SerializerFacotry.create(header.getSer()).deserialize(body);
			message.setData(data);
		}

		logger.info("Request Message:" + message);

		return message;
	}

	/**
	 * 解析报文头
	 * 
	 * @param to
	 *            头信息
	 * @param from
	 *            数据来源
	 */
	private void decodeHeader(Header to, byte[] from) {
		if (from == null | from.length != HEADER_LENGTH) {
			throw new IllegalArgumentException("data illegal");
		}

		byte head = from[0];

		to.setVer((byte) (head & MASK_VER));
		to.setSer((byte) ((head >> 2) & MASK_SERIALIZATION));
		byte request = (byte) (head & MASK_REQUEST);

		if (request == 0) {
			to.setReq(false);
		}

		to.setCmd(from[1]);

		byte[] bLength = new byte[2];
		bLength[0] = from[2];
		bLength[1] = from[3];

		to.setLength(Bytes.bytes2short(bLength));
	}

	/**
	 * 校验头数据的合法性
	 * 
	 * @param header
	 * @return
	 */
	private boolean verifyHeader(byte[] header) {
		return true;
	}

	/**
	 * 数据负载检测
	 * 
	 * @param size
	 */
	private void checkPayload(int size) {
		if (size >= DEFAULT_PAYLOAD) {
			throw new IllegalArgumentException("payload is too big.");
		}
	}
}
