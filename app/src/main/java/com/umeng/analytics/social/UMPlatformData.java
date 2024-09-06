package com.umeng.analytics.social;

import android.text.TextUtils;
import java.util.Locale;

/* loaded from: classes.dex */
public class UMPlatformData {
    private UMedia a;
    private String b;
    private String c = "";
    private String d;
    private GENDER e;

    /* loaded from: classes.dex */
    public enum UMedia {
        SINA_WEIBO { // from class: com.umeng.analytics.social.UMPlatformData.UMedia.1
            @Override // java.lang.Enum
            public String toString() {
                return "sina";
            }
        },
        TENCENT_WEIBO { // from class: com.umeng.analytics.social.UMPlatformData.UMedia.2
            @Override // java.lang.Enum
            public String toString() {
                return "tencent";
            }
        },
        TENCENT_QZONE { // from class: com.umeng.analytics.social.UMPlatformData.UMedia.3
            @Override // java.lang.Enum
            public String toString() {
                return "qzone";
            }
        },
        TENCENT_QQ { // from class: com.umeng.analytics.social.UMPlatformData.UMedia.4
            @Override // java.lang.Enum
            public String toString() {
                return "qq";
            }
        },
        WEIXIN_FRIENDS { // from class: com.umeng.analytics.social.UMPlatformData.UMedia.5
            @Override // java.lang.Enum
            public String toString() {
                return "wxsesion";
            }
        },
        WEIXIN_CIRCLE { // from class: com.umeng.analytics.social.UMPlatformData.UMedia.6
            @Override // java.lang.Enum
            public String toString() {
                return "wxtimeline";
            }
        },
        RENREN { // from class: com.umeng.analytics.social.UMPlatformData.UMedia.7
            @Override // java.lang.Enum
            public String toString() {
                return "renren";
            }
        },
        DOUBAN { // from class: com.umeng.analytics.social.UMPlatformData.UMedia.8
            @Override // java.lang.Enum
            public String toString() {
                return "douban";
            }
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'MALE' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* loaded from: classes.dex */
    public static class GENDER {
        public static final GENDER FEMALE;
        public static final GENDER MALE;
        private static final /* synthetic */ GENDER[] a;
        public int value;

        public static GENDER valueOf(String str) {
            return (GENDER) Enum.valueOf(GENDER.class, str);
        }

        public static GENDER[] values() {
            return (GENDER[]) a.clone();
        }

        static {
            int i = 0;
            MALE = new GENDER("MALE", i, i) { // from class: com.umeng.analytics.social.UMPlatformData.GENDER.1
                @Override // java.lang.Enum
                public String toString() {
                    return String.format(Locale.US, "Male:%d", Integer.valueOf(this.value));
                }
            };
            int i2 = 1;
            GENDER gender = new GENDER("FEMALE", i2, i2) { // from class: com.umeng.analytics.social.UMPlatformData.GENDER.2
                @Override // java.lang.Enum
                public String toString() {
                    return String.format(Locale.US, "Female:%d", Integer.valueOf(this.value));
                }
            };
            FEMALE = gender;
            a = new GENDER[]{MALE, gender};
        }

        private GENDER(String str, int i, int i2) {
            this.value = i2;
        }
    }

    public UMPlatformData(UMedia uMedia, String str) {
        this.b = "";
        if (uMedia == null || TextUtils.isEmpty(str)) {
            b.b(com.umeng.analytics.a.e, "parameter is not valid");
        } else {
            this.a = uMedia;
            this.b = str;
        }
    }

    public String getWeiboId() {
        return this.c;
    }

    public void setWeiboId(String str) {
        this.c = str;
    }

    public UMedia getMeida() {
        return this.a;
    }

    public String getUsid() {
        return this.b;
    }

    public String getName() {
        return this.d;
    }

    public void setName(String str) {
        this.d = str;
    }

    public GENDER getGender() {
        return this.e;
    }

    public void setGender(GENDER gender) {
        this.e = gender;
    }

    public boolean isValid() {
        return (this.a == null || TextUtils.isEmpty(this.b)) ? false : true;
    }

    public String toString() {
        return "UMPlatformData [meida=" + this.a + ", usid=" + this.b + ", weiboId=" + this.c + ", name=" + this.d + ", gender=" + this.e + "]";
    }
}
