package com.vn.company.model;

import java.util.ArrayList;

/**
 * Created by duongnx on 1/11/17.
 */

public class ResultIndustry {
    private ArrayList<Industry> LtsItem;
    private int TotalItem;

    public ArrayList<Industry> getLtsItem() {
        return LtsItem;
    }

    public void setLtsItem(ArrayList<Industry> ltsItem) {
        LtsItem = ltsItem;
    }

    public int getTotalItem() {
        return TotalItem;
    }

    public void setTotalItem(int totalItem) {
        TotalItem = totalItem;
    }
}
