package com.vn.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vn.company.R;
import com.vn.company.model.Industry;

/**
 * Created by duongnx on 1/9/17.
 */

public class AdapterIndustry extends AdapterBase<Industry, AdapterIndustry.VhIndustry> {

    public AdapterIndustry(Context context) {
        super(context);
    }

    @Override
    public VhIndustry onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_industry, parent, false);
        view.setOnClickListener(this);
        return new VhIndustry(view);
    }

    @Override
    public void onBindViewHolder(VhIndustry holder, int position) {
        holder.setContent(datas.get(position));
        holder.itemView.setTag(position);
    }


    class VhIndustry extends RecyclerView.ViewHolder {
        private TextView tvOrder, tvName, tvCount;

        public VhIndustry(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCount = (TextView) itemView.findViewById(R.id.tvName2);
            tvOrder = (TextView) itemView.findViewById(R.id.tvOrder);
        }

        public void setContent(Industry industry) {
            if (industry != null) {
                tvOrder.setText(industry.getID() + "");
                tvName.setText(industry.getTitle());
                tvCount.setText(industry.getTotalDoanhNghiep() + "");
            }
        }
    }
}
