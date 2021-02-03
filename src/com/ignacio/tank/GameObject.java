package com.ignacio.tank;

import java.awt.*;

public abstract class GameObject {
    /*
    * 在有必要时，可以将不同的类再次抽象出一个共同的父类（接口）
    * 选择抽象出抽象类还是接口时，要看语义（大体上名词-->抽象类，形容词-->接口）
    * 在抽象时，抽象出的属性、方法能少尽量少
    * */
    int x, y;

    public abstract void paint(Graphics g);
}
