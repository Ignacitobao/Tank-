package com.ignacio.tank.cor;

import com.ignacio.tank.Bullet;
import com.ignacio.tank.GameObject;
import com.ignacio.tank.Tank;

import java.awt.*;

public class BulletTankCollider implements Collider {
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;
            b.collideWithTank(t);
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            collide(o2,o1);
        }else{
            return;
        }
    }
    /*public void collideBulletTank(Bullet b,Tank t){
        if (b.getGroup() == t.getGroup()){
            return;
        }

        if(b.getRect().intersects(t.getRect())){
            b.die();
            t.die();
        }
    }*/
}
