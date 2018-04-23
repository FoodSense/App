package com.example.nikma.fs20;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.Object;
import java.util.HashMap;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentChange.Type;
import com.google.firebase.firestore.DocumentListenOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Query.Direction;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.HashMap;


public class LogIn extends AppCompatActivity {

    //FirebaseFirestore db;
    private  FirebaseFirestore mFirestore;
    private static final String TAG = "Firelog2";
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in);
        mFirestore = FirebaseFirestore.getInstance();

       // db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        //Map<String, Object> user = new HashMap<>();
        //user.put("first", "Ada");
        //user.put("last", "Lovelace");
        //user.put("born", 1815);

// Add a new document with a generated ID
        //db.collection("users")
          //      .add(user);



        Button btnNavToPostLogIn = (Button) findViewById(R.id.log_in_btn);
        Button btnNavToRegister = (Button) findViewById(R.id.register_btn);

        btnNavToPostLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                final TextView uID = (TextView) findViewById(R.id.user);
                final TextView uPass = (TextView) findViewById(R.id.pass);
                //  final boolean passGood = false;
//                final   TextView error = (TextView) findViewById(R.id.);

                final String userID = uID.getText().toString();
                final String userPass = uPass.getText().toString();
                if(userID.length() < 1 || userPass.length() < 1){

//                    error.setText("Please enter in both User Name and Password");
//                    error.setVisibility(View.VISIBLE);
                }
                else {
                    DocumentReference docRef = mFirestore.collection("users").document(userID);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                String fsPass = document.getString("userPass");
                                String fsUserID =document.getId();
                                Boolean fsAccess = document.getBoolean("access");
                                if (document != null && document.exists()) {
                                    if (fsPass.contentEquals(userPass) && fsUserID.contentEquals(userID) && fsAccess) {
//                                        error.setVisibility(View.INVISIBLE);
                                        Intent intent = new Intent(LogIn.this, post_log_in.class);
                                        startActivity(intent);
                                    }
                                } else {
   //                                 error.setText("Invalid Password or Username");
                                    Log.d(TAG, "No such document");
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });

/*                    mFirestore.collection("users").document(userID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                            if (e != null) {
                                Log.d(TAG, "error :" + e.getMessage());
                            } else {
                                String fsPass = documentSnapshot.getString("userPass");
                                String fsUserID =documentSnapshot.getId();

                                if (fsPass.contentEquals(userPass) && fsUserID.contentEquals(userID)) {
                                    error.setVisibility(View.INVISIBLE);
                                    Intent intent = new Intent(LogIn.this, post_log_in.class);
                                    startActivity(intent);
                                } else {
                                    error.setText("Invalid Password or Username");
                                }
                            }
                        }
                    });*/
                }
/*


                Intent intent = new Intent(LogIn.this,post_log_in.class );
                startActivity(intent);

               *//* DocumentReference user = mFirestore.collection("users").document(userID);
                user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot doc = task.getResult();
                            String userFromFS = doc.toString();
                            Log.d(TAG, "Password : "+userFromFS);

                        }
                    }
                });*//*


            }
        });
*/
            }

        });
        btnNavToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView uID = (TextView) findViewById(R.id.user);
                final TextView uPass = (TextView) findViewById(R.id.pass);
//                final   TextView error = (TextView) findViewById(R.id.error);

                final String userID = uID.getText().toString();
                final String userPass = uPass.getText().toString();
                if(userID.length() < 1 || userPass.length() < 1){

  //                  error.setText("Please enter in both User Name and Password");
   //                 error.setVisibility(View.VISIBLE);
                }
                else {
                    final DocumentReference  docref2= mFirestore.collection("users").document();
                    docref2.get();
                    final DocumentReference docRef = mFirestore.collection("users").document(userID);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                boolean isEmpty = !task.getResult().exists();
                                if(isEmpty){
                                    Map<String, Object> newUsr = new HashMap<>();
                                    newUsr.put("userPass", userPass);
                                    newUsr.put("access", false);
                                    mFirestore.collection("users").document(userID).set(newUsr)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(TAG, "Error writing document", e);
                                                }
                                            });
//                                    error.setVisibility(View.INVISIBLE);
                                    Intent intent = new Intent(LogIn.this, postregisterActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    boolean usrAccess = document.getBoolean("access");
                                    if (usrAccess){
                          //          error.setText("User is already in the system");
                                    Log.d(TAG, "No such document");
                                    }
                                    else{
                            //            error.setText("User is awaiting approval");
                                    }

                                }
                            }

                        }
                    });


                }

            }

        });
    }

}
