package com.sdk.feedback;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sdk.feedback.fragmentfactory.BaseFragment;
import com.sdk.feedback.fragmentfactory.factory.FragmentFactory;
import com.sdk.feedback.util.AppConstants;

public class FeedbackActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.com_sdk_feedback_activity);

        if(getIntent().getStringExtra(AppConstants.FRAGMENT_TAG)!= null)
            addFragment(getIntent().getStringExtra(AppConstants.FRAGMENT_TAG));
        else
            finish();
    }

    private void addFragment(String fragmentTag)
    {
        BaseFragment fragment = FragmentFactory.getFragment(fragmentTag);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.flLibraryContainerView,fragment);

        ft.commit();
    }
}
