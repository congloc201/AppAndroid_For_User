package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
// ...

    Dialog dialog;
    EditText edt_ten,edt_ngaysinh,edt_quequan,edt_email,edt_bienso,edt_matkhau;
    Account account=new Account();
    String[] colors = {"Màu Xanh","Màu Trắng","Màu Vàng"};
    Button btn_signup;
    String matkhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        dialog=new Dialog(this);
        Spinner Spinner = findViewById(R.id.color_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner.setAdapter(adapter);
        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                account.setMaubien(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        edt_ten = findViewById(R.id.edt_ten);
        edt_ngaysinh = findViewById(R.id.edt_ngaysinh);
        edt_quequan = findViewById(R.id.edt_quequan);
        edt_email = findViewById(R.id.edt_email);
        edt_bienso = findViewById(R.id.edt_bienso);
        edt_matkhau = findViewById(R.id.edt_matkhau);


        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account.setTen(edt_ten.getText().toString());
                account.setEmail(edt_email.getText().toString());
                account.setNgaysinh(edt_ngaysinh.getText().toString());
                account.setQuequan(edt_quequan.getText().toString());
                account.setBienso(edt_bienso.getText().toString());
                account.setLoaixe(null);
                account.setRfid(null);
                matkhau = edt_matkhau.getText().toString();
                auth.createUserWithEmailAndPassword(account.getEmail(),matkhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            addaccount(account);
                            opendialog();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());


                        }
                    }
                }) ;


            }
        });


    }

    private void opendialog() {
        dialog.setContentView(R.layout.signup_success_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        Button btn_home = dialog.findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    private void addaccount(Account account){
        mDatabase.child("users").push().setValue(account);

    }
}