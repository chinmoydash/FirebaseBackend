package com.example.chinmoydash.firebasebackend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button mFirebaseButton;
    private EditText mNameField;
    private EditText mEmailField;
    private DatabaseReference  mDatabase;
    private Button mViewData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseButton = (Button)findViewById(R.id.fb_btn);
        mNameField = (EditText)findViewById(R.id.et_name);
        mEmailField = (EditText)findViewById(R.id.et_mail);
        mViewData = (Button)findViewById(R.id.view_btn);

        mViewData.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewData.class);
                    startActivity(intent);

            }

        });

        //mDatabase is pointing to the root directory of the app
        mDatabase = FirebaseDatabase.getInstance().getReference().child("user");


        mFirebaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //1 -create child in root object
                //2-Assign some value to the child object

               String name = mNameField.getText().toString().trim();
               String email = mEmailField.getText().toString().trim();

                HashMap<String,String> dataMap = new HashMap<String, String>();
                dataMap.put("Name",name);
                dataMap.put("Email",email);

                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();


                        } else {

                            Toast.makeText(MainActivity.this, "Some Error occured", Toast.LENGTH_SHORT).show();

                        }
                    }

                });

            }

        }
        );
     }
}
