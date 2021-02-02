package com.ignacio.tank.factory;

import com.ignacio.tank.*;

public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group,TankFrame tankFrame);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group,TankFrame tankFrame);
    public abstract BaseExplode createExplode(int x, int y,TankFrame tankFrame);
}
