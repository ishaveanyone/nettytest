package com.dist.test8;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2019/4/15.
 */
public class NioTest2 {
    public static void main(String[] args) throws IOException {

        FileOutputStream fileOutputStream=new FileOutputStream("b.txt");
        FileChannel fileChannel=fileOutputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(550);
        byte[] message =" hhhhh iiii".getBytes();
        for(int i=0;i<message.length;i++){
            byteBuffer.put(message[i]);
        }
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
