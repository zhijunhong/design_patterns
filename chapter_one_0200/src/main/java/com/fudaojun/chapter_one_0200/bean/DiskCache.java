package com.fudaojun.chapter_one_0200.bean;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ZhijunHong on 2017/11/2.
 */

public class DiskCache {
    //从缓存中获取图片
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(getRootFilePath().getPath() + "/" + url);
    }

    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            File rootFilePath = getRootFilePath();
            if (rootFilePath != null) {
                File imageFile = new File(getRootFilePath().getPath() + "/" + url);
                fileOutputStream = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取存储图片根路径
     *
     * @return
     */
    private File getRootFilePath() {
        File file = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {//如果存储器已经挂载
            file = new File(Environment.getExternalStorageDirectory(), "fudaojun/image/");
            if (!file.exists()) {
                file.mkdir();
            }
        }
        return file;
    }
}
