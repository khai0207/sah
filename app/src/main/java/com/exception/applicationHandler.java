package com.exception;

import android.app.Application;
import android.content.Context;
import com.bluemobi.exception.ExceptionHandler;

/* loaded from: classes.dex */
public class applicationHandler extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        ExceptionHandler exceptionHandler = ExceptionHandler.getInstance(this);
        ExceptionHandler.setKey("errMsg");
        exceptionHandler.setSkipStatus(new ExceptionHandler.SkipStatus() { // from class: com.exception.applicationHandler.1
            @Override // com.bluemobi.exception.ExceptionHandler.SkipStatus
            public void skep(Context context) {
            }
        });
        exceptionHandler.setSave(true);
        exceptionHandler.setFilename("logs");
        exceptionHandler.setFileDirs("/sdcard/log_log/");
        exceptionHandler.setClazz(Test.class);
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
    }
}
