package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateSP extends AppCompatActivity {
    EditText edtmasp,edttensp,edtha,edtmota,edtsoluong,edtgia,edttenloai;
    Button btnthem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_s_p);

        edtmasp=findViewById(R.id.edtmaSPud);
        edttensp=findViewById(R.id.edttenSPud);
        edtha=findViewById(R.id.edthaud);
        edtgia=findViewById(R.id.edtgiaud);
        edtmota=findViewById(R.id.edtmotaud);
        edtsoluong=findViewById(R.id.edtsoluongud);
        edttenloai=findViewById(R.id.edttenloaiud);
        btnthem=findViewById(R.id.btnud);
        //don du lieu
        Intent in=getIntent();
        //dien du lieu vao edt
        Bundle b = in.getBundleExtra("sp");

        edtha.setText(b.getString("ha"));
        edtmasp.setText(b.getString("maSP"));
        edttensp.setText(b.getString("tenSp"));
        edttenloai.setText(b.getString("tenloai"));
        edtgia.setText(b.getString("gia"));
        edtsoluong.setText(b.getString("soluong"));
        edtmota.setText(b.getString("mota"));

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ha=edtha.getText().toString();
                String maSP=edtmasp.getText().toString();
                String tenSp=edttensp.getText().toString();
                String tenloai=edttenloai.getText().toString();
                int gia=Integer.parseInt(edtgia.getText().toString());
                Double soluong=Double.parseDouble(edtsoluong.getText().toString());
                String mota=edtmota.getText().toString();
                //lay gia tri id de firebase tim tren csdl,new trung id cua san pham thi cap nhat ten cuu san pham  do
                //don du lieu
                Intent in=getIntent();
                //dien du lieu vao edt
                Bundle b = in.getBundleExtra("sp");
                String id=b.getString("key");
                FirebaseDatabase fb=FirebaseDatabase.getInstance();
                DatabaseReference myref=fb.getReference("sanpham");
                myref.child(id).child("ha").setValue(ha);
                myref.child(id).child("masanpham:").setValue(maSP);
                myref.child(id).child("mota").setValue(mota);
                myref.child(id).child("soluong").setValue(soluong);
                myref.child(id).child("tenloai").setValue(tenloai);
                myref.child(id).child("tensp").setValue(tenSp);
                myref.child(id).child("gia").setValue(gia);
                Toast.makeText(getApplicationContext(),"cap nhat thanh cong",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
            }



