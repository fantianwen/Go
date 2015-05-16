package com.study.radasm.go.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by RadAsm on 15/5/15.
 */
public class NetUtils {
    public static boolean hasNetwork=false;
    public static boolean hasNetwork(Context context){
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivity.getActiveNetworkInfo();
        if(activeNetworkInfo!=null){
            hasNetwork=activeNetworkInfo.isAvailable()?true:false;
        }
        return hasNetwork;
    }
}
