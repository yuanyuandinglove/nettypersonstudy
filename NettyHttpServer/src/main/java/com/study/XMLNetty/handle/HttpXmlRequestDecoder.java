package com.study.XMLNetty.handle;

import com.study.XMLNetty.HttpXmlRequest;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

import java.util.List;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpXmlRequestDecoder extends AbstractHttpXmlDecoder<FullHttpRequest> {
    public HttpXmlRequestDecoder(Class<?> clazz) {
        this(clazz, false);
    }

    public HttpXmlRequestDecoder(Class<?> clazz, boolean isPrint) {
        super(clazz, isPrint);
    }

    @Override
        protected void decode(ChannelHandlerContext arg0, FullHttpRequest arg1, List<Object> arg2) throws Exception {
            // 对HTTP请求消息本身的解码结果进行判断，如果已经解码失败，再对消息体进行二次解码已经没有意义
            if (!arg1.getDecoderResult().isSuccess()) {
                sendError(arg0, BAD_REQUEST);
                return;
            }
            // 通过HttpsdXmlRequest和反序列化后的Order对象构造HttpXmlRequest实例

        HttpXmlRequest request = new HttpXmlRequest(arg1, decode0(arg0,arg1.content()));


        // 将请求交给下一个解码器处理
        arg2.add(request);
    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status,
                Unpooled.copiedBuffer("Failure: " + status.toString() + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}