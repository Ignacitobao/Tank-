package com.ignacio.tank.cor;

import com.ignacio.tank.Bullet;
import com.ignacio.tank.GameObject;
import com.ignacio.tank.Tank;

import java.awt.*;

public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            if (b.getGroup() == t.getGroup()) {
                return false;
            } else if (b.getRect().intersects(t.getRect())) {
                b.die();
                t.die();
                return true;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
            return false;
    }
}
    /*public boolean collideBulletTank(Bullet b,Tank t){
        if (b.getGroup() == t.getGroup()){
            return false;
        }

        if(b.getRect().intersects(t.getRect())){
            b.die();
            t.die();
            return true;
        }
        return false;
    }*/

