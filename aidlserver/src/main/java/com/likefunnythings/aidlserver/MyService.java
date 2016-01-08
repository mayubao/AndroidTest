package com.likefunnythings.aidlserver;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private static final String TAG = "com.likefunnythings.aidlserver." + MyService.class.getSimpleName();
    Context context;

    IBinder binder = new IAidlService.Stub(){

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getType() throws RemoteException {
            return 5;
        }
    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return binder;
    }


    @Override
    public void onCreate() {
        context = this;
        Toast.makeText(context, "MyService onCreate ", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "======>>>onCreate ");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(context, "MyService onStart", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "======>>>onStart ");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "======>>>onStartCommand ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "======>>>onDestroy ");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "======>>>onUnbind ");
        return super.onUnbind(intent);
    }
}
