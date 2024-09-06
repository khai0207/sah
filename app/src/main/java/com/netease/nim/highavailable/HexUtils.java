package com.netease.nim.highavailable;

import u.aly.df;

/* loaded from: classes.dex */
public class HexUtils {
    private static final char[] HEXES = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String bytes2Hex(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            sb.append("0x");
            sb.append(HEXES[(b >> 4) & 15]);
            sb.append(HEXES[b & df.m]);
            if (i < bArr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static byte[] hex2Bytes(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            int i2 = i * 2;
            sb.append(charArray[i2]);
            sb.append(charArray[i2 + 1]);
            bArr[i] = (byte) Integer.parseInt(sb.toString(), 16);
        }
        return bArr;
    }
}
