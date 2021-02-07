package com.ignacio.tank;

import com.ignacio.tank.cor.BulletTankCollider;
import com.ignacio.tank.cor.Collider;
import com.ignacio.tank.cor.ColliderChain;
import com.ignacio.tank.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank MyTank;
    /*List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();*/

    ColliderChain chain = new ColliderChain();
    //游戏内对象都被抽象成 GameObject类，因此不用每个类都用一个List去存储，只需要一个List即可
    private List<GameObject> gameObjects = new ArrayList<>();
    Collider collider = new BulletTankCollider();
    Collider collider2 = new TankTankCollider();



    private GameModel() {
    }

    private static class GameModelHolder{
        private static final GameModel INSTANCE = new GameModel();
    }

    static {
        GameModelHolder.INSTANCE.init();
    }

    public static GameModel getInstance(){
        return GameModelHolder.INSTANCE;
    }

    private void init(){
        //初始化我的Tank
        MyTank = new Tank(200,500,Dir.UP,Group.GOOD);
        //从配置文件中读取敌方坦克的数量并初始化
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        for(int i = 0;i < initTankCount;i++){
            new Tank(50 + i*100,50,Dir.DOWN,Group.BAD);
        }
        //初始化墙
        add(new Wall(150,150,200,50));
        add(new Wall(550,150,200,50));
        add(new Wall(300,300,50,200));
        add(new Wall(550,300,50,200));
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
                //方法1：遍历colliders链表，撞
                //方法2：在chain中让他们自己撞
                chain.collide(o1,o2);
            }
        }

    }
}
