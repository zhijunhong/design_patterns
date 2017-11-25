package com.fudaojun.chapter_one_0200.bean;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by ZhijunHong on 2017/11/2.
 */

public class ImageCache {
    LruCache<String, Bitmap> mImageCache;

    public ImageCache() {
        iniImageCache();
    }

    private void iniImageCache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    public Bitmap get(String url) {
        return mImageCache.get(url);
    }
}
