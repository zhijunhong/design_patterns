package com.fudaojun.chapter_three_0100;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fudaojun.fudaojunlib.utils.LibUtils;

import entity.Builder;
import entity.Director;
import entity.MacbookBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Builder builder = new MacbookBuilder();
        Director pcDirector = new Director(builder);
        pcDirector.construct("英特尔主板", "Retina显示器");

        LibUtils.myLog("Computer Info:" + builder.create().toString());

    }
}
