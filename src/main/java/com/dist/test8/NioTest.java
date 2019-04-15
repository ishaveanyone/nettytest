package com.dist.test8;

import java.nio.IntBuffer;
import java.util.Random;

/**
 * Created by Administrator on 2019/4/15.
 */
public class NioTest {
    public static void main(String[] agrs){
        IntBuffer buffer =IntBuffer.allocate(10);
        for(int i=0;i<5;i++){
            int randNum=new Random().nextInt(10);
            buffer.put(randNum);
        }

        System.out.println("limt:"+buffer.limit());

        buffer.flip();


        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
