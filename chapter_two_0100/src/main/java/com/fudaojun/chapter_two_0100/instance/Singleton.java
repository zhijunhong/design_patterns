package com.fudaojun.chapter_two_0100.instance;

/**
 * Created by ZhijunHong on 2017/11/8.
 */

public class Singleton {
    private static Singleton singleton = null;

    private Singleton() {
        //private construction
    }

    /**
     * 单利
     * @return
     */
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
