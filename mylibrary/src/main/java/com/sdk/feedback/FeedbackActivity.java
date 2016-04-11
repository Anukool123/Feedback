package com.sdk.feedback;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sdk.feedback.fragment.ScreenshotDisplayFragment;
import com.sdk.feedback.fragmentfactory.BaseFragment;
import com.sdk.feedback.fragmentfactory.factory.FragmentFactory;
import com.sdk.feedback.util.AppConstants;

public class FeedbackActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.com_sdk_feedback_activity);


        // This definitely needs refactoring sometime later
        if(getIntent().getStringExtra(AppConstants.FRAGMENT_TAG)!= ScreenshotDisplayFragment.TAG)
        {
            Bundle bundle = new Bundle ();
            bundle.putString(AppConstants.IMAGE_PATH,getIntent().getStringExtra(AppConstants.IMAGE_PATH));
            addFragmentWithData(getIntent().getStringExtra(AppConstants.FRAGMENT_TAG),bundle);
        }

        else if(getIntent().getStringExtra(AppConstants.FRAGMENT_TAG)!= null)
        {
            addFragment(getIntent().getStringExtra(AppConstants.FRAGMENT_TAG));
        }
        else
            finish();
    }

    private void addFragment(String fragmentTag)
    {
        BaseFragment fragment = FragmentFactory.getFragment(fragmentTag);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.flLibraryContainerView, fragment);

        ft.commit();
    }

    private void addFragmentWithData(String fragmentTag, Bundle bundle)
    {
        BaseFragment fragment = FragmentFactory.getFragment(fragmentTag);

        fragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.flLibraryContainerView,fragment);

        ft.commit();
    }
}
