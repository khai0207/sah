package com.android.pc.ioc.internet;

import com.android.pc.ioc.internet.FastHttp;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes.dex */
public class InternetConfig {
    public static final String UA = "Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)";
    public static final String content_type_json = "application/json;charset=utf-8";
    public static final String content_type_map = "application/x-www-form-urlencoded";
    public static final String content_type_xml = "text/xml; charset=utf-8";
    private static InternetConfig defaultConfig = new InternetConfig() { // from class: com.android.pc.ioc.internet.InternetConfig.1
        {
            setCharset("utf-8");
            setTime(30000);
            setRequest_type(0);
        }
    };
    public static final int request_file = 2;
    public static final int request_form = 4;
    public static final int request_get = 1;
    public static final int request_post = 0;
    public static final int request_webserver = 3;
    public static final int result_String = 2;
    public static final int result_entity = 1;
    public static final int result_map = 0;
    private String charset;
    private HashMap<String, File> files;
    private HashMap<String, Object> head;
    private int key;
    private String method;
    private FastHttp.Progress progress;
    private int time;
    private String content_type_web = content_type_map;
    private boolean isHttps = false;
    private int request_type = 0;
    private String name_space = "http://tempuri.org/";
    private int timeout = 30000;
    private boolean isCookies = false;
    private long all_length = 0;
    private boolean isSave = false;
    private int saveDate = -1;

    public static InternetConfig defaultConfig() {
        return defaultConfig;
    }

    public boolean isCookies() {
        return this.isCookies;
    }

    public void setCookies(boolean z) {
        this.isCookies = z;
    }

    public HashMap<String, Object> getHead() {
        return this.head;
    }

    public void setHead(HashMap<String, Object> hashMap) {
        this.head = hashMap;
    }

    public String getCharset() {
        String str = this.charset;
        return str == null ? defaultConfig().charset : str;
    }

    public void setCharset(String str) {
        this.charset = str;
    }

    public int getTime() {
        int i = this.time;
        return i == 0 ? defaultConfig().time : i;
    }

    public void setTime(int i) {
        this.time = i * 1000;
    }

    public int getRequest_type() {
        return this.request_type;
    }

    public void setRequest_type(int i) {
        this.request_type = i;
    }

    public String getName_space() {
        return this.name_space;
    }

    public void setName_space(String str) {
        this.name_space = str;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int i) {
        this.timeout = i;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public String getContent_type_web() {
        return this.content_type_web;
    }

    public void setContent_type_web(String str) {
        this.content_type_web = str;
    }

    public String toString() {
        return "InternetConfig [content_type_web=" + this.content_type_web + ", isHttps=" + this.isHttps + ", method=" + this.method + ", charset=" + this.charset + ", time=" + this.time + ", request_type=" + this.request_type + ", name_space=" + this.name_space + ", timeout=" + this.timeout + ", files=" + this.files + ", isCookies=" + this.isCookies + ", key=" + this.key + "]";
    }

    public HashMap<String, File> getFiles() {
        return this.files;
    }

    public void setFiles(HashMap<String, File> hashMap) {
        this.files = hashMap;
    }

    public boolean isHttps() {
        return this.isHttps;
    }

    public void setHttps(boolean z) {
        this.isHttps = z;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int i) {
        this.key = i;
    }

    public FastHttp.Progress getProgress() {
        return this.progress;
    }

    public void setProgress(FastHttp.Progress progress) {
        this.progress = progress;
    }

    public long getAll_length() {
        return this.all_length;
    }

    public void setAll_length(long j) {
        this.all_length = j;
    }

    public boolean isSave() {
        return this.isSave;
    }

    public void setSave(boolean z) {
        this.isSave = z;
    }

    public int getSaveDate() {
        return this.saveDate;
    }

    public void setSaveDate(int i) {
        this.saveDate = i;
    }
}
