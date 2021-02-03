package com.ignacio.tank;

import java.awt.*;

public class Main {
    //这是一个Main方法，是程序的入口
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = TankFrame.getInstance();
        //初始化敌方tank
        //从配置文件读取初始化地方坦克的数量
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        for(int i = 0;i < initTankCount;i++){
            tankFrame.tanks.add(new Tank(50 + i*100,50,Dir.DOWN,Group.BAD,tankFrame));
        }

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }


}
