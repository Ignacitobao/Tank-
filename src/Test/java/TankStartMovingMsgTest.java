import com.ignacio.tank.Dir;
import com.ignacio.tank.net.MsgDecoder;
import com.ignacio.tank.net.MsgEncoder;
import com.ignacio.tank.net.MsgType;
import com.ignacio.tank.net.TankStartMovingMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/4 at 15:42
 */
public class TankStartMovingMsgTest {

    @Test
    public void testEncoder(){
        EmbeddedChannel channel = new EmbeddedChannel();
        UUID id = UUID.randomUUID();
        TankStartMovingMsg msg = new TankStartMovingMsg(5,10, Dir.LEFT,id);

        channel.pipeline().addLast(new MsgEncoder());

        channel.writeOutbound(msg);

        ByteBuf buf = (ByteBuf)channel.readOutbound();
        MsgType type = MsgType.values()[buf.readInt()];
        Assert.assertEquals(MsgType.TankStartMovingMsg, type);
        int length = buf.readInt();
        byte[] bytes = new byte[length];
        Assert.assertEquals(28,length);

        int x = buf.readInt();
        int y = buf.readInt();
        Dir dir = Dir.values()[buf.readInt()];
        UUID uuid = new UUID(buf.readLong(),buf.readLong());

        Assert.assertEquals(5,x);
        Assert.assertEquals(10,y);
        Assert.assertEquals(Dir.LEFT,dir);
        Assert.assertEquals(id,uuid);
    }

    @Test
    public void testDecoder(){
        EmbeddedChannel channel = new EmbeddedChannel();
        UUID id = UUID.randomUUID();
        TankStartMovingMsg msg = new TankStartMovingMsg(5,10, Dir.LEFT,id);
        channel.pipeline().addLast(new MsgDecoder());

        byte[] bytes = msg.toBytes();

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(msg.getMsgType().ordinal());
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        channel.writeInbound(buf.duplicate());

        TankStartMovingMsg msg1 = (TankStartMovingMsg)channel.readInbound();
        System.out.println(msg1.toString());

        Assert.assertEquals(5,msg1.getX());
        Assert.assertEquals(10,msg1.getY());
        Assert.assertEquals(Dir.LEFT,msg1.getDir());
        Assert.assertEquals(id,msg1.getId());


    }
}
