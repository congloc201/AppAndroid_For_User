package com.example.myapplication;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class UseFullMethod {

    public static String currencyconvert(int num){
        NumberFormat formatter = new DecimalFormat("#,###");

        return formatter.format(num);
//formattedNumber is equal to 1,000,000
    }

    public static String convertday(String a){
        Long Emilis = Long.parseLong(a);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        SimpleDateFormat simpletimeFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String date = simpleDateFormat.format(Emilis);
        String time = simpletimeFormat.format(Emilis);
        return date+" "+time;

    }
}
