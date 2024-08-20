package com.example.baitest2.component;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@UtilityClass
public final class DateUtils {

    private final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final String VIETNAMESE_TIME_ZONE = "Asia/Ho_Chi_Minh";

    public Date getCurrentDateTime() {
        var calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.setTimeZone(TimeZone.getTimeZone(VIETNAMESE_TIME_ZONE));
        return calendar.getTime();
    }

    public Date convertToDate(String source) throws ParseException {
        var calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat(DATETIME_FORMAT).parse(source));
        calendar.setTimeZone(TimeZone.getTimeZone(VIETNAMESE_TIME_ZONE));
        return calendar.getTime();
    }

}
