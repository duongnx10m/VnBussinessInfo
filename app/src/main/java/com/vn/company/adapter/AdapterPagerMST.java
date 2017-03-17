package com.vn.company.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vn.company.R;
import com.vn.company.api.Urls;
import com.vn.company.fragment.FrgMST;

/**
 * Created by duongnx on 1/12/17.
 */

public class AdapterPagerMST extends FragmentPagerAdapter {
    private String[] datas = {Urls.MST_CA_DN, Urls.MST_CA_NHAN};
    private Context mContext;

    public AdapterPagerMST(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return FrgMST.init(datas[position]);
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.mst_dn);
            case 1:
                return mContext.getString(R.string.mst_cn);
            default:
                return super.getPageTitle(position);
        }
    }
}
