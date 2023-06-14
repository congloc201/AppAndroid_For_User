package com.example.myapplication;

import static com.example.myapplication.UseFullMethod.currencyconvert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleviewHistoryAdapter extends RecyclerView.Adapter<RecycleviewHistoryAdapter.ViewHolder>{
    Context context;
    ArrayList<Lichsu> arrayList;
    public RecycleviewHistoryAdapter(Context context, ArrayList<Lichsu> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public RecycleviewHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleviewHistoryAdapter.ViewHolder holder, int position) {
        Lichsu lichsu = arrayList.get(position);
        holder.txt_loaive.setText(lichsu.loaive);
        holder.txt_loaixe.setText(lichsu.loaixe);
        holder.txt_ngay.setText(lichsu.ngay);
        holder.txt_sotien.setText(currencyconvert(lichsu.sotien));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_loaixe,txt_loaive,txt_ngay,txt_sotien;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_loaive = itemView.findViewById(R.id.txt_loaive);
            txt_loaixe = itemView.findViewById(R.id.txt_loaixe);
            txt_ngay = itemView.findViewById(R.id.txt_ngay);
            txt_sotien = itemView.findViewById(R.id.txt_sotien);
        }
    }
}
