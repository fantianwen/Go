package com.study.radasm.go.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by RadAsm on 15/5/7.
 */
public class DateTimeUtils {
    /**使用东八区的时区，北京时间*/
    private static final String timeZone = "GMT+8";

    /**
     * transfer data by mills to dormattered string
     *
     * @param date
     * @param format
     * @return
     */
    public static String data2String(Date date, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
        String d = formatter.format(date);
        return d;
    }


}
