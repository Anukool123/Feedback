package myapplication.examples.com.myapplication;

import android.app.Application;
import android.util.Log;

import com.sdk.feedback.Setup;

/**
 * Copyright(c) Philips Electronics India Ltd
 * All rights reserved. Reproduction in whole or in part is prohibited without
 * the written consent of the copyright holder.
 * Project           : Feedback
 * File Name         : MyApplication
 * Description       : TODO: Enter description
 * Revision History: version 1:
 * Date: 4/7/2016
 * Original author: Bhanu Hirawat
 * Description: Initial version
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Setup.registerForFeedback(getApplicationContext(), "xyz");
    }

    @Override
    public void onTerminate() {
        Log.i("Application","onTerminate");
        super.onTerminate();
    }
}
