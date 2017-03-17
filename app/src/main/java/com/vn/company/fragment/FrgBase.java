package com.vn.company.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.vn.company.BaseActivity;
import com.vn.company.R;
import com.vn.company.utils.Logger;
import com.vn.company.utils.Utils;

/**
 * Created by duongnx on 1/9/17.
 */

public class FrgBase extends Fragment {
    protected BaseActivity mActivity;
    protected View mRootView;
    protected Gson gson = new Gson();
    private String title = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (title != null)
            mActivity.setTitle(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void callApi(String url) {
        Logger.d(getClass().getSimpleName() + ":" + url);
        if (Utils.isConnected(mActivity)) {
            RequestQueue requestQueue = Volley.newRequestQueue(mActivity);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (!TextUtils.isEmpty(response)) {
                        onResponseData(response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    onResponeError(error);
                    Logger.d(getClass().getSimpleName() + ":onErrorResponse");
                }
            });
            requestQueue.add(stringRequest);
        } else {
            mActivity.setGroupResultVisibility(View.VISIBLE);
            mActivity.setGroupResultCount(mActivity.getString(R.string.no_internet));
            onResponeError(null);
        }
    }

    protected void onResponseData(String response) {
    }

    protected void onResponeError(VolleyError error) {
    }

    @Override
    public void onStop() {
        super.onStop();
        mActivity.setGroupResultVisibility(View.GONE);
    }
}
