package com.ignacio.tank;


import java.awt.*;
import java.util.Random;

public class Explode {
    private int x, y;


    private TankFrame tankFrame = null;
    //private boolean live = true;


    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    GameModel gameModel = null;
    private int step = 0;

    public Explode() {
    }

    public Explode(int x, int y, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;
    }


    public void paint(Graphics g){

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step >= ResourceMgr.explodes.length){
            gameModel.explodes.remove(this);
        }
    }
}
