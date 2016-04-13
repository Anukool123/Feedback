package com.sdk.feedback;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.sdk.feedback.fragment.InstructionFragment;
import com.sdk.feedback.fragment.MainMenuFragment;
import com.sdk.feedback.fragment.ScreenshotDisplayFragment;
import com.sdk.feedback.util.AppConstants;

/**
 * Copyright(c) Philips Electronics India Ltd
 * All rights reserved. Reproduction in whole or in part is prohibited without
 * the written consent of the copyright holder.
 * Project           : My Application
 * File Name         : Setup
 * Description       : Handle to use feedback SDK
 * Revision History: version 1:
 * Date: 4/1/2016
 * Original author: Bhanu Hirawat
 * Description: Initial version
 */
public class Setup {

    public static final String TAG = Setup.class.getSimpleName();

    static WindowManager windowManager;
    static RelativeLayout floatingButton;
    static Context mContext;
    static WindowManager.LayoutParams params;
    static int lastYPosition = 0;

    public static void setTakeScreenshot(boolean takeScreenshot) {
        Setup.takeScreenshot = takeScreenshot;
    }

    private static boolean takeScreenshot = false;
//    private int requestCode;
//    private int resultCode;
//    private Intent data;


    synchronized public static void registerForFeedback(Context context, String apiKey) {
        Log.e(TAG, "registerForFeedback");
        startActivityWithFragment(context, InstructionFragment.TAG);
    }

    synchronized public static void enable(final Context context, String apiKey){
        Log.e(TAG, "enable");
        mContext = context;

        if(takeScreenshot)
        {
            // Take screen shot
            String imagePath = Screenshot.takeScreenShot(mContext);

            if(imagePath !=null) {
                startActivityWithDataForFragment(mContext, ScreenshotDisplayFragment.TAG, imagePath);
            }
            takeScreenshot = false;
        }

        else {
            addFloatingButton(mContext);

            addTouchEventToFloatingButton(mContext);
        }
    }


    synchronized public static void disable(){
        Log.e(TAG, "disable");
        removeFloatingButton();
        params = null;
    }

    private static void addFloatingButton(Context context) {
        windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);

        if(floatingButton == null) {
            floatingButton = new com.sdk.feedback.widget.FloatingButtonIcon(context);
            floatingButton.setTranslationX(30);
        }

        if(params == null) {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);

            params.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION;
            params.y = lastYPosition;
        }

        if(!floatingButton.isShown())
            windowManager.addView(floatingButton, params);
    }

    private static void addTouchEventToFloatingButton(final Context context) {
        floatingButton.setOnTouchListener(new View.OnTouchListener() {
            private int originalX = params.x;
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i(TAG, "ACTION_DOWN " + originalX);
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.i(TAG, "ACTION_UP " + originalX);
                        if ((Math.abs(initialTouchX - event.getRawX()) < 5) && (Math.abs(initialTouchY - event.getRawY()) < 5)) {
                            Log.e(TAG, "It's a click ! ");
                            onFloatingButtonClicked(context);
                        } else {
                            Log.e(TAG, "you moved the head");
                            params.x = originalX;
                            windowManager.updateViewLayout(floatingButton, params);
                            floatingButton.setTranslationX(30);
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        floatingButton.setTranslationX(0);
                        params.x = initialX + (int) (initialTouchX - event.getRawX());
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        lastYPosition = params.y;
                        windowManager.updateViewLayout(floatingButton, params);
                        return true;
                }
                return false;
            }
        });
    }

    private static void onFloatingButtonClicked(Context context) {
        startActivityWithFragment(context, MainMenuFragment.TAG);
    }

    private static void startActivityWithFragment(Context context, String tag) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        if(tag!=null){
            intent.putExtra(AppConstants.FRAGMENT_TAG, tag);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    private static void startActivityWithDataForFragment(Context context, String tag,String data) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        if(tag!=null){
            intent.putExtra(AppConstants.FRAGMENT_TAG, tag);
            intent.putExtra(AppConstants.IMAGE_PATH,data);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }


    private static void removeFloatingButton() {
        if (floatingButton != null &&  floatingButton.isShown()) {
            windowManager.removeView(floatingButton);
            floatingButton = null;
        }
    }

//    public void checkDrawOverlayPermission() {
//        if (!Settings.canDrawOverlays(mContext)) {
//            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                    Uri.parse("package:" + mContext.getPackageName()));
//            mContext.startActivityForResult(intent, 1);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
//        this.requestCode = requestCode;
//        this.resultCode = resultCode;
//        this.data = data;
//        if (requestCode == 1) {
//            if (Settings.canDrawOverlays(mContext)) {
//                // continue here - permission was granted
//            }
//        }
//    }
}
