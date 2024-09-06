package com.netease.nimlib.sdk.event.model;

import com.netease.nimlib.log.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class NimOnlineStateEvent {
    public static final int EVENT_TYPE = NimEventType.ONLINE_STATE.getValue();
    private static final String KEY_NIM_CONFIG = "online";
    public static final int MODIFY_EVENT_CONFIG = 10001;

    public static boolean isOnlineStateEvent(Event event) {
        return event != null && event.getEventType() == EVENT_TYPE;
    }

    public static OnlineStateEventValue getOnlineStateEventValue(Event event) {
        if (isOnlineStateEvent(event)) {
            return OnlineStateEventValue.getOnlineStateEventValue(event.getEventValue());
        }
        return null;
    }

    public static List<Integer> getOnlineClients(Event event) {
        ArrayList arrayList = null;
        try {
            JSONArray optJSONArray = new JSONObject(event.getNimConfig()).optJSONArray(KEY_NIM_CONFIG);
            if (optJSONArray == null) {
                return null;
            }
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    arrayList2.add(Integer.valueOf(optJSONArray.getInt(i)));
                } catch (JSONException e) {
                    e = e;
                    arrayList = arrayList2;
                    b.f("NimOnlineStateEvent", "getOnlineClients error: " + e);
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e2) {
            e = e2;
        }
    }

    /* loaded from: classes.dex */
    public enum OnlineStateEventValue {
        Login(1),
        Logout(2),
        DisConnect(3);

        private final int value;

        OnlineStateEventValue(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static OnlineStateEventValue getOnlineStateEventValue(int i) {
            for (OnlineStateEventValue onlineStateEventValue : values()) {
                if (onlineStateEventValue.getValue() == i) {
                    return onlineStateEventValue;
                }
            }
            return null;
        }
    }
}
