package com.example.simpletito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<videoItem> myVideoList=new ArrayList<>();//TODO:fetch the data from api

    private Button takeVideoBtn;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity_layout);
        initButton();
    }
    private void initButton() {
        open(R.id.button1, com.example.simpletito.VideoActivity.class);
        open(R.id.btn_take_video, com.example.simpletito.TakeVideoActivity.class);

        /*findViewById(R.id.btn_take_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "拍摄视频", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, TakeVideoActivity.class);
                startActivity(intent);
            }
        });
         */

    }
    private void open(int buttonId, final Class<?> clz) {
        findViewById(buttonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, clz));
            }
        });
    }

    private void initVideoList(){

    }//TODO:Add your code here(maybe)
}
