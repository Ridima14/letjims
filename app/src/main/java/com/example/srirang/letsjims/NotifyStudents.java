package com.example.srirang.letsjims;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;
import com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Srirang on 7/31/2017.
 */

public class NotifyStudents extends AppCompatActivity{

    EditText content,datens,subject;
    Button sendNotification;
    HorizontalScrollMenuView menu;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.notify_students);

        content=(EditText)findViewById(R.id.content);
        datens=(EditText)findViewById(R.id.datens);
        subject=(EditText)findViewById(R.id.subject);

        sendNotification=(Button)findViewById(R.id.SendNotification);

        menu=(HorizontalScrollMenuView)findViewById(R.id.menutxt);

        initmenu();


    }
    private void initmenu() {
        menu.addItem("CSE I",R.drawable.ic_class1);
        menu.addItem("EC I",R.drawable.ic_class2);
        menu.addItem("CSE II",R.drawable.ic_class3);
        menu.addItem("EC II",R.drawable.ic_class4);
        menu.addItem("CSE III",R.drawable.ic_class5);

        menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(MenuItem menuItem, int position) {

            }
        });
    }
   public void sendNotification(View view)                    //Adding notification to Firebase
   {
       String subjecttext=subject.getText().toString().trim();
       String contenttext=content.getText().toString().trim();
       String datenesdate= datens.getText().toString();
       FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();


       String facultyLogged=firebaseAuth.getCurrentUser().getEmail();
       NotifyStudentsClass notifyStudentsClass=new NotifyStudentsClass(subjecttext,contenttext,datenesdate,facultyLogged);
       FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference myRef = database.getReference();   //Root node NODE0
        DatabaseReference myRef1=myRef.child("NotifyStudents");  //NODE1

          //NODE2 &NODE3
          myRef1.push().setValue(notifyStudentsClass);
       Toast.makeText(getApplicationContext(),"Posting data",Toast.LENGTH_SHORT).show();

       Intent intent=new Intent(NotifyStudents.this,menuT.class);
       startActivity(intent);
   }


}
