package com.example.myapplication;

import static com.example.myapplication.UseFullMethod.currencyconvert;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AccountFragment extends Fragment {
    TabLayout tabLayout;
    TextView txt_balance;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    FirebaseUser user;
    String email ;
    ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.accountfragment,container,false);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
        txt_balance = view.findViewById(R.id.banlace_txt);
        auth = FirebaseAuth.getInstance();

        email =auth.getCurrentUser().getEmail();
        ViewPagerAccountAdapter viewPagerAccountAdapter = new ViewPagerAccountAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAccountAdapter);
        tabLayout.setupWithViewPager(viewPager);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query query = databaseReference.orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                    Account account = childSnapshot.getValue(Account.class);
//                Toast.makeText(getActivity(), account.toString(), Toast.LENGTH_SHORT).show();

                    txt_balance.setText(currencyconvert(account.getSodu()));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

}
