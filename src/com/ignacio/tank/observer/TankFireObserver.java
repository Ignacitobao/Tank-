package com.ignacio.tank.observer;

import com.ignacio.tank.Bullet;
import com.ignacio.tank.Tank;

public class TankFireObserver implements Observer {

    @Override
    public void actionOnFire(TankFireEvent event) {
       Tank t = event.getSource();
       t.fire();
    }
}
