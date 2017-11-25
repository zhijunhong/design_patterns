package com.fudaojun.chapter_one_0200.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.fudaojun.chapter_one_0200.bean.DiskCache;
import com.fudaojun.chapter_one_0200.bean.DoubleCache;
import com.fudaojun.chapter_one_0200.bean.ImageCache;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZhijunHong on 2017/11/2.
 * 单一职责
 */

public class ImagerLoaderEx {
    //内存缓存
    ImageCache mImageCache = new ImageCache();
    //SD 卡缓存
    DiskCache mDiskCache = new DiskCache();
    //双缓存
    DoubleCache mDoubleCache = new DoubleCache();

    //是否使用SD卡缓存
    private boolean isUserDiskCache = false;

    //是否使用双缓存
    private boolean isUserDoubleCache = false;

    //创建线程池：数量为CUP的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap = null;
        if (isUserDoubleCache) {
            bitmap = mDoubleCache.get(url);
        } else if (isUserDiskCache) {
            bitmap = mDiskCache.get(url);
        } else {
            mImageCache.get(url);
        }
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                //放入内存缓存
                mImageCache.put(url, bitmap);

                //放入SD卡内存
                mDiskCache.put(url, bitmap);
            }
        });
    }

    /**
     * 下载图片文件
     *
     * @param iamgeUrl
     * @return
     */
    private Bitmap downloadImage(String iamgeUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(iamgeUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    public void setUserDiskCache(boolean userDiskCache) {
        isUserDiskCache = userDiskCache;
    }

    public void setUserDoubleCache(boolean userDoubleCache) {
        isUserDoubleCache = userDoubleCache;
    }


}
