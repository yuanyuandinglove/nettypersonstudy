package com.study;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {
    public void bind(int port){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b =  new ServerBootstrap();
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChildHandler());
            //绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();

            //等待服务器端口关闭
            f.channel().closeFuture().sync();

        }catch (Exception ex){

        }finally {
           bossGroup.shutdownGracefully();
           workerGroup.shutdownGracefully();
        }
    }
    public static  void main(String args[]){
       new TimeServer().bind(8888);
    }
}
