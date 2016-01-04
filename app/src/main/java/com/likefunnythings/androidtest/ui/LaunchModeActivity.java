package com.likefunnythings.androidtest.ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.likefunnythings.androidtest.R;

/**
 * 启动模式的Activity 此Activity为Stander的启动模式
 */
public class LaunchModeActivity extends ActionBarActivity implements View.OnClickListener {

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

    /**
     * Android应用程序里面的Activity通常是放在一个栈管理，设置不同的LaunchMode可以控制Activity之间的跳转
     * Activity的启动模式：4种。 分别是：stander, singleTop, sigleTask, singleInstance.
     *
     * 1.stander 的 LaunchMode
     * 这种启动模式下的Activity，每次启动这样的Activity，只会往栈压进去一个Activity。多次启动这样Stander的启动模式的Activity会产生多个Activity
     *
     * 2.singleTop 的LaunchMode
     * 这种启动模式下的Activity, 每次启动这样的Activity，首先回去栈顶看看是否存在？如果存在的话，就重用此Activity, 并且调用onNewIntent方法; 如果不存在的话，那么另起一个Activity,并且把它压到栈顶。
     *
     * 3.singleTask 的LaunchMode
     * 这种启动模式下的Activity, 每次启动这样的Activity, 首先回去栈里看看是否存在这样的Activity?如果存在这样的Activity, 就将栈里此Activity上面的Acitivity全部弹出栈，然后重用此Activity，并且调用onNewIntent方法; 如果不存在的话，那么另起一个Activity,并且把它压到栈顶
     *
     * 4.singleInstance 的LaunchMode
     * 这种启动模式下的Activity, 会用一个新的栈创建一个该Activity的实例，多个应用共享这个栈读的Activity实例，重复使用该Activity会重新调用onNewIntent方法。
     *
     */

}
