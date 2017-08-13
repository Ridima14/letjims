package com.example.srirang.letsjims;

import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import android.app.ProgressDialog;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;


        public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

            private Button buttonlogin;
            private EditText edittextPassword;
            private TextView tv1;
            private ImageButton FloginIB;
            private EditText edittextemail;
            private ProgressDialog progressDialog;
            private FirebaseAuth firebaseAuth;


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);
                tv1 = (TextView)findViewById(R.id.tv1);
                buttonlogin = (Button)findViewById(R.id.buttonlogin);
                edittextPassword = (EditText)findViewById(R.id.edittextPassword);
                edittextemail = (EditText)findViewById(R.id.edittextemail);
                firebaseAuth=FirebaseAuth.getInstance();

          /*      if(firebaseAuth.getCurrentUser() !=null){       //User already logged in
                    //Change code,pass user name to next activity
                    Toast.makeText(getApplicationContext(),"Already logged in",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), menuT.class));



                }
                */
                progressDialog = new ProgressDialog(this);

                buttonlogin.setOnClickListener(this);


            }

    private void userLogin(){
        String email = edittextemail.getText().toString().trim();
        String password = edittextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }



        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("Logging in ...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();                     //Add intent and change to student menu

                            startActivity(new Intent(getApplicationContext(), StudentHomepage.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Could not Regitser..Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    @Override
    public void onClick(View view){

        if(view==buttonlogin)
        {

            userLogin();
        }
    }


    public void onCreateAccount(View view)
    {
        Intent intent = new Intent(LoginActivity.this,Register.class);
        startActivity(intent);


    }
    public void onFacultyLogin(View view) {                 /*CHANGE TO FACULTYLOGIN CLASS*/
        Intent intent = new Intent(LoginActivity.this,FacultyLoginActivity.class);
        startActivity(intent);
    }



}
