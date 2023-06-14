package com.example.myapplication;

public class Naprut {
    String email,ngay,lido;
    int sotien;

    public String getLido() {
        return lido;
    }

    public void setLido(String lido) {
        this.lido = lido;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public Naprut(String email, int sotien, boolean tienvao, String ngay,String lido) {
        this.email = email;
        this.sotien = sotien;
        this.tienvao = tienvao;
        this.ngay = ngay;
        this.lido = lido;
    }
    public Naprut(){}


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSotien() {
        return sotien;
    }

    public void setSotien(int sotien) {
        this.sotien = sotien;
    }

    public boolean isTienvao() {
        return tienvao;
    }

    public void setTienvao(boolean tienvao) {
        this.tienvao = tienvao;
    }

    boolean tienvao;

}
