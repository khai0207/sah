package com.netease.nimlib.search.a;

import com.netease.nimlib.log.b;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.search.model.MsgIndexRecord;
import com.netease.nimlib.search.model.NIMIndexRecord;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: MsgIndexHelper.java */
/* loaded from: classes.dex */
public class a {
    private static final int[] a = {SessionTypeEnum.P2P.getValue(), SessionTypeEnum.Team.getValue(), SessionTypeEnum.SUPER_TEAM.getValue()};
    private static final int[] b = {MsgTypeEnum.text.getValue(), MsgTypeEnum.custom.getValue()};

    public static String a(SessionTypeEnum sessionTypeEnum, String str) {
        return sessionTypeEnum.getValue() + "_" + str;
    }

    public static String a(IMMessageImpl iMMessageImpl) {
        return iMMessageImpl.getSessionType().getValue() + "_" + iMMessageImpl.getSessionId();
    }

    public static SessionTypeEnum a(String str) {
        int indexOf = str.indexOf("_");
        if (indexOf == -1) {
            return null;
        }
        try {
            return SessionTypeEnum.typeOfValue(Integer.parseInt(str.substring(0, indexOf)));
        } catch (Throwable th) {
            th.printStackTrace();
            b.e("MsgIndexHelper", "getSessionTypeFromNIMRecordId error, e=" + th.getMessage(), th);
            return null;
        }
    }

    public static String b(String str) {
        int indexOf = str.indexOf("_");
        if (indexOf == -1) {
            return null;
        }
        try {
            return str.substring(indexOf + 1);
        } catch (Throwable th) {
            th.printStackTrace();
            b.e("MsgIndexHelper", "getSessionIdFromNIMRecordId error, e=" + th.getMessage(), th);
            return null;
        }
    }

    public static NIMIndexRecord b(IMMessageImpl iMMessageImpl) {
        NIMIndexRecord nIMIndexRecord = new NIMIndexRecord();
        nIMIndexRecord.type = NIMIndexRecord.TYPE_MSG;
        nIMIndexRecord.subtype = iMMessageImpl.getMsgType().getValue();
        nIMIndexRecord.dataid = iMMessageImpl.getMessageId();
        nIMIndexRecord.id = a(iMMessageImpl);
        nIMIndexRecord.time = iMMessageImpl.getTime();
        nIMIndexRecord.content = iMMessageImpl.getContent();
        return nIMIndexRecord;
    }

    public static boolean c(IMMessageImpl iMMessageImpl) {
        int value = iMMessageImpl.getSessionType().getValue();
        int i = 0;
        while (true) {
            int[] iArr = a;
            if (i >= iArr.length || iArr[i] == value) {
                break;
            }
            i++;
        }
        if (i == a.length) {
            return false;
        }
        int value2 = iMMessageImpl.getMsgType().getValue();
        int i2 = 0;
        while (true) {
            int[] iArr2 = b;
            if (i2 >= iArr2.length || iArr2[i2] == value2) {
                break;
            }
            i2++;
        }
        return i2 != b.length;
    }

    public static List<NIMIndexRecord> a(long j, long j2) {
        List<IMMessageImpl> queryMessageListInSeqIdRange = MsgDBHelper.queryMessageListInSeqIdRange(j, j2, a, b);
        ArrayList arrayList = null;
        if (queryMessageListInSeqIdRange != null && !queryMessageListInSeqIdRange.isEmpty()) {
            for (IMMessageImpl iMMessageImpl : queryMessageListInSeqIdRange) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(b(iMMessageImpl));
            }
        }
        return arrayList;
    }

    public static List<MsgIndexRecord> a(List<NIMIndexRecord> list, String str) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<NIMIndexRecord> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new MsgIndexRecord(it.next(), str));
        }
        return arrayList;
    }
}
