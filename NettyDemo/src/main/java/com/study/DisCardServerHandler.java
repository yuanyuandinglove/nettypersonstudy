package com.study;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class DisCardServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            ByteBuf in = (ByteBuf) msg;
            byte[] data = new byte[in.readableBytes()];
            in.readBytes(data);
            System.out.println("server:" + new String(data, "utf-8").toString());
            ctx.writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));

        } catch (Exception ex) {
            ReferenceCountUtil.release(msg);
        } finally {

        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
