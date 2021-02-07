package com.ignacio.tank.observer;

public interface Observer {
    public abstract void actionOnFire(TankFireEvent event);
}
