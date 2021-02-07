package com.ignacio.tank;

import java.awt.*;

public class Wall extends GameObject{
    int width,height;
    public Rectangle rectangle;

    public Wall(int x,int y,int width,int height){
       this.x = x;
       this.y = y;
       this.width = width;
       this.height = height;
       this.rectangle = new Rectangle(x,y,width,height);

       //GameModel.getInstance().add(this);

    }
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.fillRect(x,y,width,height);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
