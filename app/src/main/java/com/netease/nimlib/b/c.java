package com.netease.nimlib.b;

import android.text.TextUtils;
import com.netease.nimlib.sdk.msg.model.LocalAntiSpamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LocalAntiSpamManager.java */
/* loaded from: classes.dex */
public class c {
    private static long b;
    private static CountDownLatch c;
    private static List<b> a = new ArrayList();
    private static boolean d = false;

    public static void a(File file) {
        c = new CountDownLatch(1);
        a.clear();
        b = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        try {
            FileReader fileReader = new FileReader(file);
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else {
                            stringBuffer.append(readLine);
                        }
                    } finally {
                    }
                }
                bufferedReader.close();
                fileReader.close();
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String stringBuffer2 = stringBuffer.toString();
        if (TextUtils.isEmpty(stringBuffer2)) {
            return;
        }
        a(stringBuffer2);
    }

    private static void a(String str) {
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("thesaurus");
            if (optJSONArray == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList<Integer> arrayList4 = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject = optJSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    d dVar = new d(jSONObject, arrayList, arrayList2, arrayList3);
                    if (!arrayList4.contains(Integer.valueOf(dVar.a()))) {
                        arrayList4.add(Integer.valueOf(dVar.a()));
                    }
                }
            }
            for (Integer num : arrayList4) {
                if (num.intValue() == 1) {
                    a.add(new b(num.intValue(), arrayList));
                } else if (num.intValue() == 2) {
                    a.add(new b(num.intValue(), arrayList2));
                } else if (num.intValue() == 3) {
                    a.add(new b(num.intValue(), arrayList3));
                }
            }
            c.countDown();
            d = true;
            com.netease.nimlib.log.b.N("load thesaurus cost time = " + (System.currentTimeMillis() - b));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static LocalAntiSpamResult a(String str, String str2) {
        CountDownLatch countDownLatch;
        if (!d && (countDownLatch = c) != null) {
            try {
                countDownLatch.await(100L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Iterator<b> it = a.iterator();
        while (it.hasNext()) {
            LocalAntiSpamResult a2 = it.next().a(str, str2);
            if (a2 != null) {
                return a2;
            }
        }
        return new LocalAntiSpamResult(0, str);
    }
}
