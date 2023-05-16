package com.example.simpletito;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.appcompat.widget.AppCompatImageView;

public class FocusImageView extends AppCompatImageView {

    private static final int NO_ID = -1;
    private int mFocusImg = NO_ID;
    private int mFocusSucceedImg = NO_ID;
    private int mFocusFailedImg = NO_ID;
    private Animation mAnimation;
    private Handler mHandler;


    public FocusImageView(Context context) {
        super(context);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.focusview_show);
        setVisibility(View.GONE);
        mHandler = new Handler();
    }

    public FocusImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.focusview_show);
        mHandler = new Handler();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FocusImageView);
        mFocusImg = typedArray.getResourceId(R.styleable.FocusImageView_focus_focusing_id, NO_ID);
        mFocusSucceedImg = typedArray.getResourceId(R.styleable.FocusImageView_focus_success_id, NO_ID);
        mFocusFailedImg = typedArray.getResourceId(R.styleable.FocusImageView_focus_fail_id, NO_ID);
        typedArray.recycle();

        if (mFocusImg == NO_ID || mFocusSucceedImg == NO_ID || mFocusFailedImg == NO_ID) {
            throw new RuntimeException("mFocusImg,mFocusSucceedImg,mFocusFailedImg is null");
        }
    }


    public void startFocus(Point point) {
        if (mFocusImg == NO_ID || mFocusSucceedImg == NO_ID || mFocusFailedImg == NO_ID) {
            throw new RuntimeException("focus image is null");
        }

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.topMargin = point.y - getMeasuredHeight() / 2;
        params.leftMargin = point.x - getMeasuredWidth() / 2;
        setLayoutParams(params);

        setVisibility(View.VISIBLE);
        setImageResource(mFocusImg);
        startAnimation(mAnimation);
    }


    public void onFocusSuccess() {
        setImageResource(mFocusSucceedImg);

        mHandler.removeCallbacks(null, null);
        mHandler.postDelayed(() -> setVisibility(View.GONE), 1000);

    }


    public void onFocusFailed() {
        setImageResource(mFocusFailedImg);

        mHandler.removeCallbacks(null, null);
        mHandler.postDelayed(() -> setVisibility(View.GONE), 1000);
    }


    public void setFocusImg(int focus) {
        this.mFocusImg = focus;
    }


    public void setFocusSucceedImg(int focusSucceed) {
        this.mFocusSucceedImg = focusSucceed;
    }
}

