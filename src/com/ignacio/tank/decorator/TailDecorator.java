package com.ignacio.tank.decorator;

import com.ignacio.tank.GameObject;

import java.awt.*;

public class TailDecorator extends GameObjectDecorator {
    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        //动态获取x,y的值（decorator的x,y会随着bullet的x,y的变化而变化）
        this.setX(go.getX());
        this.setY(go.getY());
        go.paint(g);

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(go.getX(),go.getY(),go.getX() + getWidth(),super.go.getY()+getHeight());
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
