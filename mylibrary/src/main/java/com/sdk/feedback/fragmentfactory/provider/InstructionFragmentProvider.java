/**
 * Copyright(c) Philips Electronics India Ltd
 * All rights reserved. Reproduction in whole or in part is prohibited without
 * the written consent of the copyright holder.
 * Project           : AutoPilot
 * Revision History: version 1:
 * Description: Initial version
 */

package com.sdk.feedback.fragmentfactory.provider;

import com.sdk.feedback.fragment.InstructionFragment;
import com.sdk.feedback.fragmentfactory.BaseFragment;
import com.sdk.feedback.fragmentfactory.factory.FragmentProvider;


/**
 * Created by 310169505 on 1/11/2016.
 */
public class InstructionFragmentProvider implements FragmentProvider {

    @Override
    public boolean supports(String name) {
        return isInstructionFragment(name);
    }

    private boolean isInstructionFragment(String name) {
        if (name.equalsIgnoreCase(InstructionFragment.TAG))
            return true;
        else
            return false;
    }

    @Override
    public BaseFragment getFragment() {
        BaseFragment fragment = new InstructionFragment();
        return fragment;
    }

}


