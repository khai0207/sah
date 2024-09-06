package com.netease.nimlib.sdk.chatroom.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes.dex */
public class ChatRoomCdnInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<ChatRoomCdnInfo> CREATOR = new Parcelable.Creator<ChatRoomCdnInfo>() { // from class: com.netease.nimlib.sdk.chatroom.model.ChatRoomCdnInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChatRoomCdnInfo createFromParcel(Parcel parcel) {
            return new ChatRoomCdnInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChatRoomCdnInfo[] newArray(int i) {
            return new ChatRoomCdnInfo[i];
        }
    };
    String[] cdnUrlArray;
    String decryptKey;
    boolean enable;
    long pollingInterval;
    int timeOut;
    long timestamp;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ChatRoomCdnInfo() {
        this.enable = false;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public String[] getCdnUrlArray() {
        return this.cdnUrlArray;
    }

    public void setCdnUrlArray(String[] strArr) {
        this.cdnUrlArray = strArr;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public long getPollingInterval() {
        return this.pollingInterval;
    }

    public void setPollingInterval(long j) {
        this.pollingInterval = j;
    }

    public String getDecryptKey() {
        return this.decryptKey;
    }

    public void setDecryptKey(String str) {
        this.decryptKey = str;
    }

    public int getTimeOut() {
        return this.timeOut;
    }

    public void setTimeOut(int i) {
        this.timeOut = i;
    }

    public ChatRoomCdnInfo deepClone() {
        ChatRoomCdnInfo chatRoomCdnInfo = new ChatRoomCdnInfo();
        chatRoomCdnInfo.setEnable(this.enable);
        chatRoomCdnInfo.setCdnUrlArray(this.cdnUrlArray);
        chatRoomCdnInfo.setTimestamp(this.timestamp);
        chatRoomCdnInfo.setPollingInterval(this.pollingInterval);
        chatRoomCdnInfo.setDecryptKey(this.decryptKey);
        chatRoomCdnInfo.setTimeOut(this.timeOut);
        return chatRoomCdnInfo;
    }

    protected ChatRoomCdnInfo(Parcel parcel) {
        this.enable = false;
        this.enable = parcel.readInt() == 1;
        parcel.readStringArray(this.cdnUrlArray);
        this.timestamp = parcel.readLong();
        this.pollingInterval = parcel.readLong();
        this.decryptKey = parcel.readString();
        this.timeOut = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.enable ? 1 : 0);
        parcel.writeStringArray(this.cdnUrlArray);
        parcel.writeLong(this.timestamp);
        parcel.writeLong(this.pollingInterval);
        parcel.writeString(this.decryptKey);
        parcel.writeInt(this.timeOut);
    }
}
