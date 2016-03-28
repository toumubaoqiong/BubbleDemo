package com.example.vince.bubbledemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.vince.bubbledemo.R;
import com.example.vince.bubbledemo.control.BubbleIconLayout;

/**
 *description:实现气泡从水底升起的动画
 *author:vince
 */
public class IconBubbleActivity extends Activity {

    private BubbleIconLayout mBubbleIconLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_icon);

        mBubbleIconLayout = (BubbleIconLayout)findViewById(R.id.Bubble_icon);
    }

    @Override
    protected void onResume() {
        mBubbleIconLayout.setIsVisible(true);
        mBubbleIconLayout.invalidate();
        super.onResume();
    }

    @Override
    protected void onStop() {
        mBubbleIconLayout.setIsVisible(false);
        mBubbleIconLayout.invalidate();
        super.onStop();
    }
}
