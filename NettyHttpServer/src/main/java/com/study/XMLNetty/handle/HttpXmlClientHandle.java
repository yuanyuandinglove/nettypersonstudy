package com.study.XMLNetty.handle;

import com.study.XMLNetty.HttpXmlRequest;
import com.study.XMLNetty.HttpXmlResponse;
import com.study.XMLNetty.OrderFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HttpXmlClientHandle extends SimpleChannelInboundHandler<HttpXmlResponse> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 给客户端发送请求消息，HttpXmlRequest包含FullHttpRequest和Order这个了类
        HttpXmlRequest request = new HttpXmlRequest(null, OrderFactory.create(123));
        ctx.writeAndFlush(request);
        System.out.println("客户端：向服务端发送消息完成");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.getMessage();
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpXmlResponse msg) throws Exception {
        System.out.println("接收到服务端的响应");
        System.out.println("The client receive response of http header is : " + msg.getFullHttpResponse().headers().names());
        System.out.println("The client receive response of http body is : " + msg.getResult());
    }
}
