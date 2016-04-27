package com.sdk.feedback.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sdk.feedback.R;
import com.sdk.feedback.Setup;
import com.sdk.feedback.fragmentfactory.BaseFragment;
import com.sdk.feedback.fragmentfactory.factory.FragmentFactory;

/**
 * Created by 310124463 on 4/5/2016.
 */
public class MainMenuFragment extends BaseFragment {

    public static final String TAG = MainMenuFragment.class.getSimpleName();

    private LinearLayout com_sdk_feedback_action_all_feedback,com_sdk_feedback_action_take_screeshot,com_sdk_feedback_action_add_video,com_sdk_feedback_action_close;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.com_sdk_feedback_main_menu_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        com_sdk_feedback_action_all_feedback = (LinearLayout) getView().findViewById(R.id.com_sdk_feedback_action_all_feedback);
        com_sdk_feedback_action_all_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "All Feedback", Toast.LENGTH_LONG).show();
                addFragment(FeedsFragment.TAG);
                //getActivity().finish();
            }
        });

        com_sdk_feedback_action_take_screeshot = (LinearLayout) getView().findViewById(R.id.com_sdk_feedback_action_take_screeshot);
        com_sdk_feedback_action_take_screeshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(),"Take Screenshot",Toast.LENGTH_LONG).show();
                getActivity().finish();
                // From here i have to call a method -
                Setup.setTakeScreenshot(true);
                //
            }
        });

        com_sdk_feedback_action_add_video = (LinearLayout) getView().findViewById(R.id.com_sdk_feedback_action_add_video);
        com_sdk_feedback_action_add_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Add Video",Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        });

        com_sdk_feedback_action_close = (LinearLayout) getView().findViewById(R.id.com_sdk_feedback_action_close);
        com_sdk_feedback_action_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Close",Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        });
    }


    private void addFragment(String fragmentTag)
    {
        BaseFragment fragment = FragmentFactory.getFragment(fragmentTag);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.flLibraryContainerView, fragment);

        ft.commit();
    }

}
