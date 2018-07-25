package com.study.XMLNetty;

import io.netty.handler.codec.http.FullHttpResponse;

public class HttpXmlResponse {
    private FullHttpResponse fullHttpResponse;
    //业务需要发送的应答POJO对象
    private Object result;

    public HttpXmlResponse(FullHttpResponse fullHttpResponse, Object result) {
        this.fullHttpResponse = fullHttpResponse;
        this.result = result;
    }

    public HttpXmlResponse() {
    }

    @Override
    public String toString() {
        return "HttpXmlResponse{" +
                "fullHttpResponse=" + fullHttpResponse +
                ", result=" + result +
                '}';
    }

    public FullHttpResponse getFullHttpResponse() {
        return fullHttpResponse;
    }

    public void setFullHttpResponse(FullHttpResponse fullHttpResponse) {
        this.fullHttpResponse = fullHttpResponse;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
