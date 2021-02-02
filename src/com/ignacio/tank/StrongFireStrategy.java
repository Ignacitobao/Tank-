package com.ignacio.tank;

import com.ignacio.tank.factory.BaseBullet;
import com.ignacio.tank.factory.BaseTank;
import com.ignacio.tank.factory.SquareBullet;

public class StrongFireStrategy {
    public static final StrongFireStrategy STRONG_FIRE_STRATEGY = new StrongFireStrategy();
    private StrongFireStrategy(){}
    public static StrongFireStrategy getInstance(){
        return STRONG_FIRE_STRATEGY;
    }


    public void fire(BaseTank tank) {
        TankFrame tankFrame = TankFrame.getInstance();
        int bx =  tank.getX()+ Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for(Dir dir:dirs){
            tankFrame.gf.createBullet(bx,by,dir,tank.getGroup(),tank.getTankFrame());
        }

    }
}
