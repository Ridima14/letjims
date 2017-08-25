package com.example.srirang.letsjims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;
import com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Srirang on 8/21/2017.
 */

public class StudentMenu extends AppCompatActivity{

    String[] ownerr;
    String[] contentt;

    List<String> ownerl=new ArrayList<String>();
    List<String> contentl=new ArrayList<String>();

    private SimpleAdapter simpleAdapter;

    ListView listView;
    String intentbranchyear;  //Only to send in intent on menuItem onClick
    HorizontalScrollMenuView menu;
    Button imagebutton1,imagebutton2,imagebutton3;
    String branchyear,chosenteacher;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_menu);
System.out.println("ENtered new activity...");
        listView=(ListView)findViewById(R.id.listViews);
        imagebutton1=(Button) findViewById(R.id.Imgbtn1);
        imagebutton2= (Button) findViewById(R.id.Imgbtn2);
        imagebutton3= (Button) findViewById(R.id.Imgbtn3);

        //Intent stuff

        Intent intent=this.getIntent();
        if(intent!=null)
        {
            branchyear=intent.getStringExtra("branchyear");
            chosenteacher=intent.getStringExtra("chosenteacher");
            intentbranchyear=branchyear;

        }
        System.out.println("Just below intent if statement:"+branchyear+chosenteacher);

        //HORIZONTAL SCROLLBAR
        menu=(HorizontalScrollMenuView)findViewById(R.id.menutxt);

        initmenu(branchyear);
        firebaseLoopAndStuff();
    }

    private void initmenu(String by) {

        if(by!=null) {

            menu.addItem("Notice", R.drawable.ic_class1);
            menu.addItem("General", R.drawable.ic_plus_icon);

            if (branchyear.equals("CSE II")) {
                menu.addItem("Maths IV", R.drawable.ic_class2);
                menu.addItem("DBMS", R.drawable.ic_class3);
                menu.addItem("CAO", R.drawable.ic_class4);

            } else if (branchyear.equals("CSE III")) {
                menu.addItem("JAVA", R.drawable.ic_class2);
                menu.addItem("ALGORITHMS", R.drawable.ic_class3);
                menu.addItem("DIGITAL COMM.", R.drawable.ic_class4);
                menu.addItem("COMM. SKILLS", R.drawable.ic_class5);
            }
            menu.addItem("Events", R.drawable.ic_class5);
            menu.addItem("Logout", R.drawable.ic_class1);
        }
        else
            System.out.println("Intent not going at all");

        menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(MenuItem menuItem, int position) {

                if(menuItem.getText().equals("Logout"))
                {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(getApplicationContext(),"Logging out..",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                }
                else if(menuItem.getText().equals("General"))
                {
                    //Add intent to new general class
                }
                else if(menuItem.getText().equals("Notice"))
                {
                    Intent intent=new Intent(getApplicationContext(),StudentHomepage.class);
                    intent.putExtra("branchyear",branchyear);
                    startActivity(intent);
                }
                else if(menuItem.getText().equals("Events"))
                {
                    //Add intent to new event class
                }
                else
                {
                    String pick=menuItem.getText();         //Matching subjects with teachers to send as intent
                    if(pick.equals("CAO")|| pick.equals("JAVA"))
                    {
                        chosenteacher="vandita@gmail.com";
                    }
                    else if(pick.equals("DBMS") || pick.equals("ALGORITHMS"))
                    {
                        chosenteacher="madhulika@gmail.com";
                    }
                    else if(pick.equals("Maths IV"))
                    {
                        chosenteacher="pramod@gmail.com";
                    }
                    Intent intent=new Intent(getApplicationContext(),StudentMenu.class);
                    System.out.println("Entering homepage after clicking intent with branchyear :"+intentbranchyear);
                    intent.putExtra("branchyear",intentbranchyear);
                    intent.putExtra("chosenteacher",chosenteacher);
                    System.out.println("Entering menu activity...");
                    startActivity(intent);
                }

            }
        });

    }
    private void firebaseLoopAndStuff() {
        System.out.println("Inside Firebase...");
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(); //Root node
        databaseReference.child("NotifyStudents").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postdata:dataSnapshot.getChildren())
                {
                    NotifyStudentsClass post=postdata.getValue(NotifyStudentsClass.class);

                    System.out.println(chosenteacher+branchyear+"Just outside onDataChange");
                    branchyear=branchyear.replaceAll("\\s","");
                    if(post.facultyn.equals(chosenteacher) && post.classname.equals(branchyear))
                    {
                        System.out.println(chosenteacher+branchyear+"Inside onDatachangee");
                        int index = post.facultyn.indexOf('@');
                        String subname = post.facultyn.substring(0, index);
                        ownerl.add("Last Date:"+post.datens + ":\n" + subname + ":" + post.subject);

                        contentl.add(post.content);
                        System.out.println(post.content);
                    }
                    else
                    {
                        System.out.println("Teacher was:"+post.facultyn);
                    }





                }
                simpleArray();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    private void simpleArray()
    {
        System.out.println("Inside simple array");
        ownerr=new String[ownerl.size()];
        ownerr=ownerl.toArray(ownerr);
        for(int j=0;j<ownerr.length;j++)


            contentt=new String[contentl.size()];
        contentt=contentl.toArray(contentt);
        String[] from = new String[] {"col1", "col2"};
        int[] to = new int[] {R.id.ownerText,R.id.contentText};

        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();

        for(int i = 0; i < ownerr.length; i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("col1", "" + ownerr[i]);
            System.out.println("Inside adapter for loop");
            map.put("col2", "" + contentt[i]);

            fillMaps.add(map);
        }

        simpleAdapter = new SimpleAdapter(this, fillMaps, R.layout.activityfeed_items, from, to);
        listView.setAdapter(simpleAdapter);
    }

}
