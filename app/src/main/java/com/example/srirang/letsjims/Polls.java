package com.example.srirang.letsjims;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;

import com.example.srirang.letsjims.Actors;
import com.example.srirang.letsjims.ActorsAdapter;
import com.example.srirang.letsjims.R;

import java.util.ArrayList;

public class Polls extends AppCompatActivity {
    private RecyclerView RecycleLayout;
    private RecyclerView.LayoutManager ActorManager;
    private RecyclerView.Adapter ActorAdapter;


    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls);

        RecycleLayout = (RecyclerView) findViewById(R.id.recycleView);

        ArrayList<Actors> listItem = new ArrayList<Actors>();
        listItem.add(new Actors(R.drawable.eart,"Should our tutorials be published online?","Total votes:350"));
        listItem.add(new Actors(R.drawable.kamera,"Should there be a seminar on professional photography?","140"));
        listItem.add(new Actors(R.drawable.kantor,"Should the middle building be upgraded?","342"));
        listItem.add(new Actors(R.drawable.kue,"Birthday party schemes in college canteen","400"));
        listItem.add(new Actors(R.drawable.lencana,"Professional workshop on Firebase Google cloud services","149"));
        listItem.add(new Actors(R.drawable.topi,"Documenting the farewell party","621"));

        ActorManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        RecycleLayout.setLayoutManager(ActorManager);

        ActorAdapter = new ActorsAdapter(listItem);

        RecycleLayout.setAdapter(ActorAdapter);



    }

}

