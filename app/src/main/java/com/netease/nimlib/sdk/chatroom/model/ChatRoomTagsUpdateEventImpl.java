package com.netease.nimlib.sdk.chatroom.model;

import com.netease.nimlib.log.b;
import com.netease.nimlib.o.w;
import com.netease.nimlib.push.packet.b.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class ChatRoomTagsUpdateEventImpl implements ChatRoomTagsUpdateEvent {
    private static final String TAG = "ChatRoomTagsUpdateEventImpl";
    private final String roomId;
    private final List<String> tags = new ArrayList();

    public ChatRoomTagsUpdateEventImpl(String str, c cVar) {
        this.roomId = str;
        String c = cVar.c(1);
        if (w.b((CharSequence) c)) {
            JSONArray jSONArray = null;
            try {
                jSONArray = new JSONArray(c);
            } catch (Throwable unused) {
                b.e(TAG, "the format of tags is unexpected. " + c);
            }
            if (jSONArray == null) {
                return;
            }
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                String optString = jSONArray.optString(i);
                if (optString != null) {
                    this.tags.add(optString);
                }
            }
        }
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomTagsUpdateEvent
    public String getRoomId() {
        return this.roomId;
    }

    @Override // com.netease.nimlib.sdk.chatroom.model.ChatRoomTagsUpdateEvent
    public List<String> getTags() {
        return this.tags;
    }

    public String toString() {
        return "ChatRoomTagsUpdateEventImpl{roomId='" + this.roomId + "', tags=" + this.tags + '}';
    }
}
