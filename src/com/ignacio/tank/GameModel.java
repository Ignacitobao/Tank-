package com.ignacio.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private Tank MyTank = new Tank(200,500,Dir.UP,Group.GOOD,this);
    java.util.List<Bullet> bullets = new ArrayList<>();
    java.util.List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameModel() {
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        for(int i = 0;i < initTankCount;i++){
            this.tanks.add(new Tank(50 + i*100,50,Dir.DOWN,Group.BAD,this));
        }
    }

    public Tank getMyTank() {
        return MyTank;
    }

    public void setMyTank(Tank myTank) {
        MyTank = myTank;
    }

    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawString("子弹的数量:"+ bullets.size(),10,40);
        g.drawString("敌人的数量:"+ tanks.size(),10,60);
        g.drawString("爆炸的数量:"+ explodes.size(),10,60);
        g.setColor(c);

        MyTank.paint(g);

        for(int i = 0;i < bullets.size();i++){
            bullets.get(i).paint(g);
        }

        for(int i = 0;i < tanks.size();i++){
            tanks.get(i).paint(g);
        }

        for(int i =0;i< explodes.size();i++){
            explodes.get(i).paint(g);
        }

        //碰撞判定
        for(int i = 0;i < bullets.size();i++){
            for(int j = 0;j < tanks.size();j++)
                bullets.get(i).collideWithTank(tanks.get(j));
        }
    }


}
