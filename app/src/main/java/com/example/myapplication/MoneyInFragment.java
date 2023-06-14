package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MoneyInFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Naprut> arrayList;
    RecycleviewAdapter adapter;
    DatabaseReference databaseReference;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public MoneyInFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_money_in, container, false);
        recyclerView = view.findViewById(R.id.recycleview_moneyin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        arrayList = new ArrayList<Naprut>();
        adapter = new RecycleviewAdapter(getActivity(),arrayList);

        databaseReference = FirebaseDatabase.getInstance().getReference("Naprut");
        Query query = databaseReference.orderByChild("email").equalTo("pentakillhy@gmail.com");
        query.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    Naprut naprut = snapshot1.getValue(Naprut.class);
                    assert naprut != null;
                    if(naprut.isTienvao())
                    {
                        arrayList.add(naprut);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }
}