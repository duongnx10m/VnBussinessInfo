package com.vn.company.utils;

import android.util.Log;

/**
 * Created by duongnx on 1/11/17.
 */

public class Logger {
    static boolean isShowLog = true;
    static final String TAG = "duongnx";

    public static void d(String msg) {
        if (isShowLog)
            Log.d(TAG, msg);
    }

}
