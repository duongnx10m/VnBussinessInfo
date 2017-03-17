package com.vn.company.model;

/**
 * Created by duongnx on 1/9/17.
 */

public class Company {
    private int Type;
    private String MaSoThue, NgayCap, Title, DiaChiCongTy, Updated, Created, TinhThanhTitle, NganhNgheTitle;


    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getMaSoThue() {
        return MaSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        MaSoThue = maSoThue;
    }

    public String getNgayCap() {
        return NgayCap;
    }

    public void setNgayCap(String ngayCap) {
        NgayCap = ngayCap;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDiaChiCongTy() {
        return DiaChiCongTy;
    }

    public void setDiaChiCongTy(String diaChiCongTy) {
        DiaChiCongTy = diaChiCongTy;
    }

    public String getUpdated() {
        return Updated;
    }

    public void setUpdated(String updated) {
        Updated = updated;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String created) {
        Created = created;
    }

    public String getTinhThanhTitle() {
        return TinhThanhTitle;
    }

    public void setTinhThanhTitle(String tinhThanhTitle) {
        TinhThanhTitle = tinhThanhTitle;
    }

    public String getNganhNgheTitle() {
        return NganhNgheTitle;
    }

    public void setNganhNgheTitle(String nganhNgheTitle) {
        NganhNgheTitle = nganhNgheTitle;
    }
}
