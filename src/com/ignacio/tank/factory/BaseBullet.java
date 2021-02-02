package com.ignacio.tank.factory;

import com.ignacio.tank.Tank;

import java.awt.*;

public abstract class BaseBullet {
    public abstract void paint(Graphics g);
    public abstract void collideWithTank(BaseTank tank);
}
