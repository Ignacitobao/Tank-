package com.ignacio.tank.factory;

import com.ignacio.tank.*;

import java.awt.*;
import java.util.Random;

public class SquareTank extends BaseTank { //定义一个Tank类
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private boolean moving = true;
    private TankFrame tankFrame = null;
    private boolean live = true;
    private Random randomFire = new Random();
    private Random randomMove = new Random();
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();

    //用图片的长宽定义tank的长宽
    public static final int HEIGHT = ResourceMgr.goodtankU.getHeight();
    public static final int WIDTH = ResourceMgr.goodtankU.getWidth();

    public SquareTank() {
    }

    public SquareTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;

        //在构造方法里直接给rect赋值，即每new一个tank出来，就给rect更新一次值
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;


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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public void paint(Graphics g) {
        //定义一个方法
        //让Tank类自己把自己画出来
        if(!live){
            tankFrame.tanks.remove(this);
        }
        switch (dir){
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodtankU : ResourceMgr.tankU, x, y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodtankD:ResourceMgr.tankD, x, y,null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodtankL:ResourceMgr.tankL, x, y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodtankR:ResourceMgr.tankR, x, y,null);
                break;

        }

        move();


    }

    private void move() {
        if(!moving){
            return;
        }

        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }


        //根据tank类型决定运动模式
        //如果是bad tank则随机发射炮弹，或改变方向，概率为5%
        if(this.group == Group.BAD && randomFire.nextInt(100)>94){

            this.fire(DefaultFireStrategy.getInstance());
            //随机改变方向
            randomDir();
        }

        boundsCheck();

        // 移动后同步更新rect的位置
        rect.x = this.x;
        rect.y = this.y;

    }

    private void boundsCheck() {
        if(this.x < 0){
            x = 0;
        }else if (this.y <30){
            y = 30;
        }else if (this.x > (TankFrame.GAME_WIDTH - SquareTank.WIDTH)){
            x = TankFrame.GAME_WIDTH - SquareTank.WIDTH;
        }else if(this.y > (TankFrame.GAME_HEIGHT - SquareTank.HEIGHT)){
            y = TankFrame.GAME_HEIGHT - SquareTank.HEIGHT;
        }

    }

    private void randomDir() {
        //用4个数字来随机选择方向
        this.dir = Dir.values()[randomMove.nextInt(4)];
    }

    public void fire(FireStrategy fireStrategy) {//将fire的模式通过一个参数传进方法中
        //为了让子弹从坦克的中心处发出，需要对new出来的子弹的位置进行一下计算,相应代码都写到了策略模式的代码里
        //即FireStrategy里
        /*int bx = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;*/

        /*if(group == Group.GOOD){
            try {
                fireStrategy = (FireStrategy) (Class.forName((String) ((PropertyMgr.get("myFireStrategy")))).newInstance());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        fireStrategy.fire(this);*/
        fireStrategy.fire(this);

    }


    /*public void collideWithTank(Tank tank2) {
        Rectangle recMyTank = new Rectangle(tankFrame.MyTank.getX(),tankFrame.MyTank.getY(),WIDTH,HEIGHT);
        Rectangle recTank2 = new Rectangle(tank2.getX(),tank2.getY(),Tank.HEIGHT,Tank.HEIGHT);
        if(recMyTank.intersects(recTank2)){
            tankFrame.MyTank.die();
            tank2.die();
        }

    }*/

    public void die() {
        this.live = false;
        //通过计算使爆炸的位置更准确
        int dx = this.x - SquareTank.WIDTH/2 + Bullet.WIDTH/2;
        int dy = this.y - SquareTank.HEIGHT/2 + Bullet.HEIGHT/2;
        tankFrame.explodes.add(tankFrame.gf.createExplode(dx,dy,tankFrame));
    }


}