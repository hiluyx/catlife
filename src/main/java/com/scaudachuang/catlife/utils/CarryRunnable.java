package com.scaudachuang.catlife.utils;

/**
 * @author hiluyx
 * @since 2021/8/26 23:14
 **/
public interface CarryRunnable<C> extends Runnable {
    void setCarry(C mark);
    C getCarry();
}
