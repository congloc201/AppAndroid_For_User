package com.example.myapplication;

public class Loaigoi {
    String Loaive,Loaixe;
    int Gia;

    public Loaigoi(String loaive, String loaixe, int gia) {
        Loaive = loaive;
        Loaixe = loaixe;
        Gia = gia;
    }

    public String getLoaive() {
        return Loaive;
    }

    public Loaigoi() {
    }

    public void setLoaive(String loaive) {
        Loaive = loaive;
    }


    public String getLoaixe() {
        return Loaixe;
    }

    public void setLoaixe(String loaixe) {
        Loaixe = loaixe;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }
}
