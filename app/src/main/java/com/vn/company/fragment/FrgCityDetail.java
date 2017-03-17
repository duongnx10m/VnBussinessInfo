package com.vn.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.vn.company.R;
import com.vn.company.adapter.AdapterCompany;
import com.vn.company.api.Urls;
import com.vn.company.model.Company;
import com.vn.company.model.Facet;
import com.vn.company.model.Result;
import com.vn.company.utils.Logger;

import java.util.ArrayList;

/**
 * Created by duongnx on 1/11/17.
 */

public class FrgCityDetail extends FrgRecyclerView implements AdapterView.OnItemSelectedListener {

    private String sortId = null;
    private String url = Urls.GET_CITY_DETAIL;
    private Spinner spinnerCity;
    private ArrayAdapter<Facet> facetArrayAdapter = null;

    public void initData(String sortId, String url) {
        this.sortId = sortId;
        this.url = url;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.frg_city_detail;
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
        spinnerCity = (Spinner) mRootView.findViewById(R.id.spinnerCity);
        spinnerCity.setOnItemSelectedListener(this);
        if (facetArrayAdapter != null)
            spinnerCity.setAdapter(facetArrayAdapter);
        if (mAdapter.getItemCount() == 0)
            callApi(getCurrentPage());
    }

    @Override
    public void callApi(int page) {
        if (sortId != null) {
            callApi(String.format(url, page + "", sortId));
        }
    }

    @Override
    protected void onResponseData(String response) {
        super.onResponseData(response);
        Result result = gson.fromJson(response, Result.class);
        if (result != null && result.getLtsItems() != null) {
            mActivity.setTitle(result.getTitleFacet());
            if (result.getOption() != null) {
                setGroupResultCount(result.getOption().getTotalRow());
                setCurrentPage(result.getOption().getCurrentPage());
            }
            if (result.getLtsFacets() != null) {
                ArrayList<Facet> facets = result.getLtsFacets();
                Facet facet = new Facet();
                facet.setTitle(mActivity.getString(R.string.tatca));
                facets.add(0, facet);
                facetArrayAdapter = new ArrayAdapter<Facet>(mActivity, android.R.layout.simple_spinner_item, facets);
                facetArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCity.setAdapter(facetArrayAdapter);
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
        Logger.d("onReachLastItem:" + position);
        if ((position + 1) % getPageSize() == 0) {
            Logger.d("onReachLastItem:" + (getCurrentPage() + 1));
            callApi(getCurrentPage() + 1);
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        callApi(1);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        Facet facet = (Facet) adapterView.getItemAtPosition(pos);
        if (facet != null && facet.getUrlFacet() != null) {
            sortId = facet.getUrlFacet();
            callApi(1);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
