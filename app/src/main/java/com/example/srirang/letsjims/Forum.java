package com.example.srirang.letsjims;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Srirang on 2/5/2018.
 */

public class Forum extends AppCompatActivity {

     String chosencategory;
    EditText ed;
    Button b;
    TextView cat;
    ListView listView;
    String[] ques;
    List<String> list=new ArrayList<String>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_activity);
        Intent intent=this.getIntent();
           if(intent!=null)
            chosencategory=intent.getStringExtra("chosencategory");
        cat = (TextView) findViewById(R.id.category);
        cat.setText(chosencategory);
        ed= (EditText) findViewById(R.id.editText1);
        b= (Button) findViewById(R.id.post);
        listView= (ListView) findViewById(R.id.questions);

        populateList();


    }

    private void populateList() {
            System.out.println(".....POPULATING LisT..");
       FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref1=database.getReference();
        String temp=chosencategory.replaceAll("\\s+","");
        System.out.println("The temp var is:"+temp);
        DatabaseReference reference=ref1.child("Forum").child(temp);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postdata:dataSnapshot.getChildren())
                {
                    ForumQuestion gotQuestion = postdata.getValue(ForumQuestion.class);
                    System.out.println("In firebase loop:"+gotQuestion.getUser());
                    list.add(gotQuestion.date+"\n"+gotQuestion.question+"\n"+gotQuestion.user);
                }
                ques = new String[list.size()];
                ques=list.toArray(ques);
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.simple_listforum_layout,ques);
                listView.setAdapter(arrayAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onPostButtonClick(View view){

        //remove space from category
        String s = chosencategory.replaceAll("\\s+","");
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        String currentUser=firebaseAuth.getCurrentUser().getEmail();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        ForumQuestion newQuestion = new ForumQuestion(ed.getText().toString(),currentUser,formatter.format(date));

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref1=reference.child("Forum").child(s);
         ref1.push().setValue(newQuestion);
        Intent intent=new Intent(getApplicationContext(),Forum.class);
        intent.putExtra("chosencategory",chosencategory);
        startActivity(intent);
    }

}
