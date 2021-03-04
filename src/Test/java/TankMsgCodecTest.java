import com.ignacio.tank.Dir;
import com.ignacio.tank.Group;
import com.ignacio.tank.net.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/2 at 16:04
 */


public class TankMsgCodecTest {

    @Test
    public void testTankMsgEncoder(){
        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5,10,Dir.LEFT,Group.GOOD,id,false);
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgEncoder());
        channel.writeOutbound(msg);

        ByteBuf buf = (ByteBuf) channel.readOutbound();

        int x = buf.readInt();
        int y = buf.readInt();
        Dir dir = Dir.values()[buf.readInt()];
        Group group = Group.values()[buf.readInt()];
        UUID uuid = new UUID(buf.readLong(),buf.readLong());
        boolean moving = buf.readBoolean();

        Assert.assertEquals(5,x);
        Assert.assertEquals(10,y);
        Assert.assertEquals(Dir.LEFT,dir);
        Assert.assertEquals(Group.GOOD,group);
        Assert.assertEquals(id,uuid);
        Assert.assertEquals(false,moving);
    }

    @Test
    public void testTankMsgDecoder(){
        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5,10,Dir.LEFT,Group.GOOD,id,false);
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new MsgDecoder());

        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(msg.toBytes());

        channel.writeInbound(buf.duplicate());

        TankJoinMsg msg1 = (TankJoinMsg) channel.readInbound();

        Assert.assertEquals(5,msg1.getX());
        Assert.assertEquals(10,msg1.getY());
        Assert.assertEquals(Dir.LEFT,msg1.getDir());
        Assert.assertEquals(Group.GOOD,msg1.getGroup());
        Assert.assertEquals(id,msg1.getId());
        Assert.assertEquals(false,msg1.isMoving());


    }

    @Test
    public void testEncoder(){
        EmbeddedChannel channel = new EmbeddedChannel();
        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5,10,Dir.LEFT,Group.GOOD,id,false);

        channel.pipeline().addLast(new MsgEncoder());
        channel.writeOutbound(msg);

        ByteBuf byteBuf = (ByteBuf)channel.readOutbound();
        MsgType msgType = MsgType.values()[byteBuf.readInt()];
        //验证MsgType
        Assert.assertEquals(MsgType.JoinMsg,msgType);
        //验证Byte[]的长度
        int length = byteBuf.readInt();
        Assert.assertEquals(33,length);

        int x = byteBuf.readInt();
        int y = byteBuf.readInt();
        Dir dir = Dir.values()[byteBuf.readInt()];
        Group group = Group.values()[byteBuf.readInt()];
        UUID uuid = new UUID(byteBuf.readLong(),byteBuf.readLong());
        boolean moving = byteBuf.readBoolean();

        Assert.assertEquals(5,x);
        Assert.assertEquals(10,y);
        Assert.assertEquals(Dir.LEFT,dir);
        Assert.assertEquals(Group.GOOD,group);
        Assert.assertEquals(id,uuid);
        Assert.assertEquals(false,moving);
    }

    @Test// for tankjoinmsg
    public void testDecoder(){
        EmbeddedChannel channel = new EmbeddedChannel();
        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(10,10,Dir.LEFT,Group.GOOD,id,false);
        //System.out.println(msg);
        channel.pipeline().addLast(new MsgDecoder());
        ByteBuf byteBuf = Unpooled.buffer();

        byteBuf.writeInt(MsgType.JoinMsg.ordinal());
        byte[] bytes = msg.toBytes();
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);


        channel.writeInbound(byteBuf.duplicate());

        TankJoinMsg msg1 = (TankJoinMsg) channel.readInbound();

        //System.out.println(msg1.toString());
        Assert.assertEquals(10,msg1.getX());
        Assert.assertEquals(10,msg1.getY());
        Assert.assertEquals(Dir.LEFT,msg1.getDir());
        Assert.assertEquals(Group.GOOD,msg1.getGroup());
        Assert.assertEquals(id,msg1.getId());
        Assert.assertEquals(false,msg1.isMoving());

    }


}
