package com.ignacio.tank.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/2 at 17:46
 */
public class Server {

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
                            socketChannel.pipeline().addLast(new TankJoinMsgDecoder());
                            socketChannel.pipeline().addLast(new TankChannelHandler());
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
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}