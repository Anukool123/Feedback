package com.sdk.feedback.fragmentfactory.provider;

import com.sdk.feedback.fragment.FeedsFragment;
import com.sdk.feedback.fragmentfactory.BaseFragment;
import com.sdk.feedback.fragmentfactory.factory.FragmentProvider;

/**
 * Created by 310124463 on 4/26/2016.
 */
public class FeedsFragmentProvider implements FragmentProvider {

    @Override
    public boolean supports(String name) {
        return isFeedsFragment(name);
    }

    private boolean isFeedsFragment(String name) {
        if (name.equalsIgnoreCase(FeedsFragment.TAG))
            return true;
        else
            return false;
    }

    @Override
    public BaseFragment getFragment() {
        BaseFragment fragment = new FeedsFragment();
        return fragment;
    }

}
