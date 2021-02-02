package com.ignacio.tank.factory;

import com.ignacio.tank.ResourceMgr;
import com.ignacio.tank.TankFrame;

import java.awt.*;

public class SquareExplode extends BaseExplode {
    private int x, y;


    private TankFrame tankFrame = null;
    //private boolean live = true;


    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();

    private int step = 0;

    public SquareExplode() {
    }

    public SquareExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    @Override
    public void paint(Graphics g){

        //g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,10*step,10*step);
        step++;
        if(step >= 5){
            tankFrame.explodes.remove(this);
        }
        g.setColor(c);
    }
}
