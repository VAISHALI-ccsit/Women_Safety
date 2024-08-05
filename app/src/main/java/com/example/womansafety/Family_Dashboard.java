package com.example.womansafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Family_Dashboard extends AppCompatActivity {

    RecyclerView get_FCM_msg__recycleview;
    MyAdapter myAdapter;
    Button button;
    //DatabaseReference databaseReference;
    //ArrayList<UserRecycle> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_dashboard);

        get_FCM_msg__recycleview = findViewById(R.id.get_FCM_msg__recycleview);
        get_FCM_msg__recycleview.setLayoutManager(new LinearLayoutManager(this));
        button = findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseRecyclerOptions<UserRecycle> options=
                        new FirebaseRecyclerOptions.Builder<UserRecycle>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Address"), UserRecycle.class)
                                .build();

                myAdapter=new MyAdapter(options);
                get_FCM_msg__recycleview.setAdapter(myAdapter);
            }
        });

    }
}