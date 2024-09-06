package com.netease.nimlib.n.a;

import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import java.util.HashMap;
import java.util.Map;

/* compiled from: EventConstants.java */
/* loaded from: classes.dex */
public class a {
    public static String a = "unknown error";
    public static Map<Integer, String> b = new HashMap<Integer, String>() { // from class: com.netease.nimlib.n.a.a.1
        {
            put(302, "invalid username or password");
            put(Integer.valueOf(Constants.BUCKET_ACCESS_FORBIDDEN_STATUS_CODE), "permission denied");
            put(404, "not exist");
            put(414, "parameter error");
            put(415, "connection error");
            put(500, a.a);
        }
    };
}
