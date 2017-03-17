package com.vn.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vn.company.R;

/**
 * Created by duongnx on 1/12/17.
 */

public class FrgInfo extends FrgBase {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.frg_info, container, false);
        setTitle(mActivity.getString(R.string.dieukhoang));
        return mRootView;
    }
}
