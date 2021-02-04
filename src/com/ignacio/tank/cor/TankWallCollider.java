package com.ignacio.tank.cor;

import com.ignacio.tank.GameObject;
import com.ignacio.tank.Tank;
import com.ignacio.tank.Wall;

public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){

            Tank t = (Tank)o1;
            Wall wall = (Wall)o2;

            if(t.getRect().intersects(wall.rectangle)){
                t.back();
            }
        }else if(o1 instanceof Wall && o2 instanceof Tank){
            return collide(o2,o1);
        }
            return false;
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
