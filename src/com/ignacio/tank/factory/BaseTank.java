package com.ignacio.tank.factory;

import com.ignacio.tank.Dir;
import com.ignacio.tank.FireStrategy;
import com.ignacio.tank.Group;
import com.ignacio.tank.TankFrame;

import java.awt.*;

public abstract class BaseTank {

    public abstract void paint(Graphics g);
    public abstract int getX();
    public abstract int getY();
    public abstract Dir getDir();
    public abstract Group getGroup();
    public abstract TankFrame getTankFrame();
    public abstract Rectangle getRect();
    public abstract void die();
    public abstract void fire(FireStrategy fireStrategy);


}
