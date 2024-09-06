package com.netease.nimlib.amazonaws.logging;

import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.log.b;

/* loaded from: classes.dex */
public class NimS3Log implements Log {
    private LogFactory.Level level = null;
    private final String tag;

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public boolean isDebugEnabled() {
        return true;
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public boolean isErrorEnabled() {
        return true;
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public boolean isInfoEnabled() {
        return true;
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public boolean isTraceEnabled() {
        return true;
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public boolean isWarnEnabled() {
        return true;
    }

    public NimS3Log(String str) {
        this.tag = String.format("NimS3Log-%s", str);
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void trace(Object obj) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.TRACE.getValue()) {
            b.b(this.tag, obj.toString());
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void trace(Object obj, Throwable th) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.TRACE.getValue()) {
            b.a(this.tag, obj.toString(), th);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void debug(Object obj) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.DEBUG.getValue()) {
            b.c(this.tag, obj.toString());
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void debug(Object obj, Throwable th) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.DEBUG.getValue()) {
            b.b(this.tag, obj.toString(), th);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void info(Object obj) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.INFO.getValue()) {
            b.d(this.tag, obj.toString());
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void info(Object obj, Throwable th) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.INFO.getValue()) {
            b.c(this.tag, obj.toString(), th);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void warn(Object obj) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.WARN.getValue()) {
            b.e(this.tag, obj.toString());
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void warn(Object obj, Throwable th) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.WARN.getValue()) {
            b.d(this.tag, obj.toString(), th);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void error(Object obj) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.ERROR.getValue()) {
            b.f(this.tag, obj.toString());
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void error(Object obj, Throwable th) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.ERROR.getValue()) {
            b.e(this.tag, obj.toString(), th);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void setLevel(LogFactory.Level level) {
        this.level = level;
    }

    private LogFactory.Level getLevel() {
        LogFactory.Level level = this.level;
        return level != null ? level : LogFactory.getLevel();
    }
}
