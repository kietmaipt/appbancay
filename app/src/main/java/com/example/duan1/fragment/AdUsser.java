package com.example.duan1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duan1.Adapter.SPAdapter;
import com.example.duan1.ChangePassword;
import com.example.duan1.Evaluate;
import com.example.duan1.ListSP;
import com.example.duan1.Login;
import com.example.duan1.R;
import com.example.duan1.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdUsser extends Fragment {
    LinearLayout ln,ln2,ln3;
    TextView tvten;
    FirebaseAuth firebaseAuth;
    User user;
    public AdUsser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ad_usser, container, false);
        ln=view.findViewById(R.id.doimk);
        ln2=view.findViewById(R.id.danhgia);
        tvten=view.findViewById(R.id.tvtenuser);
        ln3=view.findViewById(R.id.logout);
        firebaseAuth=FirebaseAuth.getInstance();


        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdUsser.this.requireContext(), ChangePassword.class);
                startActivity(intent);
            }
        });
        ln3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdUsser.this.requireContext(), Login.class);
                startActivity(intent);
            }
        });
        ln2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdUsser.this.requireContext(), Evaluate.class);
                startActivity(intent);
            }
        });

//            DatabaseReference ref= FirebaseDatabase.getInstance().getReference("khachhang");
//            ref.orderByChild("uid").equalTo(firebaseAuth.getUid()
//                    .addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            for (DataSnapshot ds: snapshot.getChildren()){
//                                String name=""+ds.child("gmail").getValue();
//                                tvten.setText(name);
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
        return view;


    }

}
