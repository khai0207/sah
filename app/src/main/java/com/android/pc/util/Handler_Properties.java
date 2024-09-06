package com.android.pc.util;

import com.android.pc.ioc.app.Ioc;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/* loaded from: classes.dex */
public class Handler_Properties {
    public static Properties loadProperties(String str, String str2) {
        Properties properties = new Properties();
        try {
            properties.load(Ioc.getIoc().getApplication().getResources().openRawResource(Ioc.getIoc().getApplication().getResources().getIdentifier(str, str2, Ioc.getIoc().getApplication().getPackageName())));
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e.toString());
        }
        return properties;
    }

    public static Properties loadConfig(String str) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(str));
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e.toString());
        }
        return properties;
    }

    public static void saveConfig(String str, Properties properties) {
        try {
            properties.store(new FileOutputStream(str, false), "");
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e.toString());
        }
    }

    public static Properties loadConfigNoDirs(String str) {
        Properties properties = new Properties();
        try {
            properties.load(Ioc.getIoc().getApplication().openFileInput(str));
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e.toString());
        }
        return properties;
    }

    public static void saveConfigNoDirs(String str, Properties properties) {
        try {
            properties.store(Ioc.getIoc().getApplication().openFileOutput(str, 0), "");
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e.toString());
        }
    }

    public static Properties loadConfigAssets(String str) {
        Properties properties = new Properties();
        try {
            properties.load(Ioc.getIoc().getApplication().getAssets().open(str));
        } catch (Exception e) {
            Ioc.getIoc().getLogger().e(e.toString());
        }
        return properties;
    }
}
