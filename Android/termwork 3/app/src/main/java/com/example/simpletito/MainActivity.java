package com.example.simpletito;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<videoItem> myVideoList=new ArrayList<>();//TODO:fetch the data from api

    private FloatingActionButton takeVideoBtn;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity_layout);

        takeVideoBtn = findViewById(R.id.btn_take_video);
        takeVideoBtn.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "拍摄视频", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, TakeVideoActivity.class);
            startActivity(intent);
        });
    }

    private void initVideoList(){

    }//TODO:Add your code here(maybe)
}
