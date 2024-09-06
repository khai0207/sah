package com.unionpay.mobile.android.pboctransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class e {
    static HashMap<String, String> a = new f();

    private static String a(String str, String str2) {
        if (str != null && str2 != null && str.length() > str2.length()) {
            while (str.substring(str.length() - str2.length(), str.length()).equalsIgnoreCase(str2)) {
                str = str.substring(0, str.length() - str2.length());
            }
        }
        return str;
    }

    public static final String a(byte[] bArr) {
        return a(bArr, bArr.length);
    }

    public static final String a(byte[] bArr, int i) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || i <= 0) {
            return null;
        }
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase();
    }

    private static boolean a(ArrayList<AppIdentification> arrayList, String str) {
        if (arrayList == null || str == null) {
            return false;
        }
        Iterator<AppIdentification> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().a().equalsIgnoreCase(str)) {
                return false;
            }
        }
        return true;
    }

    public static final byte[] a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (((byte) "0123456789ABCDEF".indexOf(charArray[i2 + 1])) | (((byte) "0123456789ABCDEF".indexOf(charArray[i2])) << 4));
        }
        return bArr;
    }

    public static final ArrayList<AppIdentification> b(String str) {
        if (str == null || str.length() <= 4) {
            return null;
        }
        ArrayList<AppIdentification> arrayList = new ArrayList<>();
        String substring = str.substring(str.length() - 4);
        if (substring != null && substring.equalsIgnoreCase("9000")) {
            int i = 0;
            String substring2 = str.substring(0, str.length() - 4);
            while (substring2 != null && substring2.length() > 0 && i != -1) {
                int indexOf = substring2.indexOf("61", i);
                if (indexOf == -1) {
                    break;
                }
                int i2 = indexOf + 2;
                int i3 = indexOf + 4;
                String substring3 = substring2.substring(i2, i3);
                int i4 = indexOf + 6;
                String substring4 = substring2.substring(i3, i4);
                String substring5 = (Integer.parseInt(substring4, 16) * 2) + i4 < substring2.length() ? substring2.substring(i4, (Integer.parseInt(substring4, 16) * 2) + i4) : "";
                int parseInt = (Integer.parseInt(substring3, 16) * 2) + i2;
                if (parseInt <= substring2.length()) {
                    i2 = parseInt;
                }
                String trim = substring5.trim();
                if (trim.length() > 8 && !trim.equalsIgnoreCase("A0000003334355502D4D4F42494C45") && a(arrayList, trim)) {
                    arrayList.add(new AppIdentification(trim, null));
                }
                i = i2;
            }
        }
        return arrayList;
    }

    public static String c(String str) {
        return a(str, "F");
    }

    public static String d(String str) {
        return a(str, "00");
    }

    public static String e(String str) {
        return a.containsKey(str) ? a.get(str) : "";
    }
}
