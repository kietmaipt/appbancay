package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.duan1.fragment.AdUsser;
import com.example.duan1.fragment.MainUser;
import com.example.duan1.fragment.chatUser;
import com.example.duan1.fragment.userAdmin;

public class giaodienAdmin extends AppCompatActivity {
    MeowBottomNavigation meowBottomNavigation;
    private final static int ID_chat=1;
    private final static int ID_user=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien_admin);

        meowBottomNavigation=findViewById(R.id.meoadmin);

        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.chat_icon));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.user_ic));

        getSupportFragmentManager().beginTransaction().replace(R.id.frmadmin,new userAdmin()).commit();
        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(),"clicked item"+item.getId(),Toast.LENGTH_LONG).show();
            }
        });
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment select_frament=null;
                switch (item.getId()){
                    case ID_chat:
                        select_frament=new chatUser();
                        break;
                    case ID_user:
                        select_frament=new userAdmin();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frmadmin,select_frament).commit();
            }
        });
    }
}
