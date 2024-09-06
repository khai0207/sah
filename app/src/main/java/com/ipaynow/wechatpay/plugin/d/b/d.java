package com.ipaynow.wechatpay.plugin.d.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class d {
    private ArrayList K = new ArrayList();
    private ArrayList L = new ArrayList();

    public d() {
        this.K.add("android.permission.INTERNET");
        this.K.add("android.permission.ACCESS_NETWORK_STATE");
        this.K.add("android.permission.READ_PHONE_STATE");
        this.K.add("android.permission.ACCESS_WIFI_STATE");
        this.L.add("13");
    }

    public static boolean b(Context context) {
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < installedPackages.size(); i++) {
                if (installedPackages.get(i).packageName.equalsIgnoreCase("com.tencent.mm")) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static d h() {
        d dVar;
        dVar = e.M;
        return dVar;
    }

    public final boolean a(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            Iterator it = this.K.iterator();
            int i = 0;
            while (it.hasNext()) {
                String str = (String) it.next();
                int length = strArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        if (strArr[i2].equalsIgnoreCase(str)) {
                            i++;
                            break;
                        }
                        i2++;
                    }
                }
            }
            return i == this.K.size();
        } catch (Exception unused) {
            return false;
        }
    }
}
