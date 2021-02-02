package com.ignacio.tank.factory;

import com.ignacio.tank.*;

public class SquareStyleFactory extends GameFactory {
    @Override
    public Tank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return null;
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new SquareBullet();
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new SquareExplode(x, y, tankFrame) ;
    }
}
