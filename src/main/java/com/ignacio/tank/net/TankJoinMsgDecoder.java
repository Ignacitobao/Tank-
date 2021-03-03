package com.ignacio.tank.net;

import com.ignacio.tank.Dir;
import com.ignacio.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import java.util.UUID;


/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/2 at 17:43
 */
public class TankJoinMsgDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes() < 33){//解决TCP的拆包，粘包问题：一个tankjoinmsg的长度为33个字节
            return;
        }
        TankJoinMsg msg = new TankJoinMsg();
        msg.x = byteBuf.readInt();
        msg.y = byteBuf.readInt();
        msg.dir = Dir.values()[byteBuf.readInt()];
        msg.group = Group.values()[byteBuf.readInt()];
        msg.id = new UUID(byteBuf.readLong(),byteBuf.readLong());
        msg.moving = byteBuf.readBoolean();

        list.add(msg);
    }
}
