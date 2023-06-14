package com.example.myapplication;

public class Lichsu {
    String email,loaive,loaixe,ngay;

    public Lichsu() {
    }

    public Lichsu(String email, String loaive, String loaixe, String ngay, int sotien) {
        this.email = email;
        this.loaive = loaive;
        this.loaixe = loaixe;
        this.ngay = ngay;
        this.sotien = sotien;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoaive() {
        return loaive;
    }

    public void setLoaive(String loaive) {
        this.loaive = loaive;
    }

    public String getLoaixe() {
        return loaixe;
    }

    public void setLoaixe(String loaixe) {
        this.loaixe = loaixe;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getSotien() {
        return sotien;
    }

    public void setSotien(int sotien) {
        this.sotien = sotien;
    }

    int sotien;
}
