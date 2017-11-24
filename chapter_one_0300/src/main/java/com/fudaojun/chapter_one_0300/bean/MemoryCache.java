package com.fudaojun.chapter_one_0300.bean;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.fudaojun.chapter_one_0300.base.ImageCache;

/**
 * Created by ZhijunHong on 2017/11/8.
 */

public class MemoryCache implements ImageCache {
    LruCache<String, Bitmap> mMemeryCache;

    public MemoryCache() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 4;
        mMemeryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemeryCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return mMemeryCache.get(url);
    }
}
