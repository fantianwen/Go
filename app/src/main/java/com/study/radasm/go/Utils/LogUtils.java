package com.study.radasm.go.Utils;

import android.util.Log;

import com.android.volley.VolleyLog;

import java.util.Locale;

/**
 * this is a wrapper class for android's origin logUtils
 * <p/>
 * Created by RadAsm on 15/5/7.
 */
public class LogUtils {
    private static int log_level;

    private static boolean is_debug;
    private static boolean is_info;
    private static boolean is_error;

    private static final int LEVEL_DEBUG = 1;
    private static final int LEVEL_INFO = 3;
    private static final int LEVEL_ERROR = 5;

    /**
     * ues to init log_level and other attrs;
     */
    static {
//        log_level= AppClient.log_level;
        log_level = 10;
        if (log_level < LEVEL_DEBUG) {
            is_debug = false;
            is_info = false;
            is_error = false;
        } else if (log_level < LEVEL_INFO) {
            is_debug = true;
            is_info = false;
            is_error = false;
        } else if (log_level < LEVEL_ERROR) {
            is_debug = true;
            is_info = true;
            is_info = false;
        } else {
            is_debug = is_info = is_error = true;
        }


        Log.e("hahha", is_error + "" + log_level);
    }

    public static void d(String tag, String debug_info) {
        if (is_debug) {
            Log.d(tag, debug_info);
        }
    }

    public static void i(String tag, String info_info) {
        if (is_info) {
            Log.i(tag, info_info);
        }
    }

    public static void e(String tag, String error_info) {
        if (is_error) {
            Log.e(tag, error_info);
        }
    }

    public static void i(String tag, String format, Object... args) {
        if(is_info){
            Log.i(tag,buildMessage(format,args));
        }
    }

    public static void d(String tag, String format, Object... args) {
        if (is_debug) {
            Log.d(tag, buildMessage(format, args));
        }
    }

    public static void e(String tag, String format, Object... args) {
        if (is_error) {
            Log.e(tag, buildMessage(format, args));
        }
    }


    private static String buildMessage(String format, Object... args) {
        String msg = (args == null) ? format : String.format(Locale.US, format, args);
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();

        String caller = "<unknown>";
        // Walk up the stack looking for the first caller outside of VolleyLog.
        // It will be at least two frames up, so start there.
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(VolleyLog.class)) {
                String callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);

                caller = callingClass + "." + trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s",
                Thread.currentThread().getId(), caller, msg);
    }

}
