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

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                java.lang.Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]

        FirebaseMessaging.getInstance().subscribeToTopic("door");
        FirebaseMessaging.getInstance().subscribeToTopic("temp");
        FirebaseMessaging.getInstance().subscribeToTopic("power");
        FirebaseMessaging.getInstance().subscribeToTopic("list");

        String notify = "Subscribed to door, temp, and power topics";
        Log.d(TAG, notify);
        Toast.makeText(post_log_in.this, notify, Toast.LENGTH_SHORT).show();
        Toast.makeText(post_log_in.this, "test", Toast.LENGTH_SHORT).show();

        //ListView mListView = (ListView) findViewById(R.id.contentList);
       // Object apple = new Object("Granny Smith","4/3/18","50 G");
        //Object orange = new Object("Orange","4/3/18","50 G");

        //ArrayList<Object> objList = new ArrayList<>();


        // Now we need to use for loop to fill out the objList properly
        //objList.add(apple);
        //objList.add(orange);

        //  final ArrayList<Object> objList = new ArrayList<>();
        // db.collection("list").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        //   @Override
        // public void onComplete(@NonNull Task<QuerySnapshot> task) {
        //   for(DocumentSnapshot doc:task.getResult()){
        //     Object obj = new Object(doc.getString("name"),doc.getString("DTS"), doc.getString("weight"));
        //   objList.add(obj);

        //}
        //}
        //});


        //ObjListAdapter adapter = new ObjListAdapter(this, R.layout.adapter_view_layout,objList);
       // mListView.setAdapter(adapter);



        mMainList = (RecyclerView) findViewById(R.id.mainList);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter(objListAdapter);
        mFirestore = FirebaseFirestore.getInstance();
        Button btnNavToLogIn = (Button) findViewById(R.id.log_out_btn);
        Button btnNavToSetting = (Button) findViewById(R.id.settingbtn);
      //  Object objTest = new Object("1/2/3","apple","10");

        /*DocumentReference docref = mFirestore.collection("list").document("12345");
        //docref.getId();
        Log.d(TAG, "This is just the docref.get" +docref.getId());
        docref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Object obj = documentSnapshot.toObject(Object.class);
                Log.d(TAG, "Name : "+obj.getObjName());
                Log.d(TAG, "DTS : "+obj.getObjDTS());
                Log.d(TAG, "weight : "+obj.getObjWeight());
            }
        });*/

/*        mFirestore.collection("list").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc : task.getResult()){
                        Object objectT = doc.toObject(Object.class);
                        Log.d(TAG,"This is the object"+objectT.getObjName());
                        Log.d(TAG,"This is the object get result"+getTaskId());
                        objList.add(objectT);
                        objListAdapter.notifyDataSetChanged();

                    }
                }
            }
        });*/

       /*   mFirestore.collection("list")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Object obj2 = new Object();
                                Map thing = document.getData();
                                String str = document.getData().toString();
                                //String bOfdts, eOfdts, bOfname,eOfname,bOfweight;
                               //String dts  = between(str,"DTS=",", n");
                                String dts = str.substring(str.indexOf("dts=") + "dts=".length(),str.indexOf(", name=",str.indexOf("dts=") + "dts=".length() ));
                                obj2.setObjDTS(dts);
                                //obj2.setObjName(thing.get(1).toString());
                                //obj2.setObjWeight(thing.get(2).toString());
                                objList.add(obj2);
                                objListAdapter.notifyDataSetChanged();

                               // Log.d(TAG, document.getId() + " => " + document.getData());
                                Log.d(TAG, document.getId() + "str=> " +str);
                                Log.d(TAG, document.getId() + "dts=> " +obj2.getObjDTS());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        objListAdapter.notifyDataSetChanged();*/


////This here uncomment
         mFirestore.collection("list").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if(e != null){
                    Log.d(TAG, "error :"+e.getMessage());
                }

                for(DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()){
                    //if(doc.getType() == DocumentChange.Type.ADDED){
                    String name = doc.getDocument().getString("name");
                    String dts = doc.getDocument().getString("dts");
                    String weight = doc.getDocument().getString("weight");
                    Object obj =  new Object();
                    obj.setObjName(name);
                    obj.setObjDTS(dts);
                    obj.setObjWeight(weight);
                  //  Log.d(TAG, "Name1 : "+name);
                   // Object object = doc.getDocument().toObject(Object.class);
                    //Object obj2 = doc.getDocument().getData(Object.class);
                    objList.add(obj);
                    objListAdapter.notifyDataSetChanged();
                    Log.d(TAG, "Name : "+obj.getObjName());
                    Log.d(TAG, "DTS : "+obj.getObjDTS());
                    Log.d(TAG, "weight : "+obj.getObjWeight());
                    //}
                }
            }
        });
        //objList.add(objTest);
        //objListAdapter.notifyDataSetChanged();


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

    }

    String between(String value, String a, String b) {
        // Return a substring between the two strings.
        int posA = value.indexOf(a);
        if (posA == -1) {
            return "";
        }
        int posB = value.lastIndexOf(b);
        if (posB == -1) {
            return "";
        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= posB) {
            return "";
        }
        return value.substring(adjustedPosA, posB);
    }

}



