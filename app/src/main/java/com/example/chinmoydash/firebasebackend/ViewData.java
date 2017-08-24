package com.example.chinmoydash.firebasebackend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewData extends AppCompatActivity {

    DatabaseReference mReference;
    RecyclerView recycle;
    Button view;
    FirebaseDatabase database;
    List<DataModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);
        view = (Button) findViewById(R.id.view);
        recycle = (RecyclerView) findViewById(R.id.recycle);

        database = FirebaseDatabase.getInstance();
        mReference = database.getReference().child("user");


        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list = new ArrayList<DataModel>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    DataModel value = dataSnapshot1.getValue(DataModel.class);

                    DataModel dm = new DataModel();
                    String name = value.getName();
                    String email = value.getEmail();
                    dm.setName(name);
                    dm.setEmail(email);

                    list.add(dm);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {


                Log.w("Hello", "Failed to read value.", error.toException());
            }

        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,ViewData.this);
                RecyclerView.LayoutManager recyce = new GridLayoutManager(ViewData.this,1);
                recycle.setLayoutManager(recyce);
               // recycle.setItemAnimator(new DefaultItemAnimator());
                recycle.setAdapter(recyclerAdapter);


            }
        });

    }

}