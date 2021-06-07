package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1.Adapter.HoaDonAdapter;
import com.example.duan1.Adapter.SPAdapter;
import com.example.duan1.model.HoaDon;
import com.example.duan1.model.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListCard extends AppCompatActivity {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<HoaDon> list;
    HoaDonAdapter hoaDonAdapter;
    Button btn1;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_card);
        recyclerView=findViewById(R.id.rcvCard);
        btn1=findViewById(R.id.btnbuynow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<HoaDon>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("hoadon");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    HoaDon hoaDon=dataSnapshot.getValue(HoaDon.class);
                    hoaDon.setId(dataSnapshot.getKey());
                    list.add(hoaDon);
                }
                hoaDonAdapter=new HoaDonAdapter(ListCard.this,list);
                recyclerView.setAdapter(hoaDonAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListCard.this,"cjhu",Toast.LENGTH_LONG).show();
            }
        });


    }

}
