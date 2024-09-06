package com.iflytek.common.a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/* loaded from: classes.dex */
public class a extends Thread {
    boolean a = true;
    private String b;
    private b c;

    public a(String str, b bVar) {
        this.b = str;
        this.c = bVar;
    }

    private String a(byte[] bArr) {
        String str = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            com.iflytek.common.c.c.a("HttpGetThread", "unzip len " + bArr.length);
            a(byteArrayInputStream, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            String str2 = new String(byteArrayOutputStream.toByteArray());
            try {
                com.iflytek.common.c.c.a("HttpGetThread", "unzip str " + str2);
                byteArrayOutputStream.close();
                byteArrayInputStream.close();
                return str2;
            } catch (Exception e) {
                e = e;
                str = str2;
                com.iflytek.common.c.c.a("HttpGetThread", "unzip error ", e);
                return str;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private static void a(InputStream inputStream, OutputStream outputStream) {
        GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = gZIPInputStream.read(bArr, 0, 1024);
            if (read == -1) {
                gZIPInputStream.close();
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        byte[] bArr;
        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(this.b);
            httpGet.setHeader("Accept-Encoding", "gzip, deflate");
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            if (execute.getStatusLine().getStatusCode() == 200) {
                Header firstHeader = execute.getFirstHeader("Content-Length");
                int parseInt = firstHeader != null ? Integer.parseInt(firstHeader.getValue()) : 0;
                if (parseInt <= 0 || parseInt >= 1048576) {
                    byte[] bArr2 = new byte[1048576];
                    int read = execute.getEntity().getContent().read(bArr2);
                    byte[] bArr3 = new byte[read];
                    System.arraycopy(bArr2, 0, bArr3, 0, read);
                    bArr = bArr3;
                } else {
                    bArr = new byte[parseInt];
                    execute.getEntity().getContent().read(bArr);
                }
                String a = this.a ? a(bArr) : null;
                if (a == null) {
                    a = new String(bArr);
                }
                if (this.c != null) {
                    this.c.a(a, 0);
                    return;
                }
                return;
            }
        } catch (Exception e) {
            com.iflytek.common.c.c.a("HttpGetThread", "Other Exception", e);
        }
        b bVar = this.c;
        if (bVar != null) {
            bVar.a(null, -1);
        }
    }
}
