package com.study.XMLNetty;

import io.netty.handler.codec.http.FullHttpRequest;

public class HttpXmlRequest {
    /**
     * 两个成员变量FullHttpRequest和编码对象Object，用于实现和协议栈之间的解耦
     */
    private FullHttpRequest request;
    private Object body;

    public HttpXmlRequest(FullHttpRequest request, Object body) {
        this.request = request;
        this.body = body;
    }

    public final FullHttpRequest getRequest() {
        return request;
    }

    public final void setRequest(FullHttpRequest request) {
        this.request = request;
    }

    public final Object getBody() {
        return body;
    }

    public final void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpXmlRequest [request=" + request + ", body =" + body + "]";
    }
}
