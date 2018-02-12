package com.example.srirang.letsjims;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Srirang on 2/5/2018.
 */

public class Forum extends AppCompatActivity {

     String chosencategory;
    TextView cat;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_activity);
        Intent intent=this.getIntent();
           if(intent!=null)
            chosencategory=intent.getStringExtra("chosencategory");
        cat = (TextView) findViewById(R.id.categorychosen);
        String temp=cat.getText().toString() + chosencategory;
        cat.setText(temp);
    }

}
