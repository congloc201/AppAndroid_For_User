package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class BuyPackageFragment extends Fragment {

    TabLayout tabLayout;
    TextView txt_ngaybatdau,txt_ngayketthuc,txt_gia;
    Button btn_continue;
      String email, user_key,loai_xe,ngaybatdau,ngayketthuc_thang,ngayketthuc_quy ;
    FirebaseAuth auth;
    long newtime_thang,newtime_quy,currenttime, temp_time;
    Date date1,date2,date3;
    int money_thang,money_quy,temp_money,balance;
    public BuyPackageFragment() {
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
        View view = inflater.inflate(R.layout.fragment_buy_package, container, false);
                auth = FirebaseAuth.getInstance();
                email = auth.getCurrentUser().getEmail();
                tabLayout = view.findViewById(R.id.tab_layout);
                txt_gia = view.findViewById(R.id.txt_gia);
                txt_ngaybatdau =view.findViewById(R.id.txt_ngaybatdau);
                txt_ngayketthuc = view.findViewById(R.id.txt_ngayketthuc);
                btn_continue = view.findViewById(R.id.continue_btn);

                currenttime = System.currentTimeMillis();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(currenttime);
                calendar.add(Calendar.MONTH,1);
                newtime_thang = calendar.getTimeInMillis();
                calendar.add(Calendar.MONTH,3);
                newtime_quy = calendar.getTimeInMillis();

                date1 = new Date(currenttime);
                date2 = new Date(newtime_thang);
                date3 = new Date((newtime_quy));

                 DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("users");
                Query query1 = databaseReference1.orderByChild("email").equalTo(email);
                query1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                            Account account = snapshot1.getValue(Account.class);
                            assert account != null;
                            balance = account.getSodu();
                            user_key = snapshot1.getKey();
                            loai_xe = account.getLoaixe();
                            get_cost();
                            setup_tablayout();

//                            Log.d("loaxe",loai_xe);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


//        Toast.makeText(getActivity(), String.valueOf(balance), Toast.LENGTH_SHORT).show();




        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:yyyy", Locale.getDefault());
        ngaybatdau = simpleDateFormat.format(date1);
         ngayketthuc_thang = simpleDateFormat.format(date2);
         ngayketthuc_quy = simpleDateFormat.format(date3);
        txt_ngaybatdau.setText(ngaybatdau);
        txt_ngayketthuc.setText(ngayketthuc_thang);
        txt_gia.setText(String.valueOf(money_thang));

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Goi");
                Query query1 = databaseReference.orderByChild("email").equalTo(email);
                query1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int a = balance-temp_money;
                        if(snapshot.exists()){
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                Goi goi = snapshot1.getValue(Goi.class);
                                assert goi != null;
                                String key = snapshot1.getKey();
                                Timestamp timestamp1 = new Timestamp(goi.getNgaydatdau());
                                Timestamp timestamp2 = new Timestamp(goi.getNgayketthuc());
                                int result = timestamp1.compareTo(timestamp2);


                                if(result<0){
                                    showdialog();
                                    return;
                                }
                                else if(a<0){
                                    showdialog_balance();
                                    return;
                                }
                                else {
                                    dangkigoi(balance-temp_money);
                                return;}
                        }


                        }else if(a<0){
                            showdialog_balance();
                            return;

                        }
                        else dangkigoi(balance-temp_money);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        return view;
    }
    private void get_cost(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Loaigoi");
        Query query = databaseReference.orderByChild("loaixe").equalTo(loai_xe);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    Loaigoi loaigoi = snapshot1.getValue(Loaigoi.class);
                    assert loaigoi != null;

                    if(loaigoi.getLoaive().equals("Thang")){
                        money_thang = loaigoi.getGia();
                    } else if (loaigoi.getLoaive().equals("Quy")) {
                        money_quy =loaigoi.getGia();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void setup_tablayout(){
        tabLayout.addTab(tabLayout.newTab().setText("Vé tháng"));
        tabLayout.addTab(tabLayout.newTab().setText("Vé quý"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    txt_ngayketthuc.setText(ngayketthuc_thang);
                    txt_gia.setText(String.valueOf(money_thang));
                    temp_time = newtime_thang;
                    temp_money = money_thang;
                }
                else if(tab.getPosition()==1){
                    txt_ngayketthuc.setText(ngayketthuc_quy);
                    txt_gia.setText(String.valueOf(money_quy));
                    temp_time = newtime_quy;
                    temp_money = money_quy;
                }
                else{
                    txt_gia.setText(String.valueOf(money_thang));
                    temp_money = money_thang;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                txt_gia.setText(String.valueOf(money_thang));
                temp_money = money_thang;
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void showdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông báo")
                .setMessage("Bạn đã đang kí")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Thực hiện đoạn mã khi người dùng click vào nút Có
                        ; // đóng ứng dụng
                        dialog.dismiss(); // đóng dialog
                    }
                });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showdialog_balance(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông báo")
                .setMessage("Không đủ số dư")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Thực hiện đoạn mã khi người dùng click vào nút Có
                        ; // đóng ứng dụng
                        dialog.dismiss(); // đóng dialog
                    }
                });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void dangkigoi(int surplus){
        Goi goi1 = new Goi(email,"Thang",currenttime, temp_time,"true");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Goi");
        String key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(goi1);

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
        Naprut naprut = new Naprut(email,temp_money,false,String.valueOf(System.currentTimeMillis()),"Mua vé");
        databaseReference1.child("Naprut").push().setValue(naprut);
//        Log.d("key",key);
//
        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference();

        databaseReference1.child("users").child(user_key).child("sodu").setValue(surplus);

        Intent intent = new Intent(getActivity(),SuccessTransationActivity.class);
        intent.putExtra("money",String.valueOf(temp_money));
        intent.putExtra("key",key);
        startActivity(intent);
    }
}

