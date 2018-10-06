package com.example.riyaza.bankingapp2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.riyaza.bankingapp2.Fragments.FragmentVideo;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FragmentVideo fragmentVideo= new FragmentVideo();
        FragmentManager fragmentManager3= getSupportFragmentManager();
        fragmentVideo.setArguments(getIntent().getBundleExtra("Steps"));
        fragmentManager3.beginTransaction()
                .add(R.id.video_containerPh, fragmentVideo)
                .commit();



    }


}
