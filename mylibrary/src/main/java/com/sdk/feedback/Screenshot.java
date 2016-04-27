package com.sdk.feedback;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.sdk.feedback.util.AppConstants;
import com.sdk.feedback.util.AppUtility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 310124463 on 4/11/2016.
 */
public class Screenshot {


    private static final String TAG = Screenshot.class.getSimpleName() ;

    public static String takeScreenShot(Context context) {

        Bitmap screenCaptured = null;
        try {

            // image naming and path  to include sd card  appending name you choose for file
            String mPath = AppUtility.getCapturedScreenShotPath(context);

            if(mPath==null)
                return null;

            System.out.println("Screenshot.takeScreenShot:"+ mPath);

            // create bitmap screen capture
            View rootView =((Activity)(context)). getWindow().getDecorView().getRootView();
            rootView.setDrawingCacheEnabled(true);
            screenCaptured = Bitmap.createBitmap(rootView.getDrawingCache());
          //  rootView.setDrawingCacheEnabled(false);

            List<TextureView> textureViews = getAllTextureViews(rootView);

            Log.i(TAG, "" + textureViews.size());

            Canvas canvas = new Canvas(screenCaptured);

            if(textureViews.size()>0)
            {


                for(TextureView textureView : textureViews)
                {
                    Bitmap bitmapForTextureView = textureView.getBitmap(textureView.getWidth(),textureView.getHeight());
                    int[] location = new int[2];
                    textureView.getLocationInWindow(location);
                    int[] location2 = new int[2];
                    textureView.getLocationOnScreen(location2);
                    canvas.drawBitmap(bitmapForTextureView, location[0], location[1], null);

                }
            }

            List<SurfaceView> surfaceViews = getAllSurfaceViews(rootView);
            Log.i(TAG, "" + surfaceViews.size());

            for(SurfaceView surfaceView : surfaceViews)
            {
                Bitmap bitmapForTextureView = getBitmap(surfaceView,surfaceView.getWidth(), surfaceView.getHeight());
                int[] location = new int[2];
                surfaceView.getLocationInWindow(location);
                int[] location2 = new int[2];
                surfaceView.getLocationOnScreen(location2);
                canvas.drawBitmap(bitmapForTextureView, location[0], location[1], null);

            }


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

            return mPath;

        } catch (Throwable e) {
            e.printStackTrace();
            return null ;
        }

        finally
        {
            if(screenCaptured!=null)
            {
                screenCaptured.recycle();
                screenCaptured=null;
            }
        }


    }

    private static Bitmap getBitmap(SurfaceView surfaceView, int width, int height) {

        surfaceView.getDrawingCache();

        SurfaceHolder holder = surfaceView.getHolder();

        Bitmap.Config conf = Bitmap.Config.RGB_565;

        Bitmap image = Bitmap.createBitmap(width , height, conf);

        Canvas canvas  = holder.lockCanvas(null);

        canvas.setBitmap(image);

        return null;
    }


    public static  List<TextureView> getAllTextureViews(View view)
    {
        List<TextureView> textureViews = new ArrayList<TextureView>();
        if (view instanceof TextureView) {
            textureViews.add((TextureView) view);
        }
        else if(view instanceof ViewGroup)
        {
            ViewGroup viewGroup = (ViewGroup)view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                textureViews.addAll(getAllTextureViews(viewGroup.getChildAt(i)));
            }
        }
        return textureViews;
    }


    public static  List<SurfaceView> getAllSurfaceViews(View view)
    {
        List<SurfaceView> surfaceViews = new ArrayList<SurfaceView>();
        if (view instanceof SurfaceView) {
            surfaceViews.add((SurfaceView)view);
        }
        else if(view instanceof ViewGroup)
        {
            ViewGroup viewGroup = (ViewGroup)view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                surfaceViews.addAll(getAllSurfaceViews(viewGroup.getChildAt(i)));
            }
        }
        return surfaceViews;
    }

//    private static int getStatusBarHeight(Context context)
//    {
//        try {
//            Rect rectgle = new Rect();
//            Window window = ((Activity) context).getWindow();
//            window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
//            int StatusBarHeight = rectgle.top;
//            int contentViewTop =
//                    window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
//            return (contentViewTop - StatusBarHeight);
//        }
//        catch(Exception e)
//        {
//            return 0 ;
//        }
//
//    }
}
