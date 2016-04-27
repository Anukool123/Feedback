package com.sdk.feedback.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdk.feedback.R;
import com.sdk.feedback.model.Message;

import java.util.List;

/**
 * Created by 310124463 on 4/26/2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> implements View.OnClickListener{
    private static final String TAG =MyRecyclerAdapter.class.getSimpleName() ;
    private Context mContext;
    private List<Message> messages;
    public MyRecyclerAdapter(Context chatActivity, List<Message> messages) {
        this.mContext =chatActivity;
        this.messages = messages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.com_sdk_feedback_message_item, null);

        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.ivScreenshot.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return (null != messages ? messages.size() : 0);
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "On Click");

    }



    public class MyViewHolder extends RecyclerView.ViewHolder  {
        // public ImageView audio;
        public ImageView avatar;
        public ImageView ivNew ;
        public TextView tvMessage;
        public TextView tvTime;
        public TextView tvUserId;
        public ImageView ivScreenshot;
        public TextView tvLikes ;

        MyViewHolder(View view) {
            super(view);
            // initialize
            this.avatar = (ImageView) view.findViewById(R.id.ivAvatar);
            this.ivNew = (ImageView) view.findViewById(R.id.ivNew);
            this.tvMessage = (TextView) view.findViewById(R.id.tvMessage);
            this.tvTime = (TextView) view.findViewById(R.id.tvTime);
            this.tvUserId = (TextView) view.findViewById(R.id.tvUserId);
            this.ivScreenshot = (ImageView) view.findViewById(R.id.ivScreenshot);
            this.tvLikes = (TextView) view.findViewById(R.id.tvNumberofLikes);
        }


    }
}
