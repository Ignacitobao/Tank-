package com.ignacio.tank;

import com.ignacio.tank.factory.BaseTank;

public class DefaultFireStrategy implements FireStrategy {
    public static final DefaultFireStrategy DEFAULT_FIRE_STRATEGY = new DefaultFireStrategy();
    private DefaultFireStrategy(){
    }
    public static DefaultFireStrategy getInstance(){
        return DEFAULT_FIRE_STRATEGY;
    }

    @Override
    public void fire(BaseTank tank) {
        int bx = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        new  Bullet(bx,by,tank.getDir(),tank.getGroup(),tank.getTankFrame());
    }
}
