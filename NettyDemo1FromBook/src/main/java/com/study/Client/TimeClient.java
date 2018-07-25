package com.study.Client;

import com.study.Client.Handler.MsgpackDecoder;
import com.study.Client.Handler.MsgpackEncoder;
import com.study.Client.Handler.MyHandDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;


public class TimeClient {

    public void connect(int port, String host) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                     /*   ByteBuf byteBuf = Unpooled.copiedBuffer("///".getBytes());
                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,byteBuf));//分割符
                        socketChannel.pipeline().addLast(new MyHandDecoder());
                        socketChannel.pipeline().addLast(new TimeClientHandler("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111///"));*/
                     socketChannel.pipeline().addLast(new LengthFieldPrepender(2));
                     socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                     socketChannel.pipeline().addLast(new MsgpackDecoder());
                     socketChannel.pipeline().addLast(new MsgpackEncoder());
                     socketChannel.pipeline().addLast(new TimeClientHandler("111"));
                    }
                }).option(ChannelOption.TCP_NODELAY, true);

        try {
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception ex) {

        } finally {
            group.shutdownGracefully();

        }
    }

    public  static  void main(String args[]){
        new TimeClient().connect(8888,"192.168.30.56");
    }
}
