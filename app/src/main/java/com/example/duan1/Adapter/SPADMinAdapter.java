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

import com.example.duan1.Images;
import com.example.duan1.R;
import com.example.duan1.UpdateKhachhang;
import com.example.duan1.UpdateSP;
import com.example.duan1.model.SanPham;
import com.example.duan1.model.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SPADMinAdapter extends RecyclerView.Adapter<SPADMinAdapter.Myviewholder> {
    Context context;
    ArrayList<SanPham> arrayList;
    public SPADMinAdapter(Context context,ArrayList<SanPham> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SPADMinAdapter.Myviewholder(LayoutInflater.from(context).inflate(R.layout.activity_iteam__list_sp_a_dmin,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, final int position) {
        holder.tensp.setText(arrayList.get(position).getTensp());
        holder.ma.setText(""+arrayList.get(position).getSoluong());
        holder.soluong.setText(""+arrayList.get(position).getMasanpham());

        Picasso.get().load(arrayList.get(position).getHa()).into(holder.hasp);
        holder.imgcapnhathd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //can 2 thu context+du lieu(intent)
                Context context=v.getContext();
                Intent intent=new Intent(context, UpdateSP.class);
                //tao bunder chua du lieu
                Bundle bundle=new Bundle();
                //lay gia tri  khi ta click
                bundle.putString("ha",arrayList.get(position).getHa());
                bundle.putString("maSP",arrayList.get(position).getMasanpham());
                bundle.putString("tenSp",arrayList.get(position).getTensp());
                bundle.putString("tenloai", arrayList.get(position).getTenloai());
                bundle.putString("gia",""+arrayList.get(position).getGia());
                bundle.putString("soluong",""+arrayList.get(position).getSoluong());
                bundle.putString("mota",""+arrayList.get(position).getMota());
                bundle.putString("id",""+arrayList.get(position).getId());
                //day du lieu
                intent.putExtra("sp",bundle);
                context.startActivity(intent);
            }
        });
        holder.imgdeleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase fb=FirebaseDatabase.getInstance();
                DatabaseReference myref=fb.getReference("sanpham");
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
        TextView tensp,soluong,ma;
        CircleImageView hasp;
        ImageView imgcapnhathd,imgdeleta;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tensp = itemView.findViewById(R.id.txttensp);
            soluong = itemView.findViewById(R.id.txtsoluong);
            ma = itemView.findViewById(R.id.txtmasp);
            hasp = itemView.findViewById(R.id.imgha);
            imgdeleta = itemView.findViewById(R.id.imgxoa);
            imgcapnhathd = itemView.findViewById(R.id.imgsua);
        }
    }
}
