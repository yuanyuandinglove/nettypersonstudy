package com.study;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


public class Clinet {
    public  static  void  main(String args[])throws  InterruptedException{
        String s ="111110101010101000000000000000000000000001101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

        byte [] di = new byte[s.length()];
       char []chars =  s.toCharArray();
        for (int i = 0 ; i < chars.length ; i ++){
            String m = String.valueOf(chars[i]);
            di[i] =(byte)(int)Integer.valueOf(m);
       }
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new ClinetHandler());
            }
        });
        ChannelFuture future = bootstrap.connect("192.168.30.89",31555);
       // future.channel().writeAndFlush(Unpooled.copiedBuffer("99".getBytes()));
        future.channel().writeAndFlush(Unpooled.copiedBuffer(di));
        future.channel().closeFuture().sync();
        workerGroup.shutdownGracefully();
    }
}
