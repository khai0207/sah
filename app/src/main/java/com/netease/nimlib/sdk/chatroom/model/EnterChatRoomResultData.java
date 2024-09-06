package com.netease.nimlib.sdk.chatroom.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.netease.nimlib.sdk.StatusCode;
import java.io.Serializable;

/* loaded from: classes.dex */
public class EnterChatRoomResultData implements Parcelable, Serializable {
    public static final Parcelable.Creator<EnterChatRoomResultData> CREATOR = new Parcelable.Creator<EnterChatRoomResultData>() { // from class: com.netease.nimlib.sdk.chatroom.model.EnterChatRoomResultData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EnterChatRoomResultData createFromParcel(Parcel parcel) {
            return new EnterChatRoomResultData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EnterChatRoomResultData[] newArray(int i) {
            return new EnterChatRoomResultData[i];
        }
    };
    private String account;
    private ChatRoomMember member;
    private int resCode;
    private String roomId;
    private ChatRoomInfo roomInfo;
    private StatusCode status;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public EnterChatRoomResultData(String str, int i, StatusCode statusCode, ChatRoomInfo chatRoomInfo, ChatRoomMember chatRoomMember, String str2) {
        this.roomId = str;
        this.resCode = i;
        this.status = statusCode;
        this.roomInfo = chatRoomInfo;
        this.member = chatRoomMember;
        this.account = str2;
    }

    public String getAccount() {
        return this.account;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public int getResCode() {
        return this.resCode;
    }

    public StatusCode getStatus() {
        return this.status;
    }

    public ChatRoomInfo getRoomInfo() {
        return this.roomInfo;
    }

    public ChatRoomMember getMember() {
        return this.member;
    }

    protected EnterChatRoomResultData(Parcel parcel) {
        this.roomId = parcel.readString();
        this.resCode = parcel.readInt();
        this.status = StatusCode.typeOfValue(parcel.readInt());
        this.roomInfo = (ChatRoomInfo) parcel.readParcelable(getClass().getClassLoader());
        this.member = (ChatRoomMember) parcel.readParcelable(getClass().getClassLoader());
        this.account = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.roomId);
        parcel.writeInt(this.resCode);
        parcel.writeInt(this.status.getValue());
        parcel.writeParcelable(this.roomInfo, i);
        parcel.writeParcelable(this.member, i);
        parcel.writeString(this.account);
    }
}
