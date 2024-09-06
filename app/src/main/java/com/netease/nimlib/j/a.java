package com.netease.nimlib.j;

import android.text.TextUtils;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.msg.model.CustomMessageConfig;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.MemberPushOption;
import com.netease.nimlib.sdk.msg.model.MessageRobotInfo;
import com.netease.nimlib.sdk.msg.model.MsgThreadOption;
import com.netease.nimlib.sdk.msg.model.NIMAntiSpamOption;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.ac;
import com.netease.nimlib.session.g;
import com.netease.nimlib.session.j;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: MessageConvert.java */
/* loaded from: classes.dex */
public class a {
    public static String a(IMMessage iMMessage) {
        return j.a(a((IMMessageImpl) iMMessage));
    }

    public static IMMessageImpl a(String str) {
        c b = b(str);
        if (b == null) {
            return null;
        }
        return g.a(b, false, false);
    }

    private static HashMap<Integer, Object> a(IMMessageImpl iMMessageImpl) {
        HashMap<Integer, Object> hashMap = new HashMap<>();
        hashMap.put(0, Integer.valueOf(iMMessageImpl.getSessionType().getValue()));
        hashMap.put(1, iMMessageImpl.getSessionId());
        hashMap.put(2, iMMessageImpl.getFromAccount());
        hashMap.put(4, Integer.valueOf(iMMessageImpl.getFromClientType()));
        if (iMMessageImpl.getSessionId().equals(com.netease.nimlib.c.n())) {
            hashMap.put(5, com.netease.nimlib.push.b.c());
        }
        String fromNick = iMMessageImpl.getFromNick();
        if (!TextUtils.isEmpty(fromNick)) {
            hashMap.put(6, fromNick);
        }
        hashMap.put(7, Long.valueOf(iMMessageImpl.getTime()));
        hashMap.put(8, Integer.valueOf(iMMessageImpl.getMsgType().getValue()));
        String content = iMMessageImpl.getContent();
        if (!TextUtils.isEmpty(content)) {
            hashMap.put(9, content);
        }
        String attachStr = iMMessageImpl.getAttachStr(true);
        if (!TextUtils.isEmpty(attachStr)) {
            hashMap.put(10, attachStr);
        }
        hashMap.put(11, iMMessageImpl.getUuid());
        hashMap.put(12, Long.valueOf(iMMessageImpl.getServerId()));
        String remoteExtensionStr = iMMessageImpl.getRemoteExtensionStr();
        if (!TextUtils.isEmpty(remoteExtensionStr)) {
            hashMap.put(15, remoteExtensionStr);
        }
        String pushPayloadStr = iMMessageImpl.getPushPayloadStr();
        if (!TextUtils.isEmpty(pushPayloadStr)) {
            hashMap.put(16, pushPayloadStr);
        }
        String pushContent = iMMessageImpl.getPushContent();
        if (!TextUtils.isEmpty(pushContent)) {
            hashMap.put(17, pushContent);
        }
        MemberPushOption memberPushOption = iMMessageImpl.getMemberPushOption();
        if (memberPushOption != null) {
            List<String> forcePushList = memberPushOption.getForcePushList();
            hashMap.put(18, forcePushList == null ? "#%@all@%#" : j.e(forcePushList));
            hashMap.put(19, memberPushOption.getForcePushContent());
            hashMap.put(20, Integer.valueOf(memberPushOption.isForcePush() ? 1 : 0));
        }
        NIMAntiSpamOption nIMAntiSpamOption = iMMessageImpl.getNIMAntiSpamOption();
        if (nIMAntiSpamOption != null) {
            if (!TextUtils.isEmpty(nIMAntiSpamOption.content)) {
                hashMap.put(21, 1);
                hashMap.put(22, nIMAntiSpamOption.content);
            }
            if (!TextUtils.isEmpty(nIMAntiSpamOption.antiSpamConfigId)) {
                hashMap.put(23, nIMAntiSpamOption.antiSpamConfigId);
            }
            hashMap.put(25, Integer.valueOf(nIMAntiSpamOption.enable ? 1 : 0));
        }
        hashMap.put(106, Integer.valueOf(iMMessageImpl.isInBlackList() ? 1 : 0));
        CustomMessageConfig config = iMMessageImpl.getConfig();
        if (config != null) {
            hashMap.put(107, Integer.valueOf(config.enablePush ? 1 : 0));
            hashMap.put(109, Integer.valueOf(config.enableUnreadCount ? 1 : 0));
            hashMap.put(110, Integer.valueOf(config.enablePushNick ? 1 : 0));
        }
        hashMap.put(28, Integer.valueOf(iMMessageImpl.isSessionUpdate() ? 1 : 0));
        MsgThreadOption threadOption = iMMessageImpl.getThreadOption();
        if (!iMMessageImpl.isThread()) {
            hashMap.put(29, threadOption.getReplyMsgFromAccount());
            hashMap.put(30, threadOption.getReplyMsgToAccount());
            hashMap.put(31, Long.valueOf(threadOption.getReplyMsgTime()));
            hashMap.put(32, Long.valueOf(threadOption.getReplyMsgIdServer()));
            hashMap.put(33, threadOption.getReplyMsgIdClient());
            hashMap.put(34, threadOption.getThreadMsgFromAccount());
            hashMap.put(35, threadOption.getThreadMsgToAccount());
            hashMap.put(36, Long.valueOf(threadOption.getThreadMsgTime()));
            hashMap.put(37, Long.valueOf(threadOption.getThreadMsgIdServer()));
            hashMap.put(38, threadOption.getThreadMsgIdClient());
        }
        hashMap.put(39, Integer.valueOf(iMMessageImpl.isDeleted() ? 1 : 0));
        String callbackExtension = iMMessageImpl.getCallbackExtension();
        if (!TextUtils.isEmpty(callbackExtension)) {
            hashMap.put(40, callbackExtension);
        }
        int subtype = iMMessageImpl.getSubtype();
        if (subtype > 0) {
            hashMap.put(41, Integer.valueOf(subtype));
        }
        String yidunAntiCheating = iMMessageImpl.getYidunAntiCheating();
        if (!TextUtils.isEmpty(yidunAntiCheating)) {
            hashMap.put(42, yidunAntiCheating);
        }
        String env = iMMessageImpl.getEnv();
        if (!TextUtils.isEmpty(env)) {
            hashMap.put(43, env);
        }
        String yidunAntiSpamExt = iMMessageImpl.getYidunAntiSpamExt();
        if (!TextUtils.isEmpty(yidunAntiSpamExt)) {
            hashMap.put(44, yidunAntiSpamExt);
        }
        String yidunAntiSpamRes = iMMessageImpl.getYidunAntiSpamRes();
        if (!TextUtils.isEmpty(yidunAntiSpamRes)) {
            hashMap.put(45, yidunAntiSpamRes);
        }
        ac timeConsumingStatistics = iMMessageImpl.getTimeConsumingStatistics();
        if (timeConsumingStatistics != null) {
            hashMap.put(46, timeConsumingStatistics.d().toString());
        }
        MessageRobotInfo robotInfo = iMMessageImpl.getRobotInfo();
        if (robotInfo != null) {
            hashMap.put(47, robotInfo.getFunction());
            hashMap.put(48, robotInfo.getTopic());
            hashMap.put(49, robotInfo.getCustomContent());
            hashMap.put(50, robotInfo.getAccount());
        }
        return hashMap;
    }

    public static c b(String str) {
        try {
            c cVar = new c();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                cVar.a(Integer.valueOf(next).intValue(), String.valueOf(jSONObject.get(next)));
            }
            return cVar;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
