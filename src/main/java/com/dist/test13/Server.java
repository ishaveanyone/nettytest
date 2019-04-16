package com.dist.test13;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by Administrator on 2019/4/15.
 */
public class Server {

    private static Map<String,SocketChannel> clientMap=new HashMap<>();

    public static void main(String[] args) throws Exception{

        ServerSocketChannel serverSocketChannel= ServerSocketChannel.open();
        //一定需要配置成false
        serverSocketChannel.configureBlocking(false);
        //获取socket
        ServerSocket serverSocket=serverSocketChannel.socket();

        serverSocket.bind(new InetSocketAddress(8080));

        Selector selector= Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        Charset charset=Charset.forName("utf-8");
        while(true){
            //阻塞·返回关注事件的数量
            selector.select();
            //一旦返回可以获取到key的集合
            Set<SelectionKey> selectionKeys=selector.selectedKeys();
//
//            Iterator<SelectionKey> iter=selectionKeys.iterator();

            selectionKeys.forEach(selectionKey -> {
                final  SocketChannel socketChannel;

                //判断返回的key类型
                if(selectionKey.isAcceptable()){
                    //接收到新请求
                    ServerSocketChannel serverSocketChannel1=(ServerSocketChannel)selectionKey.channel();

                    try {
                        socketChannel = serverSocketChannel1.accept();
                        socketChannel.configureBlocking(false);
                        //切换关注的事件
                        socketChannel.register(selector,SelectionKey.OP_READ);
                        String s =""+ UUID.randomUUID();
                        clientMap.put(s,socketChannel);
                        socketChannel.write(ByteBuffer.wrap("connect sessusss".getBytes()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else if(selectionKey.isReadable()){
                    socketChannel=(SocketChannel)selectionKey.channel();
                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                    String message=null;
                    try {
                        int count =socketChannel.read(byteBuffer);
                        if(count>0){
                            byteBuffer.flip();
                            message = String.valueOf(charset.decode(byteBuffer).array());
                            System.out.println(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String sender=null;
                    //向所有的客户端发送改消息
                    for(Map.Entry<String,SocketChannel> entry:clientMap.entrySet()){
                        if(socketChannel==entry.getValue()){
                            sender=entry.getKey();
                            break;
                        }
                    }
                    for(Map.Entry<String,SocketChannel> entry:clientMap.entrySet()){
                        ByteBuffer byteBuffer1=ByteBuffer.allocate(1024);
                        byteBuffer1.put((sender+":"+message).getBytes());
                        byteBuffer1.flip();
                        try {
                            entry.getValue().write(byteBuffer1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            });
            selectionKeys.clear();
        }






    }

}
