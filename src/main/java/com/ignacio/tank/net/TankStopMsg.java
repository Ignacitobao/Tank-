package com.ignacio.tank.net;

import com.ignacio.tank.Tank;
import com.ignacio.tank.TankFrame;
import java.io.*;
import java.util.UUID;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/4 at 16:37
 */
public class TankStopMsg extends Msg {

    int x,y;
    UUID id;

    public TankStopMsg() {
    }

    public TankStopMsg(int x, int y, UUID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public TankStopMsg(Tank tank){
        this.x = tank.getX();
        this.y = tank.getY();
        this.id = tank.getId();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public void handle() {
        if(this.id.equals(TankFrame.getInstance().getMytank().getId())){
            return;
        }

        Tank tank = TankFrame.getInstance().findByUUID(this.id);

            tank.setMoving(false);
            tank.setX(this.x);
            tank.setY(this.y);

    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;

        try {
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            dos.writeInt(this.x);
            dos.writeInt(this.y);
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();

            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(dos != null){
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;

    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = null;
        TankStopMsg msg = null;

        try {
            dis = new DataInputStream(new ByteArrayInputStream(bytes));
            msg = new TankStopMsg();
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.id = new UUID(dis.readLong(),dis.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(dis != null){
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStopMsg;
    }

    @Override
    public String toString() {
        return "TankStopMsg{" +
                "x=" + x +
                ", y=" + y +
                ", id=" + id +
                '}';
    }
}
