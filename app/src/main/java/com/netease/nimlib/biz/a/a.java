package com.netease.nimlib.biz.a;

/* compiled from: ISyncService.java */
/* loaded from: classes.dex */
public class a {

    /* compiled from: ISyncService.java */
    /* renamed from: com.netease.nimlib.biz.a.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public enum EnumC0026a {
        MY_INFO(1),
        UNREAD_MESSAGE(2),
        TINFO(3),
        DND_PUSH(4),
        AVCHAT(6),
        ROAMING_MSG(7),
        DELAY_TAG(8),
        BLACK_AND_MUTE(9),
        FREIND_LIST(11),
        FRIEND_INFO(13),
        MSG_READ(14),
        MY_TLIST(15),
        DONNOP_PUSH(16),
        ROAM_DELETE_MSG(17),
        SESSION_ACK_LIST(18),
        ROBOT_LIST(19),
        BROADCAST_MSG(20),
        SIGNALLING_MSG(21),
        SUPER_TINFO(22),
        MY_SUPER_TLIST(23),
        SUPER_ROAMING_MSG(24),
        ROAM_SUPERTEAM_DELETE_MSG(25),
        SUPERTEAM_SESSION_ACK_LIST(26),
        MSG_DELETE_SELF(27),
        STICK_TOP_SESSION(28),
        SESSION_HISTORY_MSGS_DELETE(29),
        YSF_UNREAD_MSG(100);

        private final int B;

        EnumC0026a(int i) {
            this.B = i;
        }

        public int a() {
            return this.B;
        }
    }
}
