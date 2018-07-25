package com.study;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestServer {

    public  static  void main(String args[]){
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel socketChannel = null;
        ByteBuffer byteBuffer = null;
        try {
             serverSocketChannel = ServerSocketChannel.open();
             serverSocketChannel.socket().bind(new InetSocketAddress(60158));
             serverSocketChannel.configureBlocking(false);

            while (true){
                socketChannel  = serverSocketChannel.accept();

                if (socketChannel != null){
                 byteBuffer  = ByteBuffer.allocate(48);

                    int presend = socketChannel.read(byteBuffer);

                    while (presend  != -1){
                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()){
                            System.out.print((char) byteBuffer.get());

                            byteBuffer.clear();

                            presend = socketChannel.read(byteBuffer);
                        }
                    }

                }
            }
        }catch (Exception ex ){
            System.out.println("ex");

        }finally {
            try {
                serverSocketChannel.close();
                socketChannel.close();
            }catch (Exception ex){

            }

        }

    }
}
