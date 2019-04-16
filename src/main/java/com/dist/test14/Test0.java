package com.dist.test14;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * java中的编解码
 * Created by Administrator on 2019/4/16.
 */
public class Test0 {
    public static void main(String[] args) throws Exception {

        String inputFile="c.txt";
        String outputFile="g.txt";

        RandomAccessFile randomAccessFile=new RandomAccessFile(inputFile,"r");
        RandomAccessFile randomAccessFile1=new RandomAccessFile(outputFile,"rw");
        long inputLent=new File(inputFile).length();



    }


}
