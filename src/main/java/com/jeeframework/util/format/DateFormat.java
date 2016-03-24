
package com.jeeframework.util.format;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    
    public static final int DT_YYYY_MM_DD           = 1 ;
    public static final int DT_YYYY_MM_DD_HHMMSS    = 2 ;
    public static final int DT_HHMMSS           = 3 ;
    public static final int DT_HHMM             = 4 ;
    public static final int DT_YYYY             = 5 ;
    public static final int DT_MMDDYYYYHHMMSS   = 6 ;
    public static final int DT_YYYY_MM_DD_EEEE    = 7 ; //ex:2007-06-15
    public static final int DT_YYMMDD    = 8 ; //ex:070602
    public static final int DT_YY_MM_DD    = 9 ;
    public static final int DT_YYYYMMDD    = 10 ; //ex:20070602
    
    public static String formatDate(Date date, int nFmt)
    {
        if(date == null)
        {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat() ;
        switch (nFmt)
        {
            default :
            case DateFormat.DT_YYYY_MM_DD :
                dateFormat.applyPattern("yyyy-MM-dd");
                break ;
            case DateFormat.DT_YYYY_MM_DD_HHMMSS :
                dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
                break ;
            case DateFormat.DT_HHMMSS :
                dateFormat.applyPattern("HH:mm:ss");
                break ;
            case DateFormat.DT_HHMM :
                dateFormat.applyPattern("HH:mm");
                break ;
            case DateFormat.DT_YYYY :
                dateFormat.applyPattern("yyyy");
                break ;
            case DateFormat.DT_MMDDYYYYHHMMSS:
                dateFormat.applyPattern("MMddyyyy:HH:mm:ss");
                break ;
            case DateFormat.DT_YYYY_MM_DD_EEEE:
                dateFormat.applyPattern("yyyy-MM-dd EEEE");
                break;
            case DateFormat.DT_YYMMDD:
                dateFormat.applyPattern("yyMMdd");
                break;
            case DateFormat.DT_YY_MM_DD:
                dateFormat.applyPattern("yy-MM-dd");
                break;
            case DateFormat.DT_YYYYMMDD:
                dateFormat.applyPattern("yyyyMMdd");
                break;
        }
        return dateFormat.format(date);
    }
    
    public static String formatDate(Date date)
    {
        return formatDate(date, DT_YYYY_MM_DD);
    }
    
    public static String formatDate(Date date, String strFmt)
    {
        if(date == null)
        {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(strFmt);
        return dateFormat.format(date);
    }
    /**
     * 格式化日期
     * @param strDate
     * @param nFmt
     * @return
     * @throws Exception
     */
    public static Date parseDate(String strDate, int nFmt) throws Exception
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        switch (nFmt)
        {
            default :
            case DateFormat.DT_YYYY_MM_DD :
                dateFormat.applyPattern("yyyy-MM-dd");
                break;
            case DateFormat.DT_YYYY_MM_DD_HHMMSS :
                dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
                break;
            case DateFormat.DT_HHMMSS :
                dateFormat.applyPattern("HH:mm:ss");
                break;
            case DateFormat.DT_HHMM :
                dateFormat.applyPattern("HH:mm");
                break;
            case DateFormat.DT_YYYYMMDD :
            	dateFormat.applyPattern("yyyyMMdd");
            	break;
        }
        return dateFormat.parse(strDate);
    }
    
	public static Date parseDate(String strDate, String strFmt) throws Exception {
		if (strDate == null || strFmt == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		dateFormat.applyPattern(strFmt);
		return dateFormat.parse(strDate);
	}
	
    public static Date parseDate(String strDate) throws Exception
    {
        return parseDate(strDate,DateFormat.DT_YYYY_MM_DD);     
    }
}


