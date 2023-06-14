package com.example.myapplication;

import static com.example.myapplication.UseFullMethod.currencyconvert;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {
    String imageUrl = "https://example.com/image.jpg";

    FirebaseAuth auth;
    FirebaseUser user;
    String email ;
    CardView setting_btn, history_btn,deposit_btn,package_btn;
    DatabaseReference databaseReference ;
    TextView txt_ten,txt_money,txt_id,txt_loaixe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        assert user != null;
        email = user.getEmail();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query query = databaseReference.orderByChild("email").equalTo(email);
        txt_ten = (TextView) view.findViewById(R.id.txt_ten);
        txt_money = (TextView)view.findViewById(R.id.txt_money);
        txt_id = (TextView)view.findViewById(R.id.txt_id);
        txt_loaixe = (TextView)view.findViewById(R.id.txt_loaixe);

//        ImageView imageView = view.findViewById(R.id.image_view);
//        Glide.with(this)
//                .load(imageUrl)
//                .placeholder(R.drawable.placeholder_image)
//                .error(R.drawable.error_image)
//                .into(imageView);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                    Account account = childSnapshot.getValue(Account.class);
//                Toast.makeText(getActivity(), account.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("account",account.getEmail());
                    txt_ten.setText(account.getTen());
                    txt_id.setText(account.getRfid());
                    txt_money.setText(currencyconvert(account.getSodu()));
                    txt_loaixe.setText(account.getLoaixe());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        deposit_btn =  view.findViewById(R.id.deposit_card);
        history_btn =  view.findViewById(R.id.history_card);
        setting_btn =  view.findViewById(R.id.setting_card);
        package_btn =  view.findViewById(R.id.package_card);


       deposit_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent = new Intent(getActivity(),DepositActivity.class);
               startActivity(intent);
           }
       });
       history_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getActivity(),HistoryActivity.class);
               startActivity(intent);
           }
       });
        package_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PackageActivity.class);
                startActivity(intent);
            }
        });
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
