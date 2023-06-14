package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.errorprone.annotations.Var;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.example.myapplication.Variable;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText edt_account,edt_pass;
    Button btn_login,btn_signup;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_account = findViewById(R.id.edt_account);
        edt_pass = findViewById(R.id.edt_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null){
                    updateUI();
                }
                else{
                    String email,pass;
                    email = String.valueOf(edt_account.getText());
                    pass = String.valueOf(edt_pass.getText());
                    if(TextUtils.isEmpty(email)){
                        Toast.makeText(LoginActivity.this, "Bạn chưa điền Email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(pass)){
                        Toast.makeText(LoginActivity.this, "Bạn chưa điền mật khẩu", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Login(email,pass);
                }

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void updateUI() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

private void Login(String email,String pass)
{
    mAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công",
                                Toast.LENGTH_SHORT).show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                                Query query = databaseReference.orderByChild("email").equalTo("pentakillhy@gmail.com");
                                query.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                    }

                                    @Override
                                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                        Account account= snapshot.getValue(Account.class);
                                        assert account != null;
                                        Variable.setEmail(account.getEmail());
                                        Variable.setLoaixe(account.getLoaixe());
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
                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Loaigoi");
                                Query query1  = databaseReference1.orderByChild("Loaixe").equalTo(Variable.getLoaixe());
                                query1.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                                            Loaigoi loaigoi= snapshot1.getValue(Loaigoi.class);
                                            assert loaigoi != null;
                                            if(loaigoi.getLoaive().equals("Thang")){
                                                Variable.setGoithang(loaigoi.getGia());
                                            } else if (loaigoi.getLoaive().equals("Quy")) {
                                                Variable.setGoiQuy(loaigoi.getGia());

                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }).start();


                        updateUI();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu",
                                Toast.LENGTH_SHORT).show();
                        // updateUI(null);
                    }
                }
            });
}
    @Override
    protected void onStart() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Variable.setEmail(currentUser.getEmail());

            databaseReference = FirebaseDatabase.getInstance().getReference("user");
            Query query = databaseReference.child("email").equalTo(email);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snapshot1:snapshot.getChildren()) {
                        Account account = snapshot1.getValue(Account.class);
                        String key = snapshot1.getKey();
                        Variable.setKey(key);
                        Variable.setLoaixe(account.getLoaixe());
                        Toast.makeText(LoginActivity.this, account.getLoaixe(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            updateUI();
        }
        super.onStart();
    }

}