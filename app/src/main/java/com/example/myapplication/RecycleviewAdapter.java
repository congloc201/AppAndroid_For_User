package com.example.myapplication;



import static com.example.myapplication.UseFullMethod.convertday;
import static com.example.myapplication.UseFullMethod.currencyconvert;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.SimpleTimeZone;
import com.example.myapplication.UseFullMethod;
public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.Viewhoder> {
    Context context;
    ArrayList<Naprut> arrayList;

    public RecycleviewAdapter(Context context, ArrayList<Naprut> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecycleviewAdapter.Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.money_in_out_item,parent,false);
        return new Viewhoder(view);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Naprut naprut = arrayList.get(position);
        String tien;
        holder.txt_lido.setText(naprut.getLido());
        if(naprut.isTienvao())
        {
            tien = currencyconvert(naprut.getSotien());
                holder.txt_tien.setTextColor(Color.parseColor("#34B327"));

        }
        else{
            tien = "-"+currencyconvert(naprut.getSotien());

            holder.txt_tien.setTextColor(Color.parseColor("#FA1A09"));

        }

        holder.txt_tien.setText(tien);
        String day = convertday(naprut.getNgay()) ;
        holder.txt_ngay.setText(day);
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class Viewhoder extends RecyclerView.ViewHolder {
        TextView txt_ngay,txt_tien,txt_lido;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            txt_ngay= itemView.findViewById(R.id.txt_ngay);
            txt_tien= itemView.findViewById(R.id.txt_tien);
            txt_lido= itemView.findViewById(R.id.txt_lido);

        }

    }
    
}
