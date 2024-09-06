package com.iflytek.cloud.a.e;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.b.c;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class a {
    public static Object a = new Object();
    private MSCSessionInfo b = new MSCSessionInfo();

    public static void a(Context context, String str, String str2, com.iflytek.cloud.a.d.a aVar) throws SpeechError, IOException {
        byte[] bArr;
        synchronized (a) {
            String a2 = c.a(context, aVar);
            if (TextUtils.isEmpty(str)) {
                if (context != null) {
                    String a3 = c.a(context);
                    if (!TextUtils.isEmpty(a3)) {
                        bArr = a3.getBytes(aVar.n());
                    }
                }
                bArr = null;
            } else {
                bArr = str.getBytes("utf-8");
            }
            int QMSPLogin = MSC.QMSPLogin(bArr, TextUtils.isEmpty(str2) ? null : str2.getBytes(aVar.n()), a2.getBytes(aVar.n()));
            com.iflytek.cloud.a.g.a.a.a("[MSPLogin]ret:" + QMSPLogin);
            if (QMSPLogin != 0) {
                throw new SpeechError(QMSPLogin);
            }
        }
    }

    public static boolean a() {
        boolean z;
        synchronized (a) {
            z = MSC.QMSPLogOut() == 0;
        }
        return z;
    }

    public synchronized byte[] a(Context context, com.iflytek.cloud.a.d.a aVar) throws SpeechError, UnsupportedEncodingException {
        byte[] QMSPDownloadData;
        synchronized (a) {
            String c = c.c(context, aVar);
            com.iflytek.cloud.a.g.a.a.a("[MSPSession downloadData]enter time:" + System.currentTimeMillis());
            QMSPDownloadData = MSC.QMSPDownloadData(c.getBytes(aVar.n()), this.b);
            StringBuilder sb = new StringBuilder();
            sb.append("[MSPSession downloadData]leavel:");
            sb.append(this.b.errorcode);
            sb.append(",data len = ");
            sb.append(QMSPDownloadData == null ? 0 : QMSPDownloadData.length);
            com.iflytek.cloud.a.g.a.a.a(sb.toString());
            int i = this.b.errorcode;
            if (i != 0 || QMSPDownloadData == null) {
                throw new SpeechError(i);
            }
        }
        return QMSPDownloadData;
    }

    public synchronized byte[] a(Context context, com.iflytek.cloud.a.d.a aVar, String str) throws SpeechError, UnsupportedEncodingException {
        byte[] QMSPSearch;
        synchronized (a) {
            String c = c.c(context, aVar);
            com.iflytek.cloud.a.g.a.a.a("[MSPSession searchResult]enter time:" + System.currentTimeMillis());
            QMSPSearch = MSC.QMSPSearch(c.getBytes(aVar.n()), str.getBytes("utf-8"), this.b);
            StringBuilder sb = new StringBuilder();
            sb.append("[QMSPSearch searchResult]leavel:");
            sb.append(this.b.errorcode);
            sb.append(",data len = ");
            sb.append(QMSPSearch == null ? 0 : QMSPSearch.length);
            com.iflytek.cloud.a.g.a.a.a(sb.toString());
            int i = this.b.errorcode;
            if (i != 0 || QMSPSearch == null) {
                throw new SpeechError(i);
            }
        }
        return QMSPSearch;
    }

    public synchronized byte[] a(Context context, String str, byte[] bArr, com.iflytek.cloud.a.d.a aVar) throws SpeechError, UnsupportedEncodingException {
        byte[] QMSPUploadData;
        synchronized (a) {
            String c = c.c(context, aVar);
            com.iflytek.cloud.a.g.a.a.a("[MSPSession uploadData]enter time:" + System.currentTimeMillis());
            QMSPUploadData = MSC.QMSPUploadData(str.getBytes(aVar.n()), bArr, bArr.length, c.getBytes("utf-8"), this.b);
            StringBuilder sb = new StringBuilder();
            sb.append("[MSPSession uploaddData]leavel:");
            sb.append(this.b.errorcode);
            sb.append(",data len = ");
            sb.append(QMSPUploadData == null ? 0 : QMSPUploadData.length);
            com.iflytek.cloud.a.g.a.a.a(sb.toString());
            int i = this.b.errorcode;
            if (i != 0 || QMSPUploadData == null) {
                throw new SpeechError(i);
            }
        }
        return QMSPUploadData;
    }
}
