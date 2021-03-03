package com.ignacio.tank.net;

/**
 * @author ：Ignacito
 * @date ：Created on 2021/3/3 at 17:24
 */
public abstract class Msg {
    public abstract void handle();
    public abstract byte[] toBytes();
}
