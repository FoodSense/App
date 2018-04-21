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
public class post_log_in  extends AppCompatActivity{


    private static final String TAG = "Firelog";
    private RecyclerView mMainList;
    private  FirebaseFirestore mFirestore;

    private List<Object> objList;

    private ObjListAdapter objListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_log_in_screen);

        objList = new ArrayList<>();
        objListAdapter = new ObjListAdapter(objList);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = "123";
            String channelName = "YES";
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                java.lang.Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]

        //If we have time add field to users as to what topics they are subriscibed too and use that as the control as to when they subbd
        FirebaseMessaging.getInstance().subscribeToTopic("door");
        FirebaseMessaging.getInstance().subscribeToTopic("temp");
        FirebaseMessaging.getInstance().subscribeToTopic("power");
        FirebaseMessaging.getInstance().subscribeToTopic("list");

        String notify = "Subscribed to door, temp, and power topics";
        Log.d(TAG, notify);
        Toast.makeText(post_log_in.this, notify, Toast.LENGTH_SHORT).show();

        mMainList = (RecyclerView) findViewById(R.id.mainList);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter(objListAdapter);
        mFirestore = FirebaseFirestore.getInstance();
        Button btnNavToLogIn = (Button) findViewById(R.id.log_out_btn);
        Button btnNavToSetting = (Button) findViewById(R.id.settingbtn);
        Button btnNavToAccess = (Button) findViewById(R.id.accessbtn);

         mFirestore.collection("list").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "error :" + e.getMessage());
                }

                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED){
                        String name = doc.getDocument().getString("name");
                        String dts = doc.getDocument().getString("dts");
                        String weight = doc.getDocument().getString("weight");
                        Object obj = new Object();
                        obj.setObjName(name);
                        obj.setObjDTS(dts);
                        obj.setObjWeight(weight);

                        objList.add(obj);
                        objListAdapter.notifyDataSetChanged();
                        Log.d(TAG, "Name : " + obj.getObjName());
                        Log.d(TAG, "DTS : " + obj.getObjDTS());
                        Log.d(TAG, "weight : " + obj.getObjWeight());

                    }
                    if(doc.getType() == DocumentChange.Type.REMOVED){
                        String name = doc.getDocument().getString("name");
                        String dts = doc.getDocument().getString("dts");
                        String weight = doc.getDocument().getString("weight");
                        Object obj = new Object();
                        obj.setObjName(name);
                        obj.setObjDTS(dts);
                        obj.setObjWeight(weight);

                        objList.remove(obj);
                        objListAdapter.notifyDataSetChanged();
                        finish();
                        startActivity(getIntent());
                    }
                }


            }
        });

        btnNavToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(post_log_in.this,LogIn.class);
                startActivity(intent);
            }
        });

        btnNavToSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(post_log_in.this,settings_activity.class);
                startActivity(intent);
            }
        });
        btnNavToAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(post_log_in.this,accessability_activity.class);
                startActivity(intent);
            }
        });

    }
}





