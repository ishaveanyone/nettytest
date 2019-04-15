package com.dist.test5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Administrator on 2019/4/12.
 */
public class ServerHandler extends
        SimpleChannelInboundHandler<MyDataInfo.MyMessage>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DataType dataType=msg.getDataType();
        if(dataType==MyDataInfo.MyMessage.DataType.PresonType){
            MyDataInfo.Person person=msg.getPerson();
            System.out.println(person.getName());

        }
        if(dataType==MyDataInfo.MyMessage.DataType.DogType){
            MyDataInfo.Dog dog=msg.getDog();
            System.out.println(dog.getName());

        }
        if(dataType==MyDataInfo.MyMessage.DataType.CatType){
            MyDataInfo.Cat cat=msg.getCat();
            System.out.println(cat.getName());

        }

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务器：连接进入");
    }
}
