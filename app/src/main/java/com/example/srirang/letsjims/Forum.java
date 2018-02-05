package com.example.srirang.letsjims;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Srirang on 2/5/2018.
 */

public class Forum extends AppCompatActivity {

     String chosencategory;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=this.getIntent();
        if(intent!=null)
            chosencategory=intent.getStringExtra("chosencategory");
    }
}
