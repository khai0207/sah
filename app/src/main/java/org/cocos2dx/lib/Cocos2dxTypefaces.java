package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Cocos2dxTypefaces {
    private static final HashMap<String, Typeface> sTypefaceCache = new HashMap<>();

    public static synchronized Typeface get(Context context, String str) {
        Typeface typeface;
        Typeface createFromAsset;
        synchronized (Cocos2dxTypefaces.class) {
            if (!sTypefaceCache.containsKey(str)) {
                if (str.startsWith("/")) {
                    createFromAsset = Typeface.createFromFile(str);
                } else {
                    createFromAsset = Typeface.createFromAsset(context.getAssets(), str);
                }
                sTypefaceCache.put(str, createFromAsset);
            }
            typeface = sTypefaceCache.get(str);
        }
        return typeface;
    }
}
