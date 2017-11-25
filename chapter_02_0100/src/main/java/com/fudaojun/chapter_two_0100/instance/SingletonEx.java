package com.fudaojun.chapter_two_0100.instance;

/**
 * Created by ZhijunHong on 2017/11/8.
 * 推荐使用的单利模式
 */

public class SingletonEx {
    private SingletonEx() {
        //private construction
    }

    public static SingletonEx getInstance() {
        return SingletonHolder.sInstance;
    }

    public static class SingletonHolder {
        private static final SingletonEx sInstance = new SingletonEx();
    }
}
