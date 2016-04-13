package com.sdk.feedback;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.sdk.feedback.util.AppConstants;
import com.sdk.feedback.util.AppUtility;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by 310124463 on 4/11/2016.
 */
public class Screenshot {


    public static String takeScreenShot(Context context) {

        try {
            String mPath = AppUtility.getCapturedScreenShotPath(context);

            if(mPath==null)
                return null;

            System.out.println("Screenshot.takeScreenShot:"+ mPath);

            // create screenCaptured screen capture
            View v1 =((Activity)(context)). getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap screenCaptured = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            //Crop Status bar if visible
            boolean isFullScreen = AppUtility.isFullScreenApp(context);
            int statusBarHeight = AppUtility.getStatusBarHeight(context);

            if(!isFullScreen)
                screenCaptured = Bitmap.createBitmap(screenCaptured,0,statusBarHeight,screenCaptured.getWidth(), screenCaptured.getHeight() - statusBarHeight, null, true);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            screenCaptured.compress(Bitmap.CompressFormat.JPEG, AppConstants.IMAGE_QUALITY, outputStream);
            outputStream.flush();
            outputStream.close();
            screenCaptured.recycle();

            return mPath;

        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
            return null ;
        }
    }

}
