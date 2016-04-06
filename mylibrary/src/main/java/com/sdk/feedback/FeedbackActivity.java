package com.sdk.feedback;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.com_sdk_feedback_activity);

        addFragment();
    }

    private void addFragment()
    {
        InstructionFragment fragment = new InstructionFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.flLibraryContainerView,fragment);

        ft.commit();
    }
}
