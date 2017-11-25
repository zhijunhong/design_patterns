package com.fudaojun.chapter_04_0100;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import entity.WordDocument;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WordDocument originDoc = new WordDocument();
        originDoc.setText("这是一篇文档");
        originDoc.addImages("图片1");
        originDoc.addImages("图片2");
        originDoc.addImages("图片3");
        originDoc.showDocument();

        try {
            WordDocument doc2 = (WordDocument) originDoc.clone();
            doc2.showDocument();
            doc2.setText("这是修改过的文档");
            doc2.showDocument();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        //拷贝的文档改变了属性，原始对象的属性并没有改变
        originDoc.showDocument();

    }
}
