package com.vn.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.vn.company.R;
import com.vn.company.api.Urls;

/**
 * Created by duongnx on 1/11/17.
 */

public class FrgMST extends FrgBase implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefresh;
    private WebView webView;
    private String url = Urls.MST_CA_DN;

    public static FrgMST init(String url) {
        FrgMST frgMST = new FrgMST();
        frgMST.setUrl(url);
        return frgMST;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.frg_mst, container, false);
        webView = (WebView) mRootView.findViewById(R.id.webView);
        swipeRefresh = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(this);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl(url);
    }

    @Override
    public void onRefresh() {
        swipeRefresh.setRefreshing(false);
        webView.loadUrl(url);
    }
}
