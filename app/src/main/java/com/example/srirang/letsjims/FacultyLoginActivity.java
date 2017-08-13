package com.example.srirang.letsjims;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Srirang on 8/4/2017.
 */

public class FacultyLoginActivity extends AppCompatActivity {

    public EditText fname,fpassword;
    public Button floginbtn;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.faculty_login);
        fname=(EditText)findViewById(R.id.fname);
        fpassword=(EditText)findViewById(R.id.fpassword);
        floginbtn=(Button)findViewById(R.id.floginbtn);
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){       //User already logged in
            //Change code,pass user name to next activity
            Toast.makeText(getApplicationContext(),"Already logged in",Toast.LENGTH_SHORT).show();
            String s=firebaseAuth.getCurrentUser().getEmail();
            System.out.println(s);


            Intent intent=new Intent(FacultyLoginActivity.this,menuT.class);
            intent.putExtra("Facultyname",s);
            startActivity(intent);


        }

    }


    public void goToMenu(View view)
    {

        final String enamex=fname.getText().toString().toLowerCase();
        final String ename=enamex+"@gmail.com";
        String epassword=fpassword.getText().toString();


        if(TextUtils.isEmpty(ename)){
            Toast.makeText(getApplicationContext(), "Please Enter First Name", Toast.LENGTH_SHORT).show();
            return;
        }



        if(TextUtils.isEmpty(epassword)){
            Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }



        firebaseAuth.signInWithEmailAndPassword(ename,epassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                               Toast.makeText(getApplicationContext(),"Logged in",Toast.LENGTH_SHORT).show();


                            finish();
                            Intent intent=new Intent(FacultyLoginActivity.this,menuT.class);
                            intent.putExtra("Facultyname",ename);
                            if(ename.equals("vandita@gmail.com"))       //Initial class for
                                                                         //Each teacher for intent extra
                            intent.putExtra("ChosenClass","CSE II");
                            else if(ename.equals("madhulika@gmail.com"))
                                intent.putExtra("ChosenClass","CSE II");
                            else if(ename.equals("pramod@gmail.com"))
                                intent.putExtra("ChosenClass","CSE II");
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Could not Regitser..Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }




}
