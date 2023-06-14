package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UpdateInforActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    EditText edt_ten,edt_quequan,edt_ngaysinh;
    FirebaseAuth auth;
    String email,key;
    Account account;
    Button btn_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_infor);
        auth = FirebaseAuth.getInstance();
        email = auth.getCurrentUser().getEmail();
        edt_ngaysinh = findViewById(R.id.edt_ngaysinh);
        edt_ten = findViewById(R.id.edt_ten);
        edt_quequan = findViewById(R.id.edt_quequan);
        btn_update = findViewById(R.id.btn_update);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query query = databaseReference.orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    key= snapshot1.getKey();
                    account = snapshot1.getValue(Account.class);
                    edt_ngaysinh.setText(account.getNgaysinh());
                    edt_quequan.setText(account.getQuequan());
                    edt_ten.setText(account.getTen());



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quequan = edt_quequan.getText().toString();
                String ngaysinh = edt_ngaysinh.getText().toString();
                String ten = edt_ten.getText().toString();
                if (TextUtils.isEmpty(quequan)||TextUtils.isEmpty(ngaysinh)||TextUtils.isEmpty(ten)) {
                    // Nếu rỗng, thực hiện hành động tương ứng
                    Toast.makeText(UpdateInforActivity.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                } else {
                        account.setNgaysinh(ngaysinh);
                        account.setTen(ten);
                        account.setQuequan(quequan);
                        databaseReference.child(key).setValue(account);
                    Toast.makeText(UpdateInforActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}