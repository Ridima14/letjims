package com.example.srirang.letsjims;


import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.view.Gravity;


public class Event extends Activity implements
        OnItemClickListener {

    public static final String[] titles = new String[] { "INDUSTRIAL VISIT",
            "ZEST & DANDIYA" };

    public static final String[] descriptions = new String[] {
            "Industrial visit for CS 5th sem students on 25th August 2017 at Aprton.",
            "ZEST 2017 on october 14"};

    public static final Integer[] images = { R.drawable.industvisit,
            R.drawable.zest };

    ListView listView;
    List<RowItem> rowItems;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.list);
       /* CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item, rowItems);

                */
       // listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}

