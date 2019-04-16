package com.dist.test9;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2019/4/15.
 * 共享buffer
 */
public class Test2 {

    public static  void  main(String[] ars){
       ByteBuffer byteBuffer=ByteBuffer.allocate(100);
       for(int i=0;i<byteBuffer.capacity();i++){
           byteBuffer.put((byte)i);
       }

       byteBuffer.position(2);
       byteBuffer.limit(6);
       ByteBuffer newByteBuffer=byteBuffer.slice();
        for(int i=0;i<newByteBuffer.capacity();i++){
            byte b=newByteBuffer.get(i);
            System.out.println(b);
            newByteBuffer.put(i,(byte)(b*2));

        }
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());

        System.out.println("limit:"+byteBuffer.limit());
        System.out.println("position:"+byteBuffer.position());
//        byteBuffer.flip();
        System.out.println("limit:"+byteBuffer.limit());
        System.out.println("position:"+byteBuffer.position());

        while (byteBuffer.hasRemaining()){
           System.out.println(byteBuffer.get());
        }
    }
}
