package com.ignacio.tank.net;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/2 at 18:02
 */
public class ServerFrame extends Frame {
    public static final ServerFrame INSTANCE = new ServerFrame();
    TextArea taLeft = new TextArea();
    TextArea taRight = new TextArea();
    Server server = new Server();

    private ServerFrame(){
        this.setSize(1080, 720);
        this.setLocation(100, 100);
        Panel panel = new Panel(new GridLayout(1,2));
        panel.add(taLeft);
        panel.add(taRight);
        this.add(panel);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    //这是一个Main方法，是程序的入口
    public static void main(String[] args) {
        ServerFrame.INSTANCE.setVisible(true);
        ServerFrame.INSTANCE.server.start();
    }
}
