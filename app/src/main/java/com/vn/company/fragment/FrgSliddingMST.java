package com.vn.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vn.company.R;
import com.vn.company.adapter.AdapterPagerMST;
import com.vn.company.slidding.SlidingTabLayout;

/**
 * Created by duongnx on 1/12/17.
 */

public class FrgSliddingMST extends FrgBase {

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private AdapterPagerMST mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.frg_slidding_mst, container, false);
        slidingTabLayout = (SlidingTabLayout) mRootView.findViewById(R.id.slidingTabLayout);
        viewPager = (ViewPager) mRootView.findViewById(R.id.viewPager);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle(mActivity.getString(R.string.tramasothue));
        if (mAdapter == null) {
            mAdapter = new AdapterPagerMST(mActivity.getSupportFragmentManager(), mActivity);
        }
        viewPager.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(viewPager);
    }
}
