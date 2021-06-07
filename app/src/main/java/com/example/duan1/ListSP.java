package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.duan1.Adapter.SPAdapter;
import com.example.duan1.model.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListSP extends AppCompatActivity {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<SanPham> list;
    SPAdapter spAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_s_p);
        recyclerView=findViewById(R.id.rcvsp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<SanPham>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("sanpham");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    SanPham sanPham=dataSnapshot.getValue(SanPham.class);
                    list.add(sanPham);

            }
                spAdapter=new SPAdapter(ListSP.this,list);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(ListSP.this,2, GridLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(spAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListSP.this,"cjhu",Toast.LENGTH_LONG).show();
            }
        });
    }
}
