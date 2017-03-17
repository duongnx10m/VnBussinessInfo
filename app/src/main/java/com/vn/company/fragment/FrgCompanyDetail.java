package com.vn.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vn.company.Define;
import com.vn.company.R;
import com.vn.company.api.Urls;
import com.vn.company.model.CompanyDetail;
import com.vn.company.utils.Utils;

/**
 * Created by duongnx on 1/10/17.
 */

public class FrgCompanyDetail extends FrgBase implements View.OnClickListener {

    private TextView tvName, tvNameEn, tvBoss, tvCode, tvCity, tvAddress, tvBusiness, tvDate;
    private String masoThue = "";
    private CompanyDetail companyDetail;

    public void setMasoThue(String masoThue) {
        this.masoThue = masoThue;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.frg_company_detail, container, false);
        tvName = (TextView) mRootView.findViewById(R.id.tvName);
        tvNameEn = (TextView) mRootView.findViewById(R.id.tvNameEn);
        tvBoss = (TextView) mRootView.findViewById(R.id.tvBoss);
        tvCode = (TextView) mRootView.findViewById(R.id.tvCode);
        tvCity = (TextView) mRootView.findViewById(R.id.tvCity);
        tvAddress = (TextView) mRootView.findViewById(R.id.tvAddress);
        tvBusiness = (TextView) mRootView.findViewById(R.id.tvBusiness);
        tvDate = (TextView) mRootView.findViewById(R.id.tvDate);
        mRootView.findViewById(R.id.btMst).setOnClickListener(this);
        mRootView.findViewById(R.id.btAddress).setOnClickListener(this);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle(mActivity.getString(R.string.chitiet));
        callApi(String.format(Urls.GET_COMPANY_DETAIL, masoThue));
    }

    @Override
    protected void onResponseData(String response) {
        companyDetail = gson.fromJson(response, CompanyDetail.class);
        if (companyDetail != null) {
            tvName.setText(companyDetail.getTitle());
            tvNameEn.setText(companyDetail.getTitleEn());
            tvBoss.setText(companyDetail.getChuSoHuu());
            tvCity.setText(companyDetail.getDiaChiCongTy());
            tvCode.setText(companyDetail.getMaSoThue());
            if (TextUtils.isEmpty(companyDetail.getNoiDangKyQuanLy_CoQuanTitle()))
                mRootView.findViewById(R.id.containerAddress).setVisibility(View.GONE);
            else
                tvAddress.setText(companyDetail.getNoiDangKyQuanLy_CoQuanTitle());
            tvBusiness.setText(companyDetail.getNganhNgheTitle());
            tvDate.setText(Utils.parseDateTime(companyDetail.getNgayCap()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btMst:
                if (companyDetail != null) {
                    Utils.setClipboard(mActivity, companyDetail.getMaSoThue());
                    String text = String.format(mActivity.getString(R.string.copy_clipboard), companyDetail.getMaSoThue());
                    Toast.makeText(mActivity, text, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btAddress:
                if (companyDetail != null)
                    Utils.startActivityWithUrl(mActivity, Define.MAP_LINK + companyDetail.getDiaChiCongTy());
                break;
        }
    }
}
