package com.dist.test9;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2019/4/15.
 * 只读buffer
 */
public class Test3 {

    public static  void  main(String[] ars){
        ByteBuffer byteBuffer=ByteBuffer.allocate(10);

        for(int i=0;i<byteBuffer.capacity();i++){
            byteBuffer.put((byte)i);
        }
        ByteBuffer byteBuffer1=byteBuffer.asReadOnlyBuffer();

        byteBuffer1.put(2,(byte)'k');

    }
}
