package com.talkingdata.sdk;

import java.util.TreeMap;

/* compiled from: td */
/* loaded from: classes.dex */
public class bx {
    private static final String a = "ad";

    /* compiled from: td */
    /* loaded from: classes.dex */
    public enum a {
        DOMOB,
        LIMEI,
        YOUMI,
        DIANRU
    }

    public static void a(a aVar, String str, String str2, String str3, String str4) {
        if (bh.b(str)) {
            return;
        }
        TreeMap treeMap = new TreeMap();
        treeMap.put("adNetwork", aVar.name());
        treeMap.put("adId", str);
        if (!bh.b(str2)) {
            treeMap.put("targetAppId", str2);
        }
        if (!bh.b(str3)) {
            treeMap.put("mediaId", str3);
        }
        if (bh.b(str4)) {
            return;
        }
        treeMap.put("impressionId", str4);
    }

    public static void b(a aVar, String str, String str2, String str3, String str4) {
        if (bh.b(str)) {
            return;
        }
        TreeMap treeMap = new TreeMap();
        treeMap.put("adNetwork", aVar.name());
        treeMap.put("adId", str);
        if (!bh.b(str2)) {
            treeMap.put("targetAppId", str2);
        }
        if (!bh.b(str3)) {
            treeMap.put("mediaId", str3);
        }
        if (bh.b(str4)) {
            return;
        }
        treeMap.put("clickId", str4);
    }
}
