package com.study;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClinetHandler extends ChannelHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       try{

           ByteBuf buf = (ByteBuf)msg;
           byte [] data = new byte[buf.readableBytes()];
           buf.readBytes(data);
           System.out.println("client:" +new String(data).toString());
       }catch (Exception ex){

       }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      cause.printStackTrace();
      ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

    }
}
