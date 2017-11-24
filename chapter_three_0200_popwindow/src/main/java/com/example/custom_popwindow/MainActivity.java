package com.example.custom_popwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 自定义popwindow
 * 建造者模式
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow customPopWindow = new CustomPopWindow.Builder(MainActivity.this)
                        .setView(R.layout.popup_layout_score_example)
                        .create().showAsDropDown(button);
            }
        });
    }
}
