package com.vn.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.vn.company.R;
import com.vn.company.adapter.AdapterCity;
import com.vn.company.api.Urls;
import com.vn.company.model.City;
import com.vn.company.model.CityResult;

/**
 * Created by duongnx on 1/9/17.
 */

public class FrgCity extends FrgRecyclerView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mAdapter == null) {
            mAdapter = new AdapterCity(mActivity);
        }
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.frg_city;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mAdapter.getItemCount() == 0)
            callApi(Urls.GET_CITY);
    }

    @Override
    protected void onResponseData(String response) {
        super.onResponseData(response);
        CityResult cityResult = gson.fromJson(response, CityResult.class);
        if (cityResult != null && cityResult.getLtsItem() != null) {
            setGroupResultCountOnePage(cityResult.getTotalDoanhNghiep());
            mAdapter.clear();
            mAdapter.addAll(cityResult.getLtsItem());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRecyclerItemClicked(View view) {
        int pos = (Integer) view.getTag();
        City city = (City) mAdapter.getItemData(pos);
        if (city != null) {
            FrgCityDetail frgCityDetail = new FrgCityDetail();
            frgCityDetail.setTitle(city.getTitle());
            frgCityDetail.initData(city.getSolrID(), Urls.GET_CITY_DETAIL);
            mActivity.pushFragment(frgCityDetail, true);
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
