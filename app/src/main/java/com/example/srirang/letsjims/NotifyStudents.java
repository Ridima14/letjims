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
    public String choosenclass;
    public String chosenclass;
    public String facultyname;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.notify_students);

        content=(EditText)findViewById(R.id.content);
        datens=(EditText)findViewById(R.id.datens);
        subject=(EditText)findViewById(R.id.subject);
        Intent intent=this.getIntent();

        if(intent!=null)
         choosenclass=intent.getStringExtra("ChosenClass");
        sendNotification=(Button)findViewById(R.id.SendNotification);

        menu=(HorizontalScrollMenuView)findViewById(R.id.menutxt);

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        facultyname=firebaseAuth.getCurrentUser().getEmail();
        initmenu();


    }
    private void initmenu() {                        //Add initial class selected in loging acitivity
        if(facultyname.equals("vandita@gmail.com")) {
            menu.addItem("CSE II", R.drawable.ic_class1);
            menu.addItem("CSE III", R.drawable.ic_class2);
            menu.addItem("CSE IV", R.drawable.ic_class3);
            menu.addItem("Logout", R.drawable.ic_class5);
        }
        else if(facultyname.equals("madhulika@gmail.com"))
        {
            menu.addItem("CSE II", R.drawable.ic_class1);
            menu.addItem("CSE III", R.drawable.ic_class2);
            menu.addItem("ECE II", R.drawable.ic_class3);
            menu.addItem("Logout", R.drawable.ic_class5);
        }
        else if(facultyname.equals("pramod@gmail.com"))
        {
            menu.addItem("CSE II", R.drawable.ic_class1);
            menu.addItem("ECE II", R.drawable.ic_class4);
            menu.addItem("ME II", R.drawable.ic_class3);
            menu.addItem("EE II", R.drawable.ic_class2);
            menu.addItem("Logout", R.drawable.ic_class5);
        }
        menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(MenuItem menuItem, int position) {

                chosenclass=menuItem.getText();

                if(menuItem.getText().equals("Logout"))
                {
                    logoutUser();}
                else
                {
                    Intent intent=new Intent(getApplicationContext(),menuT.class);
                    intent.putExtra("Facultyname",facultyname);
                    intent.putExtra("ChosenClass",menuItem.getText().replaceAll("\\s+",""));
                    startActivity(intent);
                }
            }

        });

    }
    public void logoutUser()
    {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getApplicationContext(),"Signing out..",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }
   public void sendNotification(View view)                    //Adding notification to Firebase
   {
       String subjecttext=subject.getText().toString().trim();
       String contenttext=content.getText().toString().trim();
       String datenesdate= datens.getText().toString();
       FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();


       String facultyLogged=firebaseAuth.getCurrentUser().getEmail();
       NotifyStudentsClass notifyStudentsClass=new NotifyStudentsClass(subjecttext,contenttext,datenesdate,facultyLogged,choosenclass);
       FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference myRef = database.getReference();   //Root node NODE0
        DatabaseReference myRef1=myRef.child("NotifyStudents");  //NODE1

          //NODE2 &NODE3
          myRef1.push().setValue(notifyStudentsClass);
       Toast.makeText(getApplicationContext(),"Posting data",Toast.LENGTH_SHORT).show();

       ActivityFeedClass nsFeed=new ActivityFeedClass(subjecttext,facultyLogged,"n",choosenclass);

       DatabaseReference myRef2=myRef.child("ActivityFeed");
           myRef2.push().setValue(nsFeed);


       Intent intent=new Intent(NotifyStudents.this,menuT.class);
       intent.putExtra("Facultyname",facultyLogged);
       intent.putExtra("ChosenClass",choosenclass);
       startActivity(intent);
   }


}
