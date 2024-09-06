package org.cocos2dx.lib;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class Cocos2dxHandler extends Handler {
    public static final int HANDLER_LOAD_DATA_WEBVIEW = 6;
    public static final int HANDLER_LOAD_URL_WEBVIEW = 5;
    public static final int HANDLER_SHOW_DIALOG = 1;
    public static final int HANDLER_SHOW_EDITBOX_DIALOG = 2;
    public static final int HANDLER_SHOW_WEBVIEW = 3;
    public static final int HANDLER_VISIBLE_WEBVIEW = 4;
    private WeakReference<Cocos2dxActivity> mActivity;

    public Cocos2dxHandler(Cocos2dxActivity cocos2dxActivity) {
        this.mActivity = new WeakReference<>(cocos2dxActivity);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            showDialog(message);
        } else {
            if (i != 2) {
                return;
            }
            showEditBoxDialog(message);
        }
    }

    private void showDialog(Message message) {
        Cocos2dxActivity cocos2dxActivity = this.mActivity.get();
        DialogMessage dialogMessage = (DialogMessage) message.obj;
        new AlertDialog.Builder(cocos2dxActivity).setTitle(dialogMessage.titile).setMessage(dialogMessage.message).setPositiveButton("Ok", new DialogInterface.OnClickListener() { // from class: org.cocos2dx.lib.Cocos2dxHandler.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).create().show();
    }

    private void showEditBoxDialog(Message message) {
        EditBoxMessage editBoxMessage = (EditBoxMessage) message.obj;
        new Cocos2dxEditBoxDialog(this.mActivity.get(), editBoxMessage.title, editBoxMessage.content, editBoxMessage.inputMode, editBoxMessage.inputFlag, editBoxMessage.returnType, editBoxMessage.maxLength).show();
    }

    private void showWebView(Message message) {
        showWebViewMessage showwebviewmessage = (showWebViewMessage) message.obj;
        zcCocos2dxWebView.create(this.mActivity.get(), showwebviewmessage.x, showwebviewmessage.y, showwebviewmessage.w, showwebviewmessage.h);
    }

    private void visibleWebView(Message message) {
        zcCocos2dxWebView.remove(this.mActivity.get());
    }

    private void loadUrlWebView(Message message) {
        zcCocos2dxWebView.loadUrl(((loadUrlWebViewMessage) message.obj).url);
    }

    private void loadDataWebView(Message message) {
        zcCocos2dxWebView.loadData(((loadDataWebViewMessage) message.obj).data);
    }

    /* loaded from: classes.dex */
    public static class DialogMessage {
        public String message;
        public String titile;

        public DialogMessage(String str, String str2) {
            this.titile = str;
            this.message = str2;
        }
    }

    /* loaded from: classes.dex */
    public static class EditBoxMessage {
        public String content;
        public int inputFlag;
        public int inputMode;
        public int maxLength;
        public int returnType;
        public String title;

        public EditBoxMessage(String str, String str2, int i, int i2, int i3, int i4) {
            this.content = str2;
            this.title = str;
            this.inputMode = i;
            this.inputFlag = i2;
            this.returnType = i3;
            this.maxLength = i4;
        }
    }

    /* loaded from: classes.dex */
    public static class showWebViewMessage {
        public int h;
        public String msg;
        public String title;
        public int w;
        public int x;
        public int y;

        public showWebViewMessage(String str, String str2, int i, int i2, int i3, int i4) {
            this.msg = str;
            this.title = str2;
            this.x = i;
            this.y = i2;
            this.w = i3;
            this.h = i4;
        }
    }

    /* loaded from: classes.dex */
    public static class visibleWebViewMessage {
        public String msg;
        public String title;

        public visibleWebViewMessage(String str, String str2) {
            this.msg = str;
            this.title = str2;
        }
    }

    /* loaded from: classes.dex */
    public static class loadUrlWebViewMessage {
        public String msg;
        public String title;
        public String url;

        public loadUrlWebViewMessage(String str, String str2, String str3) {
            this.msg = str;
            this.title = str2;
            this.url = str3;
        }
    }

    /* loaded from: classes.dex */
    public static class loadDataWebViewMessage {
        public String data;
        public String msg;
        public String title;

        public loadDataWebViewMessage(String str, String str2, String str3) {
            this.msg = str;
            this.title = str2;
            this.data = str3;
        }
    }
}
