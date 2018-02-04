package com.example.srirang.letsjims;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;
import com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem;
import com.google.firebase.auth.FirebaseAuth;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Srirang on 8/7/2017.
 */

public class StudentHomepage extends AppCompatActivity {

    ImageButton generalplus,collegeplus,univplus;
    TextView tv1;
    WebView webView;
    private String branchyear;
    private String chosenteacher;

    HorizontalScrollMenuView menu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_homepage);

     /*   generalplus=(ImageButton)findViewById(R.id.generalplus);
        collegeplus=(ImageButton)findViewById(R.id.collegeplus);   */ //Removed top two linear layouts
        univplus=(ImageButton)findViewById(R.id.universityplus);
       // tv1=(TextView)findViewById(R.id.generaltext);
        webView=(WebView)findViewById(R.id.collegeweb);
        branchyear=new String("");

        //Obtain the WebSettings object//
       WebSettings webSettings = webView.getSettings();

          //Call setJavaScriptEnabled(true)//
        webSettings.setJavaScriptEnabled(true);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);

        //Intent Variable
        Intent intent=this.getIntent();         //Get intent
        if(intent !=null) {
            branchyear=intent.getStringExtra("branchyear");

//a
        }

        //tv1.setText(branchyear);
        Toast toast=Toast.makeText(getApplicationContext(),"Horizontal list of your subjects.",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,0);
        toast.show();
        //HORIZONTAL SCROLLBAR
        menu=(HorizontalScrollMenuView)findViewById(R.id.menutxt);

        initmenu(branchyear);
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
             menu.addItem("Discussion",R.drawable.ic_class4);
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
                    Intent intent=new Intent(StudentHomepage.this,LoginActivity.class);
                    startActivity(intent);
                }
                else if(menuItem.getText().equals("General"))
                {
                    //Add intent to new general class
                    Intent intent=new Intent(getApplicationContext(),GeneralClgNews.class);
                    startActivity(intent);
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
                    Intent intent=new Intent(getApplicationContext(),Event.class);
                    startActivity(intent);
                }
                else if(menuItem.getText().equals("Discussion")){
                    Intent intent=new Intent(getApplicationContext(),Discussion.class);
                    intent.putExtra("branchyear",branchyear);
                    startActivity(intent);
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
                    intent.putExtra("branchyear",branchyear);
                    intent.putExtra("chosenteacher",chosenteacher);
                    System.out.println("Entering menu activity...");
                    startActivity(intent);
                }

            }
        });
    }
  //GOTTA WORK ON SCROLLING TO DESIRED POSITION
  /*  public void getGeneralNotification(View view)
    {

    }
    public void getCollegeNews(View view) {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                return false;
            }
        });
            webView.loadUrl("http://www.jimsgn.org/");
            webView.setScrollY(450);
             webView.setScrollX(500);
    }

    */
    public void getUniversityNews(View view)
    {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                return false;
            }
        });
        webView.loadUrl("http://www.ipu.ac.in/notices.php");
        webView.setScrollY(1000);
    }

}
