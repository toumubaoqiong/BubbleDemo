package com.example.vince.bubbledemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.vince.bubbledemo.R;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_menu);
    }

    public void onClick(View v){

        switch(v.getId()){
            case R.id.Button_pic:
                startActivity(new Intent(MenuActivity.this,IconBubbleActivity.class));
                break;
            case R.id.Button_circle:
                startActivity(new Intent(MenuActivity.this,CircleBubbleActivity.class));
                break;
        }
    }
}
