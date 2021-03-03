package com.ignacio.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/2 at 17:43
 */
public class TankJoinMsgEncoder extends MessageToByteEncoder<TankJoinMsg> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, TankJoinMsg tankJoinMsg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(tankJoinMsg.toBytes());
    }
}
