package com.study.NetDemo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class DecodeHandler  extends ByteToMessageDecoder{

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {


        byte [] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        list.add(data.toString());
    }
}
