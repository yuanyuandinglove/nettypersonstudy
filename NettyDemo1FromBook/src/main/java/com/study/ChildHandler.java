package com.study;


import com.study.Client.Handler.MsgpackDecoder;
import com.study.Client.Handler.MsgpackEncoder;
import com.study.Client.Handler.MyHandDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;


public class ChildHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

//        ByteBuf byteBuf = Unpooled.copiedBuffer("///".getBytes());
//        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,byteBuf));
      /*  socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(20));//定长
        socketChannel.pipeline().addLast(new MyHandDecoder());*/
        socketChannel.pipeline().addLast(new LengthFieldPrepender(2));
        socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
        socketChannel.pipeline().addLast(new MsgpackEncoder());
        socketChannel.pipeline().addLast(new MsgpackDecoder());
        socketChannel.pipeline().addLast(new TimerServerHandler());

    }
}
