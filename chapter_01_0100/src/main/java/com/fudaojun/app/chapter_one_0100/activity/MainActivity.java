package com.fudaojun.app.chapter_one_0100.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.fudaojun.app.chapter_one_0100.R;
import com.fudaojun.app.chapter_one_0100.utils.ImagerLoader;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageView;
    private ImagerLoader mImagerLoader = new ImagerLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageload();
    }


    private void imageload() {
        mImageView = (ImageView) findViewById(R.id.iv_imageview);
        mImagerLoader.displayImage("http://www.fudaojun.com/images/fudaojun_logo.png", mImageView);
    }

}
