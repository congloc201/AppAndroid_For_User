package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DepositActivity extends AppCompatActivity {

    String money,key, email;
    FirebaseAuth auth;
    Query query;
    RadioGroup radioGroup;
    Button contunue_btn;
    EditText another_monney_edt;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        auth = FirebaseAuth.getInstance();
        email = auth.getCurrentUser().getEmail();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
         query = databaseReference.orderByChild("email").equalTo(email);

        contunue_btn = findViewById(R.id.continue_btn);
        another_monney_edt = findViewById(R.id.another_money_edt);
        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.r50:
                        money="50000";
                        Toast.makeText(DepositActivity.this, money, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.r100:
                        money="100000";
                        break;
                    case R.id.r200:
                        money="200000";
                        break;
                }
            }
        });
        contunue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(another_monney_edt.getText())){

                  deposit();

                }
                else {

                   money = another_monney_edt.getText().toString();
                    deposit();
                }

            }
        });

    }
    private void deposit(){
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Account account = snapshot.getValue(Account.class);
                assert account != null;
                key = snapshot.getKey();
                int sodu = account.getSodu()+ Integer.parseInt(money);
                databaseReference.child(key).child("sodu").setValue(sodu);
                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
                Naprut naprut = new Naprut(email,Integer.parseInt(money),true,String.valueOf(System.currentTimeMillis()),"Nạp tiền");
                databaseReference1.child("Naprut").push().setValue(naprut);
                Intent intent = new Intent(DepositActivity.this,SuccessTransationActivity.class);
                intent.putExtra("key",key);
                intent.putExtra("money",money);
                startActivity(intent);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}