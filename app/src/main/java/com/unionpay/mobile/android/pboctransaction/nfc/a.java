package com.unionpay.mobile.android.pboctransaction.nfc;

import android.text.TextUtils;
import android.util.Log;
import com.android.pc.util.ThreeMap;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.pboctransaction.nfc.b;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class a {
    protected static final byte[] d = {50, 80, 65, 89, 46, 83, 89, 83, 46, 68, 68, 70, 48, 49};
    protected String a = "UnionPay Card";
    com.unionpay.mobile.android.fully.a b;
    b.C0069b c;

    public a(com.unionpay.mobile.android.fully.a aVar, b.C0069b c0069b) {
        this.b = aVar;
        this.c = c0069b;
    }

    private static String a(String str, String str2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        if (str == null) {
            return null;
        }
        byte[] a = e.a(str);
        int i10 = 0;
        while (i10 < a.length) {
            int i11 = 1;
            int i12 = ((byte) (a[i10] & 31)) == 31 ? 2 : 1;
            byte[] bArr = new byte[i12];
            System.arraycopy(a, i10, bArr, 0, i12);
            if (e.a(bArr, i12).compareToIgnoreCase(str2) == 0) {
                int i13 = i10 + i12;
                if (((byte) (a[i13] & 128)) != Byte.MIN_VALUE) {
                    i4 = a[i13];
                } else {
                    i11 = 1 + (a[i13] & 127);
                    if (i11 != 2) {
                        if (i11 == 3) {
                            i2 = (a[i13 + 1] & 255) << 8;
                            i3 = a[i13 + 2];
                        } else {
                            if (i11 != 4) {
                                i = 0;
                                byte[] bArr2 = new byte[i];
                                System.arraycopy(a, i13 + i11, bArr2, 0, i);
                                return e.a(bArr2, i);
                            }
                            i2 = ((a[i13 + 1] & 255) << 16) | ((a[i13 + 2] & 255) << 8);
                            i3 = a[i13 + 3];
                        }
                        i = i2 | (i3 & 255);
                        byte[] bArr22 = new byte[i];
                        System.arraycopy(a, i13 + i11, bArr22, 0, i);
                        return e.a(bArr22, i);
                    }
                    i4 = a[i13 + 1];
                }
                i = i4 & 255;
                byte[] bArr222 = new byte[i];
                System.arraycopy(a, i13 + i11, bArr222, 0, i);
                return e.a(bArr222, i);
            }
            int i14 = a[i10] & 32;
            int i15 = i10 + i12;
            int length = a.length;
            if (i14 != 32) {
                if (i15 >= length || ((byte) (a[i15] & 128)) != 0) {
                    i11 = i15 < a.length ? (a[i15] & 127) + 1 : 0;
                    if (i11 != 2 || (i8 = i15 + 1) >= a.length) {
                        i5 = (i11 != 3 || (i7 = i15 + 2) >= a.length) ? (i11 != 4 || (i6 = i15 + 2) >= a.length) ? 0 : ((a[i6] & 255) << 8) | ((a[i15 + 1] & 255) << 16) | (a[i15 + 3] & 255) : (a[i7] & 255) | ((a[i15 + 1] & 255) << 8);
                        i11 += i5;
                    } else {
                        i9 = a[i8];
                    }
                } else {
                    i9 = a[i15];
                }
                i5 = i9 & 255;
                i11 += i5;
            } else if (i15 < length && ((byte) (a[i15] & 128)) == Byte.MIN_VALUE) {
                i11 = 1 + (a[i15] & 127);
            }
            i10 = i15 + i11;
        }
        return null;
    }

    private static List<String> a(String str) {
        ArrayList arrayList = new ArrayList();
        if (str == null) {
            return arrayList;
        }
        byte[] a = e.a(str);
        int i = 0;
        while (i < a.length) {
            int i2 = 1;
            int i3 = ((byte) (a[i] & 31)) == 31 ? 2 : 1;
            byte[] bArr = new byte[i3];
            System.arraycopy(a, i, bArr, 0, i3);
            arrayList.add(e.a(bArr, i3));
            int i4 = i + i3;
            if (i4 < a.length && ((byte) (a[i4] & 128)) == Byte.MIN_VALUE) {
                i2 = 1 + (a[i4] & 127);
            }
            i = i4 + i2;
        }
        return arrayList;
    }

    public static void b(String str, HashMap<String, String> hashMap) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("82");
        arrayList.add("9F36");
        arrayList.add("9F10");
        arrayList.add("9F26");
        arrayList.add("5F34");
        arrayList.add("57");
        arrayList.add("9F63");
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                String str2 = (String) arrayList.get(i);
                String a = a(str, str2);
                if (a != null) {
                    hashMap.put(str2, a);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        StringBuffer stringBuffer = new StringBuffer(hashMap.get("5F34"));
        stringBuffer.insert(0, "0");
        hashMap.put("5F34", stringBuffer.toString());
        String str3 = hashMap.get("57");
        while (true) {
            if (!str3.substring(str3.length() - 1, str3.length()).equalsIgnoreCase(ThreeMap.type_float) && !str3.substring(str3.length() - 1, str3.length()).equalsIgnoreCase("F")) {
                break;
            }
            str3 = str3.substring(0, str3.length() - 1);
        }
        hashMap.put("TD2", str3.toString());
        int indexOf = str3.indexOf("D");
        String substring = str3.substring(0, indexOf);
        if (substring.endsWith("F") || substring.endsWith(ThreeMap.type_float)) {
            substring = substring.substring(0, substring.length() - 1);
        }
        hashMap.put("AN1", substring);
        hashMap.put("ED", str3.substring(indexOf + 1, indexOf + 5));
        hashMap.put("AMT", hashMap.get("9F02"));
        if (hashMap.containsKey("9F10") && ((byte) (e.a(hashMap.get("9F10"))[4] & 48)) == 32) {
            hashMap.put("9F27", "80");
        }
    }

    public final String a() {
        b.a a = this.c.a(d);
        if (!a.b()) {
            return null;
        }
        String a2 = a(a.toString(), "4F");
        if (!a2.startsWith("A000000333")) {
            return "noSupUnionpay";
        }
        b.a a3 = this.c.a(e.a(a2));
        if (a3.b()) {
            return a3.toString();
        }
        return null;
    }

    public final String a(String str, HashMap<String, String> hashMap) {
        String a = a(str, "9F38");
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : a(a)) {
            Iterator<String> it = hashMap.keySet().iterator();
            while (true) {
                if (it.hasNext()) {
                    String next = it.next();
                    if (str2.compareToIgnoreCase(next) == 0) {
                        stringBuffer.append(hashMap.get(next));
                        break;
                    }
                }
            }
        }
        b.C0069b c0069b = this.c;
        byte[] a2 = e.a(stringBuffer.toString());
        ByteBuffer allocate = ByteBuffer.allocate(a2.length + 7);
        allocate.put(Byte.MIN_VALUE).put((byte) -88).put((byte) 0).put((byte) 0).put((byte) (a2.length + 2)).put((byte) -125).put((byte) a2.length).put(a2);
        Log.e("PBOC Transceive", b.C0069b.c(allocate.array()));
        b.a aVar = new b.a(c0069b.b(allocate.array()));
        Log.e("PBOC receive", b.C0069b.c(aVar.a()));
        if (!aVar.b()) {
            return null;
        }
        String a3 = a(aVar.toString(), "9F10");
        if (TextUtils.isEmpty(a3) || ((byte) (e.a(a3)[4] & 48)) == 32) {
            return aVar.toString();
        }
        return null;
    }
}
