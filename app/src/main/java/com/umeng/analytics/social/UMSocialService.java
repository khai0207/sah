package com.umeng.analytics.social;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class UMSocialService {

    /* loaded from: classes.dex */
    public interface b {
        void a();

        void a(d dVar, UMPlatformData... uMPlatformDataArr);
    }

    private static void a(Context context, b bVar, String str, UMPlatformData... uMPlatformDataArr) {
        if (uMPlatformDataArr != null) {
            try {
                for (UMPlatformData uMPlatformData : uMPlatformDataArr) {
                    if (!uMPlatformData.isValid()) {
                        throw new com.umeng.analytics.social.a("parameter is not valid.");
                    }
                }
            } catch (com.umeng.analytics.social.a e) {
                Log.e(com.umeng.analytics.a.e, "unable send event.", e);
                return;
            } catch (Exception e2) {
                Log.e(com.umeng.analytics.a.e, "", e2);
                return;
            }
        }
        new a(f.a(context, str, uMPlatformDataArr), bVar, uMPlatformDataArr).execute(new Void[0]);
    }

    public static void share(Context context, String str, UMPlatformData... uMPlatformDataArr) {
        a(context, null, str, uMPlatformDataArr);
    }

    public static void share(Context context, UMPlatformData... uMPlatformDataArr) {
        a(context, null, null, uMPlatformDataArr);
    }

    /* loaded from: classes.dex */
    private static class a extends AsyncTask<Void, Void, d> {
        String a;
        String b;
        b c;
        UMPlatformData[] d;

        public a(String[] strArr, b bVar, UMPlatformData[] uMPlatformDataArr) {
            this.a = strArr[0];
            this.b = strArr[1];
            this.c = bVar;
            this.d = uMPlatformDataArr;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            b bVar = this.c;
            if (bVar != null) {
                bVar.a();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d doInBackground(Void... voidArr) {
            String a;
            if (TextUtils.isEmpty(this.b)) {
                a = c.a(this.a);
            } else {
                a = c.a(this.a, this.b);
            }
            try {
                JSONObject jSONObject = new JSONObject(a);
                int optInt = jSONObject.optInt("st");
                if (optInt == 0) {
                    optInt = e.t;
                }
                d dVar = new d(optInt);
                String optString = jSONObject.optString("msg");
                if (!TextUtils.isEmpty(optString)) {
                    dVar.a(optString);
                }
                String optString2 = jSONObject.optString("data");
                if (!TextUtils.isEmpty(optString2)) {
                    dVar.b(optString2);
                }
                return dVar;
            } catch (Exception e) {
                return new d(-99, e);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(d dVar) {
            b bVar = this.c;
            if (bVar != null) {
                bVar.a(dVar, this.d);
            }
        }
    }
}
