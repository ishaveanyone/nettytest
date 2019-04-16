package com.dist.test11;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer
 * Created by Administrator on 2019/4/15.
 */
public class Test1 {

    public static  void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile=new RandomAccessFile("f.txt","rw");
        FileChannel fileChannel=randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);
        mappedByteBuffer.put(0,(byte)'a');
        mappedByteBuffer.put(3,(byte)'b');
        randomAccessFile.close();



    }

}
