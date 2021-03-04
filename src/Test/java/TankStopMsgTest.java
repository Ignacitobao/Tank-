import com.ignacio.tank.net.MsgDecoder;
import com.ignacio.tank.net.MsgEncoder;
import com.ignacio.tank.net.MsgType;
import com.ignacio.tank.net.TankStopMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/4 at 17:33
 */
public class TankStopMsgTest {

    @Test
    public void testEncoder(){
        EmbeddedChannel channel = new EmbeddedChannel();
        UUID id = UUID.randomUUID();

        TankStopMsg msg = new TankStopMsg(5,10,id);

        channel.pipeline().addLast(new MsgEncoder());

        channel.writeOutbound(msg);

        ByteBuf buf = (ByteBuf)channel.readOutbound();

        MsgType type = MsgType.values()[buf.readInt()];

        Assert.assertEquals(MsgType.TankStopMsg,type);

        int length = buf.readInt();
        Assert.assertEquals(24,length);

        int x = buf.readInt();
        int y = buf.readInt();
        UUID uuid = new UUID(buf.readLong(),buf.readLong());
        Assert.assertEquals(5,x);
        Assert.assertEquals(10,y);
        Assert.assertEquals(id,uuid);

    }


    @Test
    public void testDecoder(){
        EmbeddedChannel channel = new EmbeddedChannel();
        UUID id = UUID.randomUUID();
        TankStopMsg msg = new TankStopMsg(5,10,id);
        channel.pipeline().addLast(new MsgDecoder());
        byte[] bytes = msg.toBytes();
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(MsgType.TankStopMsg.ordinal());
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        channel.writeInbound(buf.duplicate());
        TankStopMsg msg1 = (TankStopMsg) channel.readInbound();
        Assert.assertEquals(5,msg1.getX());
        Assert.assertEquals(10,msg1.getY());
        Assert.assertEquals(id,msg1.getId());

    }
}
