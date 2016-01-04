package com.likefunnythings.androidtest.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.likefunnythings.androidtest.R;

/**
 * BroadcastReceiver的测试
 */
public class BroadcastReciverActivity extends ActionBarActivity implements View.OnClickListener {

    Button btn_register_broadcast_receiver;

    MyCodeReceiver mCodeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcase_reciver);

        initView();

        regReceiver();

    }

    /**
     * 注册广播
     */
    private void regReceiver() {
        mCodeReceiver = new MyCodeReceiver();
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(MyCodeReceiver.ACTION_SEND);
        intentfilter.addAction(MyCodeReceiver.ACTION_RECEVIE);
        registerReceiver(mCodeReceiver, intentfilter);
    }

    private void initView() {
        btn_register_broadcast_receiver = (Button) this.findViewById(R.id.btn_register_broadcast_receiver);
        btn_register_broadcast_receiver.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register_broadcast_receiver:{

                Intent intent = new Intent(MyCodeReceiver.ACTION_SEND);
                sendBroadcast(intent);
                break;
            }
            default:
        }
    }

    @Override
    protected void onDestroy() {
        if(null != mCodeReceiver){
            unregisterReceiver(mCodeReceiver);
        }

        super.onDestroy();
    }

    /**
     * 代码中注册广播的方式
     */
    class MyCodeReceiver extends BroadcastReceiver{

        public static final String ACTION_SEND = "ACTION_SEND";
        public static final String ACTION_RECEVIE = "ACTION_RECEVIE";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("MyReceiver", "------>>>" + intent.getAction());
        }
    }
}
