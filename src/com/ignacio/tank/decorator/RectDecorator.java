package com.ignacio.tank.decorator;

import com.ignacio.tank.GameObject;

import java.awt.*;

public class RectDecorator extends GameObjectDecorator {
    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.setX(go.getX());
        this.setY(go.getY());
        go.paint(g);

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(super.go.getX(),super.go.getY(),getWidth()+2,getHeight()+2);
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
