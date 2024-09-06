package com.android.pc.util;

import com.kqg.main.constant.KV;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.umeng.analytics.a;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes.dex */
public class Handler_Time {
    private Calendar cal = Calendar.getInstance();

    private Handler_Time() {
    }

    public static Handler_Time getInstance() {
        return new Handler_Time();
    }

    public static Handler_Time getInstance(String str, String str2) {
        Handler_Time handler_Time = new Handler_Time();
        if (str != null && !str.equals("") && !str.equals(Constants.NULL_VERSION_ID) && str2 != null && !str2.equals("") && !str2.equals(Constants.NULL_VERSION_ID)) {
            handler_Time.cal.set(1, Integer.parseInt(str));
            handler_Time.cal.set(2, Integer.parseInt(str2) - 1);
        }
        return handler_Time;
    }

    public static Handler_Time getInstance(String str, String str2, String str3) {
        Handler_Time handler_Time = new Handler_Time();
        if (str != null && !str.equals("") && !str.equals(Constants.NULL_VERSION_ID) && str2 != null && !str2.equals("") && !str2.equals(Constants.NULL_VERSION_ID) && str3 != null && !str3.equals("") && !str3.equals(Constants.NULL_VERSION_ID)) {
            handler_Time.cal.set(1, Integer.parseInt(str));
            handler_Time.cal.set(2, Integer.parseInt(str2) - 1);
            handler_Time.cal.set(5, Integer.parseInt(str3));
        }
        return handler_Time;
    }

    public static Handler_Time getInstance(int i, int i2, int i3) {
        Handler_Time handler_Time = new Handler_Time();
        handler_Time.cal.set(1, i);
        handler_Time.cal.set(2, i2 - 1);
        handler_Time.cal.set(5, i3);
        return handler_Time;
    }

    public static Handler_Time getInstance(String str) {
        int length = str.length();
        if (length < 4) {
            return null;
        }
        Handler_Time handler_Time = new Handler_Time();
        handler_Time.set(Integer.parseInt(str.substring(0, 4)), length >= 7 ? Integer.parseInt(str.substring(5, 7)) - 1 : 0, length >= 10 ? Integer.parseInt(str.substring(8, 10)) : 1, length >= 13 ? Integer.parseInt(str.substring(11, 13)) : 0, length >= 16 ? Integer.parseInt(str.substring(14, 16)) : 0, length == 19 ? Integer.parseInt(str.substring(17, 19)) : 0);
        return handler_Time;
    }

    public static Handler_Time getInstance(long j) {
        Handler_Time handler_Time = new Handler_Time();
        handler_Time.set(j);
        return handler_Time;
    }

    public Timestamp getTimestamp() {
        return new Timestamp(this.cal.getTimeInMillis());
    }

    public String getTimestampStr() {
        String timestamp = new Timestamp(this.cal.getTimeInMillis()).toString();
        while (timestamp.length() < 23) {
            timestamp = timestamp + 0;
        }
        return timestamp;
    }

    public String getTimestampSecond() {
        return new Timestamp(this.cal.getTimeInMillis()).toString().substring(0, 19);
    }

    public Timestamp getTimestampPlus(long j) {
        return new Timestamp(this.cal.getTimeInMillis() + j);
    }

    public Timestamp getTimestamp(int i, int i2, int i3, int i4, int i5) {
        this.cal.set(i, i2, i3, i4, i5, 0);
        return new Timestamp(this.cal.getTimeInMillis());
    }

    public int getYear() {
        return this.cal.get(1);
    }

    public int getYearSimple() {
        int i = this.cal.get(1);
        int i2 = 2000;
        if (i <= 2000) {
            i2 = 1900;
            if (i <= 1900) {
                i2 = 1800;
                if (i <= 1800) {
                    return i - 1700;
                }
            }
        }
        return i - i2;
    }

    public String getYearSimpleStr() {
        return Handler_String.addPrefixZero(getYearSimple());
    }

    public int getYearPrev() {
        if (getMonth() == 1) {
            return getYear() - 1;
        }
        return getYear();
    }

    public int getYearNext() {
        if (getMonth() == 12) {
            return getYear() + 1;
        }
        return getYear();
    }

    public String getYearStr() {
        return String.valueOf(getYear());
    }

    public int getMonth() {
        return this.cal.get(2) + 1;
    }

    public int getMonthPrev() {
        if (getMonth() == 1) {
            return 12;
        }
        return getMonth() - 1;
    }

    public int getMonthNext() {
        if (getMonth() == 12) {
            return 1;
        }
        return getMonth() + 1;
    }

    public String getMonthStr() {
        return Handler_String.addPrefixZero(getMonth());
    }

    public int getDay() {
        return this.cal.get(5);
    }

    public String getDayStr() {
        return Handler_String.addPrefixZero(getDay());
    }

    public int getHour() {
        return this.cal.get(11);
    }

    public String getHourStr() {
        return Handler_String.addPrefixZero(getHour());
    }

    public int getMinute() {
        return this.cal.get(12);
    }

    public String getMinuteStr() {
        return Handler_String.addPrefixZero(getMinute());
    }

    public int getSecond() {
        return this.cal.get(13);
    }

    public String getSecondStr() {
        return Handler_String.addPrefixZero(getSecond());
    }

    public String getYYYYMMDD() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getYearStr());
        stringBuffer.append("-");
        stringBuffer.append(getMonthStr());
        stringBuffer.append("-");
        stringBuffer.append(getDayStr());
        return stringBuffer.toString();
    }

    public String getYYYYMMDDLabel() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getYearStr());
        stringBuffer.append("年");
        stringBuffer.append(getMonthStr());
        stringBuffer.append("月");
        stringBuffer.append(getDayStr());
        stringBuffer.append("日");
        return stringBuffer.toString();
    }

    public String getYYYYMMDDPrevious() {
        return getTimeHandlerPrevious().getYYYYMMDD();
    }

    public String getYYYYMMDDNext() {
        return getTimeHandlerNext().getYYYYMMDD();
    }

    public Handler_Time getTimeHandlerPrevious() {
        return getInstance(getTimeInMillis() - a.h);
    }

    public Handler_Time getTimeHandlerNext() {
        return getInstance(getTimeInMillis() + a.h);
    }

    public String getYYYYMM() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getYearStr());
        stringBuffer.append("-");
        stringBuffer.append(getMonthStr());
        return stringBuffer.toString();
    }

    public String getYYYYMMLabel() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getYearStr());
        stringBuffer.append("年");
        stringBuffer.append(getMonthStr());
        stringBuffer.append("月");
        return stringBuffer.toString();
    }

    public String getYYYYMMPrevious() {
        int year = getYear();
        int monthPrev = getMonthPrev();
        if (monthPrev == 12) {
            year--;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(year);
        stringBuffer.append("-");
        stringBuffer.append(Handler_String.addPrefixZero(monthPrev));
        return stringBuffer.toString();
    }

    public String getYYYYMMNext() {
        int year = getYear();
        int monthNext = getMonthNext();
        if (monthNext == 1) {
            year++;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(year);
        stringBuffer.append("-");
        stringBuffer.append(Handler_String.addPrefixZero(monthNext));
        return stringBuffer.toString();
    }

    public String getYyyyMmKanji() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getYearStr());
        stringBuffer.append(" 年 ");
        stringBuffer.append(getMonth());
        stringBuffer.append(" 月 ");
        return stringBuffer.toString();
    }

    public void set(int i, int i2, int i3, int i4, int i5, int i6) {
        this.cal.set(i, i2, i3, i4, i5, i6);
    }

    public void set(int i, int i2, int i3, int i4, int i5) {
        this.cal.set(i, i2, i3, i4, i5, 0);
    }

    public void set(int i, int i2) {
        this.cal.set(i, i2);
    }

    public void set(long j) {
        this.cal.setTimeInMillis(j);
    }

    public long getTimeInMillis() {
        return this.cal.getTimeInMillis();
    }

    public int getTimeInSeconds() {
        return (int) (getTimeInMillis() / 1000);
    }

    public boolean checkDate(int i, int i2, int i3) {
        this.cal.set(i, i2, 1);
        return i3 > this.cal.getActualMaximum(5);
    }

    public int getMaxDayOfTheMonth() {
        return this.cal.getActualMaximum(5);
    }

    public int getDayOfTheWeek() {
        return this.cal.get(7);
    }

    public Date getDate() {
        return new Date(getTimeInMillis());
    }

    public Calendar getCalendar() {
        return this.cal;
    }

    public static String now() {
        return getInstance().getTimestamp().toString().substring(0, 19);
    }

    public String getPeriodStr(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        long j2 = (j / 1000) / 3600;
        if (j2 > 0) {
            stringBuffer.append(Handler_String.addPrefixZero((int) j2));
            stringBuffer.append(":");
            j -= (j2 * 3600) * 1000;
        } else {
            stringBuffer.append("00:");
        }
        long j3 = (j / 1000) / 60;
        if (j3 > 0) {
            stringBuffer.append(Handler_String.addPrefixZero((int) j3));
            stringBuffer.append(":");
            j -= (j3 * 60) * 1000;
        } else {
            stringBuffer.append("00:");
        }
        stringBuffer.append(Handler_String.addPrefixZero((int) (j / 1000)));
        return stringBuffer.toString();
    }

    public static Handler_Time linuxTimeToWinTime(Handler_Time handler_Time) {
        int year = handler_Time.getYear();
        int month = handler_Time.getMonth();
        handler_Time.set(year, month - 1, handler_Time.getDay(), handler_Time.getHour() + 8, handler_Time.getMinute(), handler_Time.getSecond());
        return handler_Time;
    }

    public String toString() {
        return getTimestampStr();
    }

    public static String formatDate(int i, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i);
        stringBuffer.append("-");
        stringBuffer.append(Handler_String.addPrefixZero(i2 + 1));
        stringBuffer.append("-");
        stringBuffer.append(Handler_String.addPrefixZero(i3));
        return stringBuffer.toString();
    }

    public static String formatDuring(long j) {
        String str;
        long j2 = j / a.h;
        long j3 = (j % a.h) / a.i;
        long j4 = (j % a.i) / KV.GET_CODE_INTERVAL;
        long j5 = (j % KV.GET_CODE_INTERVAL) / 1000;
        if (j5 != 0) {
            str = j5 + "秒";
        } else {
            str = "";
        }
        if (j4 != 0) {
            str = j4 + "分" + str;
        }
        if (j3 != 0) {
            str = j3 + "小时" + str;
        }
        if (j2 == 0) {
            return str;
        }
        return j2 + "天" + str;
    }
}
