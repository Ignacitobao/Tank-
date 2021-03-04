package com.ignacio.tank.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/2 at 17:46
 */
public class Server {
    //装clients的容器
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public void start(){
        EventLoopGroup bossgroup = new NioEventLoopGroup(1);
        EventLoopGroup workergroup = new NioEventLoopGroup(5);

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            ChannelFuture f = bootstrap
                    .group(bossgroup, workergroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new MsgEncoder())
                                    .addLast(new MsgDecoder())
                                    .addLast(new TankChannelHandler());
                        }
                    })
                    .bind(8889);
            f.sync();
            System.out.println("Server Started");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally { workergroup.shutdownGracefully();
        bossgroup.shutdownGracefully();
        }
    }


}

class TankChannelHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        ServerFrame.INSTANCE.updateClientMsg(msg.toString());
        //Server将接收到的参数直接转发给所有clients
        Server.clients.writeAndFlush(msg);
    }
}