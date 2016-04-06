package com.sdk.feedback.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

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

    private static int sScreenWidth = -1;

    public static int getScreenWidth(Context context) {
        if (sScreenWidth == -1) {
            Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            sScreenWidth = size.x;
        }
        return sScreenWidth;
    }
}
