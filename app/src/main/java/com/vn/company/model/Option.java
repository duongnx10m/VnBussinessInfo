package com.vn.company.model;

/**
 * Created by duongnx on 1/9/17.
 */

public class Option {
    private int RowPerPage, CurrentPage, TotalRow;

    public int getRowPerPage() {
        return RowPerPage;
    }

    public void setRowPerPage(int rowPerPage) {
        RowPerPage = rowPerPage;
    }

    public int getCurrentPage() {
        return CurrentPage;
    }

    public void setCurrentPage(int currentPage) {
        CurrentPage = currentPage;
    }

    public int getTotalRow() {
        return TotalRow;
    }

    public void setTotalRow(int totalRow) {
        TotalRow = totalRow;
    }
}
