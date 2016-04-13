package com.sdk.feedback;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Environment;
import android.view.View;
import android.view.Window;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by 310124463 on 4/11/2016.
 */
public class Screenshot {


    public static String takeScreenShot(Context context) {

        Date now = new Date();
        Bitmap bitmap = null;

        Bitmap croppedBitmap = null;

        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".png";

            System.out.println("Screenshot.takeScreenShot:"+ mPath);

            // create bitmap screen capture
            View v1 =((Activity)(context)). getWindow().getDecorView();
            v1.setDrawingCacheEnabled(true);
            bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            int statusBarHeight = getStatusBarHeight(context);

            croppedBitmap = Bitmap.createBitmap(bitmap, 0, statusBarHeight, bitmap.getWidth(), bitmap.getHeight() - statusBarHeight, null, true);

            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 70;
            croppedBitmap.compress(Bitmap.CompressFormat.PNG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            return mPath;

        } catch (Throwable e) {
            e.printStackTrace();
            return null ;
        }

        finally
        {
            if(bitmap!=null)
            {
                bitmap.recycle();
                bitmap=null;

            }

            if(croppedBitmap!=null)
            {
                croppedBitmap.recycle();
                croppedBitmap=null;

            }
        }


    }

    private static int getStatusBarHeight(Context context)
    {
        try {
            Rect rectgle = new Rect();
            Window window = ((Activity) context).getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
            int StatusBarHeight = rectgle.top;
            int contentViewTop =
                    window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
            return (contentViewTop - StatusBarHeight);
        }
        catch(Exception e)
        {
            return 0 ;
        }

    }
}
