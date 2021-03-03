package com.ignacio.tank;


import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {//创建一个类用来管理配置文件

    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("resource/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object get(String key){
        if(props == null){
            return null;
        }return props.get(key);
    }

    //这是一个Main方法，是程序的入口
    //测试一下
    public static void main(String[] args) {
        System.out.println(PropertyMgr.props.get("initTankCount"));
    }
}
