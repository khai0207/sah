package com.kqg.main.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* loaded from: classes.dex */
public class UiHandler extends Handler {
    private int handleId;
    private IHandler handler;

    /* loaded from: classes.dex */
    public interface IHandler {
        void handleMessage(Message message);
    }

    public UiHandler(Looper looper) {
        super(looper);
        this.handleId = 0;
    }

    public void setHandler(IHandler iHandler) {
        Log.v("---handler", iHandler.toString());
        this.handler = iHandler;
    }

    public IHandler getHandler() {
        return this.handler;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        IHandler iHandler = this.handler;
        if (iHandler != null) {
            iHandler.handleMessage(message);
        }
    }
}
