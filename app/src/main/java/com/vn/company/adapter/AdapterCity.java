package com.vn.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vn.company.R;
import com.vn.company.model.City;

/**
 * Created by duongnx on 1/9/17.
 */

public class AdapterCity extends AdapterBase<City, AdapterCity.VhCity> {

    public AdapterCity(Context context) {
        super(context);
    }

    @Override
    public VhCity onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_city, parent, false);
        view.setOnClickListener(this);
        return new VhCity(view);
    }

    @Override
    public void onBindViewHolder(VhCity holder, int position) {
        holder.setContent(datas.get(position));
        holder.itemView.setTag(position);
    }

    class VhCity extends RecyclerView.ViewHolder {
        private TextView tvName, tvCount;

        public VhCity(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCount = (TextView) itemView.findViewById(R.id.tvName2);
        }

        public void setContent(City company) {
            if (company != null) {
                tvName.setText(company.getTitle());
                tvCount.setText(company.getTotalDoanhNghiep() + "");
            }
        }
    }
}
