package com.aming.translate.tools;

import android.util.Log;

/**
 * Created by wenming on 2016/6/27.
 */
public class MyLog {
    private static final String TAG = "translate";

    public static void v(Object o,String message){
        Log.v(TAG + o.getClass().getSimpleName(), message);
    }

    public static void v(String message){
        Log.v(TAG,message);
    }

}
