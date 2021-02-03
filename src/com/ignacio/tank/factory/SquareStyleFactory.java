package com.ignacio.tank.factory;

import com.ignacio.tank.*;

public class SquareStyleFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new SquareTank(x,y,dir,group,tankFrame);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new SquareBullet(x,y,dir,group,tankFrame);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new SquareExplode(x, y, tankFrame) ;
    }
}
