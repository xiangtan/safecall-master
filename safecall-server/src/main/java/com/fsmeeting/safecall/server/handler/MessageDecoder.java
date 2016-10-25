/*
 * 文件名：MessageDecode.java
 * 版权：Copyright by www.fsmeeting.com
 * 描述：
 * 修改人：liuyc
 * 修改时间：2016年10月12日
 * 修改内容：
 */

package com.fsmeeting.safecall.server.handler;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmeeting.safecall.beans.common.Header;
import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.utils.Bytes;
import com.fsmeeting.safecall.utils.SerializeUtils;

/**
 * 
 * <pre>
 * 消息解码：
 * version(0-1)+Serialization id(2-6)+Req/Res(7)+cmd(8-15)+data+length(16-31)+data(32~)
 * </pre>
 * 
 * @author yicai.liu<moon>
 * @version 2016年10月17日
 * @see MessageDecoder
 * @since
 */
public class MessageDecoder extends FrameDecoder {

	private static final Logger logger = LoggerFactory.getLogger(MessageDecoder.class);

	protected static final int HEADER_LENGTH = 4;
	public static final int DEFAULT_PAYLOAD = 1 << 10 << 5; // 32K

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {

		logger.info("LocalAddress:" + channel.getLocalAddress());
		if (buffer.readableBytes() < HEADER_LENGTH) {
			logger.info("NEED_MORE_INPUT");
			return null;
		}
		buffer.markReaderIndex();
		Header header = new Header();
		byte[] hData = new byte[HEADER_LENGTH];
		buffer.readBytes(hData, 0, HEADER_LENGTH);

		header.decode(hData);

		short totalBodySize = header.getLength();
		if (buffer.readableBytes() < totalBodySize) {
			buffer.resetReaderIndex();
			logger.info("NEED_MORE_INPUT");
			return null;
		}

		byte[] body = new byte[totalBodySize];

		buffer.readBytes(body);
		Message message = new Message();

		if (0 != body.length) {
			logger.info("Request Message Body Hex:" + Bytes.bytes2hex(body));
			Object data = SerializeUtils.deserialize(body);
			message.setData(data);
		}

		message.setHeader(header);

		logger.info("Request Message:" + message);

		return message;
	}

	protected boolean validateHeader(byte[] header) {
		return true;
	}

	protected static void checkPayload(Channel channel, long size) throws IOException {
		if (size > DEFAULT_PAYLOAD) {
			IOException e = new IOException(
					"Data length too large: " + size + ", max payload: " + DEFAULT_PAYLOAD + ", channel: " + channel);
			logger.error("over-length:", e);
			throw e;
		}
	}

}
