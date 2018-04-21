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
import android.widget.CompoundButton;
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
import android.widget.Switch;
import android.widget.Toast;


public class settings_activity extends  AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);


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
         //       Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]
        Switch doorSW = (Switch) findViewById(R.id.door_sw);
        Switch tempSW = (Switch) findViewById(R.id.temp_sw);
        Switch powerSW = (Switch) findViewById(R.id.power_sw);
        Switch listSW = (Switch) findViewById(R.id.list_sw);




        doorSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(isChecked){
                  FirebaseMessaging.getInstance().unsubscribeFromTopic("door");
                  Toast.makeText(settings_activity.this, "Door Notifications are On", Toast.LENGTH_SHORT).show();
              }
              else{
                  FirebaseMessaging.getInstance().subscribeToTopic("door");
                  Toast.makeText(settings_activity.this, "Door Notifications are Off", Toast.LENGTH_SHORT).show();
              }
            }

        });
        tempSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("temp");
                    Toast.makeText(settings_activity.this, "Tempature Notifications are On", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseMessaging.getInstance().subscribeToTopic("temp");
                    Toast.makeText(settings_activity.this, "Tempature Notifications are Off", Toast.LENGTH_SHORT).show();
                }
            }

        });
        powerSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("power");
                    Toast.makeText(settings_activity.this, "Power Notifications are On", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseMessaging.getInstance().subscribeToTopic("power");
                    Toast.makeText(settings_activity.this, "Power Notifications are Off", Toast.LENGTH_SHORT).show();
                }
            }

        });
        listSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("list");
                    Toast.makeText(settings_activity.this, "List Notifications are On", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseMessaging.getInstance().subscribeToTopic("list");
                    Toast.makeText(settings_activity.this, "List Notifications are Off", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}
