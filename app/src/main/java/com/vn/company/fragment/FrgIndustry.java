package com.vn.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.vn.company.R;
import com.vn.company.adapter.AdapterIndustry;
import com.vn.company.api.Urls;
import com.vn.company.model.Industry;
import com.vn.company.model.ResultIndustry;

/**
 * Created by duongnx on 1/9/17.
 */

public class FrgIndustry extends FrgRecyclerView {

    @Override
    public int getLayoutResourceId() {
        return R.layout.frg_industry;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(mActivity.getString(R.string.nganhnghe));
        if (mAdapter == null) {
            mAdapter = new AdapterIndustry(mActivity);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mAdapter.getItemCount() == 0)
            callApi(Urls.GET_INDUSTRY);
    }

    @Override
    protected void onResponseData(String response) {
        super.onResponseData(response);
        ResultIndustry resultIndustry = gson.fromJson(response, ResultIndustry.class);
        if (resultIndustry != null && resultIndustry.getLtsItem() != null) {
            setGroupResultCountOnePage(resultIndustry.getTotalItem());
            mAdapter.clear();
            mAdapter.addAll(resultIndustry.getLtsItem());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRecyclerItemClicked(View view) {
        int pos = (Integer) view.getTag();
        Industry industrycity = (Industry) mAdapter.getItemData(pos);
        if (industrycity != null) {
            FrgCityDetail frgCityDetail = new FrgCityDetail();
            frgCityDetail.setTitle(industrycity.getTitle());
            String sortId = industrycity.getSolrID().replaceAll("/nganh-nghe/", "");
            frgCityDetail.initData("", Urls.GET_CITY_DETAIL + "&i=" + sortId);
            mActivity.pushFragment(frgCityDetail, true);
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
