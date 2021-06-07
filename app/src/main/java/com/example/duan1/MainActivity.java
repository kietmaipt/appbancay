package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.duan1.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
     FirebaseAuth mAuth;
     EditText edtname,edtpass,edtsdt,edtfullname,edtemail;
     Button btnDK;
     FirebaseFirestore fStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fStorage=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        edtname=findViewById(R.id.edtusername);
        edtpass=findViewById(R.id.etbPassword);
        edtsdt=findViewById(R.id.edtsdt);
        edtfullname=findViewById(R.id.edtfullname);
        edtemail=findViewById(R.id.edtgmail);
        btnDK=findViewById(R.id.btnSignup);

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });
    }
    public void DangKy() {
        String email = edtemail.getText().toString();
        String password = edtpass.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Account Created", Toast.LENGTH_LONG).show();
                        DocumentReference df = fStorage.collection("User").document(user.getUid());
                        Map<String, Object> userInfo = new HashMap<>();
                        userInfo.put("fullname", edtfullname.getText().toString());
                        userInfo.put("email", edtemail.getText().toString());
                        userInfo.put("phone", edtsdt.getText().toString());
                        //specify if the user is admin
                        userInfo.put("isUser", "1");
                        df.set(userInfo);
                        Inserdata(authResult.getUser().getUid());
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Failed to Account Created", Toast.LENGTH_LONG).show();
            }
        });
    }
        public void Inserdata(String uid){

            DocumentReference df=fStorage.collection("User").document(uid);
            //extrat the data from document
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                //idetify the user access level
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.getString("isUser")!=null){
                        String name = edtname.getText().toString();
                        String fullnam = edtfullname.getText().toString();
                        String gmail = edtemail.getText().toString();
                        String pass = edtpass.getText().toString();
                        String sdt = edtsdt.getText().toString();
                        final User user = new User(name,fullnam,pass,gmail,Integer.parseInt(sdt));
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference db = database.getReference("khachhang");
                        //tao id ngau nhien tren firebase
                        String id = db.push().getKey();
                        db.child(id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "them thanh cong", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "them that bai", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                        if (documentSnapshot.getString("isAdmin")!=null){
                        String name = edtname.getText().toString();
                        String fullnam = edtfullname.getText().toString();
                        String gmail = edtemail.getText().toString();
                        String pass = edtpass.getText().toString();
                        String sdt = edtsdt.getText().toString();
                        final User user = new User(name,fullnam,pass,gmail,Integer.parseInt(sdt));
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference db = database.getReference("Admin");
                        //tao id ngau nhien tren firebase
                        String id = db.push().getKey();
                        db.child(id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "them thanh cong", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "them that bai", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            });

        }
}
