package com.sdk.feedback.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sdk.feedback.R;
import com.sdk.feedback.Screenshot;
import com.sdk.feedback.fragmentfactory.BaseFragment;
import com.sdk.feedback.util.AppConstants;
import com.sdk.feedback.util.AppUtility;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by 310124463 on 4/11/2016.
 */
public class ScreenshotDisplayFragment extends BaseFragment {

    public static final String TAG = Screenshot.class.getSimpleName();

    private String imagePath;

    private ImageView com_sdk_feedback_screenshot_image;

    private Button com_sdk_feedback_action_button_instruction_ok, com_sdk_feedback_action_screen_shot_save, com_sdk_feedback_action_screen_shot_cancel;

    private RelativeLayout com_sdk_feedback_instruction_to_save;

    private LinearLayout com_sdk_feedback_action_button_layout;

    Bitmap mCapturedScreen, mEditedCapturedScreen;
    Canvas mCanvas;
    Paint mPaint;
    Matrix mMatrix;
    float mTouchDownX = 0;
    float mTouchDownY = 0;
    float mTouchUpX = 0;
    float mTouchUpY = 0;


    /*
    1. Draw on screen or tap save
    2. Save and cancel button
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.com_sdk_feedback_screenshot_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            imagePath = bundle.getString(AppConstants.IMAGE_PATH, null);
        }

        com_sdk_feedback_action_button_layout = (LinearLayout) getView().findViewById(R.id.com_sdk_feedback_action_button_layout);

        com_sdk_feedback_screenshot_image = (ImageView) getView().findViewById(R.id.com_sdk_feedback_screenshot_image);

        com_sdk_feedback_instruction_to_save = (RelativeLayout) getView().findViewById(R.id.com_sdk_feedback_instruction_to_save);

        com_sdk_feedback_action_button_instruction_ok = (Button) getView().findViewById(R.id.com_sdk_feedback_action_button_instruction_ok);

        com_sdk_feedback_action_button_instruction_ok.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (com_sdk_feedback_instruction_to_save != null && com_sdk_feedback_instruction_to_save.isShown()) {
                            com_sdk_feedback_instruction_to_save.setVisibility(View.GONE);
                        }
                    }
                }
        );

        com_sdk_feedback_action_screen_shot_save = (Button) getView().findViewById(R.id.com_sdk_feedback_action_screen_shot_save);
        com_sdk_feedback_action_screen_shot_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScreenShot();
            }
        });

        com_sdk_feedback_action_screen_shot_cancel = (Button) getView().findViewById(R.id.com_sdk_feedback_action_screen_shot_cancel);
        com_sdk_feedback_action_screen_shot_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discardScreenShot();
            }
        });

        setDrawingCanvas();
    }

    private void setDrawingCanvas() {
        mCapturedScreen = BitmapFactory.decodeFile(imagePath);

        mEditedCapturedScreen = Bitmap.createBitmap(mCapturedScreen.getWidth(), mCapturedScreen
                .getHeight(), mCapturedScreen.getConfig());
        mCanvas = new Canvas(mEditedCapturedScreen);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        mMatrix = new Matrix();
        mCanvas.drawBitmap(mCapturedScreen, mMatrix, mPaint);
        com_sdk_feedback_screenshot_image.setImageBitmap(mEditedCapturedScreen);

        com_sdk_feedback_screenshot_image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        com_sdk_feedback_action_button_layout.setVisibility(View.GONE);
                        mTouchDownX = event.getX();
                        mTouchDownY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mTouchUpX = event.getX();
                        mTouchUpY = event.getY();
                        mCanvas.drawLine(mTouchDownX, mTouchDownY, mTouchUpX, mTouchUpY, mPaint);
                        com_sdk_feedback_screenshot_image.invalidate();
                        mTouchDownX = mTouchUpX;
                        mTouchDownY = mTouchUpY;
                        break;
                    case MotionEvent.ACTION_UP:
                        mTouchUpX = event.getX();
                        mTouchUpY = event.getY();
                        mCanvas.drawLine(mTouchDownX, mTouchDownY, mTouchUpX, mTouchUpY, mPaint);
                        com_sdk_feedback_screenshot_image.invalidate();
                        com_sdk_feedback_action_button_layout.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }

    private void saveScreenShot(){
        if (mEditedCapturedScreen != null) {

            try {
                String mImagePath = AppUtility.getSavedScreenShotPath(getActivity());
                if(mImagePath==null){
                    Toast t = Toast.makeText(getActivity(), "Error while saving", Toast.LENGTH_SHORT);
                    t.show();
                }
                File imageFile = new File(mImagePath);
                FileOutputStream outputStream = new FileOutputStream(imageFile);
                mEditedCapturedScreen.compress(Bitmap.CompressFormat.JPEG, AppConstants.IMAGE_QUALITY, outputStream);
                outputStream.flush();
                outputStream.close();
                mEditedCapturedScreen.recycle();
                discardScreenShot();
                Toast t = Toast.makeText(getActivity(), "Saved!", Toast.LENGTH_SHORT);
                t.show();

            } catch (Exception e) {
                Log.v("EXCEPTION", e.getMessage());
            }
        }
    }

    private void discardScreenShot(){

        File fileToDelete = new File(imagePath);
        if(fileToDelete.exists() && fileToDelete.isFile())
            fileToDelete.delete();
        getActivity().finish();
    }
}
