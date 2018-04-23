package com.example.nikma.fs20;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import android.os.Build;
import android.os.Bundle;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class accessability_activity  extends  AppCompatActivity{


    private static final String TAG = "Firelog";
    private RecyclerView mMainList;
    private  FirebaseFirestore mFirestore;

    private List<User> userList;

    private UsrListAdapter usrListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.accessability_layout);

        userList = new ArrayList<>();
        usrListAdapter = new UsrListAdapter(userList);


        mMainList = (RecyclerView) findViewById(R.id.user_view);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter(usrListAdapter);
        mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if(e != null){
                    Log.d(TAG, "error :"+e.getMessage());
                }

                for(DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()){

                    String usr = doc.getDocument().getId();
                    Boolean accs = doc.getDocument().getBoolean("access");
                    User obj =  new User();
                    obj.setusrAcc(accs);
                    obj.setusrName(usr);

                    userList.add(obj);
                    boolean test = obj.getusrAcc();
                    if(test){
                        Toast.makeText(accessability_activity.this, "Access True", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(accessability_activity.this, "Access False", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(accessability_activity.this, obj.getusrName().toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Name: " +obj.getusrName());
                    Log.d(TAG, "Access: " +obj.getusrAcc());
                    usrListAdapter.notifyDataSetChanged();

                }

               Log.d(TAG,"Number of users : " +usrListAdapter.getItemCount());
               Log.d(TAG,"User 1 name from list adapter : " +usrListAdapter.usrList.get(0).getusrName());
               Log.d(TAG,"User 1 access from list adapter : " +usrListAdapter.usrList.get(0).getusrAcc());
            }
        });




    }



}
