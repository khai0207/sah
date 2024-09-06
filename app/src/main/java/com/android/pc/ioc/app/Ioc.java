package com.android.pc.ioc.app;

import android.app.Application;
import android.view.View;
import com.android.pc.ioc.core.kernel.KernelObject;
import com.android.pc.ioc.core.kernel.KernelReflect;
import com.android.pc.ioc.db.sqlite.DbUtils;
import com.android.pc.ioc.util.ContextUtils;
import com.android.pc.ioc.util.InjectViewUtils;
import com.android.pc.util.Handler_Properties;
import com.android.pc.util.Logger;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Properties;

/* loaded from: classes.dex */
public class Ioc {
    private static Ioc ioc;
    private Application application;
    private InstrumentationBean instrumentation;
    private Logger logger = null;
    private int mode_w = 480;
    private int mode_h = 800;
    private HashMap<String, DbUtils> dbMap = new HashMap<>();
    private String dbName = "db";
    Thread initThread = new Thread() { // from class: com.android.pc.ioc.app.Ioc.1
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ContextUtils.getFactoryProvider();
        }
    };

    public Application getApplication() {
        return this.application;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public static Ioc getIoc() {
        if (ioc == null) {
            ioc = new Ioc();
        }
        return ioc;
    }

    public void init(Application application) {
        long currentTimeMillis = System.currentTimeMillis();
        this.application = application;
        this.logger = Logger.getLogger("debug");
        Properties loadConfigAssets = Handler_Properties.loadConfigAssets("mvc.properties");
        if (loadConfigAssets != null && loadConfigAssets.containsKey("standard_w")) {
            this.mode_w = Integer.valueOf(loadConfigAssets.get("standard_w").toString()).intValue();
        }
        if (loadConfigAssets != null && loadConfigAssets.containsKey("standard_h")) {
            this.mode_h = Integer.valueOf(loadConfigAssets.get("standard_h").toString()).intValue();
        }
        boolean z = false;
        if (loadConfigAssets != null && loadConfigAssets.containsKey("iscompatible")) {
            z = Boolean.valueOf(loadConfigAssets.get("iscompatible").toString()).booleanValue();
        }
        this.initThread.start();
        InjectViewUtils.setApplication(this.application);
        Object declaredGet = KernelObject.declaredGet(this.application.getBaseContext(), "mMainThread");
        Field declaredField = KernelReflect.declaredField(declaredGet.getClass(), "mInstrumentation");
        this.instrumentation = new InstrumentationBean();
        if (z) {
            KernelObject.copy(KernelReflect.get(declaredGet, declaredField), this.instrumentation);
        }
        KernelReflect.set(declaredGet, declaredField, this.instrumentation);
        this.logger.d("appliaction 加载时间为:" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public int getMode_w() {
        return this.mode_w;
    }

    public void setMode_w(int i) {
        this.mode_w = i;
    }

    public int getMode_h() {
        return this.mode_h;
    }

    public void setMode_h(int i) {
        this.mode_h = i;
    }

    public void keypress(View view, final int i) {
        view.setFocusable(true);
        view.requestFocus();
        new Thread(new Runnable() { // from class: com.android.pc.ioc.app.Ioc.2
            @Override // java.lang.Runnable
            public void run() {
                Ioc.this.instrumentation.sendKeyDownUpSync(i);
            }
        }).start();
    }

    public DbUtils getDb() {
        return getDb(null, this.dbName);
    }

    public DbUtils getDb(String str, String str2) {
        String str3;
        DbUtils dbUtils;
        if (str == null) {
            str3 = str2;
        } else {
            str3 = str + str2;
        }
        if (this.dbMap.containsKey(str3)) {
            return this.dbMap.get(str3);
        }
        if (str == null) {
            dbUtils = DbUtils.create(this.application, str2);
            this.dbMap.put(str2, dbUtils);
        } else {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            DbUtils create = DbUtils.create(this.application, str, str2);
            this.dbMap.put(str + str2, create);
            dbUtils = create;
        }
        dbUtils.configDebug(true);
        dbUtils.configAllowTransaction(true);
        return dbUtils;
    }
}
