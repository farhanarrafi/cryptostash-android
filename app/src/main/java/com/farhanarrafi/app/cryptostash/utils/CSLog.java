package com.farhanarrafi.app.cryptostash.utils;

import android.util.Log;

public class CSLog {

    private static final boolean showLog = true;
    private static final String TAG = "CryptoStash";

    public static void d(String message) {
        if(showLog) Log.d(TAG, ""+message);
    }

    public static void d(String tag, String message) {
        if(showLog) Log.d(TAG + "_" + tag, ""+message);
    }

    public static void e(String message) {
        if(showLog) Log.e(TAG, ""+message);
    }

    public static void e(String tag, String message) {
        if(showLog) Log.e(TAG + "_" + tag, ""+message);
    }

    public static void i(String message) {
        if(showLog) Log.i(TAG, ""+message);
    }

    public static void i(String tag, String message) {
        if(showLog) Log.i(TAG + "_" + tag, ""+message);
    }

    public static void w(String message) {
        if(showLog) Log.w(TAG, ""+message);
    }

    public static void w(String tag, String message) {
        if(showLog) Log.w(TAG + "_" + tag, ""+message);
    }


}
