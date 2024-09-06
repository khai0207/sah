package com.netease.nimlib.sdk.chatroom.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.netease.nimlib.sdk.chatroom.constant.MemberType;
import com.netease.nimlib.session.j;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public class ChatRoomMember implements Parcelable, Serializable {
    public static final Parcelable.Creator<ChatRoomMember> CREATOR = new Parcelable.Creator<ChatRoomMember>() { // from class: com.netease.nimlib.sdk.chatroom.model.ChatRoomMember.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChatRoomMember createFromParcel(Parcel parcel) {
            return new ChatRoomMember(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChatRoomMember[] newArray(int i) {
            return new ChatRoomMember[i];
        }
    };
    private String account;
    private String avatar;
    private long enterTime;
    private Map<String, Object> extension;
    private boolean inBlackList;
    private boolean isMuted;
    private boolean isOnline;
    private boolean isTempMuted;
    private boolean isValid;
    private int memberLevel;
    private String nick;
    private String notifyTargetTags;
    private String roomId;
    private String tags;
    private long tempMuteDuration;
    private MemberType type;
    private long updateTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ChatRoomMember() {
    }

    public String getRoomId() {
        return this.roomId;
    }

    public String getAccount() {
        return this.account;
    }

    public MemberType getMemberType() {
        return this.type;
    }

    public int getMemberLevel() {
        return this.memberLevel;
    }

    public String getNick() {
        return this.nick;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public Map<String, Object> getExtension() {
        return this.extension;
    }

    public long getEnterTime() {
        return this.enterTime;
    }

    public boolean isOnline() {
        return this.isOnline;
    }

    public boolean isInBlackList() {
        return this.inBlackList;
    }

    public boolean isMuted() {
        return this.isMuted;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void setRoomId(String str) {
        this.roomId = str;
    }

    public void setAccount(String str) {
        this.account = str;
    }

    public void setMemberType(MemberType memberType) {
        this.type = memberType;
    }

    public void setMemberLevel(int i) {
        this.memberLevel = i;
    }

    public void setNick(String str) {
        this.nick = str;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public void setExtension(Map<String, Object> map) {
        this.extension = map;
    }

    public void setOnline(boolean z) {
        this.isOnline = z;
    }

    public void setEnterTime(long j) {
        this.enterTime = j;
    }

    public void setInBlackList(boolean z) {
        this.inBlackList = z;
    }

    public void setMuted(boolean z) {
        this.isMuted = z;
    }

    public void setValid(boolean z) {
        this.isValid = z;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long j) {
        this.updateTime = j;
    }

    public boolean isTempMuted() {
        return this.isTempMuted;
    }

    public void setTempMuted(boolean z) {
        this.isTempMuted = z;
    }

    public long getTempMuteDuration() {
        return this.tempMuteDuration;
    }

    public void setTempMuteDuration(long j) {
        this.tempMuteDuration = j;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String str) {
        this.tags = str;
    }

    public String getNotifyTargetTags() {
        return this.notifyTargetTags;
    }

    public void setNotifyTargetTags(String str) {
        this.notifyTargetTags = str;
    }

    protected ChatRoomMember(Parcel parcel) {
        this.roomId = parcel.readString();
        this.account = parcel.readString();
        this.type = MemberType.typeOfValue(parcel.readInt());
        this.memberLevel = parcel.readInt();
        this.nick = parcel.readString();
        this.avatar = parcel.readString();
        setExtension(j.c(parcel.readString()));
        this.isOnline = parcel.readByte() == 1;
        this.inBlackList = parcel.readByte() == 1;
        this.isMuted = parcel.readByte() == 1;
        this.isValid = parcel.readByte() == 1;
        this.enterTime = parcel.readLong();
        this.updateTime = parcel.readLong();
        this.isTempMuted = parcel.readByte() == 1;
        this.tempMuteDuration = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.roomId);
        parcel.writeString(this.account);
        parcel.writeInt(this.type.getValue());
        parcel.writeInt(this.memberLevel);
        parcel.writeString(this.nick);
        parcel.writeString(this.avatar);
        parcel.writeString(j.a(getExtension()));
        parcel.writeByte(this.isOnline ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.inBlackList ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isMuted ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isValid ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.enterTime);
        parcel.writeLong(this.updateTime);
        parcel.writeByte(this.isTempMuted ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.tempMuteDuration);
    }
}
