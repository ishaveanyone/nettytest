package com.dist.xpp.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Created by Administrator on 2019/4/12.
 */
public class TestProto {

    public static void main(String[] agrs) throws InvalidProtocolBufferException {

        DataInfo.Student student= DataInfo.Student.newBuilder().setName("zs").
                setAge(20).setAddress("北京").build();
        byte[] student2ByteArray=student.toByteArray();


        DataInfo.Student student1=DataInfo.Student.parseFrom(student2ByteArray);



        System.out.println(student1.getAge());


    }
}
