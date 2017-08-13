package com.example.srirang.letsjims;

/*
 * Created by Srirang on 7/30/2017.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;

public class Register extends AppCompatActivity {

    private TextView tvregister;
    private EditText edittextname;
    private EditText edittextemail;

    private TextView tv2;
    private  EditText edittextPassword;
    private  Button buttonregi;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    public Spinner dropdown1,dropdown2;
       private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register1);


        tvregister = (TextView)findViewById(R.id.tvregister);
         edittextname = (EditText)findViewById(R.id.edittextname);
         edittextemail = (EditText)findViewById(R.id.edittextemail);
        tv2 = (TextView) findViewById(R.id.tv2);
        edittextPassword = (EditText)findViewById(R.id.edittextPassword);
         buttonregi = (Button)findViewById(R.id.buttonregi);
        firebaseAuth=FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
         buttonregi.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 registerUser();
             }
         });


        dropdown1 = (Spinner) findViewById(R.id.spinner1);
        String[] Course1 = new String[]{"CSE", "ECE", "CE", "ME", "EE"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Course1);
        dropdown1.setAdapter(adapter1);

        dropdown2 = (Spinner) findViewById(R.id.spinner2);
        String[] Course2 = new String[]{"I", "II", "III", "IV"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Course2);
        dropdown2.setAdapter(adapter2);





    }       //END OF onCreate()

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private void registerUser(){

        //Getting values
        final String email = edittextemail.getText().toString().trim();
        String password = edittextPassword.getText().toString().trim();
        final String name = edittextname.getText().toString().trim();
        final String branch=dropdown1.getSelectedItem().toString();
        final String year=dropdown2.getSelectedItem().toString();
        final String branchyear=branch +" "+ year;

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }



        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }


        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            //Adding to database
                            MakeUser makeUser=new MakeUser(name,email,branch,year,branchyear);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference();   //Root node NODE0
                            DatabaseReference myRef1=myRef.child("Students");  //NODE1

                            int index = email.indexOf('@');                 //Getting username
                            String nameFromEmail = email.substring(0,index);
                            DatabaseReference myRef2=myRef1.child(nameFromEmail);

                            myRef2.setValue(makeUser);




                            Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Register.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Could not Regitser..Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }




}
