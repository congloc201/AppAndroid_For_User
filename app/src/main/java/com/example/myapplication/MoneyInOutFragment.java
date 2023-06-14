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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoneyInOutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoneyInOutFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Naprut> arrayList;
    RecycleviewAdapter adapter;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    public MoneyInOutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoneyInOutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoneyInOutFragment newInstance(String param1, String param2) {
        MoneyInOutFragment fragment = new MoneyInOutFragment();

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
        auth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_money_in, container, false);
        recyclerView = view.findViewById(R.id.recycleview_moneyin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        arrayList = new ArrayList<Naprut>();
        adapter = new RecycleviewAdapter(getActivity(),arrayList);

        databaseReference = FirebaseDatabase.getInstance().getReference("Naprut");
        Query query = databaseReference.orderByChild("email").equalTo(auth.getCurrentUser().getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    Naprut naprut = snapshot1.getValue(Naprut.class);
                        arrayList.add(naprut);

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