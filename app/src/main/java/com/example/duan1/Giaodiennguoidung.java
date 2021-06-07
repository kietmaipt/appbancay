package com.example.duan1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.duan1.fragment.AdUsser;
import com.example.duan1.fragment.MainUser;
import com.example.duan1.fragment.chatUser;

public class Giaodiennguoidung extends AppCompatActivity {
    MeowBottomNavigation meowBottomNavigation;
    private final static int ID_chat=1;
    private final static int ID_home=2;
    private final static int ID_user=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_giaodiennguoidung);
         meowBottomNavigation=findViewById(R.id.meo);

        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.chat_icon));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.home_ic));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.user_ic));

        getSupportFragmentManager().beginTransaction().replace(R.id.frm,new MainUser()).commit();
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
                    case ID_home:
                        select_frament=new MainUser();
                        break;
                    case ID_user:
                        select_frament=new AdUsser();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frm,select_frament).commit();
            }
        });
    }
}
