package com.netease.nimlib.o;

import android.text.TextUtils;
import com.netease.nimlib.amazonaws.util.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* compiled from: TimeUtil.java */
/* loaded from: classes.dex */
public class y {
    public static long a() {
        return System.currentTimeMillis();
    }

    public static String b() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    public static int c() {
        return (int) (new Date().getTime() / 1000);
    }

    public static long d() {
        return new Date().getTime();
    }

    public static String a(long j) {
        return a(j, "yyyyMMdd");
    }

    public static String a(long j, String str) {
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date(j));
    }

    public static String a(long j, boolean z) {
        String format;
        Date date = new Date(j);
        Date date2 = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Date time = calendar.getTime();
        Date date3 = new Date(time.getTime() - com.umeng.analytics.a.h);
        Date date4 = new Date(date3.getTime() - com.umeng.analytics.a.h);
        if (!date.before(time)) {
            format = "今天";
        } else if (!date.before(date3)) {
            format = "昨天";
        } else if (!date.before(date4)) {
            format = "前天";
        } else if (a(date, date2)) {
            format = b(date);
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date);
        }
        String format2 = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);
        if (z) {
            return !date.before(time) ? a(date) : format;
        }
        return format + " " + format2;
    }

    public static String a(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("KK:mm", Locale.getDefault());
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm", Locale.getDefault());
        int i = calendar.get(11);
        if (i >= 0 && i < 5) {
            return "凌晨 " + simpleDateFormat.format(date);
        }
        if (i >= 5 && i < 12) {
            return "上午 " + simpleDateFormat.format(date);
        }
        if (i >= 12 && i < 18) {
            return "下午 " + simpleDateFormat2.format(date);
        }
        if (i < 18 || i >= 24) {
            return "";
        }
        return "晚上 " + simpleDateFormat2.format(date);
    }

    public static String b(Date date) {
        String[] strArr = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return strArr[calendar.get(7) - 1];
    }

    public static boolean a(Date date, Date date2) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        calendar2.setTime(date2);
        int i = calendar.get(1) - calendar2.get(1);
        return i == 0 ? calendar.get(3) == calendar2.get(3) : (1 == i && 11 == calendar2.get(2)) ? calendar.get(3) == calendar2.get(3) : -1 == i && 11 == calendar.get(2) && calendar.get(3) == calendar2.get(3);
    }

    public static long b(long j) {
        return ((j / 100) + 5) / 10;
    }

    public static String a(int i) {
        if (i <= 0) {
            return "00:00";
        }
        int i2 = i / 60;
        if (i2 < 60) {
            return b(i2) + ":" + b(i % 60);
        }
        int i3 = i2 / 60;
        if (i3 > 99) {
            return "99:59:59";
        }
        int i4 = i2 % 60;
        return b(i3) + ":" + b(i4) + ":" + b((i - (i3 * 3600)) - (i4 * 60));
    }

    public static String b(int i) {
        if (i >= 0 && i < 10) {
            return "0" + Integer.toString(i);
        }
        return "" + i;
    }

    public static long a(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return j;
        }
        try {
            Date parse = new SimpleDateFormat(DateUtils.RFC822_DATE_PATTERN, Locale.ENGLISH).parse(str);
            return parse == null ? j : parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return j;
        }
    }
}
