package com.sdk.feedback.fragment;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.sdk.feedback.R;
import com.sdk.feedback.fragmentfactory.BaseFragment;
import com.sdk.feedback.widget.FloatingButtonIcon;

/**
 * Created by 310124463 on 4/5/2016.
 */
public class InstructionFragment extends BaseFragment {

    public static final String TAG = InstructionFragment.class.getSimpleName();

    private Button con_sdk_feedback_action_button_instruction_ok;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.com_sdk_feedback_instruction_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        con_sdk_feedback_action_button_instruction_ok = (Button) getView().findViewById(R.id.con_sdk_feedback_action_button_instruction_ok);
        con_sdk_feedback_action_button_instruction_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        addFloatingButton(getActivity());

    }

    private static void addFloatingButton(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        FloatingButtonIcon floatingButton = new com.sdk.feedback.widget.FloatingButtonIcon(context);
        floatingButton.setTranslationX(30);


        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        params.type = WindowManager.LayoutParams.TYPE_APPLICATION;

        windowManager.addView(floatingButton, params);
    }
}
