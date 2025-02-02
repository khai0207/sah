package com.umeng.analytics;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MobclickAgentJSInterface {
    private Context a;

    public MobclickAgentJSInterface(Context context, WebView webView) {
        this.a = context;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new a(null));
    }

    public MobclickAgentJSInterface(Context context, WebView webView, WebChromeClient webChromeClient) {
        this.a = context;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new a(webChromeClient));
    }

    /* loaded from: classes.dex */
    final class a extends WebChromeClient {
        WebChromeClient a;
        private final String c = "ekv";
        private final String d = NotificationCompat.CATEGORY_EVENT;

        public a(WebChromeClient webChromeClient) {
            this.a = null;
            if (webChromeClient == null) {
                this.a = new WebChromeClient();
            } else {
                this.a = webChromeClient;
            }
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            if ("ekv".equals(str2)) {
                try {
                    JSONObject jSONObject = new JSONObject(str3);
                    HashMap hashMap = new HashMap();
                    String str4 = (String) jSONObject.remove("id");
                    int intValue = jSONObject.isNull("duration") ? 0 : ((Integer) jSONObject.remove("duration")).intValue();
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        hashMap.put(next, jSONObject.getString(next));
                    }
                    MobclickAgent.getAgent().a(MobclickAgentJSInterface.this.a, str4, hashMap, intValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (NotificationCompat.CATEGORY_EVENT.equals(str2)) {
                try {
                    JSONObject jSONObject2 = new JSONObject(str3);
                    String optString = jSONObject2.optString("label");
                    if ("".equals(optString)) {
                        optString = null;
                    }
                    MobclickAgent.getAgent().a(MobclickAgentJSInterface.this.a, jSONObject2.getString("tag"), optString, jSONObject2.optInt("duration"), 1);
                } catch (Exception unused) {
                }
            } else {
                return this.a.onJsPrompt(webView, str, str2, str3, jsPromptResult);
            }
            jsPromptResult.confirm();
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onCloseWindow(WebView webView) {
            this.a.onCloseWindow(webView);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            return this.a.onCreateWindow(webView, z, z2, message);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            return this.a.onJsAlert(webView, str, str2, jsResult);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
            return this.a.onJsBeforeUnload(webView, str, str2, jsResult);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            return this.a.onJsConfirm(webView, str, str2, jsResult);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            this.a.onProgressChanged(webView, i);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedIcon(WebView webView, Bitmap bitmap) {
            this.a.onReceivedIcon(webView, bitmap);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            this.a.onReceivedTitle(webView, str);
        }

        @Override // android.webkit.WebChromeClient
        public void onRequestFocus(WebView webView) {
            this.a.onRequestFocus(webView);
        }
    }
}
