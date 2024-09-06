package com.android.pc.ioc.internet;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import com.alipay.sdk.m.o.a;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.util.Handler_Network;
import com.netease.nimlib.amazonaws.http.HttpHeader;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes.dex */
public class FastHttp {
    public static final String LINEND = "\r\n";
    private static final String METHOD = "method";
    public static final String MULTIPART_FROM_DATA = "multipart/form-data";
    public static final String PREFIX = "--";
    public static String cookies = null;
    public static final int result_net_err = 1;
    public static final int result_ok = 0;
    public static final String BOUNDARY = UUID.randomUUID().toString();
    private static MyHostnameVerifier hnv = new MyHostnameVerifier();

    /* loaded from: classes.dex */
    public interface Progress {
        void progress(int i);
    }

    public static ResponseEntity get(String str) {
        return get(str, null, InternetConfig.defaultConfig());
    }

    public static ResponseEntity get(String str, InternetConfig internetConfig) {
        return get(str, null, internetConfig);
    }

    public static ResponseEntity get(String str, LinkedHashMap<String, String> linkedHashMap) {
        return get(str, linkedHashMap, InternetConfig.defaultConfig());
    }

    public static ResponseEntity get(String str, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig) {
        internetConfig.setRequest_type(1);
        if (linkedHashMap != null) {
            String str2 = str.indexOf("\\?") != -1 ? str + "?" : str + a.l;
            for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                str2 = str2 + entry.getKey() + "=" + entry.getValue() + a.l;
            }
            str = str2.substring(0, str2.length() - 1);
        }
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setUrl(str);
        responseEntity.setParams(linkedHashMap);
        responseEntity.setKey(internetConfig.getKey());
        if (internetConfig.isSave() && !Handler_Network.isNetworkAvailable(Ioc.getIoc().getApplication())) {
            Ioc.getIoc().getLogger().e("无法连接到网络 将获取离线数据");
            String urlCache = HttpCache.getUrlCache(str, linkedHashMap);
            if (urlCache != null) {
                responseEntity.setContent(urlCache, false);
                responseEntity.setStatus(0);
                return responseEntity;
            }
        }
        try {
            HttpURLConnection defaultHttpClient = getDefaultHttpClient(str, internetConfig);
            InputStream inputStream = defaultHttpClient.getInputStream();
            getCookies(internetConfig, responseEntity, defaultHttpClient);
            responseEntity.setContent(inputStreamToString(inputStream, internetConfig.getCharset()), internetConfig.isSave());
            responseEntity.setConfig(internetConfig);
            defaultHttpClient.disconnect();
            responseEntity.setStatus(0);
            if (responseEntity.getContentAsString().length() == 0) {
                responseEntity.setStatus(1);
            }
            return responseEntity;
        } catch (Exception e) {
            responseEntity.setStatus(1);
            e.printStackTrace();
            return responseEntity;
        }
    }

    public static ResponseEntity post(String str) {
        return post(str, null, InternetConfig.defaultConfig());
    }

    public static ResponseEntity post(String str, LinkedHashMap<String, String> linkedHashMap) {
        return post(str, linkedHashMap, InternetConfig.defaultConfig());
    }

    public static ResponseEntity post(String str, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig) {
        internetConfig.setRequest_type(0);
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setUrl(str);
        responseEntity.setParams(linkedHashMap);
        responseEntity.setKey(internetConfig.getKey());
        if (internetConfig.isSave() && !Handler_Network.isNetworkAvailable(Ioc.getIoc().getApplication())) {
            Ioc.getIoc().getLogger().e("无法连接到网络 将获取离线数据");
            String urlCache = HttpCache.getUrlCache(str, linkedHashMap);
            if (urlCache != null) {
                responseEntity.setContent(urlCache, false);
                responseEntity.setStatus(0);
                return responseEntity;
            }
        }
        try {
            HttpURLConnection defaultHttpClient = getDefaultHttpClient(str, internetConfig);
            defaultHttpClient.setDoOutput(true);
            defaultHttpClient.setDoInput(true);
            defaultHttpClient.connect();
            OutputStream outputStream = defaultHttpClient.getOutputStream();
            String str2 = "";
            if (linkedHashMap != null && linkedHashMap.size() > 0) {
                for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                    str2 = str2 + entry.getKey() + "=" + entry.getValue() + a.l;
                }
                outputStream.write(str2.substring(0, str2.length() - 1).getBytes(internetConfig.getCharset()));
            }
            outputStream.flush();
            outputStream.close();
            InputStream inputStream = defaultHttpClient.getInputStream();
            getCookies(internetConfig, responseEntity, defaultHttpClient);
            responseEntity.setContent(inputStreamToString(inputStream, internetConfig.getCharset()), internetConfig.isSave());
            responseEntity.setConfig(internetConfig);
            defaultHttpClient.disconnect();
            responseEntity.setStatus(0);
            if (responseEntity.getContentAsString().length() == 0) {
                responseEntity.setStatus(1);
            }
            return responseEntity;
        } catch (Exception e) {
            responseEntity.setStatus(1);
            e.printStackTrace();
            return responseEntity;
        }
    }

    public static ResponseEntity postString(String str, String str2, InternetConfig internetConfig) {
        internetConfig.setRequest_type(0);
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setUrl(str);
        try {
            HttpURLConnection defaultHttpClient = getDefaultHttpClient(str, internetConfig);
            defaultHttpClient.setDoOutput(true);
            defaultHttpClient.setDoInput(true);
            defaultHttpClient.connect();
            OutputStream outputStream = defaultHttpClient.getOutputStream();
            if (str2 != null && str2.length() > 0) {
                outputStream.write(str2.getBytes(internetConfig.getCharset()));
            }
            outputStream.flush();
            outputStream.close();
            InputStream inputStream = defaultHttpClient.getInputStream();
            cookies = defaultHttpClient.getHeaderField("set-cookie");
            if (internetConfig.isCookies() && cookies != null) {
                for (String str3 : cookies.split(";")) {
                    String[] split = str3.split("=");
                    if (split.length > 1) {
                        responseEntity.cookie(split[0], split[1]);
                    }
                }
            }
            responseEntity.setContent(inputStreamToString(inputStream, internetConfig.getCharset()), internetConfig.isSave());
            defaultHttpClient.disconnect();
            responseEntity.setStatus(0);
            if (responseEntity.getContentAsString().length() == 0) {
                responseEntity.setStatus(1);
            }
            return responseEntity;
        } catch (Exception e) {
            responseEntity.setStatus(1);
            e.printStackTrace();
            return responseEntity;
        }
    }

    public static ResponseEntity form(String str) {
        return form(str, null, null, InternetConfig.defaultConfig());
    }

    public static ResponseEntity form(String str, LinkedHashMap<String, String> linkedHashMap) {
        return form(str, linkedHashMap, null, InternetConfig.defaultConfig());
    }

    public static ResponseEntity form(String str, LinkedHashMap<String, String> linkedHashMap, HashMap<String, File> hashMap) {
        return form(str, linkedHashMap, hashMap, InternetConfig.defaultConfig());
    }

    public static ResponseEntity form(String str, LinkedHashMap<String, String> linkedHashMap, HashMap<String, File> hashMap, InternetConfig internetConfig) {
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
        }
        internetConfig.setRequest_type(4);
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setUrl(str);
        responseEntity.setParams(linkedHashMap);
        responseEntity.setKey(internetConfig.getKey());
        if (internetConfig.isSave() && !Handler_Network.isNetworkAvailable(Ioc.getIoc().getApplication())) {
            Ioc.getIoc().getLogger().e("无法连接到网络 将获取离线数据");
            String urlCache = HttpCache.getUrlCache(str, linkedHashMap);
            if (urlCache != null) {
                responseEntity.setContent(urlCache, false);
                responseEntity.setStatus(0);
                return responseEntity;
            }
        }
        try {
            HttpURLConnection defaultHttpClient = getDefaultHttpClient(str, internetConfig);
            defaultHttpClient.setDoOutput(true);
            defaultHttpClient.setDoInput(true);
            defaultHttpClient.setUseCaches(false);
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                sb.append("--");
                sb.append(BOUNDARY);
                sb.append(LINEND);
                sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Content-Type: text/plain; charset=");
                sb2.append(internetConfig.getCharset());
                sb2.append(LINEND);
                sb.append(sb2.toString());
                sb.append("Content-Transfer-Encoding: 8bit\r\n");
                sb.append(LINEND);
                sb.append(entry.getValue());
                sb.append(LINEND);
            }
            DataOutputStream dataOutputStream = new DataOutputStream(defaultHttpClient.getOutputStream());
            if (sb.length() > 0) {
                dataOutputStream.write(sb.toString().getBytes());
            }
            if (hashMap != null) {
                for (Map.Entry<String, File> entry2 : hashMap.entrySet()) {
                    if (entry2.getValue().exists()) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("--");
                        sb3.append(BOUNDARY);
                        sb3.append(LINEND);
                        sb3.append("Content-Disposition: form-data; name=\"" + entry2.getKey() + "\"; filename=\"" + entry2.getValue().getName() + "\"" + LINEND);
                        sb3.append("Content-Type: image/pjpeg; \r\n");
                        sb3.append(LINEND);
                        dataOutputStream.write(sb3.toString().getBytes());
                        FileInputStream fileInputStream = new FileInputStream(entry2.getValue());
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            dataOutputStream.write(bArr, 0, read);
                        }
                        fileInputStream.close();
                        dataOutputStream.write(LINEND.getBytes());
                    }
                }
                dataOutputStream.write(("--" + BOUNDARY + "--" + LINEND).getBytes());
                dataOutputStream.flush();
                InputStream inputStream = defaultHttpClient.getInputStream();
                getCookies(internetConfig, responseEntity, defaultHttpClient);
                responseEntity.setContent(inputStreamToString(inputStream, internetConfig.getCharset()), internetConfig.isSave());
                responseEntity.setKey(internetConfig.getKey());
                dataOutputStream.close();
                defaultHttpClient.disconnect();
                responseEntity.setStatus(0);
                if (responseEntity.getContentAsString().length() == 0) {
                    responseEntity.setStatus(1);
                }
            } else {
                responseEntity.setContent(inputStreamToString(defaultHttpClient.getInputStream(), internetConfig.getCharset()), internetConfig.isSave());
                defaultHttpClient.disconnect();
                responseEntity.setStatus(0);
                if (responseEntity.getContentAsString().length() == 0) {
                    responseEntity.setStatus(1);
                }
            }
        } catch (Exception e) {
            responseEntity.setStatus(1);
            e.printStackTrace();
        }
        return responseEntity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.HashMap] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r5v1, types: [com.android.pc.ioc.internet.ResponseEntity] */
    public static ResponseEntity formProgress(String str, LinkedHashMap<String, String> linkedHashMap, HashMap<String, File> hashMap, InternetConfig internetConfig, Progress progress) {
        long ortherLength;
        long j;
        HttpURLConnection defaultHttpClient;
        String str2;
        String str3;
        BufferedOutputStream bufferedOutputStream;
        String str4;
        ?? r1 = hashMap;
        LinkedHashMap<String, String> linkedHashMap2 = linkedHashMap == null ? new LinkedHashMap<>() : linkedHashMap;
        internetConfig.setRequest_type(4);
        ?? responseEntity = new ResponseEntity();
        responseEntity.setUrl(str);
        responseEntity.setParams(linkedHashMap2);
        responseEntity.setKey(internetConfig.getKey());
        if (internetConfig.isSave() && !Handler_Network.isNetworkAvailable(Ioc.getIoc().getApplication())) {
            Ioc.getIoc().getLogger().e("无法连接到网络 将获取离线数据");
            String urlCache = HttpCache.getUrlCache(str, linkedHashMap2);
            if (urlCache != null) {
                responseEntity.setContent(urlCache, false);
                responseEntity.setStatus(0);
                return responseEntity;
            }
        }
        try {
            ortherLength = getOrtherLength(linkedHashMap2, r1, internetConfig);
            j = 0;
            internetConfig.setAll_length(ortherLength);
            defaultHttpClient = getDefaultHttpClient(str, internetConfig);
            defaultHttpClient.setDoOutput(true);
            defaultHttpClient.setDoInput(true);
            defaultHttpClient.setUseCaches(false);
            StringBuilder sb = new StringBuilder();
            Iterator<Map.Entry<String, String>> it = linkedHashMap2.entrySet().iterator();
            while (true) {
                str2 = "\"";
                str3 = "Content-Disposition: form-data; name=\"";
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next = it.next();
                sb.append("--");
                sb.append(BOUNDARY);
                sb.append(LINEND);
                sb.append("Content-Disposition: form-data; name=\"" + next.getKey() + "\"" + LINEND);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Content-Type: text/plain; charset=");
                sb2.append(internetConfig.getCharset());
                sb2.append(LINEND);
                sb.append(sb2.toString());
                sb.append("Content-Transfer-Encoding: 8bit\r\n");
                sb.append(LINEND);
                sb.append(next.getValue());
                sb.append(LINEND);
            }
            bufferedOutputStream = new BufferedOutputStream(defaultHttpClient.getOutputStream());
            if (sb.length() > 0) {
                bufferedOutputStream.write(sb.toString().getBytes());
                if (progress != null) {
                    j = 0 + sb.toString().getBytes().length;
                    progress.progress((int) ((j * 100) / ortherLength));
                }
            }
            try {
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            r1 = 1;
        }
        if (r1 != 0) {
            Iterator<Map.Entry<String, File>> it2 = hashMap.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<String, File> next2 = it2.next();
                if (next2.getValue().exists()) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("--");
                    Iterator<Map.Entry<String, File>> it3 = it2;
                    sb3.append(BOUNDARY);
                    sb3.append(LINEND);
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(str3);
                    String str5 = str3;
                    sb4.append(next2.getKey());
                    sb4.append("\"; filename=\"");
                    sb4.append(next2.getValue().getName());
                    sb4.append(str2);
                    sb4.append(LINEND);
                    sb3.append(sb4.toString());
                    sb3.append("Content-Type: image/pjpeg; \r\n");
                    sb3.append(LINEND);
                    bufferedOutputStream.write(sb3.toString().getBytes());
                    if (progress != null) {
                        str4 = str2;
                        j += sb3.toString().getBytes().length;
                        progress.progress((int) ((j * 100) / ortherLength));
                    } else {
                        str4 = str2;
                    }
                    FileInputStream fileInputStream = new FileInputStream(next2.getValue());
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                        j += read;
                        if (progress != null) {
                            progress.progress((int) ((j * 100) / ortherLength));
                        }
                    }
                    fileInputStream.close();
                    bufferedOutputStream.write(LINEND.getBytes());
                    if (progress != null) {
                        j += LINEND.getBytes().length;
                        progress.progress((int) ((j * 100) / ortherLength));
                    }
                    it2 = it3;
                    str2 = str4;
                    str3 = str5;
                }
            }
            bufferedOutputStream.write(("--" + BOUNDARY + "--" + LINEND).getBytes());
            bufferedOutputStream.flush();
            if (progress != null) {
                progress.progress((int) (((j + r1.length) * 100) / ortherLength));
            }
            InputStream inputStream = defaultHttpClient.getInputStream();
            getCookies(internetConfig, responseEntity, defaultHttpClient);
            responseEntity.setContent(inputStreamToString(inputStream, internetConfig.getCharset()), internetConfig.isSave());
            responseEntity.setKey(internetConfig.getKey());
            responseEntity.setConfig(internetConfig);
            bufferedOutputStream.close();
            defaultHttpClient.disconnect();
            responseEntity.setStatus(0);
            if (responseEntity.getContentAsString().length() == 0) {
                r1 = 1;
                responseEntity.setStatus(1);
            }
            return responseEntity;
        }
        responseEntity.setContent(inputStreamToString(defaultHttpClient.getInputStream(), internetConfig.getCharset()), internetConfig.isSave());
        defaultHttpClient.disconnect();
        responseEntity.setStatus(0);
        if (responseEntity.getContentAsString().length() == 0) {
            r1 = 1;
            responseEntity.setStatus(1);
        }
        return responseEntity;
        e = e;
        responseEntity.setStatus(r1);
        e.printStackTrace();
        return responseEntity;
    }

    public static void ajaxForm(String str, AjaxCallBack ajaxCallBack) {
        ajaxForm(str, null, null, InternetConfig.defaultConfig(), ajaxCallBack);
    }

    public static void ajaxForm(String str, InternetConfig internetConfig, AjaxCallBack ajaxCallBack) {
        ajaxForm(str, null, null, internetConfig, ajaxCallBack);
    }

    public static void ajaxForm(String str, LinkedHashMap<String, String> linkedHashMap, AjaxCallBack ajaxCallBack) {
        ajaxForm(str, linkedHashMap, null, InternetConfig.defaultConfig(), ajaxCallBack);
    }

    public static void ajaxForm(String str, LinkedHashMap<String, String> linkedHashMap, HashMap<String, File> hashMap, AjaxCallBack ajaxCallBack) {
        ajaxForm(str, linkedHashMap, hashMap, InternetConfig.defaultConfig(), ajaxCallBack);
    }

    public static void ajaxForm(String str, LinkedHashMap<String, String> linkedHashMap, HashMap<String, File> hashMap, InternetConfig internetConfig, AjaxCallBack ajaxCallBack) {
        internetConfig.setRequest_type(4);
        internetConfig.setFiles(hashMap);
        new Thread(new AjaxTask(str, linkedHashMap, internetConfig, ajaxCallBack)).start();
    }

    public static void ajax(String str, AjaxCallBack ajaxCallBack) {
        ajax(str, (LinkedHashMap<String, String>) null, InternetConfig.defaultConfig(), ajaxCallBack);
    }

    public static void ajax(String str, InternetConfig internetConfig, AjaxCallBack ajaxCallBack) {
        ajax(str, (LinkedHashMap<String, String>) null, internetConfig, ajaxCallBack);
    }

    public static void ajax(String str, LinkedHashMap<String, String> linkedHashMap, AjaxCallBack ajaxCallBack) {
        ajax(str, linkedHashMap, InternetConfig.defaultConfig(), ajaxCallBack);
    }

    public static void ajax(String str, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig, AjaxCallBack ajaxCallBack) {
        internetConfig.setRequest_type(0);
        new Thread(new AjaxTask(str, linkedHashMap, internetConfig, ajaxCallBack)).start();
    }

    public static void ajax(String str, AjaxTimeCallBack ajaxTimeCallBack) {
        InternetConfig defaultConfig = InternetConfig.defaultConfig();
        defaultConfig.setRequest_type(0);
        ajax(str, (LinkedHashMap<String, String>) null, defaultConfig, ajaxTimeCallBack);
    }

    public static void ajax(String str, LinkedHashMap<String, String> linkedHashMap, AjaxTimeCallBack ajaxTimeCallBack) {
        InternetConfig defaultConfig = InternetConfig.defaultConfig();
        defaultConfig.setRequest_type(0);
        ajax(str, linkedHashMap, defaultConfig, ajaxTimeCallBack);
    }

    public static void ajax(String str, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig, AjaxTimeCallBack ajaxTimeCallBack) {
        internetConfig.setRequest_type(0);
        new Thread(new TimeTask(str, linkedHashMap, internetConfig, ajaxTimeCallBack)).start();
    }

    public static ResponseEntity webServer(String str, String str2) {
        return webServer(str, null, InternetConfig.defaultConfig(), str2);
    }

    public static ResponseEntity webServer(String str, InternetConfig internetConfig, String str2) {
        return webServer(str, null, internetConfig, str2);
    }

    public static ResponseEntity webServer(String str, LinkedHashMap<String, String> linkedHashMap, String str2) {
        return webServer(str, linkedHashMap, InternetConfig.defaultConfig(), str2);
    }

    public static ResponseEntity webServer(String str, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig, String str2) {
        internetConfig.setRequest_type(3);
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setUrl(str);
        responseEntity.setParams(linkedHashMap);
        responseEntity.setKey(internetConfig.getKey());
        if (internetConfig.isSave() && !Handler_Network.isNetworkAvailable(Ioc.getIoc().getApplication())) {
            Ioc.getIoc().getLogger().e("无法连接到网络 将获取离线数据");
            String urlCache = HttpCache.getUrlCache(str, linkedHashMap);
            if (urlCache != null) {
                responseEntity.setContent(urlCache, false);
                responseEntity.setStatus(0);
                return responseEntity;
            }
        }
        try {
            internetConfig.setMethod(str2);
            HttpURLConnection defaultHttpClient = getDefaultHttpClient(str, internetConfig);
            defaultHttpClient.setDoOutput(true);
            defaultHttpClient.setDoInput(true);
            defaultHttpClient.connect();
            OutputStream outputStream = defaultHttpClient.getOutputStream();
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap<>();
            }
            outputStream.write(getXml(linkedHashMap, str2, internetConfig.getName_space()).replace(" standalone='yes' ", "").getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            InputStream inputStream = defaultHttpClient.getInputStream();
            getCookies(internetConfig, responseEntity, defaultHttpClient);
            responseEntity.setContent(XMLtoJsonUtil.XMLtoJson(inputStreamToString(inputStream, internetConfig.getCharset()), str2, internetConfig.getCharset()), internetConfig.isSave());
            responseEntity.setKey(internetConfig.getKey());
            responseEntity.setConfig(internetConfig);
            defaultHttpClient.disconnect();
            responseEntity.setStatus(0);
            if (responseEntity.getContentAsString().length() == 0) {
                responseEntity.setStatus(1);
            }
            return responseEntity;
        } catch (Exception e) {
            responseEntity.setStatus(1);
            e.printStackTrace();
            return responseEntity;
        }
    }

    public static void ajaxWebServer(String str, String str2, AjaxCallBack ajaxCallBack) {
        InternetConfig internetConfig = new InternetConfig();
        internetConfig.setMethod(str2);
        internetConfig.setRequest_type(3);
        ajaxWebServer(str, str2, (LinkedHashMap<String, String>) null, internetConfig, ajaxCallBack);
    }

    public static void ajaxWebServer(String str, String str2, InternetConfig internetConfig, AjaxCallBack ajaxCallBack) {
        ajaxWebServer(str, str2, (LinkedHashMap<String, String>) null, internetConfig, ajaxCallBack);
    }

    public static void ajaxWebServer(String str, String str2, LinkedHashMap<String, String> linkedHashMap, AjaxCallBack ajaxCallBack) {
        InternetConfig defaultConfig = InternetConfig.defaultConfig();
        defaultConfig.setMethod(str2);
        defaultConfig.setRequest_type(3);
        ajaxWebServer(str, str2, linkedHashMap, defaultConfig, ajaxCallBack);
    }

    public static void ajaxWebServer(String str, String str2, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig, AjaxCallBack ajaxCallBack) {
        if (internetConfig == null) {
            internetConfig = InternetConfig.defaultConfig();
        }
        internetConfig.setMethod(str2);
        internetConfig.setRequest_type(3);
        new Thread(new AjaxTask(str, linkedHashMap, internetConfig, ajaxCallBack)).start();
    }

    public static void ajaxWebServer(String str, String str2, LinkedHashMap<String, String> linkedHashMap, AjaxTimeCallBack ajaxTimeCallBack) {
        InternetConfig defaultConfig = InternetConfig.defaultConfig();
        defaultConfig.setMethod(str2);
        defaultConfig.setRequest_type(3);
        new Thread(new TimeTask(str, linkedHashMap, defaultConfig, ajaxTimeCallBack)).start();
    }

    public static void ajaxWebServer(String str, String str2, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig, AjaxTimeCallBack ajaxTimeCallBack) {
        if (internetConfig == null) {
            internetConfig = InternetConfig.defaultConfig();
        }
        internetConfig.setMethod(str2);
        internetConfig.setRequest_type(3);
        new Thread(new TimeTask(str, linkedHashMap, internetConfig, ajaxTimeCallBack)).start();
    }

    public static void ajaxGet(String str, AjaxCallBack ajaxCallBack) {
        ajaxGet(str, (LinkedHashMap<String, String>) null, InternetConfig.defaultConfig(), ajaxCallBack);
    }

    public static void ajaxGet(String str, InternetConfig internetConfig, AjaxCallBack ajaxCallBack) {
        ajaxGet(str, (LinkedHashMap<String, String>) null, internetConfig, ajaxCallBack);
    }

    public static void ajaxGet(String str, LinkedHashMap<String, String> linkedHashMap, AjaxCallBack ajaxCallBack) {
        ajaxGet(str, linkedHashMap, InternetConfig.defaultConfig(), ajaxCallBack);
    }

    public static void ajaxGet(String str, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig, AjaxCallBack ajaxCallBack) {
        if (internetConfig == null) {
            internetConfig = InternetConfig.defaultConfig();
        }
        internetConfig.setRequest_type(1);
        new Thread(new AjaxTask(str, linkedHashMap, internetConfig, ajaxCallBack)).start();
    }

    public static void ajaxGet(String str, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig, AjaxTimeCallBack ajaxTimeCallBack) {
        if (internetConfig == null) {
            internetConfig = InternetConfig.defaultConfig();
        }
        internetConfig.setRequest_type(1);
        new Thread(new TimeTask(str, linkedHashMap, internetConfig, ajaxTimeCallBack)).start();
    }

    /* loaded from: classes.dex */
    static class AjaxTask extends basicRunable implements Runnable {
        private AjaxCallBack mCallBack;
        private Handler mHandler = new Handler() { // from class: com.android.pc.ioc.internet.FastHttp.AjaxTask.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (AjaxTask.this.mCallBack.stop()) {
                    return;
                }
                AjaxTask.this.mCallBack.callBack((ResponseEntity) message.obj);
            }
        };

        public AjaxTask(String str, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig, AjaxCallBack ajaxCallBack) {
            this.mCallBack = ajaxCallBack;
            this.internetConfig = internetConfig;
            this.url = str;
            this.params = linkedHashMap;
        }

        @Override // java.lang.Runnable
        public void run() {
            Message message = new Message();
            int request_type = this.internetConfig.getRequest_type();
            if (request_type == 0) {
                message.obj = FastHttp.post(this.url, this.params, this.internetConfig);
            } else if (request_type == 1) {
                message.obj = FastHttp.get(this.url, this.params, this.internetConfig);
            } else if (request_type == 3) {
                message.obj = FastHttp.webServer(this.url, this.params, this.internetConfig, this.internetConfig.getMethod());
            } else if (request_type == 4) {
                message.obj = FastHttp.formProgress(this.url, this.params, this.internetConfig.getFiles(), this.internetConfig, this.internetConfig.getProgress());
            }
            this.mHandler.sendMessage(message);
        }
    }

    /* loaded from: classes.dex */
    static class basicRunable {
        InternetConfig internetConfig;
        LinkedHashMap<String, String> params;
        String url;

        basicRunable() {
        }
    }

    /* loaded from: classes.dex */
    static class TimeTask extends basicRunable implements Runnable {
        private AjaxTimeCallBack mCallBack;
        private Handler mHandler = new Handler() { // from class: com.android.pc.ioc.internet.FastHttp.TimeTask.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                TimeTask.this.mCallBack.callBack((ResponseEntity) message.obj);
            }
        };

        public TimeTask(String str, LinkedHashMap<String, String> linkedHashMap, InternetConfig internetConfig, AjaxTimeCallBack ajaxTimeCallBack) {
            this.mCallBack = ajaxTimeCallBack;
            this.internetConfig = internetConfig;
            this.url = str;
            this.params = linkedHashMap;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (this.mCallBack.getIsContinue()) {
                Message message = new Message();
                int request_type = this.internetConfig.getRequest_type();
                if (request_type == 0) {
                    message.obj = FastHttp.post(this.url, this.params, this.internetConfig);
                } else if (request_type == 1) {
                    message.obj = FastHttp.get(this.url, this.params, this.internetConfig);
                } else if (request_type == 3) {
                    message.obj = FastHttp.webServer(this.url, this.params, this.internetConfig, this.internetConfig.getMethod());
                }
                this.mHandler.sendMessage(message);
                try {
                    Thread.sleep(this.internetConfig.getTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static HttpURLConnection getDefaultHttpClient(String str, InternetConfig internetConfig) throws ProtocolException, MalformedURLException, IOException {
        String str2;
        if (internetConfig.isHttps()) {
            SSLSocketFactoryEx sSLSocketFactoryEx = null;
            try {
                sSLSocketFactoryEx = new SSLSocketFactoryEx(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            SSLContext sSLContext = sSLSocketFactoryEx.sslContext;
            if (sSLContext != null) {
                HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
            }
            HttpsURLConnection.setDefaultHostnameVerifier(hnv);
        }
        System.out.println(str);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        if (internetConfig.getFiles() != null) {
            httpURLConnection.setFixedLengthStreamingMode((int) internetConfig.getAll_length());
        }
        httpURLConnection.setConnectTimeout(internetConfig.getTimeout());
        httpURLConnection.setReadTimeout(internetConfig.getTimeout());
        if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
            httpURLConnection.setRequestProperty(Headers.CONNECTION, "close");
        }
        if (internetConfig.getRequest_type() == 1) {
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charsert", internetConfig.getCharset());
            httpURLConnection.setRequestProperty("Content-Type", internetConfig.getContent_type_web());
        } else if (internetConfig.getRequest_type() == 0) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", internetConfig.getContent_type_web());
        } else if (internetConfig.getRequest_type() == 3) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Charsert", internetConfig.getCharset());
            httpURLConnection.setRequestProperty("Content-Type", InternetConfig.content_type_xml);
            httpURLConnection.setRequestProperty("SOAPAction", internetConfig.getName_space() + internetConfig.getMethod());
            httpURLConnection.setRequestProperty("method", internetConfig.getMethod());
        } else if (internetConfig.getRequest_type() == 4) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("connection", "keep-alive");
            httpURLConnection.setRequestProperty("Charsert", internetConfig.getCharset());
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
        }
        httpURLConnection.setRequestProperty(HttpHeader.USER_AGENT, InternetConfig.UA);
        if (internetConfig.isCookies() && (str2 = cookies) != null) {
            httpURLConnection.setRequestProperty("cookie", str2);
        }
        if (internetConfig.getHead() != null) {
            HashMap<String, Object> head = internetConfig.getHead();
            for (String str3 : head.keySet()) {
                httpURLConnection.setRequestProperty(str3, head.get(str3).toString());
            }
        }
        return httpURLConnection;
    }

    public static String inputStreamToString(InputStream inputStream, String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuffer.append(readLine);
            } else {
                return stringBuffer.toString();
            }
        }
    }

    private static String getXml(LinkedHashMap<String, String> linkedHashMap, String str, String str2) {
        XmlSerializer newSerializer = Xml.newSerializer();
        StringWriter stringWriter = new StringWriter();
        try {
            newSerializer.setOutput(stringWriter);
            newSerializer.startDocument("UTF-8", true);
            newSerializer.startTag("", "soap:Envelope");
            newSerializer.attribute("", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            newSerializer.attribute("", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
            newSerializer.attribute("", "xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
            newSerializer.startTag("", "soap:Body");
            newSerializer.startTag("", str);
            newSerializer.attribute("", "xmlns", str2);
            for (String str3 : linkedHashMap.keySet()) {
                newSerializer.startTag("", str3);
                newSerializer.text(linkedHashMap.get(str3).toString());
                newSerializer.endTag("", str3);
            }
            newSerializer.endTag("", str);
            newSerializer.endTag("", "soap:Body");
            newSerializer.endTag("", "soap:Envelope");
            newSerializer.endDocument();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        }
        return stringWriter.getBuffer().toString();
    }

    /* loaded from: classes.dex */
    static class MyHostnameVerifier implements HostnameVerifier {
        MyHostnameVerifier() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            Ioc.getIoc().getLogger().w("Warning: URL Host: " + str + " vs. " + sSLSession.getPeerHost());
            return true;
        }
    }

    private static void getCookies(InternetConfig internetConfig, ResponseEntity responseEntity, HttpURLConnection httpURLConnection) {
        if (internetConfig.isCookies()) {
            for (Map.Entry<String, List<String>> entry : httpURLConnection.getHeaderFields().entrySet()) {
                String key = entry.getKey();
                if (key != null && key.equalsIgnoreCase("Set-Cookie")) {
                    Iterator<String> it = entry.getValue().iterator();
                    while (it.hasNext()) {
                        for (String str : it.next().split(";")) {
                            String[] split = str.split("=");
                            if (split.length > 1) {
                                responseEntity.cookie(split[0], split[1]);
                            }
                        }
                    }
                }
            }
        }
    }

    private static int getOrtherLength(LinkedHashMap<String, String> linkedHashMap, HashMap<String, File> hashMap, InternetConfig internetConfig) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append(LINEND);
            sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Content-Type: text/plain; charset=");
            sb2.append(internetConfig.getCharset());
            sb2.append(LINEND);
            sb.append(sb2.toString());
            sb.append("Content-Transfer-Encoding: 8bit\r\n");
            sb.append(LINEND);
            sb.append(entry.getValue());
            sb.append(LINEND);
        }
        long length = 0 + sb.toString().getBytes().length;
        if (hashMap != null) {
            for (Map.Entry<String, File> entry2 : hashMap.entrySet()) {
                if (entry2.getValue().exists()) {
                    long length2 = length + entry2.getValue().length();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("--");
                    sb3.append(BOUNDARY);
                    sb3.append(LINEND);
                    sb3.append("Content-Disposition: form-data; name=\"" + entry2.getKey() + "\"; filename=\"" + entry2.getValue().getName() + "\"" + LINEND);
                    sb3.append("Content-Type: image/pjpeg; \r\n");
                    sb3.append(LINEND);
                    length = length2 + ((long) sb3.toString().getBytes().length) + ((long) LINEND.getBytes().length);
                }
            }
            length += ("--" + BOUNDARY + "--" + LINEND).getBytes().length;
        }
        return (int) length;
    }
}
