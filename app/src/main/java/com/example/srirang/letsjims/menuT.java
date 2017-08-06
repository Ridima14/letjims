package com.example.srirang.letsjims;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;
import com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Srirang on 7/27/2017.
 */

public class menuT extends AppCompatActivity {

    HorizontalScrollMenuView menu;
    TextView trial;
    Button notifystudents,queries,activityfeed,submitmaterial;
   public String facultyname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_trial);

        notifystudents=(Button)findViewById(R.id.notifystudents);
        queries=(Button)findViewById(R.id.queries);
        activityfeed=(Button)findViewById(R.id.activityfeed);
        submitmaterial=(Button)findViewById(R.id.submitmaterial);
        trial=(TextView)findViewById(R.id.triall);
        facultyname=new String("Not proper");
        Intent intent=this.getIntent();
        if(intent !=null)
            facultyname = intent.getStringExtra("Facultyname");

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();


        String s=firebaseAuth.getCurrentUser().getEmail();

        trial.setText(s);
        //HORIZONTAL SCROLLBAR
        menu=(HorizontalScrollMenuView)findViewById(R.id.menutxt);
        
        initmenu();
    }

    private void initmenu() {
    menu.addItem("CSE I",R.drawable.ic_class1);
        menu.addItem("EC I",R.drawable.ic_class2);
        menu.addItem("CSE II",R.drawable.ic_class3);
        menu.addItem("EC II",R.drawable.ic_class4);
        menu.addItem("Logout",R.drawable.ic_class5);

        menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(MenuItem menuItem, int position) {

                if(menuItem.getText().equals("Logout"))
                    logoutUser();
            }
        });
    }

public void goToNS(View view)
{
    Intent intent=new Intent(menuT.this,NotifyStudents.class);

    startActivity(intent);
}
    public void goToQ(View view)
    {
        Intent intent=new Intent(menuT.this,Query.class);
        startActivity(intent);
    }
    /*
    public void goToAF(View view)
    {
        Intent intent=new Intent(menuT.this,ActivityFeed.class);
        startActivity(intent);
    }
 */   public void goToSM(View view)
    {
        Intent intent=new Intent(menuT.this,FacultyLoginActivity.class);
        startActivity(intent);
    }
    public void logoutUser()
    {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getApplicationContext(),"Signing out..",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(menuT.this,LoginActivity.class);
        startActivity(intent);
    }



}
