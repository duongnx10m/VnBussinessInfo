package com.vn.company;

import android.app.Application;

/**
 * Created by duongnx on 1/13/17.
 */

public class MyApplication extends Application {

    static MyApplication instance;
    private boolean isShowFull = false;
    private int adsCount = 0;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public boolean isShowFull() {
        return isShowFull;
    }

    public void setShowFull(boolean showFull) {
        isShowFull = showFull;
    }

    public int getAdsCount() {
        return adsCount;
    }

    public void setAdsCount(int adsCount) {
        this.adsCount = adsCount;
    }
}
