package com.sdk.feedback.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.sdk.feedback.R;

/**
 * Created by 310124463 on 4/27/2016.
 */
public class SharedPreferenceHelper {
    public static void setFirstLaunchDone(Context context) {

        try {
            SharedPreferences prefs = context.getSharedPreferences(context.getResources().
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(context.getResources().getString(R.string.first_launch_done), true);
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean isFirstLaunchDone(Context context) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(context.getResources().
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            boolean isFirstLaunchDone = prefs.getBoolean(context.getResources().getString(R.string.first_launch_done), false);

            return isFirstLaunchDone;
        } catch (Exception e) {
            return false;
        }
    }
}
