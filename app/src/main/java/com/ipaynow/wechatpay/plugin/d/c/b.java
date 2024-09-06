package com.ipaynow.wechatpay.plugin.d.c;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import com.ipaynow.wechatpay.plugin.view.IpaynowLoading;

/* loaded from: classes.dex */
public final class b extends AsyncTask {
    private com.ipaynow.wechatpay.plugin.f.a.a R;
    private com.ipaynow.wechatpay.plugin.d.c.c.a U;
    private int W;
    private ProgressDialog S = null;
    private IpaynowLoading T = null;
    private int V = 1;

    public b(com.ipaynow.wechatpay.plugin.f.a.a aVar, int i) {
        this.R = null;
        this.U = null;
        this.R = aVar;
        this.W = i;
        this.U = new a(this);
    }

    public final void a(ProgressDialog progressDialog) {
        this.S = progressDialog;
    }

    public final void a(IpaynowLoading ipaynowLoading) {
        this.T = ipaynowLoading;
    }

    public final void b(String str) {
        publishProgress(str);
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ Object doInBackground(Object... objArr) {
        return this.U.a(this.W, (String[]) objArr);
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(Object obj) {
        com.ipaynow.wechatpay.plugin.d.c.a.a aVar = (com.ipaynow.wechatpay.plugin.d.c.a.a) obj;
        if (aVar == null) {
            try {
                Log.i("ipaynow", "空指针-IpaynowPluginTask-59");
                aVar = new com.ipaynow.wechatpay.plugin.d.c.a.a();
            } catch (Exception e) {
                e.printStackTrace();
                Thread.currentThread();
                com.ipaynow.wechatpay.plugin.d.b.a.a(e);
                return;
            }
        }
        super.onPostExecute(aVar);
        if (aVar.W == 0) {
            aVar.X = this.V;
            aVar.W = this.W;
        }
        if (this.R != null) {
            this.R.f(aVar);
        }
    }

    @Override // android.os.AsyncTask
    protected final void onPreExecute() {
        super.onPreExecute();
        ProgressDialog progressDialog = this.S;
        if (progressDialog != null && !progressDialog.isShowing()) {
            this.S.show();
            return;
        }
        IpaynowLoading ipaynowLoading = this.T;
        if (ipaynowLoading != null) {
            ipaynowLoading.show();
        }
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onProgressUpdate(Object... objArr) {
        String[] strArr = (String[]) objArr;
        ProgressDialog progressDialog = this.S;
        if (progressDialog != null) {
            progressDialog.setMessage(strArr[0]);
        }
        super.onProgressUpdate(strArr);
    }
}
