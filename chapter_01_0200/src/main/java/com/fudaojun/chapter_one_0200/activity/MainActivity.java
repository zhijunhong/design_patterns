package com.fudaojun.chapter_one_0200.activity;

import android.Manifest;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.fudaojun.chapter_one_0200.R;
import com.fudaojun.chapter_one_0200.utils.ImagerLoaderEx;


public class MainActivity extends BaseActivity {
    private ImageView mImageView;
    private ImagerLoaderEx mImagerLoaderEx = new ImagerLoaderEx();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //点击获取权限
        requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100, new OnRequestPermissionListener() {
            @Override
            public void permissionGrant() {
                Toast.makeText(MainActivity.this, "获取权限成功", Toast.LENGTH_SHORT).show();
                imageloadEx();
            }

            @Override
            public void permissionReject() {
                Toast.makeText(MainActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void imageloadEx() {
        mImagerLoaderEx.setUserDiskCache(true);
        mImageView = (ImageView) findViewById(R.id.iv_imageview);
        mImagerLoaderEx.displayImage("http://www.fudaojun.com/images/fudaojun_media05.png",mImageView);
    }


}
