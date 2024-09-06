package com.android.pc.ioc.internet;

import com.android.pc.ioc.app.Ioc;
import com.android.pc.util.Handler_File;
import com.android.pc.util.MD5;
import com.kqg.main.constant.KV;
import java.io.File;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public class HttpCache {
    public static String getUrlCache(String str, LinkedHashMap<String, String> linkedHashMap) {
        if (str == null) {
            return null;
        }
        for (String str2 : linkedHashMap.keySet()) {
            str = str + str2 + linkedHashMap.get(str2);
        }
        int saveDate = InternetConfig.defaultConfig().getSaveDate();
        File file = new File(Ioc.getIoc().getApplication().getCacheDir(), getCacheDecodeString(str));
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis() - file.lastModified();
        Ioc.getIoc().getLogger().d("缓存了:" + (currentTimeMillis / KV.GET_CODE_INTERVAL) + "分钟");
        if (saveDate != -1 && currentTimeMillis > saveDate) {
            file.delete();
            return null;
        }
        return Handler_File.getAsString(file);
    }

    public static void setUrlCache(String str, String str2) {
        Handler_File.write(new File(Ioc.getIoc().getApplication().getCacheDir(), getCacheDecodeString(str2)), str);
    }

    private static String getCacheDecodeString(String str) {
        return MD5.Md5(str.replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+"));
    }
}
