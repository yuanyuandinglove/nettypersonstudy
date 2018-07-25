package com.study;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimerServerHandler extends ChannelHandlerAdapter {
    int couter = 0 ;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

   /*    ByteBuf buf = (ByteBuf)msg;
        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);
        String body = new String(data,"UTF-8");*/
       /* String  body = (String)msg;
        System.out.println( couter++ +"：server receive:"+ body);
        String return1 ="回复了消息的///";
        ByteBuf resp = Unpooled.copiedBuffer(return1.getBytes());*/
       System.out.println("server receive message:"+msg);
       ctx.write(msg);

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常："+cause.getCause());
      ctx.close();
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
     ctx.flush();
    }
}
