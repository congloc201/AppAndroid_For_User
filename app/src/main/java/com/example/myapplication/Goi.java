package com.example.myapplication;

public class Goi {
    String Email,Loaive;
    long Ngaydatdau,Ngayketthuc;
    String Trangthai;

    public Goi() {
    }

    public Goi(String email, String loaive, long ngaydatdau, long ngayketthuc, String trangthai) {
        Email = email;
        Loaive = loaive;
        Ngaydatdau = ngaydatdau;
        Ngayketthuc = ngayketthuc;
        Trangthai = trangthai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLoaive() {
        return Loaive;
    }

    public void setLoaive(String loaive) {
        Loaive = loaive;
    }

    public long getNgaydatdau() {
        return Ngaydatdau;
    }

    public void setNgaydatdau(long ngaydatdau) {
        Ngaydatdau = ngaydatdau;
    }

    public long getNgayketthuc() {
        return Ngayketthuc;
    }

    public void setNgayketthuc(long ngayketthuc) {
        this.Ngayketthuc = ngayketthuc;
    }

    public String getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(String trangthai) {
        Trangthai = trangthai;
    }
}
