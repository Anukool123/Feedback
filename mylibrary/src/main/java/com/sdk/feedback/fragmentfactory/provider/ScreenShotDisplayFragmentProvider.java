package com.sdk.feedback.fragmentfactory.provider;

import com.sdk.feedback.fragment.ScreenshotDisplayFragment;
import com.sdk.feedback.fragmentfactory.BaseFragment;
import com.sdk.feedback.fragmentfactory.factory.FragmentProvider;

/**
 * Created by 310124463 on 4/11/2016.
 */
public class ScreenShotDisplayFragmentProvider implements FragmentProvider{


    @Override
    public boolean supports(String name) {
        return isScreenshotFragment(name);
    }

    private boolean isScreenshotFragment(String name) {
        if (name.equalsIgnoreCase(ScreenshotDisplayFragment.TAG))
            return true;
        else
            return false;
    }

    @Override
    public BaseFragment getFragment() {
        BaseFragment fragment = new ScreenshotDisplayFragment();
        return fragment;
    }
}
