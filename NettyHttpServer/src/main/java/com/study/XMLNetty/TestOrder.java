package com.study.XMLNetty;

import org.jibx.runtime.*;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class TestOrder {
    private IBindingFactory factory = null;
    private StringWriter writer =null;
    private StringReader reader=null;
    private final  static  String CHARSET_NAME = "UTF-8";

    private  String encode2xml(Order order)throws JiBXException{
        factory = BindingDirectory.getFactory(order.getClass());
        writer = new StringWriter();
        IMarshallingContext mctx = factory.createMarshallingContext();
        mctx.setIndent(2);
        mctx.marshalDocument(order,CHARSET_NAME,null,writer);
        String xmlStr =writer.toString();
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(xmlStr.toString());
        return  xmlStr;

    }
    private  Order decode2Order (String xmlBody)throws  JiBXException{
        reader = new StringReader(xmlBody);
        IUnmarshallingContext uctx =factory.createUnmarshallingContext();
        Order order = (Order)uctx.unmarshalDocument(reader);
        return order;

    }
    public  static  void  main(String []args)throws  JiBXException{
       TestOrder testOrder = new TestOrder();
       Order order = OrderFactory.create(123);
       String body = testOrder.encode2xml(order);
       Order order1 =  testOrder.decode2Order(body);
       System.out.println(order1);

    }
}
