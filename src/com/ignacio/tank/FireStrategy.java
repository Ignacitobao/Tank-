package com.ignacio.tank;

import com.ignacio.tank.factory.BaseTank;

public interface FireStrategy {
    public abstract void fire(BaseTank tank);

}
