package com.ignacio.tank.net;

import com.ignacio.tank.Dir;
import com.ignacio.tank.Group;
import com.ignacio.tank.Tank;
import com.ignacio.tank.TankFrame;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/2 at 15:00
 */
public class TankJoinMsg extends Msg {
    int x,y;
    Dir dir;
    Group group;
    UUID id;
    boolean moving;

    public TankJoinMsg(){

    }

    public TankJoinMsg(Tank t){
        this.x = t.getX();
        this.y = t.getY();
        this.dir = t.getDir();
        this.group = t.getGroup();
        this.id = t.getId();
        this.moving = t.isMoving();
    }

    public TankJoinMsg(int x, int y, Dir dir, Group group, UUID id, boolean moving) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.id = id;
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dir getDir() {
        return dir;
    }

    public Group getGroup() {
        return group;
    }

    public UUID getId() {
        return id;
    }

    public boolean isMoving() {
        return moving;
    }

    @Override
    public byte[] toBytes(){
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;

        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeInt(group.ordinal());
            /*一个UUID有128位，用getMost/leastSignificantBits()方法取前后64位
            * 刚好一个Long也是64位*/
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeBoolean(moving);
            dos.flush();
            bytes = baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(dos != null){
                    try {
                        dos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bytes;
    }

    @Override
    public String toString() {
        return "TankJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", id=" + id +
                ", moving=" + moving +
                '}';
    }

    @Override
    public void handle() {
        if(this.id.equals(TankFrame.getInstance().getMytank().getId()) || TankFrame.getInstance().findByUUID(this.getId()) != null) {
            return;
        }

        System.out.println(this);
        Tank tank = new Tank(this);
        TankFrame.getInstance().addTank(tank);

        //
        Client.getInstance().send(new TankJoinMsg(TankFrame.getInstance().getMytank()));
    }
}
