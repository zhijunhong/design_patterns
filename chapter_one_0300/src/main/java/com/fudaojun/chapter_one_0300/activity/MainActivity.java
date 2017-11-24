package com.fudaojun.chapter_one_0300.activity;

import android.Manifest;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.fudaojun.chapter_one_0300.R;
import com.fudaojun.chapter_one_0300.bean.DoubleCache;
import com.fudaojun.chapter_one_0300.utils.ImgeLoader;

public class MainActivity extends BaseActivity {
    private ImgeLoader mImgeLoader = new ImgeLoader();
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //点击获取权限
        requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100, new OnRequestPermissionListener() {
            @Override
            public void permissionGrant() {
                Toast.makeText(MainActivity.this, "获取权限成功", Toast.LENGTH_SHORT).show();
                imageload();
            }

            @Override
            public void permissionReject() {
                Toast.makeText(MainActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void imageload() {
        DoubleCache doubleCache = new DoubleCache();
        //注入双缓存
        mImgeLoader.setImageCache(doubleCache);

        mImageView = (ImageView) findViewById(R.id.iv_imageview);
        mImgeLoader.displayImge("http://www.fudaojun.com/images/fudaojun_logo.png", mImageView);
    }
}
