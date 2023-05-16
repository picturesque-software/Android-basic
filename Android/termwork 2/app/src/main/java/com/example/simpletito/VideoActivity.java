package com.example.simpletito;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class VideoActivity extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video);
        setTitle("VideoView");

        //start
        videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(getVideoPath(R.raw.bytedance));
        videoView.start();
        //set click,关键代码！
        videoView.setOnTouchListener(new MyClickListener
                (new MyClickListener.MyClickCallBack() {

                    @Override
                    public void oneClick() {
                        if (videoView.isPlaying()){
                            videoView.pause();
                        }else {
                            videoView.start();
                        }
                    }
                    @Override
                    public void doubleClick() {
                        Toast.makeText(getApplicationContext(), "❤️",
                                Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private String getVideoPath(int resId) {
        return "android.resource://" + this.getPackageName() + "/" + resId;
    }
}

