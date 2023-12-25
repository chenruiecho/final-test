package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    Fragment fragment1,fragment2,fragment3,fragment4;
    FragmentManager manager;
    int transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout1=findViewById(R.id.music);
        linearLayout2=findViewById(R.id.mysongs);
        linearLayout3=findViewById(R.id.share);
        linearLayout4=findViewById(R.id.setting);

        fragment1=new fragment_music();
        fragment2=new fragment_mysong();
        fragment3=new fragment_guangchang();
        fragment4=new fragment_setting();
        manager=getSupportFragmentManager();

        initial();
        fragmentHide();
        showfragment(fragment1);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);

    }

    public void onClick(View view){
        fragmentHide();
        if(view.getId()==R.id.music)
        {
            showfragment(fragment1);
        }
        else if (view.getId()==R.id.mysongs) {
            showfragment(fragment2);
        }
        else if (view.getId()==R.id.share) {
            showfragment(fragment3);
        }
        else if(view.getId()==R.id.setting) {
            showfragment(fragment4);
        }
    }

    private void showfragment(Fragment fragment){
        transaction=manager.beginTransaction()
                .show(fragment)
                .commit();
    }

    public void initial(){
        transaction=manager.beginTransaction()
                .add(R.id.content,fragment1)
                .add(R.id.content,fragment2)
                .add(R.id.content,fragment3)
                .add(R.id.content,fragment4)
                .commit();
    }

    public void fragmentHide(){
        transaction=manager.beginTransaction()
                .hide(fragment1)
                .hide(fragment2)
                .hide(fragment3)
                .hide(fragment4)
                .commit();
    }
}
