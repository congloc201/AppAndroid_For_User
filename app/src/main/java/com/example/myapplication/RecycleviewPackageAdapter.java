package com.example.myapplication;

import static com.example.myapplication.UseFullMethod.convertday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleviewPackageAdapter extends RecyclerView.Adapter<RecycleviewPackageAdapter.ViewHolder> {
    ArrayList<Goi> arrayList  ;
    Context context;
    public RecycleviewPackageAdapter(Context context,ArrayList<Goi> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecycleviewPackageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.using_package_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleviewPackageAdapter.ViewHolder holder, int position) {
        Goi goi = arrayList.get(position);
        String batdau,ketthuc,trangthai,loaive;
        batdau = convertday(String.valueOf(goi.getNgaydatdau()));
        ketthuc = convertday(String.valueOf(goi.getNgayketthuc()));
        if(goi.getTrangthai().equals("true")){
            trangthai = "Đang hoạt động";
        }
        else trangthai = "Hết hạn";
        if(goi.getLoaive().equals("Thang")){
            loaive = "Vé Tháng";
        }else loaive ="Ve Quy";
        holder.txt_trangthai.setText(trangthai);
        holder.txt_ngaybatdau.setText(batdau);
        holder.txt_ngayketthuc.setText(ketthuc);
        holder.txt_loaive.setText(loaive);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_loaive,txt_ngaybatdau,txt_ngayketthuc,txt_trangthai;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txt_loaive = itemView.findViewById(R.id.txt_loaive);
            txt_ngaybatdau = itemView.findViewById(R.id.txt_ngaybatdau);
            txt_ngayketthuc = itemView.findViewById(R.id. txt_ngayketthuc);
            txt_trangthai = itemView.findViewById(R.id.txt_trangthai);
        }
    }
}
