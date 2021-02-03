package com.ignacio.tank.cor;

import com.ignacio.tank.Bullet;
import com.ignacio.tank.GameObject;
import com.ignacio.tank.Tank;

public class TankTankCollider implements Collider {
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank)o1;
            Tank t2 = (Tank)o2;
            if(t1.getRect().intersects(t2.getRect())){
                t1.stop();
                t2.stop();
            }

        }else{
            return;
        }
    }

    /*private void tankCollideTank(Tank t1, Tank t2) {

        if (t1.getRect().intersects(t2.getRect())){
            if(t1.getGroup() == t2.getGroup()){
                t1.setMoving(false);
                t2.setMoving(false);
            }else{
                t1.die();
                t2.die();
            }
        }
    }*/
}
