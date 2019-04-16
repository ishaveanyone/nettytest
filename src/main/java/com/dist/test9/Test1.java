package com.dist.test9;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2019/4/15.
 * buffer 类型一致
 */
public class Test1 {

    public static  void  main(String[] ars){
        ByteBuffer byteBuffer=ByteBuffer.allocate(10);
        byteBuffer.putInt(10);
        byteBuffer.flip();
        System.out.println(byteBuffer.getInt());
    }
}
