package com.study.XMLNetty.handle;

import com.study.XMLNetty.*;
import com.thoughtworks.xstream.XStream;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.List;

public abstract class AbstractHttpXmlEncoder <T> extends MessageToMessageEncoder<T>{
    IBindingFactory factory = null;
    StringWriter writer = null;
    final  static  String CHARSET_NAME = "UTF-8";
    final static Charset UTF8 = Charset.forName(CHARSET_NAME);

    private ByteBuf encodeBuf;
   /* @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, T t, List<Object> list) throws Exception {
        factory = BindingDirectory.getFactory(t.getClass());
        HttpXmlRequest httpXmlRequest =(HttpXmlRequest)t;
        writer = new StringWriter();
        IMarshallingContext ctx = factory.createMarshallingContext();
        ctx.setIndent(2);
        ctx.marshalDocument(httpXmlRequest.getBody(),CHARSET_NAME,null,writer);
        String xmlStr = writer.toString();
        writer.close();
        writer = null;
        encodeBuf = Unpooled.copiedBuffer(xmlStr,UTF8);
        list.add(encodeBuf);

    }

    protected ByteBuf getEncodeBuf(){
        return encodeBuf;
    }
    */

    public ByteBuf encode0(ChannelHandlerContext context,Object body) {
        XStream xStream = new XStream();
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.processAnnotations(new Class[]{
                Order.class, Customer.class, Shipping.class, Address.class
        });
            String xml =  xStream.toXML(body);
            ByteBuf encodeBuf = Unpooled.copiedBuffer(xml,UTF8);
            return  encodeBuf;
    }
}
