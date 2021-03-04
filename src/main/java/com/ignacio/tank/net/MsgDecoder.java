package com.ignacio.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.lang.reflect.Constructor;
import java.util.List;



/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/2 at 17:43
 */
public class MsgDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes() < 8){//解决TCP的拆包，粘包问题：Encoder中，消息头（消息的类型信息和长度信息一共是2个int，共8位）
            return;
        }
        //标记reader读到的位置
        byteBuf.markReaderIndex();

        MsgType type = MsgType.values()[byteBuf.readInt()];
        int length = byteBuf.readInt();

        if(byteBuf.readableBytes() < length){
            byteBuf.resetReaderIndex();
            return;
        }

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Msg msg = null;

        /*msg = (Msg)Class.forName("com.ignacio.tank.net." + type.toString() + "Msg").getDeclaredConstructor().newInstance();*/

        switch (type){
            case JoinMsg:
                msg = new TankJoinMsg();
                break;
            case TankStartMovingMsg:
                msg = new TankStartMovingMsg();
                break;
            case TankStopMsg:
                msg = new TankStopMsg();
                break;
            default:
                break;
        }
        msg.parse(bytes);
        list.add(msg);




        /*msg = byteBuf.readInt();
        msg.y = byteBuf.readInt();
        msg.dir = Dir.values()[byteBuf.readInt()];
        msg.group = Group.values()[byteBuf.readInt()];
        msg.id = new UUID(byteBuf.readLong(),byteBuf.readLong());
        msg.moving = byteBuf.readBoolean();

        list.add(msg);*/
    }
}
