package com.dist.test8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2019/4/15.
 */
public class NioTest1 {

    public static void main(String[] args) throws IOException {


        FileInputStream inputStream=new FileInputStream("a.txt");
        FileChannel channel= inputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(550);
        channel.read(byteBuffer);

        byteBuffer.flip();
        while (byteBuffer.remaining()>0){
            System.out.println("char :"+(char)byteBuffer.get());
        }
        inputStream.close();

    }
}
