package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.duan1.Adapter.KhachHangAdapter;
import com.example.duan1.Adapter.SPAdapter;
import com.example.duan1.model.SanPham;
import com.example.duan1.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListKH extends AppCompatActivity {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<User> list;
    KhachHangAdapter khachHangAdapter;
    Button themkh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_k_h);
        themkh=findViewById(R.id.themkh);
        themkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        recyclerView=findViewById(R.id.rcvkh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<User>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("khachhang");
        databaseReference.addValueEventListener(new ValueEventListener() {
            // onDataChange()hàm khong tra ve du lieu  nó trả về mang khachhang voi kich thuoc .
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    User user=dataSnapshot.getValue(User.class);
                    user.setId(dataSnapshot.getKey());
                    list.add(user);
                }
                khachHangAdapter=new KhachHangAdapter(ListKH.this,list);
                recyclerView.setAdapter(khachHangAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListKH.this,"cjhu",Toast.LENGTH_LONG).show();
            }
        });
    }
}
