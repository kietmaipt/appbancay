package com.example.duan1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1.Adapter.SPAdapter;
import com.example.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Adminchat extends Fragment {
    public Adminchat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adminchat, container, false);
    }
}
