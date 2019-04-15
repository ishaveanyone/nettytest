package com.dist.test2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by Administrator on 2019/4/11.
 */
public class ServerHandler extends SimpleChannelInboundHandler<String>{

    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        //实现channel广播
        channelGroup.writeAndFlush("[服务器]——》："+channel.remoteAddress()+"加入\n");
        channelGroup.add(channel);

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
//        channelGroup.remove(channel); 没有必要 netty 自动会从组中移除
        channelGroup.writeAndFlush("[服务器]——》："+channel.remoteAddress()+"离开\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
//        channel.writeAndFlush("[服务器]——》："+"上线\n");
        System.out.println(channel.remoteAddress()+"上线\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
//        channel.writeAndFlush("[服务器]——》："+"下线\n");
        System.out.println(channel.remoteAddress()+"下线\n");

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel channel=channelHandlerContext.channel();
        channelGroup.forEach(ch->{
            if(channel!=ch){
                ch.writeAndFlush(channel.remoteAddress()+" 发送消息："+s+"\n");
            }else{
                ch.writeAndFlush("自己发送消息："+s+"\n");
            }
        });
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
