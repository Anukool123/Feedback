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
import com.sdk.feedback.fragmentfactory.provider.FeedsFragmentProvider;
import com.sdk.feedback.fragmentfactory.provider.InstructionFragmentProvider;
import com.sdk.feedback.fragmentfactory.provider.MainMenuFragmentProvider;
import com.sdk.feedback.fragmentfactory.provider.ScreenShotDisplayFragmentProvider;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("deprecation")
public class FragmentFactory {

	private static List<FragmentProvider> mProviders = getProviders();

	private static List<FragmentProvider> getProviders() {
		return Arrays.asList(new InstructionFragmentProvider(),
				new MainMenuFragmentProvider(),
				new ScreenShotDisplayFragmentProvider(),
				new FeedsFragmentProvider());
	}

	public static BaseFragment getFragment(String name) {
		for (FragmentProvider provider : mProviders) {
			if (provider.supports(name)) {
				return provider.getFragment();
			}
		}
		return null;

	}

}
