package u.aly;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.umeng.analytics.AnalyticsConfig;
import java.net.URLEncoder;

/* compiled from: NetworkHelper.java */
/* loaded from: classes.dex */
public class u {
    private String a;
    private String b = "10.0.0.172";
    private int c = 80;
    private Context d;
    private s e;

    public u(Context context) {
        this.d = context;
        this.a = a(context);
    }

    public void a(s sVar) {
        this.e = sVar;
    }

    public byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        int i = 0;
        while (true) {
            if (i >= com.umeng.analytics.a.g.length) {
                break;
            }
            bArr2 = a(bArr, com.umeng.analytics.a.g[i]);
            if (bArr2 != null) {
                s sVar = this.e;
                if (sVar != null) {
                    sVar.c();
                }
            } else {
                s sVar2 = this.e;
                if (sVar2 != null) {
                    sVar2.d();
                }
                i++;
            }
        }
        return bArr2;
    }

    private boolean a() {
        String extraInfo;
        if (this.d.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.d.getPackageName()) != 0) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.d.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() != 1 && (extraInfo = activeNetworkInfo.getExtraInfo()) != null) {
                if (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap")) {
                    if (extraInfo.equals("uniwap")) {
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0124: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:53:0x0124 */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0127  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] a(byte[] r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.u.a(byte[], java.lang.String):byte[]");
    }

    private String a(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Android");
        stringBuffer.append("/");
        stringBuffer.append(com.umeng.analytics.a.c);
        stringBuffer.append(" ");
        try {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(bt.x(context));
            stringBuffer2.append("/");
            stringBuffer2.append(bt.d(context));
            stringBuffer2.append(" ");
            stringBuffer2.append(Build.MODEL);
            stringBuffer2.append("/");
            stringBuffer2.append(Build.VERSION.RELEASE);
            stringBuffer2.append(" ");
            stringBuffer2.append(bu.a(AnalyticsConfig.getAppkey(context)));
            stringBuffer.append(URLEncoder.encode(stringBuffer2.toString(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
