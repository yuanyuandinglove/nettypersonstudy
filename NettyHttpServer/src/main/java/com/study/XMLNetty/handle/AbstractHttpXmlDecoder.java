package com.study.XMLNetty.handle;


import com.study.XMLNetty.Address;
import com.study.XMLNetty.Customer;
import com.study.XMLNetty.Order;
import com.study.XMLNetty.Shipping;
import com.thoughtworks.xstream.XStream;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;


import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.List;



public abstract  class AbstractHttpXmlDecoder<T> extends MessageToMessageDecoder<T> {
   private IBindingFactory factory;
   private StringReader reader;
    private  Class<?> clazz;
    private  boolean isPrint;
    private final static  String CHARSET_NAME ="UTF-8";
    private final  static Charset UTF8 = Charset.forName(CHARSET_NAME);
    Object result ;

    protected  AbstractHttpXmlDecoder(Class<?> clazz){

       this(clazz,false);
    }


    protected AbstractHttpXmlDecoder(Class<?> clazz,boolean isPrint){
        this.clazz = clazz;
        this.isPrint = isPrint;
    }

   /* @Override*/
    /*protected void decode(ChannelHandlerContext channelHandlerContext, T t, List<Object> list) throws Exception {
       factory = BindingDirectory.getFactory(clazz);
        ByteBuf buf = (ByteBuf)t;
        String content = buf.toString();
        if (isPrint){
            System.out.println("the body is:"+ content);

        }
        reader = new StringReader(content);
        IUnmarshallingContext utx = factory.createUnmarshallingContext();
        result = utx.unmarshalDocument(reader);
        reader.close();
        reader = null;

    }
    public Object getResult(){
        return  result;
    }*/

   protected Object decode0(ChannelHandlerContext context,ByteBuf buf){
       String content =buf.toString(UTF8);
       if (isPrint){
           System.out.println("the body is"+ content);
       }
        XStream xs = new XStream();
       xs.processAnnotations(new Class[]{
               Order.class, Customer.class, Shipping.class, Address.class
       });
       Object result = xs.fromXML(content);
       return  result;
    }
}
