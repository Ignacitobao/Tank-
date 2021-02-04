package com.ignacio.tank;

import java.awt.*;

public class Wall extends GameObject{
    int x,y;
    GameModel gameModel;
    public Wall(){
        gameModel.add(this);
    }
    @Override
    public void paint(Graphics g) {
        g.fillRect(500,600,50,200);
    }
}
