package com.unionpay;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WebViewJavascriptBridge implements Serializable {
    ab _messageHandler;
    Map _messageHandlers = new HashMap();
    Map _responseCallbacks = new HashMap();
    long _uniqueId = 0;
    Activity mContext;
    WebView mWebView;

    public WebViewJavascriptBridge(Activity activity, WebView webView, ab abVar) {
        this.mContext = activity;
        this.mWebView = webView;
        this._messageHandler = abVar;
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.addJavascriptInterface(this, "_WebViewJavascriptBridge");
        byte b = 0;
        this.mWebView.setWebViewClient(new aa(this, b));
        this.mWebView.setWebChromeClient(new z(this, b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _callbackJs(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("responseId", str);
        hashMap.put("responseData", str2);
        _dispatchMessage(hashMap);
    }

    private void _dispatchMessage(Map map) {
        String jSONObject = new JSONObject(map).toString();
        com.unionpay.utils.g.a("test", "sending:" + jSONObject);
        this.mContext.runOnUiThread(new x(this, String.format("javascript:WebViewJavascriptBridge._handleMessageFromJava('%s');", doubleEscapeString(jSONObject))));
    }

    private void _sendData(String str, ac acVar, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("data", str);
        if (acVar != null) {
            StringBuilder sb = new StringBuilder("java_cb_");
            long j = this._uniqueId + 1;
            this._uniqueId = j;
            sb.append(j);
            String sb2 = sb.toString();
            this._responseCallbacks.put(sb2, acVar);
            hashMap.put("callbackId", sb2);
        }
        if (str2 != null) {
            hashMap.put("handlerName", str2);
        }
        _dispatchMessage(hashMap);
    }

    public static String convertStreamToString(InputStream inputStream) {
        String str;
        str = "";
        try {
            Scanner useDelimiter = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            str = useDelimiter.hasNext() ? useDelimiter.next() : "";
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    private String doubleEscapeString(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r").replace("\f", "\\f");
    }

    private void loadWebViewJavascriptBridgeJs(WebView webView) {
        webView.loadUrl("javascript:" + convertStreamToString(getClass().getResourceAsStream("res/webviewjavascriptbridge.js")));
    }

    @JavascriptInterface
    public void _handleMessageFromJs(String str, String str2, String str3, String str4, String str5) {
        ab abVar;
        if (str2 != null) {
            ((ac) this._responseCallbacks.get(str2)).a(str3);
            this._responseCallbacks.remove(str2);
            return;
        }
        y yVar = str4 != null ? new y(this, str4) : null;
        if (str5 != null) {
            abVar = (ab) this._messageHandlers.get(str5);
            if (abVar == null) {
                com.unionpay.utils.g.b("test", "WVJB Warning: No handler for " + str5);
                return;
            }
        } else {
            abVar = this._messageHandler;
        }
        try {
            this.mContext.runOnUiThread(new w(this, abVar, str, yVar));
        } catch (Exception e) {
            com.unionpay.utils.g.b("test", "WebViewJavascriptBridge: WARNING: java handler threw. " + e.getMessage());
        }
    }

    public void callHandler(String str) {
        callHandler(str, null, null);
    }

    public void callHandler(String str, String str2) {
        callHandler(str, str2, null);
    }

    public void callHandler(String str, String str2, ac acVar) {
        _sendData(str2, acVar, str);
    }

    public void registerHandler(String str, ab abVar) {
        this._messageHandlers.put(str, abVar);
    }

    public void send(String str) {
        send(str, null);
    }

    public void send(String str, ac acVar) {
        _sendData(str, acVar, null);
    }
}
