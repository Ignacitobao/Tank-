package com.ignacio.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceMgr {
    //我方坦克的4个方向
    public static BufferedImage goodtankL, goodtankU, goodtankR, goodtankD;
    //敌方坦克的4个方向
    public static BufferedImage tankL, tankU, tankR, tankD;
    //子弹的4个方向
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    //爆炸的16个过程
    public static BufferedImage[] explodes = new BufferedImage[16];

    public static final ResourceMgr INSTANCE = new ResourceMgr();

    private ResourceMgr() {
    }
    public static ResourceMgr getInstance(){
        return INSTANCE;
    }

    static{//用静态代码块来load资源，（在类加载前就执行)
        //利用反射来加载已经打在包中的图片，使其加载到内存中，方便渲染画面时直接使用
        try {
            //利用工具类中的旋转方法
            goodtankU = ImageIO.read(new File("D:\\Java\\Exercise\\tank\\src\\GoodTank1.png"));
            goodtankD = ImageUtil.rotateImage(goodtankU,180);
            goodtankL = ImageUtil.rotateImage(goodtankU,-90);
            goodtankR = ImageUtil.rotateImage(goodtankU,90);

            tankU = ImageIO.read(new File("D:\\Java\\Exercise\\tank\\src\\tankU.gif"));
            tankD = ImageUtil.rotateImage(tankU,180);
            tankL = ImageUtil.rotateImage(tankU,-90);
            tankR = ImageUtil.rotateImage(tankU,90);

            bulletU = ImageIO.read(new File("D:\\Java\\Exercise\\tank\\src\\bulletU.gif"));
            bulletD = ImageUtil.rotateImage(bulletU,180);
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            //加载爆炸过程的图片
            for(int i=0;i < 16;i++){
               explodes[i] = ImageIO.read(new File("D:\\Java\\Exercise\\tank\\src\\e" + (i+1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
