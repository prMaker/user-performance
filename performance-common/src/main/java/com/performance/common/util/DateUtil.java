package com.performance.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtil {

    public static String formatNow(){
        SimpleDateFormat smf = new SimpleDateFormat("yyyyMM");
        return smf.format(new Date());
    }


}
