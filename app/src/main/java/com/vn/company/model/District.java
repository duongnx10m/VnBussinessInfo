package com.vn.company.model;

/**
 * Created by duongnx on 1/10/17.
 */

public class District {
    private int Type, ID, TinhThanhID;
    private String Title, TinhThanhTitle, SolrID;

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

    public int getTinhThanhID() {
        return TinhThanhID;
    }

    public void setTinhThanhID(int tinhThanhID) {
        TinhThanhID = tinhThanhID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTinhThanhTitle() {
        return TinhThanhTitle;
    }

    public void setTinhThanhTitle(String tinhThanhTitle) {
        TinhThanhTitle = tinhThanhTitle;
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
