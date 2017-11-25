package com.fudaojun.chapter_one_0300.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.fudaojun.chapter_one_0300.base.ImageCache;
import com.fudaojun.chapter_one_0300.bean.MemoryCache;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZhijunHong on 2017/11/8.
 */

public class ImgeLoader {
    //线程池的数量为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //默认为内存缓存
    ImageCache
            mImageCache = new MemoryCache();

    //注入缓存实现
    public void setImageCache(ImageCache imageCache) {
        mImageCache = imageCache;
    }

    public void displayImge(String imageUrl, ImageView imageView) {
        Bitmap bitmap = mImageCache.get(imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }

        submitLoadRequest(imageUrl, imageView);

    }

    /**
     * 显示图片
     *
     * @param imageUrl
     * @param imageView
     */
    private void submitLoadRequest(final String imageUrl, final ImageView imageView) {
        imageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(imageUrl);
                if (bitmap == null) return;

                if (imageView.getTag().equals(imageUrl)) {
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(imageUrl, bitmap);
            }
        });
    }

    /**
     * 下载图片
     *
     * @param imageUrl
     * @return
     */
    private Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            //关闭连接资源
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
