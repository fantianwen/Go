package com.study.radasm.go.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.study.radasm.go.common.Constants;

/**
 * Created by RadAsm on 15/5/7.
 */
public class AppUtils {
    public static String getVersion(Context context){
        String versionName=null;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static void transfer2Activity(Bundle bundle, Context context, Class activityClass) {
        Intent intent =new Intent (context,activityClass);
        intent.putExtra(Constants.BUNDLE,bundle);
        context.startActivity(intent);
    }
}
