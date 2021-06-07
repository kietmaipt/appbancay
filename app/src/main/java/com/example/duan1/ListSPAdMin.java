package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.Adapter.HoaDonAdapter;
import com.example.duan1.Adapter.SPADMinAdapter;
import com.example.duan1.model.HoaDon;
import com.example.duan1.model.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListSPAdMin extends AppCompatActivity {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<SanPham> list;
    SPADMinAdapter spadMinAdapter;
    Button btnthem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_s_p_ad_min);
        btnthem=findViewById(R.id.them);
        recyclerView=findViewById(R.id.rcvspadmin);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ThemSanPham.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<SanPham>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("sanpham");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    SanPham sanPham=dataSnapshot.getValue(SanPham.class);
                    sanPham.setId(dataSnapshot.getKey());
                    list.add(sanPham);
                }
                spadMinAdapter=new SPADMinAdapter(ListSPAdMin.this,list);
                recyclerView.setAdapter(spadMinAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListSPAdMin.this,"cjhu",Toast.LENGTH_LONG).show();
            }
        });
    }
}
