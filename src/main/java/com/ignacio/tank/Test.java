package com.ignacio.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Test {

    //这是一个Main方法，是程序的入口
    public static void main(String[] args) throws IOException {

       /* BufferedImage image = ImageIO.read(Test.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

        if(image == null){
            System.out.println("图片加载失败");
        }*/
       BufferedImage[] explodes = new BufferedImage[16];
        for(int i=0;i < 16;i++){
            explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("src/images/e" +(i+1)+".gif"));
        }
        if(explodes == null){
            System.out.println("图片加载失败");
        }

    }
}
