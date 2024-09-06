package com.netease.nimlib.amazonaws.logging;

import com.netease.nimlib.amazonaws.logging.LogFactory;

@Deprecated
/* loaded from: classes.dex */
public class ApacheCommonsLogging implements Log {
    private LogFactory.Level level = null;
    private Log log;
    private Class logClass;
    private String logString;

    public ApacheCommonsLogging(Class cls) {
        this.logClass = cls;
        this.log = LogFactory.getLog((Class<?>) cls);
    }

    public ApacheCommonsLogging(String str) {
        this.logString = str;
        this.log = LogFactory.getLog(str);
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public boolean isDebugEnabled() {
        return this.log.isDebugEnabled() && (getLevel() == null || getLevel().getValue() <= LogFactory.Level.DEBUG.getValue());
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public boolean isErrorEnabled() {
        return this.log.isErrorEnabled() && (getLevel() == null || getLevel().getValue() <= LogFactory.Level.ERROR.getValue());
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public boolean isInfoEnabled() {
        return this.log.isInfoEnabled() && (getLevel() == null || getLevel().getValue() <= LogFactory.Level.INFO.getValue());
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public boolean isTraceEnabled() {
        return this.log.isTraceEnabled() && (getLevel() == null || getLevel().getValue() <= LogFactory.Level.TRACE.getValue());
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public boolean isWarnEnabled() {
        return this.log.isWarnEnabled() && (getLevel() == null || getLevel().getValue() <= LogFactory.Level.WARN.getValue());
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void trace(Object obj) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.TRACE.getValue()) {
            this.log.trace(obj);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void trace(Object obj, Throwable th) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.TRACE.getValue()) {
            this.log.trace(obj, th);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void debug(Object obj) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.DEBUG.getValue()) {
            this.log.debug(obj);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void debug(Object obj, Throwable th) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.DEBUG.getValue()) {
            this.log.debug(obj, th);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void info(Object obj) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.INFO.getValue()) {
            this.log.info(obj);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void info(Object obj, Throwable th) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.INFO.getValue()) {
            this.log.info(obj, th);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void warn(Object obj) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.WARN.getValue()) {
            this.log.warn(obj);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void warn(Object obj, Throwable th) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.WARN.getValue()) {
            this.log.warn(obj, th);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void error(Object obj) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.ERROR.getValue()) {
            this.log.error(obj);
        }
    }

    @Override // com.netease.nimlib.amazonaws.logging.Log
    public void error(Object obj, Throwable th) {
        if (getLevel() == null || getLevel().getValue() <= LogFactory.Level.ERROR.getValue()) {
            this.log.error(obj, th);
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
