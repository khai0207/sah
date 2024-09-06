package com.talkingdata.sdk;

/* compiled from: td */
/* loaded from: classes.dex */
public class af {
    String a = "";
    AccountType b = AccountType.ANONYMOUS;
    String c = "";
    Gender d = Gender.UNKNOW;
    int e = 0;

    /* compiled from: td */
    /* loaded from: classes.dex */
    public enum AccountType {
        ANONYMOUS(0),
        REGISTERED(1),
        SINA_WEIBO(2),
        QQ(3),
        QQ_WEIBO(4),
        ND91(5),
        WEIXIN(6),
        TYPE1(11),
        TYPE2(12),
        TYPE3(13),
        TYPE4(14),
        TYPE5(15),
        TYPE6(16),
        TYPE7(17),
        TYPE8(18),
        TYPE9(19),
        TYPE10(20);

        private final int a;

        AccountType(int i) {
            this.a = i;
        }

        public int index() {
            return this.a;
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    public enum Gender {
        UNKNOW(0),
        MALE(1),
        FEMALE(2);

        private final int a;

        Gender(int i) {
            this.a = i;
        }

        public int index() {
            return this.a;
        }
    }
}
