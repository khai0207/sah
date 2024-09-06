package com.netease.nimlib.f;

import com.netease.nimlib.push.packet.b.c;
import com.netease.nimlib.sdk.event.model.Event;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: EventImpl.java */
/* loaded from: classes.dex */
public class a extends Event {
    public a(Event event) {
        super(event.getEventType(), event.getEventValue(), event.getExpiry());
        super.setConfig(event.getConfig());
        super.setSyncSelfEnable(event.isSyncSelfEnable());
        super.setBroadcastOnlineOnly(event.isBroadcastOnlineOnly());
    }

    public a(c cVar) {
        a(cVar);
    }

    public void a(c cVar) {
        if (cVar.f(1)) {
            this.eventType = cVar.d(1);
        }
        if (cVar.f(2)) {
            this.eventValue = cVar.d(2);
        }
        if (cVar.f(3)) {
            this.eventId = cVar.c(3);
        }
        if (cVar.f(4)) {
            this.config = cVar.c(4);
        }
        if (cVar.f(5)) {
            this.expiry = cVar.e(5);
        }
        if (cVar.f(10)) {
            this.publishTime = cVar.e(10);
        }
        if (cVar.f(103)) {
            this.publisherAccount = cVar.c(103);
        }
        if (cVar.f(12)) {
            this.publisherClientType = cVar.d(12);
        }
        if (cVar.f(13)) {
            this.nimConfig = cVar.c(13);
        }
        if (cVar.f(14)) {
            this.multiClientConfig = cVar.c(14);
            a();
        }
    }

    private void a() {
        if (this.multiClientConfig == null) {
            return;
        }
        this.multiClientConfigMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(this.multiClientConfig);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String string = jSONObject.getString(next);
                if (string != null) {
                    this.multiClientConfigMap.put(Integer.valueOf(next), string);
                }
            }
        } catch (JSONException e) {
            com.netease.nimlib.log.b.f("EventImpl", "parseMultiClientConfig error: " + e);
        }
    }

    public void a(String str) {
        this.eventId = str;
    }

    public static String a(Event event) {
        return "Event{eventId='" + event.getEventId() + "', eventType=" + event.getEventType() + ", eventValue=" + event.getEventValue() + ", config='" + event.getConfig() + "', expiry=" + event.getExpiry() + ", syncSelfEnable=" + event.isSyncSelfEnable() + ", broadcastOnlineOnly=" + event.isBroadcastOnlineOnly() + ", publisherAccount='" + event.getPublisherAccount() + "', publishTime=" + event.getPublishTime() + ", publisherClientType=" + event.getPublisherClientType() + ", multiClientConfig='" + event.getMultiClientConfig() + "', nimConfig='" + event.getNimConfig() + "'}";
    }
}
