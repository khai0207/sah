package com.netease.nimlib.o;

/* compiled from: HexDump.java */
/* loaded from: classes.dex */
public class j {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final int[] b = {60, 56, 52, 48, 44, 40, 36, 32, 28, 24, 20, 16, 12, 8, 4, 0};

    private static int a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'a';
        if (c < 'a' || c > 'f') {
            c2 = 'A';
            if (c < 'A' || c > 'F') {
                return -1;
            }
        }
        return (c - c2) + 10;
    }

    private static String a(long j, int i) {
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(a[(int) ((j >> b[(16 - i) + i2]) & 15)]);
        }
        return sb.toString();
    }

    public static String a(byte b2) {
        return a(b2, 2);
    }

    public static String a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static String a(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        int i3 = i2 + i;
        while (i < i3) {
            sb.append(a(bArr[i]));
            i++;
        }
        return sb.toString();
    }

    public static byte[] a(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int a2 = a(str.charAt(i2));
            int a3 = a(str.charAt(i2 + 1));
            if (a2 == -1 || a3 == -1) {
                return null;
            }
            bArr[i] = (byte) ((a2 << 4) + a3);
        }
        return bArr;
    }
}
