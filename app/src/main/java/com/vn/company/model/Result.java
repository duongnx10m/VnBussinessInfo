package com.vn.company.model;

import java.util.ArrayList;

/**
 * Created by duongnx on 1/9/17.
 */

public class Result {
    private ArrayList<Company> LtsItems;
    private Option Option;
    private ArrayList<Facet> LtsFacets;
    private String TitleFacet;

    public ArrayList<Company> getLtsItems() {
        return LtsItems;
    }

    public void setLtsItems(ArrayList<Company> ltsItems) {
        LtsItems = ltsItems;
    }

    public com.vn.company.model.Option getOption() {
        return Option;
    }

    public void setOption(com.vn.company.model.Option option) {
        Option = option;
    }

    public ArrayList<Facet> getLtsFacets() {
        return LtsFacets;
    }

    public void setLtsFacets(ArrayList<Facet> ltsFacets) {
        LtsFacets = ltsFacets;
    }

    public String getTitleFacet() {
        return TitleFacet;
    }

    public void setTitleFacet(String titleFacet) {
        TitleFacet = titleFacet;
    }
}
