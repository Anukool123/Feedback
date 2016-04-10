package com.sdk.feedback.widget;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.sdk.feedback.R;


public class FloatingButtonIcon extends RelativeLayout {

    public FloatingButtonIcon(final Context context) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.com_sdk_feedback_floating_button, this, true);
    }
}
