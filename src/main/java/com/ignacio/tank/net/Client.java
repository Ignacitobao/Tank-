package com.ignacio.tank.net;

import com.ignacio.tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/2 at 17:04
 */
public class Client {
    private Channel channel = null;

    private Client(){}

    private static class ClientInstanceHolder{
        private static final Client INSTANCE = new Client();
    }


    public static Client getInstance(){
        return ClientInstanceHolder.INSTANCE;
    }

    public void connect(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        try {
            ChannelFuture f = b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer())
                    .connect("localhost", 8889);

                    f.sync();
                    f.addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            if(!f.isSuccess()){
                                System.out.println("Failed to connect");
                            }
                            System.out.println("Connected to server");
                            channel = channelFuture.channel();
                        }
                    });
                    //将异步改为同步，直到执行close()方法时才返回（程序结束）
                    f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {group.shutdownGracefully();
        }
    }
    public void send(Msg msg) {
        channel.writeAndFlush(msg);
    }
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast(new MsgEncoder())
                .addLast(new MsgDecoder())
                .addLast(new ClientChannelHandler());
    }
}

class ClientChannelHandler extends SimpleChannelInboundHandler<Msg> {

    @Override
    public void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg) throws Exception {
        System.out.println(msg.toString());
        msg.handle();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(new TankJoinMsg(TankFrame.getInstance().getMytank()));
    }
}
