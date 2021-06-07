package com.example.duan1.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.ListCard;
import com.example.duan1.R;
import com.example.duan1.model.HoaDon;
import com.example.duan1.model.SanPham;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.Myviewholder> {
    Context context;
    ArrayList<HoaDon> arrayList;
    public HoaDonAdapter(Context context,ArrayList<HoaDon> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HoaDonAdapter.Myviewholder(LayoutInflater.from(context).inflate(R.layout.activity_iteam_listcard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, final int position) {
        holder.ten.setText(arrayList.get(position).getTensanpham());
        holder.soluongsp.setText(arrayList.get(position).getSoluongsanpham());
        holder.gia.setText("$"+arrayList.get(position).getTongtien());
        Picasso.get().load(arrayList.get(position).getImges()).into(holder.imgha);

      //  double tv=Double.parseDouble(String.valueOf(holder.gia))*Double.parseDouble(String.valueOf(holder.soluongsp));
       // holder.gia.setText(""+String.format("",tv));
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase fb=FirebaseDatabase.getInstance();
                DatabaseReference myref=fb.getReference("hoadon");
                myref.child(arrayList.get(position).getId()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(context,"xoa  thanh cong",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder
    {
        TextView ten,gia,soluongsp;
        ImageView imgha,imgdelete;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            ten= itemView.findViewById(R.id.tvten);
            gia= itemView.findViewById(R.id.tvgia);
            soluongsp= itemView.findViewById(R.id.tvsoluong);
            imgha=itemView.findViewById(R.id.idimg);
            imgdelete=itemView.findViewById(R.id.ivDelete);
        }
    }
}
