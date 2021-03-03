import com.ignacio.tank.Dir;
import com.ignacio.tank.Group;
import com.ignacio.tank.net.TankJoinMsg;
import com.ignacio.tank.net.TankJoinMsgDecoder;
import com.ignacio.tank.net.TankJoinMsgEncoder;
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
        channel.pipeline().addLast(new TankJoinMsgEncoder());
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
        channel.pipeline().addLast(new TankJoinMsgDecoder());

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
}
