package com.ignacio.tank.cor;

import com.ignacio.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{


    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain(){

        add(new BulletWallCollider());
        add(new TankWallCollider());
        add(new BulletTankCollider());
        add(new TankTankCollider());
    }
    public void add(Collider collider){
        colliders.add(collider);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (colliders.get(i).collide(o1, o2)) {
                return true;
            }
        }
        return false;
    }
}
