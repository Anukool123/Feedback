package com.sdk.feedback.util;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.view.WindowManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Copyright(c) Philips Electronics India Ltd
 * All rights reserved. Reproduction in whole or in part is prohibited without
 * the written consent of the copyright holder.
 * Project           : My Application
 * File Name         : AppUtility
 * Description       : TODO: Enter description
 * Revision History: version 1:
 * Date: 4/2/2016
 * Original author: Bhanu Hirawat
 * Description: Initial version
 */
public class AppUtility {

    public static boolean isFullScreenApp(Context context) {
        return (((Activity)(context)).getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
    }


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = ((Activity)(context)).getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = ((Activity)(context)).getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static File getFeedbackStorageDirectory(Context context){
        File feedbackStorageDirectory = null;

        feedbackStorageDirectory = new File(Environment.getExternalStorageDirectory(),context.getPackageName());

        // Create the storage directory if it does not exist
        if (!feedbackStorageDirectory.exists()) {
            if (!feedbackStorageDirectory.mkdirs()) {
                System.out.print("failed to create directory");
                return null;
            }
        }
        return feedbackStorageDirectory;
    }

    public static String getCapturedScreenShotPath(Context context) {
        File feedbackStorageDirectory = getFeedbackStorageDirectory(context);
        if(feedbackStorageDirectory!=null)
            return feedbackStorageDirectory.toString() + "/" + AppConstants.IMAGE_NAME + AppConstants.IMAGE_EXTENSION;
        return null;
    }

    public static String getSavedScreenShotPath(Context context) {
        File feedbackStorageDirectory = getFeedbackStorageDirectory(context);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        if(feedbackStorageDirectory!=null)
            return feedbackStorageDirectory.toString() + "/" + "IMG_"+timeStamp + AppConstants.IMAGE_EXTENSION;
        return null;
    }

}
