package com.ignacio.tank;



public class StrongFireStrategy {
    public static final StrongFireStrategy STRONG_FIRE_STRATEGY = new StrongFireStrategy();
    private StrongFireStrategy(){}
    public static StrongFireStrategy getInstance(){
        return STRONG_FIRE_STRATEGY;
    }


    public void fire() {
        TankFrame tankFrame = TankFrame.getInstance();
        int bx =  tankFrame.MyTank.getX()+ Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tankFrame.MyTank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for(Dir dir:dirs){
            new Bullet(bx,by,dir,tankFrame.MyTank.getGroup(),tankFrame);
        }

    }
}
