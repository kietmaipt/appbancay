package com.example.duan1.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Images;
import com.example.duan1.InvoiceAdd;
import com.example.duan1.ListCard;
import com.example.duan1.R;
import com.example.duan1.model.HoaDon;
import com.example.duan1.model.SanPham;
import com.example.duan1.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SPAdapter extends RecyclerView.Adapter<SPAdapter.Myviewholder> {
    Context context;
    ArrayList<SanPham> arraysp;
    public static String hinh = "";
    public SPAdapter(Context context,ArrayList<SanPham> arraysp){
        this.context=context;
        this.arraysp=arraysp;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Myviewholder(LayoutInflater.from(context).inflate(R.layout.activity_item_list__s_p,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, final int position) {
             holder.name.setText(""+arraysp.get(position).getTensp());
             holder.gia.setText("gia:$"+arraysp.get(position).getGia());
             holder.ma.setText(""+arraysp.get(position).getMasanpham());
             Picasso.get().load(arraysp.get(position).getHa()).into(holder.imgha);
             hinh = arraysp.get(position).getHa();
//             holder.imgha.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View v) {
//                     Context context=v.getContext();
//                   Intent intent=new Intent(context, Images.class);
//                     context.startActivity(intent);
//
//                 }
//             });

             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     View view=LayoutInflater.from(context).inflate(R.layout.activity_invoice_add,null);
                     AlertDialog.Builder builder= new AlertDialog.Builder(context);
                     builder.setView(view);
                     final TextView tensp=view.findViewById(R.id.nameinvoiceAdd);
                     final TextView gia=view.findViewById(R.id.GiaInvoiceAdd);
                     final EditText soluong=view.findViewById(R.id.soluong);
                     final TextView masanpham=view.findViewById(R.id.masp);
                     final CircleImageView imageView=view.findViewById(R.id.imghd);
                     Button btnsanpham=view.findViewById(R.id.addCard);
                     Button btnshowcard=view.findViewById(R.id.btnshow);



                     btnshowcard.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             Intent inten=new Intent(context, ListCard.class);
                             context.startActivity(inten);
                         }
                     });
                     //lay gia trị v=cua mang khi nhan vao gan vao tẽtview
                     tensp.setText(arraysp.get(position).getTensp());
                     gia.setText(""+arraysp.get(position).getGia());
                      soluong.getText().toString();
                     masanpham.setText(arraysp.get(position).getMasanpham());
                     Picasso.get().load(arraysp.get(position).getHa()).into(imageView);
                     AlertDialog dialog=builder.create();
                     dialog.show();
                     btnsanpham.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {

                             String ten=tensp.getText().toString();
                             String giasp=gia.getText().toString();
                             String ma=masanpham.getText().toString();
                             String soluong1=soluong.getText().toString();
                             String hinh1 = hinh;

                             double tv =Double.parseDouble(giasp)*Double.parseDouble(soluong1);
                              String tongtienSP=(String.format(String.valueOf(tv)));
                             HoaDon hoaDon =new HoaDon(hinh1,ten,soluong1,tongtienSP,giasp,ma);
                             FirebaseDatabase database=FirebaseDatabase.getInstance();
                             DatabaseReference db=database.getReference("hoadon");
                             //tao id ngau nhien tren firebase
                             String id=db.push().getKey();
                             db.child(id).setValue(hoaDon).addOnSuccessListener(new OnSuccessListener<Void>() {
                                 @Override
                                 public void onSuccess(Void aVoid) {
                                     Toast.makeText(context,"them vao hoa don thanh cong",Toast.LENGTH_LONG).show();
                                 }
                             }).addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {
                                     Toast.makeText(context,"them vao hoa don that bai",Toast.LENGTH_LONG).show();
                                 }
                             });
                         }
                     });
                 }
             });
    }





    @Override
    public int getItemCount() {
        return arraysp.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder
    {
           TextView name,gia,ma;
           CircleImageView imgha;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.tvten);
            gia= itemView.findViewById(R.id.tvgia);
            ma= itemView.findViewById(R.id.tvMaSanPham);
            imgha=itemView.findViewById(R.id.img);
        }
    }
}
