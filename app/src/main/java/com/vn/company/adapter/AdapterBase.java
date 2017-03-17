package com.vn.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by duongnx on 1/9/17.
 */

public abstract class AdapterBase<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements View.OnClickListener {

    public interface OnRecyclerListener {
        void onRecyclerItemClicked(View view);

        void onReachLastItem(int position);
    }

    protected ArrayList<T> datas = new ArrayList<>();
    protected Context mContext;
    protected OnRecyclerListener onRecyclerListener;

    public AdapterBase(Context context) {
        this.mContext = context;
    }

    public void setOnRecyclerListener(OnRecyclerListener onRecyclerListener) {
        this.onRecyclerListener = onRecyclerListener;
    }

    public void addAll(ArrayList<T> datas) {
        this.datas.addAll(datas);
    }


    public void clear() {
        if (datas != null)
            datas.clear();
    }

    public T getItemData(int position) {
        if (position >= 0 && position < datas.size()) {
            return datas.get(position);
        } else
            return null;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerListener != null)
            onRecyclerListener.onRecyclerItemClicked(view);
    }
}
