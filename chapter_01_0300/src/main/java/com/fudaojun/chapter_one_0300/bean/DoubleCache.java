package com.fudaojun.chapter_one_0300.bean;

import android.graphics.Bitmap;

import com.fudaojun.chapter_one_0300.base.ImageCache;

/**
 * Created by ZhijunHong on 2017/11/8.
 */

public class DoubleCache implements ImageCache {
    ImageCache mMemoryCache = new MemoryCache();
    DiskCache mDiskCache = new DiskCache();

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url,bitmap);
        mDiskCache.put(url,bitmap);
    }

    /**
     * 先从内存中获取图片，如果没有，再从SD卡中获取
     * @param url
     * @return
     */
    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }
}
