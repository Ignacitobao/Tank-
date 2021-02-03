package com.ignacio.tank;

import com.ignacio.tank.cor.BulletTankCollider;
import com.ignacio.tank.cor.Collider;
import com.ignacio.tank.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank MyTank = new Tank(200,500,Dir.UP,Group.GOOD,this);
    /*List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();*/
    //游戏内对象都被抽象成 GameObject类，因此不用每个类都用一个List去存储，只需要一个List即可
    private List<GameObject> gameObjects = new ArrayList<>();
    Collider collider = new BulletTankCollider();
    Collider collider2 = new TankTankCollider();

    public GameModel() {
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        for(int i = 0;i < initTankCount;i++){
            this.gameObjects.add(new Tank(50 + i*100,50,Dir.DOWN,Group.BAD,this));
        }
    }

    public void add(GameObject gameObject){
        this.gameObjects.add(gameObject);
    }

    public void remove(GameObject gameObject){
        this.gameObjects.remove(gameObject);
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
       /* g.drawString("子弹的数量:"+ bullets.size(),10,40);
        g.drawString("敌人的数量:"+ tanks.size(),10,60);
        g.drawString("爆炸的数量:"+ explodes.size(),10,60);*/
        g.setColor(c);

        MyTank.paint(g);

        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }

        /*for(int i = 0;i < tanks.size();i++){
            tanks.get(i).paint(g);
        }

        for(int i =0;i< explodes.size();i++){
            explodes.get(i).paint(g);
        }*/

        //碰撞判定
        /*for(int i = 0;i < bullets.size();i++){
            for(int j = 0;j < tanks.size();j++)
                bullets.get(i).collideWithTank(tanks.get(j));
        }*/
        //利用comparator的原理判定碰撞
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                collider.collide(o1, o2);
                collider2.collide(o1, o2);
            }
        }

    }
}
