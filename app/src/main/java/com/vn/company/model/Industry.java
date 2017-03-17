package com.vn.company.model;

/**
 * Created by duongnx on 1/11/17.
 */

public class Industry {
    private int Type, ID, TotalDoanhNghiep;
    private String SolrID, Title, Lv1, Lv2, Lv3, Lv4, Lv5;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTotalDoanhNghiep() {
        return TotalDoanhNghiep;
    }

    public void setTotalDoanhNghiep(int totalDoanhNghiep) {
        TotalDoanhNghiep = totalDoanhNghiep;
    }

    public String getSolrID() {
        return SolrID;
    }

    public void setSolrID(String solrID) {
        SolrID = solrID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLv1() {
        return Lv1;
    }

    public void setLv1(String lv1) {
        Lv1 = lv1;
    }

    public String getLv2() {
        return Lv2;
    }

    public void setLv2(String lv2) {
        Lv2 = lv2;
    }

    public String getLv3() {
        return Lv3;
    }

    public void setLv3(String lv3) {
        Lv3 = lv3;
    }

    public String getLv4() {
        return Lv4;
    }

    public void setLv4(String lv4) {
        Lv4 = lv4;
    }

    public String getLv5() {
        return Lv5;
    }

    public void setLv5(String lv5) {
        Lv5 = lv5;
    }
}
