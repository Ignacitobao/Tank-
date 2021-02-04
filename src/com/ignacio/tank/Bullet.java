package com.ignacio.tank;




import java.awt.*;

public class Bullet extends GameObject{
    private static final int SPEED = 8;
    private int x, y;

    private boolean live = true;

    private Dir dir;
    private Group group = Group.BAD;
    GameModel gameModel;

    public Rectangle rect = new Rectangle();

    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();


    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir,Group group,GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gameModel = gameModel;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        gameModel.add(this);
    }

    public static int getSPEED() {
        return SPEED;
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

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public void paint(Graphics g){
        if(!live){
            gameModel.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }


        move();

    }



    private void move() {
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

        //更新rect的值
        rect.x = this.x;
        rect.y = this.y;

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            live = false;
        }
    }

    public void collideWithTank(Tank tank) {
        if(this.group == tank.getGroup()){
            return;
        }
        Rectangle recTank = new Rectangle(this.x,this.y,WIDTH,HEIGHT);

        if(rect.intersects(tank.getRect())){
            this.die();
            tank.die();
        }

    }

    public void die(){
        this.live = false;
    }
}
