package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStateManagerControl;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.botom_nav);
        viewPager = findViewById(R.id.view_pager);
        setupAdapter();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()){
                   case R.id.home:
                       Toast.makeText(MainActivity.this, "Trang chủ", Toast.LENGTH_SHORT).show();
                       viewPager.setCurrentItem(0);
                       break;
                   case R.id.account:
                       Toast.makeText(MainActivity.this, "Tài khoản", Toast.LENGTH_SHORT).show();
                       viewPager.setCurrentItem(1);
                       break;
                   case R.id.gift:
                       Toast.makeText(MainActivity.this, "Ưu đãi", Toast.LENGTH_SHORT).show();
                       viewPager.setCurrentItem(2);
                       break;
                   case R.id.station:
                       Toast.makeText(MainActivity.this, "Trạm", Toast.LENGTH_SHORT).show();
                       viewPager.setCurrentItem(3);
                       break;
               }
                return true;
            }
        });


//            databaseReference = FirebaseDatabase.getInstance().getReference("user");
//            Query query = databaseReference.child("email").equalTo(email);
//            query.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for (DataSnapshot snapshot1:snapshot.getChildren()) {
//                        Account account = snapshot1.getValue(Account.class);
//                        String key = snapshot1.getKey();
//                        Variable.setEmail(email);
//                        Variable.setKey(key);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });


    }

    protected void setupAdapter()
    {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
viewPager.setAdapter(viewPagerAdapter);
    }
}