package com.vn.company.model;

import java.util.ArrayList;

/**
 * Created by duongnx on 1/10/17.
 */

public class CityResult {
    private int TotalDoanhNghiep;
    private ArrayList<City> LtsItem;

    public int getTotalDoanhNghiep() {
        return TotalDoanhNghiep;
    }

    public void setTotalDoanhNghiep(int totalDoanhNghiep) {
        TotalDoanhNghiep = totalDoanhNghiep;
    }

    public ArrayList<City> getLtsItem() {
        return LtsItem;
    }

    public void setLtsItem(ArrayList<City> ltsItem) {
        LtsItem = ltsItem;
    }
}
