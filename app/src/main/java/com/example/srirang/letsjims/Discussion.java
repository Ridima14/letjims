package com.example.srirang.letsjims;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;
import android.widget.ListView;
import android.view.View;
import android.widget.*;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;

import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;
import com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem;
import android.widget.AdapterView;

import static com.example.srirang.letsjims.R.id.parent;

/**
 * Created by Srirang on 2/4/2018.
 */

public class Discussion extends AppCompatActivity{

    HorizontalScrollMenuView menu;
    String branchyear;
    String chosenSection;

    String[] allCategories={"WEB DEVELOPMENT","ANDROID DEVELOPMENT","MICROPROCESSOR","MATHEMATICS"
            ,"C++","MECHANICS","NETWORKING"};
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion_category);

        Intent intent=this.getIntent();
        if(intent !=null)
        {
            branchyear=intent.getStringExtra("branchyear");
        }

        menu= (HorizontalScrollMenuView) findViewById(R.id.menutxt);
        listView= (ListView) findViewById(R.id.categories);

        initmenu();


        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.simple_list_layout,allCategories);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selecteditem= (String) parent.getItemAtPosition(position);
                 Intent intent1=new Intent(view.getContext(),Forum.class);
                 intent1.putExtra("chosencategory",selecteditem);
                 startActivity(intent1);
            }
        });
    }






//End of onCreate()

public void initmenu() {                        //Add initial class selected in loging acitivity


        menu.addItem("Category", R.drawable.ic_class2);
        menu.addItem("Forum", R.drawable.ic_class3);
        menu.addItem("Home", R.drawable.ic_class1);

        menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
public void onHSMClick(MenuItem menuItem, int position) {
        chosenSection=menuItem.getText();
        if(chosenSection.equals("Home"))
        {
        Intent intent=new Intent(getApplicationContext(),StudentHomepage.class);
        intent.putExtra("branchyear",branchyear);
        startActivity(intent);
        }
        else if(chosenSection.equals("Category")){

        }
        else{

        }
        }
        });
}

        } //End of class