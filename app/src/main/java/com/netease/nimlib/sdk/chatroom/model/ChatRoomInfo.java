package com.netease.nimlib.sdk.chatroom.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.netease.nimlib.session.j;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public class ChatRoomInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<ChatRoomInfo> CREATOR = new Parcelable.Creator<ChatRoomInfo>() { // from class: com.netease.nimlib.sdk.chatroom.model.ChatRoomInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChatRoomInfo createFromParcel(Parcel parcel) {
            return new ChatRoomInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChatRoomInfo[] newArray(int i) {
            return new ChatRoomInfo[i];
        }
    };
    private String announcement;
    private String broadcastUrl;
    private String creator;
    private Map<String, Object> extension;
    private int mute;
    private String name;
    private int onlineUserCount;
    private int queueLevel;
    private String roomId;
    private int validFlag;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ChatRoomInfo() {
    }

    public String getRoomId() {
        return this.roomId;
    }

    public String getName() {
        return this.name;
    }

    public String getAnnouncement() {
        return this.announcement;
    }

    public String getBroadcastUrl() {
        return this.broadcastUrl;
    }

    public String getCreator() {
        return this.creator;
    }

    public boolean isValid() {
        return this.validFlag == 1;
    }

    public Map<String, Object> getExtension() {
        return this.extension;
    }

    public int getOnlineUserCount() {
        return this.onlineUserCount;
    }

    public boolean isMute() {
        return this.mute == 1;
    }

    public void setRoomId(String str) {
        this.roomId = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setAnnouncement(String str) {
        this.announcement = str;
    }

    public void setBroadcastUrl(String str) {
        this.broadcastUrl = str;
    }

    public void setCreator(String str) {
        this.creator = str;
    }

    public void setValidFlag(int i) {
        this.validFlag = i;
    }

    public void setExtension(Map<String, Object> map) {
        this.extension = map;
    }

    public void setOnlineUserCount(int i) {
        this.onlineUserCount = i;
    }

    public void setMute(int i) {
        this.mute = i;
    }

    public void setQueueLevel(int i) {
        this.queueLevel = i;
    }

    public int getQueueLevel() {
        return this.queueLevel;
    }

    protected ChatRoomInfo(Parcel parcel) {
        this.roomId = parcel.readString();
        this.name = parcel.readString();
        this.announcement = parcel.readString();
        this.broadcastUrl = parcel.readString();
        this.creator = parcel.readString();
        this.validFlag = parcel.readInt();
        this.onlineUserCount = parcel.readInt();
        setExtension(j.c(parcel.readString()));
        this.mute = parcel.readInt();
        this.queueLevel = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.roomId);
        parcel.writeString(this.name);
        parcel.writeString(this.announcement);
        parcel.writeString(this.broadcastUrl);
        parcel.writeString(this.creator);
        parcel.writeInt(this.validFlag);
        parcel.writeInt(this.onlineUserCount);
        parcel.writeString(j.a(this.extension));
        parcel.writeInt(this.mute);
        parcel.writeInt(this.queueLevel);
    }
}
