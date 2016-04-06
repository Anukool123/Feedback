package com.sdk.feedback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by 310124463 on 4/6/2016.
 */
public class InstructionFragment extends Fragment{

    private Button btnOk ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_instruction,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnOk = (Button) getView().findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                 getActivity().getSupportFragmentManager().beginTransaction().remove(InstructionFragment.this).commit();
            }
        });
    }
}
