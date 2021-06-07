package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.model.HoaDon;
import com.example.duan1.model.SanPham;
import com.example.duan1.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InvoiceAdd extends AppCompatActivity {
    Button btnshowcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_add);
       btnshowcard=findViewById(R.id.btnshow);
       btnshowcard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent inten=new Intent(InvoiceAdd.this,ListCard.class);
               startActivity(inten);
           }
       });

    }

}
