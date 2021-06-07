package com.example.duan1.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.duan1.ListSP;
import com.example.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainUser extends Fragment {
    Button btn1;

    public MainUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_main_user, container, false);
        btn1=view.findViewById(R.id.btnXemSp);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainUser.this.requireContext(),ListSP.class);
                startActivity(intent);
            }
        });
        return view;

    }
}
