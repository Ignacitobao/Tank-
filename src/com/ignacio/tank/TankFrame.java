package com.ignacio.tank;




import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
public class TankFrame extends Frame {//继承frame类用来重写frame类的方法（setxxx等）

    GameModel gm = GameModel.getInstance();

    public static final int GAME_WIDTH = 1080;
    public static final int GAME_HEIGHT = 720;


    //Constructor
    private TankFrame() {
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("Tank War");
        this.setVisible(true);
        //窗口事件的监听：
        this.addKeyListener(new MyKeyListener());

        this.addWindowListener(new WindowAdapter() {//重写匿名内部类的方法
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private static class TankFrameHolder{
        private static final TankFrame TANK_FRAME = new TankFrame();
    }

    public static TankFrame getInstance(){
        return TankFrameHolder.TANK_FRAME;
    }

    /*public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }*/

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

    //使用双缓冲解决闪烁问题
    Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }


    @Override
    public void paint(Graphics g) {
        gm.paint(g);





    }

    //定义一个内部类（只有自己用，不需要暴露给外界）用来处理键盘事件
    class MyKeyListener extends KeyAdapter {

        boolean BL = false;
        boolean BR = false;
        boolean BU = false;
        boolean BD = false;


        //控制坦克的方向
        private void setMainTankDir(){
            Tank MyTank = gm.getMyTank();
            if(!BL && !BU && !BR && !BD){
                MyTank.setMoving(false);
            }else{
                MyTank.setMoving(true);
            }

            if(BL) {
                MyTank.setDir(Dir.LEFT);
            }
            if(BU) {
                MyTank.setDir(Dir.UP);
            }
            if(BR) {
                MyTank.setDir(Dir.RIGHT);
            }
            if(BD) {
                MyTank.setDir(Dir.DOWN);
            }

        }


        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    BL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    BR = true;
                    break;
                case KeyEvent.VK_UP:
                    BU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    BD = true;
                    break;
            }
            setMainTankDir();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    BL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    BR = false;
                    break;
                case KeyEvent.VK_UP:
                    BU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    BD = false;
                    break;
                case KeyEvent.VK_CONTROL:


                   /* String goodFSname = (String)PropertyMgr.get("myFireStrategy");*/


                   /* int FireModeNo = Integer.parseInt((String) PropertyMgr.get("myFireStrategy"));
                    System.out.println(FireModeNo);
                    System.out.println((FireModeNo == 1));*/
                    /*if("strong".equals(FireModeName))*/
                       // MyTank.fire(StrongFireStrategy.getInstance());
                        gm.getMyTank().fire();
                    /*switch(FireModeName){
                        case "strong":
                            MyTank.fire(StrongFireStrategy.getInstance());
                            break;
                        case "default":
                            MyTank.fire(DefaultFireStrategy.getInstance());
                            break;
                    }*/
                    break;
                    }
                setMainTankDir();

        }
    }
}


