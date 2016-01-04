package com.likefunnythings.aidlserver;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "AIDLService";

    Button btn_bind_service;
    Button btn_unbind_service;

    Context context;


    IAidlService iAidlServce;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "======>>>onServiceConnected ");
            iAidlServce = IAidlService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iAidlServce = null;
            Log.i(TAG, "======>>>onServiceDisconnected ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        initView();
    }

    private void initView() {
        btn_bind_service = (Button) this.findViewById(R.id.btn_bind_service);
        btn_unbind_service = (Button) this.findViewById(R.id.btn_unbind_service);


        btn_bind_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "btn_bind_service click", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "======>>>btn_bind_service click");

//                Intent intent = new Intent(context, MyService.class);
//                startService(intent);

                Intent intent = new Intent(context, MyService.class);

                bindService(intent,conn, Service.BIND_AUTO_CREATE);
            }
        });

        btn_unbind_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "btn_unbind_service click", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "======>>>btn_unbind_service click");

                Intent intent = new Intent(context, MyService.class);
                stopService(intent);
            }
        });

    }
}
