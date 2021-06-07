package com.example.duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.UpdateKhachhang;
import com.example.duan1.model.HoaDon;
import com.example.duan1.model.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.Myviewholder> {
    Context context;
    ArrayList<User> arrayList;

    public KhachHangAdapter(Context context,ArrayList<User> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new KhachHangAdapter.Myviewholder(LayoutInflater.from(context).inflate(R.layout.activity_iteam__list__k_h,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, final int position) {
        holder.ten.setText(arrayList.get(position).getName());
        holder.email.setText(arrayList.get(position).getEmail());
        holder.sdt.setText(""+arrayList.get(position).getSdt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"xoa  thanh cong"+position,Toast.LENGTH_LONG).show();

            }
        });
        holder.imgcapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //can 2 thu context+du lieu(intent)
                Context context=v.getContext();
                Intent intent=new Intent(context, UpdateKhachhang.class);
                //tao bunder chua du lieu
                Bundle bundle=new Bundle();
                //lay gia tri  khi ta click
                bundle.putString("emailkey",arrayList.get(position).getEmail());
                bundle.putString("namekey",arrayList.get(position).getName());
                bundle.putString("passkey",arrayList.get(position).getPass());
                bundle.putString("sdtkey", ""+arrayList.get(position).getSdt());
                bundle.putString("fullnamekey",arrayList.get(position).getFullname());
                bundle.putString("key",arrayList.get(position).getId());
                //day du lieu
                intent.putExtra("kh",bundle);
                context.startActivity(intent);
            }
        });
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase fb=FirebaseDatabase.getInstance();
                DatabaseReference myref=fb.getReference("khachhang");
                myref.child(arrayList.get(position).getId()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(context,"xoa  thanh cong",Toast.LENGTH_LONG).show();
                        notifyDataSetChanged();
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
        TextView ten,email,sdt;
        ImageView imgcapnhat,imgdelete;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            ten=itemView.findViewById(R.id.txtten);
            email=itemView.findViewById(R.id.txtemail);
            sdt=itemView.findViewById(R.id.txtphone);
            imgdelete=itemView.findViewById(R.id.imageView9);
            imgcapnhat=itemView.findViewById(R.id.imageView10);

        }
    }
}
