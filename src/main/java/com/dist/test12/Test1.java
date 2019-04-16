package com.dist.test12;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.util.Set;
import java.util.Iterator;

/**
 * MappedByteBuffer
 * Created by Administrator on 2019/4/15.
 */
public class Test1 {

    public static  void main(String[] args) throws IOException {
        int ports[]={2001,2002,2003,2004};

        Selector selector=Selector.open();
        for(int i=0;i<ports.length;i++){
            ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket=serverSocketChannel.socket();
            InetSocketAddress address=new InetSocketAddress(ports[i]);
            serverSocket.bind(address);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口："+ports[i]);
        }
        while(true){
            int number =selector.select();
            System.out.println("number:"+number);
            Set<SelectionKey> selectionKeySet =selector.selectedKeys();

            System.out.println("selectionlkeys:"+selectionKeySet);

            Iterator<SelectionKey> iter=selectionKeySet.iterator();



            while(iter.hasNext()){
                SelectionKey selectionKey=iter.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel serverSocketChannel=(ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel=serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    iter.remove();
                    System.out.println("客户端连接"+socketChannel);
                    socketChannel.write(ByteBuffer.wrap("connect success".getBytes()));

                }else if(selectionKey.isReadable()){
                    SocketChannel socketChannel=(SocketChannel)selectionKey.channel();
                    int index=0;
                    ByteBuffer byteBuffer=ByteBuffer.allocate(500);
                    while(true){
                        byteBuffer.clear();
                        int flag=socketChannel.read(byteBuffer);
                        if(flag<=0){
                            break;
                        }
                        byteBuffer.flip();
//                        打印读取数据
                        while(byteBuffer.hasRemaining()){
                            System.out.println("打印字符:"+(char)byteBuffer.get());
                        }
                    }
                    iter.remove();
                }
            }

        }
    }

}
