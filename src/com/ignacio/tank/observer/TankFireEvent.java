package com.ignacio.tank.observer;

import com.ignacio.tank.Tank;

public class TankFireEvent {
    Tank tank;

    public Tank getSource(){
        return tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }
}
