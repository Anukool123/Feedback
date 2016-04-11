package com.sdk.feedback.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sdk.feedback.R;
import com.sdk.feedback.Screenshot;
import com.sdk.feedback.fragmentfactory.BaseFragment;
import com.sdk.feedback.util.AppConstants;

/**
 * Created by 310124463 on 4/11/2016.
 */
public class ScreenshotDisplayFragment extends BaseFragment {

    public static final String TAG = Screenshot.class.getSimpleName();

    private String imagePath;

    private ImageView ivScreenShot;

    private Button btnOk;

    private RelativeLayout rlInstructionToSave;

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

        ivScreenShot = (ImageView) getView().findViewById(R.id.ivScreenshotImage);

        ivScreenShot.setImageBitmap(BitmapFactory.decodeFile(imagePath, null));

        rlInstructionToSave = (RelativeLayout) getView().findViewById(R.id.rlInstructionToSave);

        btnOk = (Button) getView().findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         if (rlInstructionToSave != null && rlInstructionToSave.isShown()) {
                                             rlInstructionToSave.setVisibility(View.GONE);
                                         }
                                     }
                                 }
        );


    }
}
