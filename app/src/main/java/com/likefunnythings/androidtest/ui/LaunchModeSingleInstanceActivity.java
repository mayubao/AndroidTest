package com.likefunnythings.androidtest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.likefunnythings.androidtest.R;

public class LaunchModeSingleInstanceActivity extends ActionBarActivity implements View.OnClickListener {

    Button btn_activity_stander;
    Button btn_activity_single_top;
    Button btn_activity_single_task;
    Button btn_activity_single_instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);

//        setTitle("Stander");
        initView();
    }

    private void initView() {
        btn_activity_stander = (Button) this.findViewById(R.id.btn_activity_stander);
        btn_activity_single_top = (Button) this.findViewById(R.id.btn_activity_single_top);
        btn_activity_single_task = (Button) this.findViewById(R.id.btn_activity_single_task);
        btn_activity_single_instance = (Button) this.findViewById(R.id.btn_activity_single_instance);

        btn_activity_stander.setOnClickListener(this);
        btn_activity_single_top.setOnClickListener(this);
        btn_activity_single_task.setOnClickListener(this);
        btn_activity_single_instance.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_activity_stander:{
                startActivity(LaunchModeActivity.class);
                break;
            }
            case R.id.btn_activity_single_top:{
                startActivity(LaunchModeSingleTopActivity.class);
                break;
            }
            case R.id.btn_activity_single_task:{
                startActivity(LaunchModeSingleTaskActivity.class);
                break;
            }
            case R.id.btn_activity_single_instance:{
                startActivity(LaunchModeSingleInstanceActivity.class);
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
