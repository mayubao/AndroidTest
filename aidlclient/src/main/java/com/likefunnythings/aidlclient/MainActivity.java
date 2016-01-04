package com.likefunnythings.aidlclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.likefunnythings.aidlserver.IAidlService;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "com.likefunnythings.aidlclient." + MainActivity.class.getSimpleName();
    Button btn;

    IAidlService iAidlService;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "======>>>onServiceConnected");
            iAidlService = IAidlService.Stub.asInterface(service);
            int result = 0;
            try {
                result = iAidlService.getType();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.i(TAG,"远程调用的结果------------" + result + "-------------");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "======>>>onServiceDisconnected");
            iAidlService = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btn = (Button) this.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "btn======>>>onClick");


                Intent intent = new Intent();
                intent.setAction("com.likefunnythings.aidlserver.MyService");
                bindService(intent, conn, Service.BIND_AUTO_CREATE);

            }
        });
    }


}
