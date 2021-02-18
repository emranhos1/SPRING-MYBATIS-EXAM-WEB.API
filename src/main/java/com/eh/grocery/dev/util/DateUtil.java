package com.eh.grocery.dev.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
public class DateUtil {
    private static Logger LOG = LoggerFactory.getLogger(DateUtil.class);

    public static final int   MILLISECONDS_PER_SECOND = 1000;
    public static final int   SECONDS_PER_MINUTE      = 60;
    public static final int   MINUTES_PER_HOUR        = 60;
    public static final int   HOURS_PER_DAY           = 24;
    public static final float DAYS_PER_MONTH          = 30.4375f;
    public static final float DAYS_PER_YEAR           = 365.24f;
    public static final int   MONTHS_PER_YEAR         = 12;

    public static final String YYYY_MM_DD             = "yyyy-MM-dd";
    public static final String YYYY_M_D               = "yyyy-M-d";
    public static final String YY_M_D                 = "yy-M-d";
    public static final String YYYYMMDD               = "yyyyMMdd";

    public static final String DD_MM_YYYY             = "dd/MM/yyyy";
    public static final String D_M_YYYY               = "d/M/yyyy";
    public static final String D_M_YY                 = "d/M/yy";
    public static final String DDMMYYYY               = "ddMMyyyy";
    public static final String TIME_ZONE              = "GMT";
    public static final String DATE_EXP               = "EEE, d MMM yyyy HH:mm:ss z";

    public static final String ISO_DATE               = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String HL7_DATE_FORMAT        = "yyyyMMddHHmmss.SSSZ";
    public static final String HL7v2_DATE_FORMAT      = "yyyyMMddHHmmss";


    public static String format(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return date != null ? formatter.format(date) : null;
    }

    public static boolean isEqual(Date refDate, Date date) {
        long refTime = refDate != null ? refDate.getTime() : 0;
        long time    = date != null ? date.getTime(): 0;
        return refTime == time;
    }

    public static Date parse(String date, String pattern) {
        Date retDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            retDate = formatter.parse(date);
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return retDate;
    }

    public static Date parse(String date) {
        Date javaDate    = null;
        boolean notParse = true;
        int index        = 0;
        
        String[] formats = new String[] { "yyyyMMddHHmmss.SSSZ",
                "yyyyMMddHHmmss.SSS", "yyyyMMddHHmmss.SS", 
                "yyyyMMddHHmmss.S", "yyyyMMddHHmmss", 
                "yyyyMMddHHmm", "yyyyMMddHH", 
                "yyyyMMdd", "yyyyMM", "yyyy" };
        
        while(notParse && index < formats.length){
            try {
                String format = formats[index];
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                javaDate = formatter.parse(date);
                notParse = false;
            } catch (ParseException e) {
                index++;
            }
        }
        
        return javaDate;
    }

    public static Date minDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date maxDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static String gmtString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_EXP);
        format.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        return format.format(date);
    }
}
