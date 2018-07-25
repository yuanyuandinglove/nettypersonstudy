package com.study;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class Test {

    public  static  void main(String args[]){
        try {
            RandomAccessFile accessFile = new RandomAccessFile("C:\\Users\\dinyy\\Desktop\\test.txt","rw");
            FileChannel inChanner = accessFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);
            StringBuffer stringBuffer  = new StringBuffer();
            int bytesend = inChanner.read(buf);
            while (bytesend != -1){
                buf.flip();

                while (buf.hasRemaining()){
                    System.out.println((char) buf.get());
                    stringBuffer.append((char)buf.get());
                }
                buf.clear();
                bytesend = inChanner.read(buf);
            }
            ByteBuffer buf1 = ByteBuffer.allocate(48);
            buf1.put(stringBuffer.toString().getBytes());
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("192.168.30.80",60158));
            socketChannel.configureBlocking(false);

            buf1.flip();
            while (buf1.hasRemaining()){
                socketChannel.write(buf1);
            }
        }catch (Exception ex){

        }

    }
}
