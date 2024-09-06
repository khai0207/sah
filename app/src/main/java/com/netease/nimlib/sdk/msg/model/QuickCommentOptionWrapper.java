package com.netease.nimlib.sdk.msg.model;

import android.text.TextUtils;
import com.netease.nimlib.log.b;
import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class QuickCommentOptionWrapper implements Serializable {
    private static final String TAG = "QuickCommentOptionWrapper";
    private MessageKey key;
    private boolean modify;
    private ArrayList<QuickCommentOption> quickCommentList;
    private long time;

    public QuickCommentOptionWrapper(MessageKey messageKey, ArrayList<QuickCommentOption> arrayList, boolean z, long j) {
        this.key = messageKey;
        this.modify = z;
        this.time = j;
        this.quickCommentList = arrayList;
    }

    public static QuickCommentOptionWrapper fromProperty(c cVar) {
        return new QuickCommentOptionWrapper(getMessageKey(cVar), getCommentListFromJsonStr(cVar.c(7)), cVar.d(8) == 1, cVar.e(100));
    }

    private static ArrayList<QuickCommentOption> getCommentListFromJsonStr(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            return new ArrayList<>(0);
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            ArrayList<QuickCommentOption> arrayList = new ArrayList<>(length);
            i = 0;
            while (i < length) {
                try {
                    arrayList.add(QuickCommentOption.fromJson((JSONObject) jSONArray.get(i)));
                    i++;
                } catch (JSONException e) {
                    e = e;
                    e.printStackTrace();
                    b.d(TAG, "parse json string err when " + i);
                    return new ArrayList<>(0);
                }
            }
            return arrayList;
        } catch (JSONException e2) {
            e = e2;
            i = 0;
        }
    }

    private static MessageKey getMessageKey(c cVar) {
        return new MessageKey(SessionTypeEnum.typeOfValue(cVar.d(1)), cVar.c(2), cVar.c(3), cVar.e(4), cVar.e(5), cVar.c(6));
    }

    public MessageKey getKey() {
        return this.key;
    }

    public ArrayList<QuickCommentOption> getQuickCommentList() {
        return this.quickCommentList;
    }

    public boolean isModify() {
        return this.modify;
    }

    public long getTime() {
        return this.time;
    }
}
