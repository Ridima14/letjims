package com.example.srirang.letsjims;

import android.util.Log;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Srirang on 7/17/2017.
 */

public class MessageDataSource {

    private static final Firebase sRef = new Firebase("https://letsjims.firebaseio.com/Query");

    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddmmss");

    private static final String TAG = "MessageDataSource";

    private static final String COLUMN_TEXT = "text";

    private static final String COLUMN_SENDER = "sender";





    public static void saveMessage(Message message, String convoId){

        Date date = message.getmDate();

        String key = sDateFormat.format(date);

        HashMap<String, String> msg = new HashMap<>();


        msg.put(COLUMN_TEXT, message.getmText());

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        String facultyname=firebaseAuth.getCurrentUser().getEmail();   //For student this will be diffrent.

        msg.put(COLUMN_SENDER,facultyname);  //take value from login page
        int index = facultyname.indexOf('@');                 //Getting username
        facultyname = facultyname.substring(0,index);

        sRef.child(facultyname).child(convoId).child(key).setValue(msg);

    }

    //For student side query!!!

    public static void saveMessage(Message message, String convoId,String facultyname1,String studentname){

        Date date = message.getmDate();

        String key = sDateFormat.format(date);

        HashMap<String, String> msg = new HashMap<>();


        msg.put(COLUMN_TEXT, message.getmText());

           //For student this will be diffrent.

        msg.put(COLUMN_SENDER,studentname);//take value from login page

        //Facultyname1 is name iwthout @ sign
        System.out.println("CHECKING INSIDE OVERLOADED SAVEMESSAGE METHOD+"+facultyname1+" "+studentname);



        sRef.child(facultyname1).child(convoId).child(key).setValue(msg);

    }





    public static MessagesListener addMessagesListener(String convoId, final MessagesCallbacks callbacks){

        MessagesListener listener = new MessagesListener(callbacks);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        String facultyname=firebaseAuth.getCurrentUser().getEmail();   //For student this will be diffrent.
        int index = facultyname.indexOf('@');                 //Getting username
        facultyname = facultyname.substring(0,index);

        sRef.child(facultyname).child(convoId).addChildEventListener(listener);

        return listener;

//a

    }

    //FOR STUDENTS MESSAGE LISTENER

    public static MessagesListener addMessagesListener(String convoId, final MessagesCallbacks callbacks,String teachername){

        MessagesListener listener = new MessagesListener(callbacks);


        sRef.child(teachername).child(convoId).addChildEventListener(listener);

        return listener;



    }



    public static void stop(MessagesListener listener){

        sRef.removeEventListener(listener);

    }



    public static class MessagesListener implements ChildEventListener {

        private MessagesCallbacks callbacks;

        MessagesListener(MessagesCallbacks callbacks){

            this.callbacks = callbacks;

        }

        @Override

        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            HashMap<String,String> msg = (HashMap)dataSnapshot.getValue();

            Message message = new Message();

            message.setmSender(msg.get(COLUMN_SENDER));

            message.setmText(msg.get(COLUMN_TEXT));

            try {

                message.setmDate(sDateFormat.parse(dataSnapshot.getKey()));

            }catch (Exception e){

                Log.d(TAG, "Couldn't parse date"+e);

            }

            if(callbacks != null){

                callbacks.onMessageAdded(message);

            }



        }





        @Override

        public void onChildChanged(DataSnapshot dataSnapshot, String s) {



        }



        @Override

        public void onChildRemoved(DataSnapshot dataSnapshot) {





        }



        @Override

        public void onChildMoved(DataSnapshot dataSnapshot, String s) {



        }



        @Override

        public void onCancelled(FirebaseError firebaseError) {



        }

    }





    public interface MessagesCallbacks{

        public void onMessageAdded(Message message);

    }

}
