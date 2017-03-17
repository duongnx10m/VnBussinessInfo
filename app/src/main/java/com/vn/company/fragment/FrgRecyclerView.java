package com.vn.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.vn.company.Define;
import com.vn.company.R;
import com.vn.company.adapter.AdapterBase;

/**
 * Created by duongnx on 1/9/17.
 */

public abstract class FrgRecyclerView extends FrgBase implements SwipeRefreshLayout.OnRefreshListener, AdapterBase.OnRecyclerListener {

    protected SwipeRefreshLayout swipeRefreshLayout;
    protected RecyclerView recyclerView;
    private int currentPage = 1;
    //private int totalPage = 1000;
    private int pageSize = 20;
    protected AdapterBase mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutResourceId() > 0)
            mRootView = inflater.inflate(getLayoutResourceId(), container, false);
        else
            mRootView = inflater.inflate(R.layout.frg_recyclerview, container, false);
        recyclerView = (RecyclerView) mRootView.findViewById(R.id.recylerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        return mRootView;
    }

    public int getLayoutResourceId() {
        return 0;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mAdapter != null) {
            recyclerView.setAdapter(mAdapter);
            mAdapter.setOnRecyclerListener(this);
        }
    }

    @Override
    public void onRefresh() {
        //swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRecyclerItemClicked(View view) {

    }

    @Override
    public void onReachLastItem(int position) {
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void callApi(String url) {
        swipeRefreshLayout.setRefreshing(true);
        super.callApi(url);
    }

    public void callApi(int page) {
    }

    @Override
    protected void onResponseData(String response) {
        super.onResponseData(response);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onStop() {
        super.onStop();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onResponeError(VolleyError error) {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void setGroupResultCount(int count) {
        mActivity.setGroupResultVisibility(View.VISIBLE);
        int totalPage = (int) Math.round((count / 20.0) + 0.5);
        String text = String.format(Define.all, count + "", getCurrentPage() + "", totalPage + "");
        mActivity.setGroupResultCount(text);
    }

    public void setGroupResultCountOnePage(int count) {
        mActivity.setGroupResultVisibility(View.VISIBLE);
        String text = String.format(Define.all, count + "", getCurrentPage() + "", "1");
        mActivity.setGroupResultCount(text);
    }
}
