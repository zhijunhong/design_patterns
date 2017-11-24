package com.fudaojun.chapter_one_0300.base;

import android.graphics.Bitmap;

/**
 * Created by ZhijunHong on 2017/11/8.
 */

public interface ImageCache {
    public void put(String url, Bitmap bitmap);

    public Bitmap get(String url);
}
