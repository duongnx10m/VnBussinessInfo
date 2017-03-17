package com.vn.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.vn.company.adapter.AdapterCompany;
import com.vn.company.api.Urls;
import com.vn.company.model.Company;
import com.vn.company.model.Result;

/**
 * Created by duongnx on 1/9/17.
 */

public class FrgCompany extends FrgRecyclerView {
    private String apiUrl = Urls.GET_COMPANY;

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mAdapter == null) {
            mAdapter = new AdapterCompany(mActivity);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mAdapter.getItemCount() == 0)
            callApi(getCurrentPage());
    }

    @Override
    public void callApi(int page) {
        callApi(String.format(apiUrl, page + ""));
    }

    @Override
    protected void onResponseData(String response) {
        super.onResponseData(response);
        Result result = gson.fromJson(response, Result.class);
        if (result != null && result.getLtsItems() != null) {
            if (result.getOption() != null) {
                setCurrentPage(result.getOption().getCurrentPage());
                setGroupResultCount(result.getOption().getTotalRow());
            }
            if (getCurrentPage() == 1)
                mAdapter.clear();
            mAdapter.addAll(result.getLtsItems());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRecyclerItemClicked(View view) {
        int pos = (Integer) view.getTag();
        Company company = (Company) mAdapter.getItemData(pos);
        if (company != null) {
            FrgCompanyDetail frgCompanyDetail = new FrgCompanyDetail();
            frgCompanyDetail.setMasoThue(company.getMaSoThue());
            mActivity.pushFragment(frgCompanyDetail, true);
        }
    }

    @Override
    public void onReachLastItem(int position) {
        if ((position + 1) % getPageSize() == 0) {
            callApi(getCurrentPage() + 1);
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        callApi(1);
    }
}
