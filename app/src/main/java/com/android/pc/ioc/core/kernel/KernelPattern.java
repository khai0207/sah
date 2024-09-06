package com.android.pc.ioc.core.kernel;

import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class KernelPattern {
    public static Pattern[] getPatterns(String[] strArr, int i) {
        int length;
        if (strArr == null || (length = strArr.length) == 0) {
            return null;
        }
        Pattern[] patternArr = new Pattern[length];
        for (int i2 = 0; i2 < length; i2++) {
            patternArr[i2] = Pattern.compile(strArr[i2], i);
        }
        return patternArr;
    }

    public static boolean matchPatterns(String str, Pattern[] patternArr) {
        if (patternArr == null) {
            return false;
        }
        for (Pattern pattern : patternArr) {
            if (pattern.matcher(str).find()) {
                return true;
            }
        }
        return false;
    }
}
