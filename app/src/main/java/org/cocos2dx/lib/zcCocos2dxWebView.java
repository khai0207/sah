package org.cocos2dx.lib;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.netease.nimlib.amazonaws.services.s3.util.Mimetypes;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class zcCocos2dxWebView {
    private static View m_homeView = null;
    private static boolean m_isShow = false;
    private static String m_url = "";
    private static View m_view;
    private static WebView m_webView;

    public static void setWebView(WebView webView) {
        m_webView = webView;
        if (webView == null) {
            return;
        }
        webView.setWebViewClient(new WebViewClient() { // from class: org.cocos2dx.lib.zcCocos2dxWebView.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                webView2.loadUrl(str);
                return true;
            }
        });
    }

    public static void setView(View view) {
        m_view = view;
    }

    public static void setHomeView(View view) {
        m_homeView = view;
    }

    public static boolean isShow() {
        return m_isShow;
    }

    public static WebView create(Context context, int i, int i2, int i3, int i4) {
        WebView webView = m_webView;
        if (webView == null) {
            return null;
        }
        webView.setVisibility(0);
        View view = m_view;
        if (view != null) {
            view.setVisibility(0);
        }
        View view2 = m_homeView;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        setPosition(i, i2, i3, i4);
        m_isShow = true;
        return m_webView;
    }

    public static void loadUrl(String str) {
        m_url = str;
        if (m_webView == null || str == "") {
            return;
        }
        Pattern.compile("/static/notice/notice.html").matcher(str).find();
        m_view.setVisibility(8);
        m_webView.loadUrl(str);
    }

    public static void reload() {
        WebView webView = m_webView;
        if (webView == null) {
            return;
        }
        webView.loadUrl(m_url);
    }

    public static void loadData(String str) {
        WebView webView = m_webView;
        if (webView == null) {
            return;
        }
        webView.loadData(str, Mimetypes.MIMETYPE_HTML, null);
    }

    public static void remove(Context context) {
        WebView webView = m_webView;
        if (webView == null) {
            return;
        }
        webView.setVisibility(8);
        View view = m_view;
        if (view != null) {
            view.setVisibility(8);
        }
        View view2 = m_homeView;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        m_isShow = false;
    }

    public static void setPosition(int i, int i2, int i3, int i4) {
        if (m_webView == null) {
        }
    }
}
