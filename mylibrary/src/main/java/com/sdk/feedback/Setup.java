package com.sdk.feedback;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Copyright(c) Philips Electronics India Ltd
 * All rights reserved. Reproduction in whole or in part is prohibited without
 * the written consent of the copyright holder.
 * Project           : My Application
 * File Name         : Setup
 * Description       : TODO: Enter description
 * Revision History: version 1:
 * Date: 4/1/2016
 * Original author: Bhanu Hirawat
 * Description: Initial version
 */
public class Setup {

    public static final String TAG = Setup.class.getSimpleName();

    static WindowManager windowManager;
    static RelativeLayout chatHead;
    static Activity mContext;
    static WindowManager.LayoutParams params;
    private int requestCode;
    private int resultCode;
    private Intent data;

    public static void enable(final Activity activity, String apiKey){

        mContext = activity;
        windowManager = (WindowManager) activity.getSystemService(activity.WINDOW_SERVICE);

        chatHead = new com.sdk.feedback.widget.floatingButton(activity);
        chatHead.setTranslationX(30);

        if(params == null) {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);

            params.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        }

        chatHead.setOnTouchListener(new View.OnTouchListener() {
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
                        if( (Math.abs(initialTouchX - event.getRawX())<5) && (Math.abs(initialTouchY - event.getRawY())<5) )
                        {
                            Log.e(TAG,"It's a click ! ");
                            onFloatingButtonClicked(activity);
                        }
                        else {
                            Log.e(TAG, "you moved the head");
                            params.x = originalX;
                            windowManager.updateViewLayout(chatHead, params);
                            chatHead.setTranslationX(30);
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        chatHead.setTranslationX(0);
                        params.x = initialX + (int) (initialTouchX - event.getRawX());
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(chatHead, params);
                        return true;
                }
                return false;
            }
        });

        windowManager.addView(chatHead, params);
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

    private static void onFloatingButtonClicked(Activity activity) {
        Intent intent = new Intent(activity, FeedbackActivity.class);
        activity.startActivity(intent);
        removeFloatingButton();
    }

    public static void disable(){
        removeFloatingButton();
        params = null;
    }

    private static void removeFloatingButton() {
        if (chatHead != null) {
            windowManager.removeView(chatHead);
            chatHead = null;
        }
    }
}
