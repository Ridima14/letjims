package com.example.srirang.letsjims;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Srirang on 8/15/2017.
 */

public class ActivityFeed extends AppCompatActivity {




    String[] ownerr;
    String[] contentt;

    List<String> ownerl=new ArrayList<String>();
    List<String> contentl=new ArrayList<String>();

    ListView listView;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

         listView=(ListView)findViewById(R.id.listitems);




        Toast.makeText(getApplicationContext(),"Hit back to enter menu page",Toast.LENGTH_LONG).show();

        firebaseLoopAndStuff();

        System.out.println("Outside firebase loop");


    }

    private void firebaseLoopAndStuff() {
        System.out.println("Inside Firebase...");
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(); //Root node
        databaseReference.child("ActivityFeed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postdata:dataSnapshot.getChildren())
                {
                    ActivityFeedClass post=postdata.getValue(ActivityFeedClass.class);


                    ownerl.add(post.date+":"+post.owner+":"+post.classname);

                    contentl.add(post.subject);
                    System.out.println(post.subject);

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


