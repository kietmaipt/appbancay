package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class Evaluate extends AppCompatActivity {
    RatingBar ratingBar;
    Button btnsend;
    float myrateting=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        btnsend=findViewById(R.id.btnsend);
        ratingBar=findViewById(R.id.Ratingbar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean fromUser) {
                int rating=(int) v;
                String message=null;
                myrateting=  ratingBar.getRating();
                switch (rating){
                    case  1:
                         message="ung dung te";
                         break;
                    case  2:
                        message="ung dung tam duoc";
                        break;
                    case  3:
                        message="ung dung on";
                        break;
                    case  4:
                        message="tot ";
                        break;
                    case  5:
                        message="qua tot";
                        break;
                }
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }
        });
           btnsend.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                       Toast.makeText(getApplicationContext(),String.valueOf(myrateting),Toast.LENGTH_LONG).show();
               }
           });
    }
}
