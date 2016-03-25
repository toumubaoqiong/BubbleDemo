package com.example.vince.bubbledemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.vince.bubbledemo.R;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
