package com.example.loginmll;

import java.io.Serializable;

public class ThongBao implements Serializable {
    private String Mathongbao, Name, Noidung, Ngaytao, Tennguoitao;

    public String getMathongbao() {
        return Mathongbao;
    }

    public void setMathongbao(String mathongbao) {
        Mathongbao = mathongbao;
    }
//
//    public String getId() {
//        return Id;
//    }
//
//    public void setId(String id) {
//        Id = id;
//    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String noidung) {
        Noidung = noidung;
    }

    public String getNgaytao() {
        return Ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        Ngaytao = ngaytao;
    }

    public String getTennguoitao() {
        return Tennguoitao;
    }

    public void setTennguoitao(String tennguoitao) {
        Tennguoitao = tennguoitao;
    }

    public ThongBao(String mathongbao, String name, String noidung, String ngaytao, String tennguoitao) {

        this.Name = name;
        this.Mathongbao = mathongbao;
        this.Noidung = noidung;
        this.Ngaytao = ngaytao;
        this.Tennguoitao = tennguoitao;
    }

}

