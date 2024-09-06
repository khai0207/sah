package com.netease.nimlib.chatroom;

import com.netease.nimlib.sdk.chatroom.model.GetMessagesByTagsParam;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import java.util.List;
import org.json.JSONArray;

/* compiled from: ChatroomProtocolTransfer.java */
/* loaded from: classes.dex */
public class k {
    public static com.netease.nimlib.push.packet.b.c a(GetMessagesByTagsParam getMessagesByTagsParam) {
        com.netease.nimlib.push.packet.b.c cVar = new com.netease.nimlib.push.packet.b.c();
        if (getMessagesByTagsParam == null) {
            return cVar;
        }
        JSONArray jSONArray = new JSONArray();
        List<String> tags = getMessagesByTagsParam.getTags();
        if (com.netease.nimlib.o.f.d(tags)) {
            for (String str : tags) {
                if (str != null) {
                    jSONArray.put(str);
                }
            }
        }
        cVar.a(1, jSONArray.toString());
        JSONArray jSONArray2 = new JSONArray();
        List<MsgTypeEnum> types = getMessagesByTagsParam.getTypes();
        if (com.netease.nimlib.o.f.d(types)) {
            for (MsgTypeEnum msgTypeEnum : types) {
                if (msgTypeEnum != null) {
                    jSONArray2.put(msgTypeEnum.getValue());
                }
            }
        }
        cVar.a(2, jSONArray2.toString());
        Long fromTime = getMessagesByTagsParam.getFromTime();
        if (fromTime != null) {
            cVar.a(3, fromTime.longValue());
        }
        Long toTime = getMessagesByTagsParam.getToTime();
        if (toTime != null) {
            cVar.a(4, toTime.longValue());
        }
        Integer limit = getMessagesByTagsParam.getLimit();
        if (limit != null) {
            cVar.a(5, limit.intValue());
        }
        Boolean reverse = getMessagesByTagsParam.getReverse();
        if (reverse != null) {
            cVar.a(6, reverse.booleanValue() ? 1 : 0);
        }
        return cVar;
    }
}
