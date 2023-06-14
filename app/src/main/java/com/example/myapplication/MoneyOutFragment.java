package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoneyOutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoneyOutFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Naprut> arrayList;
    RecycleviewAdapter adapter;
    DatabaseReference databaseReference;

    public MoneyOutFragment() {
        // Required empty public constructor
    }


    public static MoneyOutFragment newInstance(String param1, String param2) {
        MoneyOutFragment fragment = new MoneyOutFragment();

        return fragment;
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
                    if(!naprut.isTienvao())
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