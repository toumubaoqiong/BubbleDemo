package com.example.vince.bubbledemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.vince.bubbledemo.R;
import com.example.vince.bubbledemo.control.BubbleLayout;

/**
 *description:实现气泡从水底升起的动画
 *author:vince
 */
public class CircleBubbleActivity extends Activity {

    private BubbleLayout mBubbleLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_circle);

        mBubbleLayout = (BubbleLayout)findViewById(R.id.Bubble_circle);
    }

    @Override
    protected void onResume() {
        mBubbleLayout.setIsVisible(true);
        mBubbleLayout.setStarting(false);
        mBubbleLayout.invalidate();
        super.onResume();
    }

    @Override
    protected void onStop() {
        mBubbleLayout.setIsVisible(true);
        mBubbleLayout.setStarting(false);
        mBubbleLayout.invalidate();
        super.onStop();
    }
}
