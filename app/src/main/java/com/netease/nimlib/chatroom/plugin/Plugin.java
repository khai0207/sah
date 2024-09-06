package com.netease.nimlib.chatroom.plugin;

import android.content.Context;
import com.netease.nimlib.chatroom.d;
import com.netease.nimlib.i.j;
import com.netease.nimlib.log.b;
import com.netease.nimlib.o.k;
import com.netease.nimlib.plugin.a;
import com.netease.nimlib.plugin.interact.IChatRoomInteract;
import com.netease.nimlib.sdk.chatroom.ChatRoomService;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomNotificationAttachment;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomPartClearAttachment;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomQueueBatchAddAttachment;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomQueueChangeAttachment;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomRecallAttachment;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomRoomMemberInAttachment;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomTempMuteAddAttachment;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomTempMuteRemoveAttachment;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;
import com.netease.nimlib.sdk.msg.attachment.NotificationAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.NotificationType;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Plugin implements a {
    @Override // com.netease.nimlib.plugin.a
    public Map<Class<? extends com.netease.nimlib.biz.e.a>, com.netease.nimlib.biz.c.a> b() {
        return null;
    }

    @Override // com.netease.nimlib.plugin.a
    public void c(Context context) {
    }

    @Override // com.netease.nimlib.plugin.a
    public Map<Class<?>, Class<? extends j>> a() {
        HashMap hashMap = new HashMap(1);
        hashMap.put(ChatRoomService.class, com.netease.nimlib.chatroom.e.a.class);
        return hashMap;
    }

    @Override // com.netease.nimlib.plugin.a
    public MsgAttachment a(int i, JSONObject jSONObject) {
        NotificationAttachment chatRoomRoomMemberInAttachment;
        NotificationAttachment notificationAttachment = null;
        if (i == MsgTypeEnum.notification.getValue()) {
            try {
                NotificationType typeOfValue = NotificationType.typeOfValue(k.a(jSONObject, "id"));
                switch (AnonymousClass1.a[typeOfValue.ordinal()]) {
                    case 1:
                        chatRoomRoomMemberInAttachment = new ChatRoomRoomMemberInAttachment();
                        notificationAttachment = chatRoomRoomMemberInAttachment;
                        break;
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
                    case 15:
                        chatRoomRoomMemberInAttachment = new ChatRoomQueueChangeAttachment();
                        notificationAttachment = chatRoomRoomMemberInAttachment;
                        break;
                    case 16:
                        chatRoomRoomMemberInAttachment = new ChatRoomPartClearAttachment();
                        notificationAttachment = chatRoomRoomMemberInAttachment;
                        break;
                    case 17:
                    case 18:
                    case 19:
                        chatRoomRoomMemberInAttachment = new ChatRoomNotificationAttachment();
                        notificationAttachment = chatRoomRoomMemberInAttachment;
                        break;
                    case 20:
                        chatRoomRoomMemberInAttachment = new ChatRoomTempMuteAddAttachment();
                        notificationAttachment = chatRoomRoomMemberInAttachment;
                        break;
                    case 21:
                        chatRoomRoomMemberInAttachment = new ChatRoomTempMuteRemoveAttachment();
                        notificationAttachment = chatRoomRoomMemberInAttachment;
                        break;
                    case 22:
                        chatRoomRoomMemberInAttachment = new ChatRoomRecallAttachment();
                        notificationAttachment = chatRoomRoomMemberInAttachment;
                        break;
                    case 23:
                        chatRoomRoomMemberInAttachment = new ChatRoomQueueBatchAddAttachment();
                        notificationAttachment = chatRoomRoomMemberInAttachment;
                        break;
                }
                if (notificationAttachment != null) {
                    notificationAttachment.setType(typeOfValue);
                    notificationAttachment.parse(jSONObject.getJSONObject("data"));
                }
            } catch (Exception e) {
                b.d("Attach", "parse attachment error: " + e);
            }
        }
        return notificationAttachment;
    }

    /* renamed from: com.netease.nimlib.chatroom.plugin.Plugin$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[NotificationType.values().length];
            a = iArr;
            try {
                iArr[NotificationType.ChatRoomMemberIn.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[NotificationType.ChatRoomMemberExit.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[NotificationType.ChatRoomMemberBlackAdd.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[NotificationType.ChatRoomMemberBlackRemove.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[NotificationType.ChatRoomMemberMuteAdd.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[NotificationType.ChatRoomMemberMuteRemove.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[NotificationType.ChatRoomManagerAdd.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[NotificationType.ChatRoomManagerRemove.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[NotificationType.ChatRoomCommonAdd.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[NotificationType.ChatRoomCommonRemove.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[NotificationType.ChatRoomClose.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[NotificationType.ChatRoomInfoUpdated.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                a[NotificationType.ChatRoomMemberKicked.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                a[NotificationType.ChatRoomMyRoomRoleUpdated.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                a[NotificationType.ChatRoomQueueChange.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                a[NotificationType.ChatRoomQueueBatchChange.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                a[NotificationType.ChatRoomRoomMuted.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                a[NotificationType.ChatRoomRoomDeMuted.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                a[NotificationType.ChatRoomTagsUpdate.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                a[NotificationType.ChatRoomMemberTempMuteAdd.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                a[NotificationType.ChatRoomMemberTempMuteRemove.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                a[NotificationType.ChatRoomRecall.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                a[NotificationType.ChatRoomQueueBatchAdd.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    @Override // com.netease.nimlib.plugin.a
    public void a(Context context) {
        com.netease.nimlib.plugin.interact.b.a().a(IChatRoomInteract.class, ChatRoomInteract.class);
        b.z("PluginInteractManager add ChatRoomInteract");
    }

    @Override // com.netease.nimlib.plugin.a
    public void b(Context context) {
        d.e().a(context);
    }

    @Override // com.netease.nimlib.plugin.a
    public void d(Context context) {
        d.e().a(false);
        if (d.e().b() == 0) {
            d.e().a();
            d.e().a(context);
        }
    }
}
