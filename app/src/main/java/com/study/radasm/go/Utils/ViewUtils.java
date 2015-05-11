package com.study.radasm.go.Utils;

import android.content.Context;
import android.view.View;

/**
 * Created by RadAsm on 15/5/11.
 */
public class ViewUtils {
    public static View inflate(Context context,int layout_id){
        return View.inflate(context,layout_id,null);
    }
}
