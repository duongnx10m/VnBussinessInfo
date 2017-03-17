package com.vn.company.model;

/**
 * Created by duongnx on 1/11/17.
 */

public class Facet {
    private String UrlFacet, Title;
    private int Total;

    public String getUrlFacet() {
        return UrlFacet;
    }

    public void setUrlFacet(String urlFacet) {
        UrlFacet = urlFacet;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
