package com.study.radasm.go.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by RadAsm on 15/5/7.
 */
public class DateTimeUtils {

    /**
     * transfer data by mills to dormattered string
     * @param date
     * @param format
     * @return
     */
    public static String data2String(Date date,String format){
        DateFormat formatter=new SimpleDateFormat(format);
        String d = formatter.format(date);
        return d;
    }


}
