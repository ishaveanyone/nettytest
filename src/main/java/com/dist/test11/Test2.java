package com.dist.test11;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 锁 共享锁以及排他锁
 * Created by Administrator on 2019/4/15.
 */
public class Test2 {

    public static  void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile=new RandomAccessFile("f.txt","rw");
        FileChannel fileChannel=randomAccessFile.getChannel();

       FileLock fileCh= fileChannel.lock(3,6,true);
        System.out.println(fileCh.isValid());
        System.out.println(fileCh.isShared());
        fileCh.release();

        randomAccessFile.close();

    }
}
