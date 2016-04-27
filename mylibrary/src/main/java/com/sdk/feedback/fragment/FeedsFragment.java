package com.sdk.feedback.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.sdk.feedback.R;
import com.sdk.feedback.adapter.MyRecyclerAdapter;
import com.sdk.feedback.fragmentfactory.BaseFragment;
import com.sdk.feedback.model.Message;

import java.util.ArrayList;
import java.util.List;

;

public class FeedsFragment extends BaseFragment {


    public static final String TAG = BaseFragment.class.getSimpleName() ;

    private RecyclerView mRecyclerView ;
    private RecyclerView.Adapter adapter;
    private EditText sendText;
    private ImageView ivSend;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.com_sdk_feedback_chat_fragment,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initList();
    }

    // Testing
    private List<Message> getListOfMessages()
    {
        List<Message> messages = new ArrayList<Message>();

        Message m = new Message("","Anukool","yesterday",true,"this screenshot has a problem and i think we can change this .",5 );

        messages.add(m);

        messages.add(m);

        messages.add(m);

        messages.add(m);

        messages.add(m);

        messages.add(m);

        messages.add(m);

        messages.add(m);

        return messages ;
    }




    private void initList() {
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.rvMessages);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new MyRecyclerAdapter(getActivity(), getListOfMessages());
        mRecyclerView.setAdapter(adapter);
    }

}
