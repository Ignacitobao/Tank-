package com.ignacio.tank;


import java.awt.*;
import java.util.Random;

public class Explode extends GameObject{



    private TankFrame tankFrame = null;
    //private boolean live = true;


    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();

    private int step = 0;

    public Explode() {
    }

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;

        GameModel.getInstance().add(this);
    }


    public void paint(Graphics g){

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step >= ResourceMgr.explodes.length){
            GameModel.getInstance().remove(this);
        }
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
