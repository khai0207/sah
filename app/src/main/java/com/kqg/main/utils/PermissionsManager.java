package com.kqg.main.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class PermissionsManager {
    private static final String PACKAGE_URL_SCHEME = "package:";
    private Activity mTargetActivity;

    public abstract void authorized(int i);

    public abstract void ignore();

    public abstract void noAuthorization(int i, String[] strArr);

    public PermissionsManager(Activity activity) {
        this.mTargetActivity = activity;
    }

    public void checkPermissions(int i, String... strArr) {
        if (Build.VERSION.SDK_INT >= 23) {
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                if (ContextCompat.checkSelfPermission(this.mTargetActivity.getApplicationContext(), str) == -1) {
                    arrayList.add(str);
                }
            }
            if (!arrayList.isEmpty()) {
                ActivityCompat.requestPermissions(this.mTargetActivity, (String[]) arrayList.toArray(new String[arrayList.size()]), i);
                return;
            } else {
                authorized(i);
                return;
            }
        }
        ignore();
    }

    public void recheckPermissions(int i, String[] strArr, int[] iArr) {
        for (int i2 : iArr) {
            if (i2 == -1) {
                noAuthorization(i, strArr);
                return;
            }
        }
        authorized(i);
    }

    public static void startAppSettings(Context context) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setFlags(268435456);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + context.getPackageName()));
        context.startActivity(intent);
    }
}
