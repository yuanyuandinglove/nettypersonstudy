package com.study.XMLNetty.handle;


import com.study.XMLNetty.HttpXmlRequest;
import com.study.XMLNetty.HttpXmlResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponse;

import java.util.List;

public class HttpXmlResponseDecoder extends AbstractHttpXmlDecoder<DefaultFullHttpResponse> {
    protected HttpXmlResponseDecoder(Class<?> clazz) {
        super(clazz);
    }

    public HttpXmlResponseDecoder(Class<?> clazz, boolean isPrint) {
        super(clazz, isPrint);
    }
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,  DefaultFullHttpResponse msg, List<Object> list) throws Exception {

        Object result = decode0(channelHandlerContext,msg.content());

        HttpXmlResponse httpXmlResponse  =new HttpXmlResponse(msg,result);
        list.add(httpXmlResponse);
    }
    }


