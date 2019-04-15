package com.dist.test8;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2019/4/15.
 */
public class NioTest3 {

    public  static  void main(String[] agrs) throws IOException {
        FileInputStream fileInputStream=new FileInputStream("c.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("d.txt");
        FileChannel inputFileChannel=fileInputStream.getChannel();

        FileChannel outPutChannel=fileOutputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(19);


        //如果是 缓存区没有空间了 那么就会返回0 ，如果是数据已经读完了 那么返回-1
        for(;;){

//           byteBuffer.clear();

           int i= inputFileChannel.read(byteBuffer);
           System.out.println(i);
           if(i<0){
               break;
           }

           byteBuffer.flip();
           outPutChannel.write(byteBuffer);
//           byteBuffer.rewind();
        }



    }
}
