package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecycleviewHistoryAdapter adapter;
    ArrayList<Lichsu> arrayList = new ArrayList<Lichsu>();
    FirebaseAuth auth;
    String email;
    DatabaseReference databaseReference;
    public HistoryActivity() {
        // Required empty public constructor
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recycleview);
        auth = FirebaseAuth.getInstance();
        email = auth.getCurrentUser().getEmail();
        adapter = new RecycleviewHistoryAdapter(HistoryActivity.this,arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
        databaseReference = FirebaseDatabase.getInstance().getReference("Lichsu");
        Query query = databaseReference.orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    Lichsu lichsu = snapshot1.getValue(Lichsu.class);

                        arrayList.add(lichsu);


                }
                adapter.notifyDataSetChanged();
                Log.d("size",String.valueOf(arrayList.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setAdapter(adapter);

    }
}