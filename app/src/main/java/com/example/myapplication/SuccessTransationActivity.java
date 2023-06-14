package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SuccessTransationActivity extends AppCompatActivity {

    TextView amount_monney_txt,transation_id;
    String money,key;
    Button backhome_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_transation);
        Intent intent = getIntent();
        money = intent.getStringExtra("money");
        key = intent.getStringExtra("key");
        amount_monney_txt = findViewById(R.id.amountmoney_txt);
        backhome_btn = findViewById(R.id.backhome_btn);
        transation_id= findViewById(R.id.transationcode_txt);
        amount_monney_txt.setText(money);
        transation_id.setText(key);
        backhome_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SuccessTransationActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

    }
}