/**
 * Copyright(c) Philips Electronics India Ltd
 * All rights reserved. Reproduction in whole or in part is prohibited without
 * the written consent of the copyright holder.
 * Project           : AutoPilot
 * Revision History: version 1:
 * Description: Initial version
 */

package com.sdk.feedback.fragmentfactory.factory;


import com.sdk.feedback.fragmentfactory.BaseFragment;

public interface FragmentProvider
{
	 boolean supports(String name);
	 BaseFragment getFragment();
	
}
