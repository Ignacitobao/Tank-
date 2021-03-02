package com.ignacio.tank.msg;

import com.ignacio.tank.Dir;
import com.ignacio.tank.Group;

import java.util.UUID;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/2 at 15:00
 */
public class PlayerJoinMsg {
    int x,y;
    Dir dir;
    boolean moving;
    Group group;
    public UUID id;
    String name;
}
