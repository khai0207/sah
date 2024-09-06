package com.android.pc.ioc.core.kernel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class KernelLang {
    public static final Object NULL_OBJECT = new Object();
    public static final Object[] NULL_OBJECTS = new Object[0];
    public static final String[] NULL_STRINGS = new String[0];

    /* loaded from: classes.dex */
    public static class BreakException extends Exception {
    }

    /* loaded from: classes.dex */
    public interface CallbackBreak<T> {
        void doWith(T t) throws BreakException;
    }

    /* loaded from: classes.dex */
    public interface CallbackTemplate<T> {
        void doWith(T t);
    }

    /* loaded from: classes.dex */
    public interface CloneTemplate<T> extends Cloneable {
        T clone();
    }

    /* loaded from: classes.dex */
    public interface FilterTemplate<T> {
        boolean doWith(T t) throws BreakException;
    }

    public static int min(int i, int i2, int i3) {
        if (i >= i2) {
            i = i2;
        }
        return i < i3 ? i : i3;
    }

    /* loaded from: classes.dex */
    public static class CauseRuntimeException extends RuntimeException {
        public CauseRuntimeException(Throwable th) {
            super(th);
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            Throwable cause = super.getCause();
            while (cause != null && cause != this) {
                if (cause instanceof CauseRuntimeException) {
                    cause = cause.getCause();
                }
            }
            return cause;
        }

        @Override // java.lang.Throwable
        public void printStackTrace() {
            Throwable cause = getCause();
            if (cause != null) {
                cause.printStackTrace();
            }
            super.printStackTrace();
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            Throwable cause = getCause();
            return cause == null ? super.getMessage() : cause.getMessage();
        }
    }

    /* loaded from: classes.dex */
    public static class ObjectTemplate<T> {
        public T object;

        public ObjectTemplate() {
        }

        public ObjectTemplate(T t) {
            this.object = t;
        }
    }

    /* loaded from: classes.dex */
    public static class PropertyFilter {
        private Set<String> excludes;
        private Set<String> includes;
        private String propertyPath = "";

        public boolean isMatch() {
            return true;
        }

        public PropertyFilter newly() {
            PropertyFilter propertyFilter = new PropertyFilter();
            propertyFilter.includes = this.includes;
            propertyFilter.excludes = this.excludes;
            return propertyFilter;
        }

        public void begin() {
            this.propertyPath = "";
        }

        public PropertyFilter inlcude(String... strArr) {
            if (this.includes == null) {
                this.includes = new HashSet();
            }
            KernelArray.copy((Object[]) strArr, (Collection) this.includes);
            return this;
        }

        public PropertyFilter exlcude(String... strArr) {
            if (this.excludes == null) {
                this.excludes = new HashSet();
            }
            KernelArray.copy((Object[]) strArr, (Collection) this.excludes);
            return this;
        }

        public boolean isNonePath() {
            return this.includes == null && this.excludes == null;
        }

        public boolean isMatch(String str) {
            if (!KernelString.isEmpty(str)) {
                if (KernelString.isEmpty(this.propertyPath)) {
                    this.propertyPath = str;
                } else {
                    this.propertyPath += "." + str;
                }
            }
            return isMatch();
        }

        public boolean isMatchPath(String str) {
            this.propertyPath = str;
            return isMatch();
        }

        public boolean isMatchPath(String str, String str2) {
            setPropertyPath(str);
            return isMatch(str2);
        }

        public String getPropertyPath() {
            return this.propertyPath;
        }

        public void setPropertyPath(String str) {
            this.propertyPath = str;
        }
    }
}
