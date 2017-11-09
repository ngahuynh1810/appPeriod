package com.example.mattershmily.myapplication;

import java.io.Serializable;

/**
 * Created by Matter Shmily on 09/11/2017.
 */

public class item_caidat implements Serializable {

    private String ten;
    private String ma;
    private int hinh;

    public item_caidat(String ten, String ma, int hinh) {
        this.ten = ten;
        this.ma = ma;
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
