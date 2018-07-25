package com.study.Client;


import com.study.UserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


public class TimeClientHandler extends ChannelHandlerAdapter {
    private ByteBuf firstmessage;
    int counter = 0 ;
    byte send[];
    private  String s ;
    public TimeClientHandler(String  s) {
      send  = s.getBytes();
      this.s = s;

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

       for (int i = 0 ; i < 1000;i++){
         /*   firstmessage = Unpooled.buffer(send.length);
            firstmessage.writeBytes(send);
            ctx.writeAndFlush(firstmessage);*/
            UserInfo userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setName("yuanyuanding");
            ctx.writeAndFlush(userInfo);
      }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
     /*   ByteBuf buf = (ByteBuf) msg;
        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);
        String body = new String(data, "UTF-8");*/
       /* String body = (String)msg;
         System.out.println(" client receive:" + body+ counter++);*/
         System.out.println(s +""+"client receive messagapckm:"+msg);
       //  ctx.write(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
