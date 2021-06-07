package com.example.duan1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.duan1.ListKH;
import com.example.duan1.ListSP;
import com.example.duan1.ListSPAdMin;
import com.example.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userAdmin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userAdmin extends Fragment {
    LinearLayout ln1,ln2,ln3;

    public userAdmin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_admin, container, false);
        ln1=view.findViewById(R.id.khachhang);
        ln2=view.findViewById(R.id.sanpham);
        ln1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(userAdmin.this.requireContext(), ListKH.class);
                startActivity(intent);
            }
        });
        ln2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(userAdmin.this.requireContext(), ListSPAdMin.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
