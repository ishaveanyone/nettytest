package com.dist.test10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2019/4/15.
 * 直接缓冲 堆外创建数据 实现0拷贝的buffer缓冲
 *
 */
public class DirctrotByteBuffer {


    public static  void main(String[] args) throws IOException{

        FileInputStream fileInputStream=new FileInputStream("c.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("f.txt");
        FileChannel inputFileChannel=fileInputStream.getChannel();

        FileChannel outPutChannel=fileOutputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(19);

        //如果是 缓存区没有空间了 那么就会返回0 ，如果是数据已经读完了 那么返回-1
        for(;;){

           byteBuffer.clear();

            int i= inputFileChannel.read(byteBuffer);
            System.out.println(i);
            if(i<0){
                break;
            }

            byteBuffer.flip();
            outPutChannel.write(byteBuffer);
        }


    }


}
