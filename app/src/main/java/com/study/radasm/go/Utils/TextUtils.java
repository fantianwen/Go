package com.study.radasm.go.Utils;

/**
 * Created by RadAsm on 15/5/16.
 */
public class TextUtils {
    public static boolean isNotEmpty(String... args) {
        boolean flag = true;
        int count=args.length;
        if(count>0){
            for(int i=0;i<count;i++){
                boolean b = !android.text.TextUtils.isEmpty(args[i]);
                flag = flag & b;
            }
        }
        return flag;
    }
}
