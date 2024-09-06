package com.netease.nimlib.sdk.msg.attachment;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class NetCallAttachment implements MsgAttachment {
    private long channelId;
    private List<Duration> durations;
    private int status;
    private int type;

    public int getType() {
        return this.type;
    }

    public long getChannelId() {
        return this.channelId;
    }

    public int getStatus() {
        return this.status;
    }

    public List<Duration> getDurations() {
        return this.durations;
    }

    @Override // com.netease.nimlib.sdk.msg.attachment.MsgAttachment
    public String toJson(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.type);
            jSONObject.put("channelId", this.channelId);
            jSONObject.put("status", this.status);
            JSONArray jSONArray = new JSONArray();
            Iterator<Duration> it = this.durations.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toJson());
            }
            jSONObject.put("durations", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static NetCallAttachment fromJson(String str) {
        NetCallAttachment netCallAttachment = new NetCallAttachment();
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("type");
            long optLong = jSONObject.optLong("channelId");
            int optInt2 = jSONObject.optInt("status");
            LinkedList linkedList = new LinkedList();
            JSONArray optJSONArray = jSONObject.optJSONArray("durations");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        linkedList.add(Duration.fromJson(optJSONObject));
                    }
                }
            }
            netCallAttachment.type = optInt;
            netCallAttachment.channelId = optLong;
            netCallAttachment.status = optInt2;
            netCallAttachment.durations = linkedList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return netCallAttachment;
    }

    /* loaded from: classes.dex */
    public static final class NetCallAttachmentBuilder {
        private long channelId;
        private List<Duration> durations = new LinkedList();
        private int status;
        private int type;

        public NetCallAttachmentBuilder withType(int i) {
            this.type = i;
            return this;
        }

        public NetCallAttachmentBuilder withChannelId(long j) {
            this.channelId = j;
            return this;
        }

        public NetCallAttachmentBuilder withStatus(int i) {
            this.status = i;
            return this;
        }

        public NetCallAttachmentBuilder withDurations(List<Duration> list) {
            this.durations = list;
            return this;
        }

        public NetCallAttachment build() {
            NetCallAttachment netCallAttachment = new NetCallAttachment();
            netCallAttachment.durations = this.durations;
            netCallAttachment.type = this.type;
            netCallAttachment.channelId = this.channelId;
            netCallAttachment.status = this.status;
            return netCallAttachment;
        }
    }

    /* loaded from: classes.dex */
    public static class Duration implements Serializable {
        private String accid;
        private int duration;

        public String getAccid() {
            return this.accid;
        }

        public int getDuration() {
            return this.duration;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("accid", this.accid);
                jSONObject.put("duration", this.duration);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }

        public static Duration fromJson(JSONObject jSONObject) {
            Duration duration = new Duration();
            duration.accid = jSONObject.optString("accid");
            duration.duration = jSONObject.optInt("duration");
            return duration;
        }
    }
}
