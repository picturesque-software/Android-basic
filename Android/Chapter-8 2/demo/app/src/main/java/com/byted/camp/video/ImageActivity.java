package com.byted.camp.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.Locale;

public class ImageActivity extends AppCompatActivity {
    private final static int REQUEST_CAMERA = 123;
    private final static String IMAGE_URL =
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201408%2F07%2F200155xgo7jhigcbjcw39j.jpg&refer=http%3A%2F%2Fattach.bbs.miui.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628930059&t=1d84ec81f67d559f69e5822942a7ee53";
    private final static String IMAGE_URL2 =
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562328963755&di"
                    + "=f4aa87b95c87dc01ff0ca2c9150845c8&imgtype=0&src=http%3A%2F%2Fwww.uimaker"
                    + ".com%2Fuploads%2Fallimg%2F121105%2F1_121105084854_2.jpg";
    private ImageView mImageView;
    private File imagePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_image);

        initButton();
    }

    private void initButton() {
        mImageView = findViewById(R.id.imageView);

        findViewById(R.id.buttonCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSystemCamera();
            }
        });

        findViewById(R.id.buttonLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });
    }

    private void loadImage() {
        RequestOptions cropOptions = new RequestOptions();
        cropOptions = cropOptions.circleCrop();
        Glide.with(ImageActivity.this)
                .load(IMAGE_URL)
                .apply(cropOptions)
                .placeholder(R.drawable.icon_progress_bar)
                .error(R.drawable.icon_failure)
                .fallback(R.drawable.ic_launcher_background)
////                .thumbnail(Glide.with(this).load(IMAGE_URL2))
                .transition(DrawableTransitionOptions.withCrossFade(4000))
                .into(mImageView);
    }

    /**
     * 打开系统相机
     */
    private void openSystemCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String imageName = String.format(Locale.getDefault(), "img_%d.jpg", System.currentTimeMillis());
        imagePath = new File(getExternalFilesDir(""), imageName);
        Uri outUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", imagePath);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            Glide.with(this).load(imagePath).into(mImageView);
        }
    }
}
