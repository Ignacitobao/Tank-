package com.ignacio.tank.net;

import com.ignacio.tank.Dir;
import com.ignacio.tank.Tank;
import com.ignacio.tank.TankFrame;

import java.io.*;
import java.util.UUID;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/4 at 14:05
 */
public class TankStartMovingMsg extends Msg {


    int x,y;
    Dir dir;
    UUID id;

    public TankStartMovingMsg() {
    }

    public TankStartMovingMsg(int x, int y, Dir dir,UUID id) {

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.id = id;
    }

     public TankStartMovingMsg(Tank tank){

        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public void handle() {
        if(this.id.equals(TankFrame.getInstance().getMytank().getId())) {
            return;
        }

        Tank tank = TankFrame.getInstance().findByUUID(this.id);

        tank.setMoving(true);
        tank.setX(this.x);
        tank.setY(this.y);
        tank.setDir(this.dir);


    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;

        try {
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);

            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
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
        ByteArrayInputStream bis = null;
        DataInputStream dis = null;
        TankStartMovingMsg tankStartMovingMsg = null;

        try {
            bis = new ByteArrayInputStream(bytes);
            dis = new DataInputStream(bis);
            tankStartMovingMsg = new TankStartMovingMsg();

            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
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
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStartMovingMsg;
    }

    @Override
    public String toString() {
        return "TankStartMovingMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", id=" + id +
                '}';
    }
}
