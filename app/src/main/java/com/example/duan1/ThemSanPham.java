package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1.model.SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThemSanPham extends AppCompatActivity {
   EditText edtmasp,edttensp,edtha,edtmota,edtsoluong,edtgia,edttenloai;
   Button btnthem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        edtmasp=findViewById(R.id.edtmaSP);
        edttensp=findViewById(R.id.edttenSP);
        edtha=findViewById(R.id.edtha);
        edtgia=findViewById(R.id.edtgia);
        edtmota=findViewById(R.id.edtmota);
        edtsoluong=findViewById(R.id.edtsoluong);
        edttenloai=findViewById(R.id.edttenloai);
        btnthem=findViewById(R.id.btnThem);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inserdata();
            }
        });

    }
    public void Inserdata(){
        String tenloai=edttenloai.getText().toString();
        String tensp=edttensp.getText().toString();
        String ha=edtha.getText().toString();
        String soluong=edtsoluong.getText().toString();
        String gia=edtgia.getText().toString();
        String mota=edtmota.getText().toString();
        String maSP=edtmasp.getText().toString();
        SanPham sAnPham =new SanPham(ha,maSP,tensp,tenloai,Integer.parseInt(gia),Double.parseDouble(soluong),mota);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference db=database.getReference("sanpham");
        //tao id ngau nhien tren firebase
        String id=db.push().getKey();
        db.child(id).setValue(sAnPham).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"them thanh cong",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"them that bai",Toast.LENGTH_LONG).show();
            }
        });
    }
}
