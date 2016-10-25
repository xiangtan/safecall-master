
package com.fsmeeting.safecall.server.handler;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.fsmeeting.safecall.beans.common.Message;
import com.fsmeeting.safecall.utils.Bytes;

/**
 * 
 * 消息编码
 * 
 * <pre>
 * version(0-1)+Serialization id(2-6)+Req/Res(7)+cmd(8-15)+data+length(16-31)+data(32~)
 * </pre>
 * 
 * @author yicai.liu<moon>
 * @version 2016年10月13日
 * @see MessageEncoder
 * @since
 */
public class MessageEncoder extends OneToOneEncoder {
	private static final Logger logger = Logger.getLogger(MessageEncoder.class);

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
		logger.info("Response Message:" + msg);
		byte[] bytes = msg.encode();
		logger.info("Response Message Hex:" + Bytes.bytes2hex(bytes));
		return bytes;

	}

	public static void main(String[] args) {
		byte[] bs = new byte[10];
		bs[0] = 0x01;
		bs[1] = 2;
		System.out.println(bs[0]);
	}
}
