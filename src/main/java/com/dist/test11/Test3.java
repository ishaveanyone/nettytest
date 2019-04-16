package com.dist.test11;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 * NIO中Scattering与Gathering
 *
 * Created by Administrator on 2019/4/15.
 */
public class Test3 {
    public static void main(String[] args)
            throws IOException {

        ServerSocketChannel serverSocketChannel =
                ServerSocketChannel.open();
        InetSocketAddress address=new InetSocketAddress(8080);
        serverSocketChannel.socket().bind(address);
        int messageLenth=2+3+4;
        ByteBuffer[] byteBuffer=new ByteBuffer[3];
        byteBuffer[0]=ByteBuffer.allocate(2);
        byteBuffer[1]=ByteBuffer.allocate(3);
        byteBuffer[2]=ByteBuffer.allocate(4);
        SocketChannel socketChannel=serverSocketChannel.accept();

        while(true){
            int byteRead=0;
            while(byteRead<messageLenth){
                long r=socketChannel.read(byteBuffer);

            }
        }
    }
}
