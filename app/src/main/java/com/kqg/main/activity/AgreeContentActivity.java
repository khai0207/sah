package com.kqg.main.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import com.alipay.sdk.m.h.c;
import com.kqg.main.base.BaseActivity;
import com.kqg.main.utils.UiUtils;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;

/* loaded from: classes.dex */
public class AgreeContentActivity extends BaseActivity {
    private WebView content_view;
    private Button pwd_agree;
    private Button pwd_close;

    @Override // com.kqg.main.base.BaseActivity
    protected void afterOnCreate() {
        setView("kqg_service_tip");
        Bundle bundle = getIntent().getExtras().getBundle("bundle");
        String string = bundle.getString(Constants.URL_ENCODING);
        String string2 = bundle.getString(c.e);
        this.pwd_close = (Button) getView("pwd_close");
        this.pwd_agree = (Button) getView("pwd_agree");
        WebView webView = (WebView) getView("content_view");
        this.content_view = webView;
        webView.loadUrl(string);
        registOnClicks("click", this.pwd_close);
        registOnClicks("click", this.pwd_agree);
        int i = getResources().getDisplayMetrics().densityDpi;
        if (i == 240) {
            this.content_view.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (i == 160) {
            this.content_view.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else {
            this.content_view.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        }
        this.content_view.setScrollBarStyle(33554432);
        this.content_view.setInitialScale(100);
        ((TextView) getView("title_name")).setText(string2);
    }

    public void click(View view) {
        int id = view.getId();
        if (id == UiUtils.getId("pwd_close")) {
            finish();
        } else if (id == UiUtils.getId("pwd_agree")) {
            finish();
        }
    }
}
