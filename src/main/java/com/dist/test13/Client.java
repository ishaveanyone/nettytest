package com.dist.test13;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2019/4/16.
 */
public class Client {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector=Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress(8080));
        while(true){
            selector.select();
            Set<SelectionKey> selectionKeys=selector.selectedKeys();
            for(SelectionKey selectionKey:selectionKeys){
                if(selectionKey.isConnectable()){
                    SocketChannel client=(SocketChannel) selectionKey.channel();
                    client.register(selector,SelectionKey.OP_READ);
                    if(client.isConnectionPending()){
                        client.finishConnect();
                        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                        byteBuffer.put((LocalDateTime.now()+"连接成功").getBytes());
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        ExecutorService executorService= Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                        executorService.submit(() -> {
                           while(true){
                               byteBuffer.clear();
                               InputStreamReader in=new InputStreamReader(System.in);
                               BufferedReader br=new BufferedReader(in);
                               String message=br.readLine();
                               byteBuffer.put(message.getBytes());
                               byteBuffer.flip();
                               socketChannel.write(byteBuffer);
                           }
                        });

                    }
                }else if(selectionKey.isReadable()){
                    SocketChannel client=(SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer =ByteBuffer.allocate(1024);
                    int count =client.read(byteBuffer);
                    byteBuffer.flip();

                    if(count>0){
                        String reciverMessage =new String(byteBuffer.array(),0,count);
                        System.out.println(reciverMessage);
                    }

                }
                selectionKeys.remove(selectionKey);
            }

        }


    }


}
