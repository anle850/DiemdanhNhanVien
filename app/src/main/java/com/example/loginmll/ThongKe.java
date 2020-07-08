package com.example.loginmll;

import java.io.Serializable;

public class ThongKe implements Serializable {
    private String MaNv, TenNv, SoNgay;

    public String getMaNv() {
        return MaNv;
    }

    public void setMaNv(String maNv) {
        MaNv = maNv;
    }

    public String getTenNv() {
        return TenNv;
    }

    public void setTenNv(String tenNv) {
        TenNv = tenNv;
    }

    public String getSoNgay() {
        return SoNgay;
    }

    public void setSoNgay(String soNgay) {
        SoNgay = soNgay;
    }

    public ThongKe(String maNv, String tenNv, String soNgay) {

        this.MaNv = maNv;
        this.TenNv = tenNv;
        this.SoNgay = soNgay;

    }

}

