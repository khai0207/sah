package com.android.pc.util;

import android.content.SharedPreferences;
import android.util.Base64;
import com.android.pc.ioc.app.Ioc;
import com.netease.nimlib.amazonaws.services.s3.AmazonS3Client;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Handler_SharedPreferences {
    public static final int BOOLEAN = 2;
    public static final int INT = 1;
    public static final int STRING = 0;

    public static void WriteSharedPreferences(String str, ThreeMap threeMap) {
        if (threeMap == null) {
            return;
        }
        SharedPreferences.Editor edit = Ioc.getIoc().getApplication().getSharedPreferences(str, 0).edit();
        threeMap.addValueInEditor(edit);
        edit.commit();
    }

    public static void ClearSharedPreferences(String str) {
        SharedPreferences.Editor edit = Ioc.getIoc().getApplication().getSharedPreferences(str, 0).edit();
        edit.clear();
        edit.commit();
    }

    public static void removeSharedPreferences(String str, String str2) {
        SharedPreferences.Editor edit = Ioc.getIoc().getApplication().getSharedPreferences(str, 0).edit();
        edit.remove(str2);
        edit.commit();
    }

    public static SharedPreferences ReadSharedPreferences(String str) {
        return Ioc.getIoc().getApplication().getSharedPreferences(str, 0);
    }

    public static HashMap<String, Object> getAllByBasesName(String str) {
        return (HashMap) Ioc.getIoc().getApplication().getSharedPreferences(str, 0).getAll();
    }

    public static <T> T getValueByName(String str, String str2, int i) {
        SharedPreferences sharedPreferences = Ioc.getIoc().getApplication().getSharedPreferences(str, 0);
        if (i == 0) {
            return (T) sharedPreferences.getString(str2, "");
        }
        if (i == 1) {
            return (T) Integer.valueOf(sharedPreferences.getInt(str2, 0));
        }
        if (i != 2) {
            return null;
        }
        return (T) Boolean.valueOf(sharedPreferences.getBoolean(str2, false));
    }

    public static void WriteSharedPreferences(String str, String str2, Object obj) {
        if (str2 == null || obj == null) {
            return;
        }
        SharedPreferences.Editor edit = Ioc.getIoc().getApplication().getSharedPreferences(str, 0).edit();
        if (obj instanceof Integer) {
            edit.putInt(str2, Integer.parseInt(obj.toString()));
        } else if (obj instanceof Long) {
            edit.putLong(str2, Long.parseLong(obj.toString()));
        } else if (obj instanceof Boolean) {
            edit.putBoolean(str2, Boolean.parseBoolean(obj.toString()));
        } else if (obj instanceof String) {
            edit.putString(str2, obj.toString());
        } else if (obj instanceof Float) {
            edit.putFloat(str2, Float.parseFloat(obj.toString()));
        }
        edit.commit();
    }

    public static void saveObject(String str, String str2, Object obj) {
        SharedPreferences sharedPreferences = Ioc.getIoc().getApplication().getSharedPreferences(str, 0);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
            String str3 = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(str2, str3);
            edit.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T readObject(String str, String str2) {
        return (T) readObject(Ioc.getIoc().getApplication().getSharedPreferences(str, 0), str2);
    }

    public static <T> T readObject(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(str, "");
        if (string == "") {
            return null;
        }
        try {
            return (T) new ObjectInputStream(new ByteArrayInputStream(Base64.decode(string.getBytes(), 0))).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> HashMap<String, T> readObject(String str) {
        return readObject(Ioc.getIoc().getApplication().getSharedPreferences(str, 0));
    }

    public static <T> HashMap<String, T> readObject(SharedPreferences sharedPreferences) {
        HashMap hashMap = (HashMap) sharedPreferences.getAll();
        AmazonS3Client.AnonymousClass1 anonymousClass1 = (HashMap<String, T>) new HashMap();
        for (String str : hashMap.keySet()) {
            try {
                anonymousClass1.put(str, new ObjectInputStream(new ByteArrayInputStream(Base64.decode(((String) hashMap.get(str)).getBytes(), 0))).readObject());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return anonymousClass1;
    }
}
