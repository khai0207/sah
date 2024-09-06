package com.netease.nimlib.session;

import android.util.SparseArray;
import com.netease.nimlib.sdk.msg.attachment.AudioAttachment;
import com.netease.nimlib.sdk.msg.attachment.FileAttachment;
import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.attachment.LocationAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachmentParser;
import com.netease.nimlib.sdk.msg.attachment.NetCallAttachment;
import com.netease.nimlib.sdk.msg.attachment.NotificationAttachment;
import com.netease.nimlib.sdk.msg.attachment.VideoAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.NotificationType;
import com.netease.nimlib.sdk.robot.model.RobotAttachment;
import com.netease.nimlib.sdk.team.model.DismissAttachment;
import com.netease.nimlib.sdk.team.model.LeaveTeamAttachment;
import com.netease.nimlib.sdk.team.model.MemberChangeAttachment;
import com.netease.nimlib.sdk.team.model.MuteMemberAttachment;
import com.netease.nimlib.sdk.team.model.UpdateTeamAttachment;
import org.json.JSONObject;

/* compiled from: MsgAttachmentCreator.java */
/* loaded from: classes.dex */
public class i {
    private final SparseArray<MsgAttachmentParser> a = new SparseArray<>();

    public static i a() {
        return d.a().c();
    }

    public i() {
        a(MsgTypeEnum.image.getValue());
        a(MsgTypeEnum.audio.getValue());
        a(MsgTypeEnum.video.getValue());
        a(MsgTypeEnum.location.getValue());
        a(MsgTypeEnum.file.getValue());
        a(MsgTypeEnum.robot.getValue());
        a(MsgTypeEnum.nrtc_netcall.getValue());
        a(MsgTypeEnum.notification.getValue(), new b(null));
    }

    public void a(int i, MsgAttachmentParser msgAttachmentParser) {
        synchronized (this.a) {
            this.a.put(i, msgAttachmentParser);
        }
    }

    public MsgAttachment a(int i, String str) {
        MsgAttachmentParser b2 = b(i);
        MsgAttachment msgAttachment = null;
        if (b2 != null) {
            try {
                msgAttachment = b2.parse(str);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return msgAttachment == null ? com.netease.nimlib.plugin.c.a().a(i, str) : msgAttachment;
    }

    private void a(int i) {
        a(i, new a(i));
    }

    private MsgAttachmentParser b(int i) {
        MsgAttachmentParser msgAttachmentParser;
        synchronized (this.a) {
            msgAttachmentParser = this.a.get(i);
        }
        return msgAttachmentParser;
    }

    /* compiled from: MsgAttachmentCreator.java */
    /* loaded from: classes.dex */
    private static class a implements MsgAttachmentParser {
        private int a;

        a(int i) {
            this.a = i;
        }

        @Override // com.netease.nimlib.sdk.msg.attachment.MsgAttachmentParser
        public MsgAttachment parse(String str) {
            int i = this.a;
            if (i == 1) {
                return new ImageAttachment(str);
            }
            if (i == 2) {
                return new AudioAttachment(str);
            }
            if (i == 3) {
                return new VideoAttachment(str);
            }
            if (i == 4) {
                return new LocationAttachment(str);
            }
            if (i == 6) {
                return new FileAttachment(str);
            }
            if (i == 11) {
                return new RobotAttachment(str);
            }
            if (i != 12) {
                return null;
            }
            return NetCallAttachment.fromJson(str);
        }
    }

    /* compiled from: MsgAttachmentCreator.java */
    /* loaded from: classes.dex */
    private static class b implements MsgAttachmentParser {
        private b() {
        }

        /* synthetic */ b(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.netease.nimlib.sdk.msg.attachment.MsgAttachmentParser
        public MsgAttachment parse(String str) {
            NotificationAttachment memberChangeAttachment;
            NotificationAttachment notificationAttachment = null;
            try {
                JSONObject jSONObject = new JSONObject(str);
                NotificationType typeOfValue = NotificationType.typeOfValue(com.netease.nimlib.o.k.a(jSONObject, "id"));
                switch (AnonymousClass1.a[typeOfValue.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                        memberChangeAttachment = new MemberChangeAttachment();
                        notificationAttachment = memberChangeAttachment;
                        break;
                    case 15:
                    case 16:
                        memberChangeAttachment = new MuteMemberAttachment();
                        notificationAttachment = memberChangeAttachment;
                        break;
                    case 17:
                    case 18:
                        memberChangeAttachment = new DismissAttachment();
                        notificationAttachment = memberChangeAttachment;
                        break;
                    case 19:
                    case 20:
                        memberChangeAttachment = new LeaveTeamAttachment();
                        notificationAttachment = memberChangeAttachment;
                        break;
                    case 21:
                    case 22:
                        memberChangeAttachment = new UpdateTeamAttachment();
                        notificationAttachment = memberChangeAttachment;
                        break;
                }
                if (notificationAttachment != null) {
                    notificationAttachment.setType(typeOfValue);
                    notificationAttachment.parse(jSONObject.getJSONObject("data"));
                }
            } catch (Exception e) {
                com.netease.nimlib.log.b.d("Attach", "parse attachment error: " + e);
            }
            return notificationAttachment;
        }
    }

    /* compiled from: MsgAttachmentCreator.java */
    /* renamed from: com.netease.nimlib.session.i$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[NotificationType.values().length];
            a = iArr;
            try {
                iArr[NotificationType.InviteMember.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[NotificationType.PassTeamApply.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[NotificationType.AcceptInvite.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[NotificationType.TransferOwner.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[NotificationType.AddTeamManager.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[NotificationType.RemoveTeamManager.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[NotificationType.KickMember.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[NotificationType.SUPER_TEAM_CHANGE_OWNER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[NotificationType.SUPER_TEAM_ADD_MANAGER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[NotificationType.SUPER_TEAM_REMOVE_MANAGER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[NotificationType.SUPER_TEAM_INVITE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[NotificationType.SUPER_TEAM_KICK.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                a[NotificationType.SUPER_TEAM_APPLY_PASS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                a[NotificationType.SUPER_TEAM_INVITE_ACCEPT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                a[NotificationType.MuteTeamMember.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                a[NotificationType.SUPER_TEAM_MUTE_TLIST.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                a[NotificationType.DismissTeam.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                a[NotificationType.SUPER_TEAM_DISMISS.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                a[NotificationType.LeaveTeam.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                a[NotificationType.SUPER_TEAM_LEAVE.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                a[NotificationType.UpdateTeam.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                a[NotificationType.SUPER_TEAM_UPDATE_T_INFO.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
        }
    }
}
