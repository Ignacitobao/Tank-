package com.ignacio.tank;

import com.ignacio.tank.factory.BaseExplode;

import java.awt.*;
import java.util.Random;

public class Explode extends BaseExplode {
    private int x, y;


    private TankFrame tankFrame = null;
    //private boolean live = true;


    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();

    private int step = 0;

    public Explode() {
    }

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    @Override
    public void paint(Graphics g){

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step >= ResourceMgr.explodes.length){
            tankFrame.explodes.remove(this);
        }
    }
}
