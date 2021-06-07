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

public class UpdateKhachhang extends AppCompatActivity {
    EditText edtname,edtpass,edtsdt,edtfullname,edtemail;
    Button btnDN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_khachhang);
        edtname=findViewById(R.id.edtusernameud);
        edtpass=findViewById(R.id.etbPasswordud);
        edtsdt=findViewById(R.id.edtsdtud);
        edtfullname=findViewById(R.id.edtfullnameud);
        edtemail=findViewById(R.id.edtgmailud);
        btnDN=findViewById(R.id.btnUpdate);

        //don du lieu
        Intent in=getIntent();
        //dien du lieu vao edt
        Bundle b = in.getBundleExtra("kh");

        edtname.setText(b.getString("namekey"));
        edtpass.setText(b.getString("passkey"));
        edtemail.setText(b.getString("emailkey"));
        edtfullname.setText(b.getString("fullnamekey"));
        edtsdt.setText(b.getString("sdtkey"));

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sdt=Integer.parseInt(edtsdt.getText().toString());
                String fullname=edtfullname.getText().toString();
                String pass= edtpass.getText().toString();
                String email1=edtemail.getText().toString();
                String ten1=edtname.getText().toString();
                //lay gia tri id de firebase tim tren csdl,new trung id cua san pham thi cap nhat ten cuu san pham  do
                //don du lieu
                Intent in=getIntent();
                //dien du lieu vao edt
                Bundle b = in.getBundleExtra("kh");
                String id=b.getString("key");
                FirebaseDatabase fb=FirebaseDatabase.getInstance();
                DatabaseReference myref=fb.getReference("khachhang");
                myref.child(id).child("email").setValue(email1);
                myref.child(id).child("fullname").setValue(fullname);
                myref.child(id).child("name").setValue(ten1);
                myref.child(id).child("pass").setValue(pass);
                myref.child(id).child("sdt").setValue(sdt);
                Toast.makeText(getApplicationContext(),"cap nhat thanh cong",Toast.LENGTH_LONG).show();
                finish();
            }
        });
            }
}
