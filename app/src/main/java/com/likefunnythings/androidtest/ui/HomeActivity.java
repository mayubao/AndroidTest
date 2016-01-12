package com.likefunnythings.androidtest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.likefunnythings.androidtest.R;

/**
 * 主界面
 */
public class HomeActivity extends ActionBarActivity implements View.OnClickListener {

    Button btn_screen_orientation;
    Button btn_animation_test;
    Button btn_launch_mode;
    Button btn_bitmap;
    Button btn_broadcast_receiver;
    Button btn_custome_view1;
    Button btn_custome_view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        intitView();
    }

    /**
     * 初始化界面
     */
    private void intitView() {
        btn_screen_orientation = (Button) this.findViewById(R.id.btn_screen_orientation);
        btn_animation_test = (Button) this.findViewById(R.id.btn_animation_test);
        btn_launch_mode = (Button) this.findViewById(R.id.btn_launch_mode);
        btn_bitmap = (Button) this.findViewById(R.id.btn_bitmap);
        btn_broadcast_receiver = (Button) this.findViewById(R.id.btn_broadcast_receiver);
        btn_custome_view1 = (Button) this.findViewById(R.id.btn_custome_view1);
        btn_custome_view2 = (Button) this.findViewById(R.id.btn_custome_view2);

        btn_screen_orientation.setOnClickListener(this);
        btn_animation_test.setOnClickListener(this);
        btn_launch_mode.setOnClickListener(this);
        btn_bitmap.setOnClickListener(this);
        btn_broadcast_receiver.setOnClickListener(this);
        btn_custome_view1.setOnClickListener(this);
        btn_custome_view2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_screen_orientation:{
               startActivity(ScreenOrientationActivity.class);
               break;
           }

           case R.id.btn_animation_test:{
               startActivity(AnimationActivity.class);
               break;
           }
           case R.id.btn_launch_mode:{
               startActivity(LaunchModeActivity.class);
               break;
           }
           case R.id.btn_bitmap:{
               startActivity(BitmapActivity.class);
               break;
           }
           case R.id.btn_broadcast_receiver:{
               startActivity(BroadcastReciverActivity.class);
               break;
           }
           case R.id.btn_custome_view1:{
               startActivity(CustomeView1Activity.class);
               break;
           }
           case R.id.btn_custome_view2:{
               startActivity(CustomeImageViewActivity.class);
               break;
           }
           default:
       }
    }


    private void startActivity(Class clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
