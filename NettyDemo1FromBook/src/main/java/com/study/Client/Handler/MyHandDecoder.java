package com.study.Client.Handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyHandDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        String body = new String(data,"UTF-8");
        list.add(body);
    }
}
