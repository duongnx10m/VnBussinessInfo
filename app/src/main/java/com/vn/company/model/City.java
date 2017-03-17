package com.vn.company.model;

/**
 * Created by duongnx on 1/10/17.
 */

public class City {
    private int Type, ID, TotalDoanhNghiep;
    private String Title, SolrID;

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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSolrID() {
        return SolrID;
    }

    public void setSolrID(String solrID) {
        SolrID = solrID;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
