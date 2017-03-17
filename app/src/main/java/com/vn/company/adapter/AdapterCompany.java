package com.vn.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vn.company.R;
import com.vn.company.model.Company;
import com.vn.company.utils.Utils;

/**
 * Created by duongnx on 1/9/17.
 */

public class AdapterCompany extends AdapterBase<Company, AdapterCompany.VhCompany> {


    public AdapterCompany(Context context) {
        super(context);
    }


    @Override
    public VhCompany onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_company, parent, false);
        view.setOnClickListener(this);
        return new VhCompany(view);
    }

    @Override
    public void onBindViewHolder(VhCompany holder, int position) {
        holder.setContent(datas.get(position));
        holder.itemView.setTag(position);
        if (position > 0 && position == datas.size() - 1 && onRecyclerListener != null) {
            onRecyclerListener.onReachLastItem(position);
        }
    }


    class VhCompany extends RecyclerView.ViewHolder {
        private TextView tvName, tvCode, tvCity, tvDate;

        public VhCompany(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCode = (TextView) itemView.findViewById(R.id.tvCode);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
        }

        public void setContent(Company company) {
            tvName.setText(company.getTitle());
            tvCode.setText(company.getMaSoThue());
            tvCity.setText(company.getDiaChiCongTy());
            tvDate.setText(Utils.parseDateTime(company.getNgayCap()));
        }
    }
}
