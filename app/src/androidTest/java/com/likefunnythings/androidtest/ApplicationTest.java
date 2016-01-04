package com.likefunnythings.androidtest;

import android.app.Application;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
//    BitmapFactory
    Handler handler;
    public ApplicationTest() {
        super(Application.class);
    }
}