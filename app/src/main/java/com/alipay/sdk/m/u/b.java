package com.alipay.sdk.m.u;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes.dex */
public final class b {
    public static String a(String str, String str2) {
        Throwable th;
        File file;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            file = new File(str, str2);
        } catch (IOException unused) {
        } catch (Throwable th2) {
            th = th2;
        }
        if (!file.exists()) {
            return null;
        }
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        while (true) {
            try {
                String readLine = bufferedReader2.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                }
            } catch (IOException unused2) {
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    bufferedReader2 = bufferedReader;
                    break;
                    bufferedReader2.close();
                }
                return sb.toString();
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = bufferedReader2;
                if (bufferedReader == null) {
                    throw th;
                }
                try {
                    bufferedReader.close();
                    throw th;
                } catch (Throwable unused3) {
                    throw th;
                }
            }
            try {
                break;
            } catch (Throwable unused4) {
            }
        }
        bufferedReader2.close();
        return sb.toString();
    }
}
