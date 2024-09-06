package com.iflytek.cloud.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes.dex */
public class ResourceUtil {
    public static final String ASR_RES_PATH = "asr_res_path";
    public static final String ENGINE_DESTROY = "engine_destroy";
    public static final String ENGINE_START = "engine_start";
    public static final String GRM_BUILD_PATH = "grm_build_path";
    public static final String IVW_RES_PATH = "ivw_res_path";
    public static final String TTS_RES_PATH = "tts_res_path";

    /* loaded from: classes.dex */
    public enum RESOURCE_TYPE {
        assets,
        res,
        path
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v8, types: [android.content.res.AssetFileDescriptor] */
    private static String a(Context context, RESOURCE_TYPE resource_type, String str) {
        long startOffset;
        String str2 = null;
        str2 = null;
        str2 = null;
        AssetFileDescriptor assetFileDescriptor = null;
        if (TextUtils.isEmpty(str) || context == 0) {
            return null;
        }
        String packageResourcePath = context.getPackageResourcePath();
        try {
            try {
                try {
                } catch (Exception e) {
                    e = e;
                    context = 0;
                } catch (Throwable th) {
                    th = th;
                    if (assetFileDescriptor != null) {
                        try {
                            assetFileDescriptor.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    if (resource_type == RESOURCE_TYPE.assets) {
                        AssetFileDescriptor openFd = context.getAssets().openFd(str);
                        startOffset = openFd.getStartOffset();
                        context = openFd;
                    } else {
                        AssetFileDescriptor openRawResourceFd = context.getResources().openRawResourceFd(Integer.valueOf(str).intValue());
                        startOffset = openRawResourceFd.getStartOffset();
                        context = openRawResourceFd;
                    }
                    str2 = "fo|" + packageResourcePath + "|" + startOffset + "|" + context.getLength();
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    if (context != 0) {
                        context.close();
                    }
                    return str2;
                }
                if (context != 0) {
                    context.close();
                }
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            return str2;
        } catch (Throwable th2) {
            th = th2;
            assetFileDescriptor = context;
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        return "fo|" + str + "|0|" + file.length();
    }

    public static String generateResourcePath(Context context, RESOURCE_TYPE resource_type, String str) {
        return resource_type == RESOURCE_TYPE.path ? a(str) : a(context, resource_type, str);
    }
}
