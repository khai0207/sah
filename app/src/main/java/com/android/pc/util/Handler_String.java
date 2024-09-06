package com.android.pc.util;

import com.alipay.sdk.m.o.a;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class Handler_String {
    public static final int TYPE_CHINA = 3;
    public static final int TYPE_ENGLISH = 1;
    public static final int TYPE_FUHAO = 2;
    public static final int TYPE_NUMBER = 0;

    public static String nullStrToEmpty(String str) {
        return str == null ? "" : str;
    }

    public static int sepMarkNot(char c) {
        if (c > '/' && c < ':') {
            return 0;
        }
        if (c > '@' && c < '[') {
            return 1;
        }
        if (c <= '`' || c >= 'z') {
            return (c < 19968 || c > 40891) ? 2 : 3;
        }
        return 1;
    }

    public static String addPrefix(int i, String str) {
        if (i >= 10) {
            return String.valueOf(i);
        }
        return str + i;
    }

    public static String addPrefix(String str, String str2) {
        return addPrefix(Integer.parseInt(str), str2);
    }

    public static String addPrefixZero(int i) {
        return addPrefix(i, "0");
    }

    public static String addPrefixZero(String str) {
        return addPrefix(str, "0");
    }

    public static String addPrefixHtmlSpace(int i) {
        return addPrefix(i, "&nbsp;");
    }

    public static String addPrefixHtmlSpace(String str) {
        return addPrefix(str, "&nbsp;");
    }

    public static String commaInt(Object[] objArr, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < objArr.length; i++) {
            stringBuffer.append(objArr[i]);
            if (i < objArr.length - 1) {
                stringBuffer.append(str);
            }
        }
        return stringBuffer.toString();
    }

    public static String commaInt(Object[] objArr) {
        return commaInt(objArr, ",");
    }

    public static boolean isNull(String str) {
        return str == null || str.length() == 0;
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append('0');
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public static String prettyBytes(long j) {
        char c = 0;
        String[] strArr = {"B", "KB", "MB", "GB", "TB"};
        StringBuilder sb = new StringBuilder();
        if (j < ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) {
            sb.append(String.valueOf(j));
        } else if (j < 1048576) {
            double d = j;
            Double.isNaN(d);
            sb.append(String.format("%.1f", Double.valueOf(d / 1024.0d)));
            c = 1;
        } else if (j < Constants.GB) {
            double d2 = j;
            Double.isNaN(d2);
            sb.append(String.format("%.2f", Double.valueOf(d2 / 1048576.0d)));
            c = 2;
        } else if (j < 1099511627776L) {
            double d3 = j;
            Double.isNaN(d3);
            sb.append(String.format("%.3f", Double.valueOf(d3 / 1.073741824E9d)));
            c = 3;
        } else {
            double d4 = j;
            Double.isNaN(d4);
            sb.append(String.format("%.4f", Double.valueOf(d4 / 1.099511627776E12d)));
            c = 4;
        }
        sb.append(' ');
        sb.append(strArr[c]);
        return sb.toString();
    }

    public static String repeat(String str, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public static int getLargestLengthInArray(String[] strArr) {
        int i = 0;
        for (String str : strArr) {
            if (str.length() > i) {
                i = str.length();
            }
        }
        return i;
    }

    public static String replaceAllByStringBuffer(String str, String str2, String str3) {
        int indexOf;
        StringBuffer stringBuffer = new StringBuffer(str3);
        int i = 0;
        do {
            indexOf = stringBuffer.indexOf(str, i);
            if (indexOf > -1) {
                stringBuffer.replace(indexOf, str.length() + indexOf, str2);
                i = str2.length() + indexOf;
            }
        } while (indexOf > -1);
        return stringBuffer.toString();
    }

    public static int getLengths(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            i = sepMarkNot(str.charAt(i2)) == 3 ? i + 2 : i + 1;
        }
        return i;
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }
        char charAt = str.charAt(0);
        if (!Character.isLetter(charAt) || Character.isUpperCase(charAt)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        sb.append(Character.toUpperCase(charAt));
        sb.append(str.substring(1));
        return sb.toString();
    }

    public static String utf8Encode(String str) {
        if (isEmpty(str) || str.getBytes().length == str.length()) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
        }
    }

    public static String utf8Encode(String str, String str2) {
        if (isEmpty(str) || str.getBytes().length == str.length()) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str2;
        }
    }

    public static String getHrefInnerHtml(String str) {
        if (isEmpty(str)) {
            return "";
        }
        Matcher matcher = Pattern.compile(".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*", 2).matcher(str);
        return matcher.matches() ? matcher.group(1) : str;
    }

    public static String htmlEscapeCharsToString(String str) {
        return isEmpty(str) ? str : str.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", a.l).replaceAll("&quot;", "\"");
    }

    public static String fullWidthToHalfWidth(String str) {
        if (isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 12288) {
                charArray[i] = ' ';
            } else if (charArray[i] >= 65281 && charArray[i] <= 65374) {
                charArray[i] = (char) (charArray[i] - 65248);
            } else {
                charArray[i] = charArray[i];
            }
        }
        return new String(charArray);
    }

    public static String halfWidthToFullWidth(String str) {
        if (isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                charArray[i] = 12288;
            } else if (charArray[i] >= '!' && charArray[i] <= '~') {
                charArray[i] = (char) (charArray[i] + 65248);
            } else {
                charArray[i] = charArray[i];
            }
        }
        return new String(charArray);
    }
}
