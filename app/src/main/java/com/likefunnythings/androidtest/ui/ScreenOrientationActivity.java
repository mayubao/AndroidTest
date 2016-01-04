package com.likefunnythings.androidtest.ui;

import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.likefunnythings.androidtest.R;

/**
 * 屏幕切换方向的Activity
 */
public class ScreenOrientationActivity extends ActionBarActivity {

    private static final String TAG = ScreenOrientationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_orientation);
        Log.i(TAG, "======>>>" + "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "======>>>" + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "======>>>" + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "======>>>" + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "======>>>" + "onStart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "======>>>" + "onDestroy");
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "======>>>" + "onConfigurationChanged");
    }
}
