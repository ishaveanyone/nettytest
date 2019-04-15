package com.dist.test5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * Created by Administrator on 2019/4/12.
 */
public class ClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                MyDataInfo.MyMessage msg)

            throws Exception {
    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {


        int randInt=new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage=null;
        if(0==randInt){
            myMessage=MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.PresonType)
                    .setPerson(MyDataInfo.Person.newBuilder().setName("战三")
                            .setAge(20).setAddress("北京").build()
                    ).build();
        }else  if(1==randInt){
            myMessage=MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder().setName("小狗")
                            .setAge(20).build()
                    ).build();
        }else {
             myMessage=MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setCat(MyDataInfo.Cat.newBuilder().setName("小猫")
                            .setAge(20).build()
                    ).build();
        }
        ctx.channel().writeAndFlush(myMessage);
    }
}
