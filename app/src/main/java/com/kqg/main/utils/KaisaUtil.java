package com.kqg.main.utils;

/* loaded from: classes.dex */
public class KaisaUtil {
    public static int KaisaKey = 41968146;

    public static String encryptKaisa(String str, int i) {
        char[] charArray = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (char c : charArray) {
            stringBuffer.append((char) (c + i));
        }
        return stringBuffer.toString();
    }

    public static String decryptKaiser(String str, int i) {
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            sb.append((char) (c - i));
        }
        return sb.toString();
    }
}
