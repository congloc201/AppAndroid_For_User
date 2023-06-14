package com.example.myapplication;

public class Account {
    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public Account(String ten, String ngaysinh, String email, String quequan, String bienso, String maubien, int sodu, String loaixe,String rfid) {
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.email = email;
        this.quequan = quequan;
        this.bienso = bienso;
        this.maubien = maubien;
        this.sodu = sodu;
        this.loaixe = loaixe;
        this.rfid = rfid;
    }
    String loaixe;
    String rfid;

    public String getLoaixe() {
        return loaixe;
    }

    public void setLoaixe(String loaixe) {
        this.loaixe = loaixe;
    }

    public Account(){

    }
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getBienso() {
        return bienso;
    }

    public void setBienso(String bienso) {
        this.bienso = bienso;
    }

    public String getMaubien() {
        return maubien;
    }

    public void setMaubien(String maubien) {
        this.maubien = maubien;
    }

    public int getSodu() {
        return sodu;
    }

    public void setSodu(int sodu) {
        this.sodu = sodu;
    }

    String ten, ngaysinh, email, quequan, bienso, maubien;
    int sodu;
}
