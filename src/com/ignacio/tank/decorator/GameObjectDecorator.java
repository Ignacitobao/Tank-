package com.ignacio.tank.decorator;

import com.ignacio.tank.GameObject;

import java.awt.*;

public abstract class GameObjectDecorator extends GameObject {
    GameObject go;

    public GameObjectDecorator(GameObject go){
        this.go = go;
    }
    @Override
    public abstract void paint(Graphics g) ;
}
