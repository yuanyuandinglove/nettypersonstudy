package com.study.XMLNetty.handle;

import com.study.XMLNetty.Address;
import com.study.XMLNetty.HttpXmlRequest;
import com.study.XMLNetty.HttpXmlResponse;
import com.study.XMLNetty.Order;
import com.sun.xml.internal.ws.server.sei.MessageFiller;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.ArrayList;
import java.util.List;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpXmlServerHandler extends SimpleChannelInboundHandler<HttpXmlRequest> {
    @Override
    public void messageReceived(final ChannelHandlerContext ctx, HttpXmlRequest xmlRequest) throws Exception {
        System.out.println("服务端接收到客户端发送过来的数据");
        HttpRequest request = xmlRequest.getRequest();
        Object object =  xmlRequest.getBody();
        HttpXmlRequest httpXmlRequest = (HttpXmlRequest) object;
        Order order =(Order)httpXmlRequest.getBody();
        // 输出解码获得的Order对象
        System.out.println("Http server receive request : " + order);
        dobusiness(order);
        System.out.println(order);
        //向客户端发送应答消息
        System.out.println("服务端向客户端：开始响应数据");
        ChannelFuture future = ctx.writeAndFlush(new HttpXmlResponse(null, order));
        System.out.println("服务端向客户端：响应数据完成");
        if (!isKeepAlive(request) ) {
            future.addListener(new GenericFutureListener<Future<? super Void>>() {
                public void operationComplete(Future future) throws Exception {
                    ctx.close();
                }
            });
        }
    }

    private void dobusiness(Order order) {
        order.getCustomer().setFirstName("狄");
        order.getCustomer().setLastName("仁杰");
        List<String> midNames = new ArrayList<String>();
        midNames.add("李元芳");
        order.getCustomer().setMiddleNames(midNames);
        Address address = order.getBillTo();
        address.setCity("洛阳");
        address.setCountry("大唐");
        address.setState("河南道");
        address.setPostCode("123456");
        order.setBillTo(address);
        order.setShipTo(address);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 在链路没有关闭并且出现异常的时候发送给客户端错误信息
        if (ctx.channel().isActive()) {
            sendError(ctx, INTERNAL_SERVER_ERROR);
        }
    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status,
                Unpooled.copiedBuffer("失败: " + status.toString() + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}