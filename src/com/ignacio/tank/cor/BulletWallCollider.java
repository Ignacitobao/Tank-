package com.ignacio.tank.cor;

import com.ignacio.tank.Bullet;
import com.ignacio.tank.GameObject;
import com.ignacio.tank.Tank;
import com.ignacio.tank.Wall;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {

            Bullet b = (Bullet) o1;
            Wall wall = (Wall) o2;
            if (b.rect.intersects(wall.rectangle)) {
                b.die();
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
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

