package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    EditText edtpass,edtemail;
    Button btnLogin,btndangky;
    FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        edtpass=findViewById(R.id.etbPasswordLG);
        edtemail=findViewById(R.id.etbgmailLG);
        btnLogin=findViewById(R.id.btnLogin);
        btndangky=findViewById(R.id.btndangky);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangnhap();
            }
        });
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void dangnhap(){
        String email=edtemail.getText().toString();
        String password=edtpass.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
              .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                  @Override
                  public void onSuccess(AuthResult authResult) {
                      Toast.makeText(Login.this,"loggedun Successfully",Toast.LENGTH_LONG).show();
                      checkUserAccessLevel(authResult.getUser().getUid());
                  }
              }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df=firebaseFirestore.collection("User").document(uid);
        //extrat the data from document
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            //idetify the user access level
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                  if(documentSnapshot.getString("isAdmin")!=null){
                      //usser is admin
                      startActivity(new Intent(getApplicationContext(),giaodienAdmin.class));
                      finish();
                  }
                  if (documentSnapshot.getString("isUser")!=null){
                      startActivity(new Intent(getApplicationContext(),Giaodiennguoidung.class));
                      finish();
                  }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
//           startActivity(new Intent(getApplicationContext(),Giaodiennguoidung.class));
//           finish();
//        }
    }
}
