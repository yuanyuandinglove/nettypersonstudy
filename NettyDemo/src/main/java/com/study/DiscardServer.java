package com.study;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {

    private  int port ;
    public DiscardServer(int port ){
        this.port =port;
    }

    public void run() throws  Exception {


                EventLoopGroup bossGroup = new NioEventLoopGroup();
                EventLoopGroup workerGroup = new NioEventLoopGroup();

                try
                {
                    ServerBootstrap b = new ServerBootstrap();
                    b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                            childHandler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel socketChannel) throws Exception {
                                    socketChannel.pipeline().addLast(new DisCardServerHandler());
                                }
                            });

                    ChannelFuture f = b.bind(port).sync();
                    f.channel().closeFuture().sync();
                    workerGroup.shutdownGracefully();
                    bossGroup.shutdownGracefully().sync();
                }catch(
                        Exception ex)
                {
                }
            }





    public static  void main(String args[]){
        int port = 8090;
        try {
                new DiscardServer(port).run();

        }catch (Exception e){

        }

    }
}