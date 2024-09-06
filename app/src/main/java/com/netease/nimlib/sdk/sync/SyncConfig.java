package com.netease.nimlib.sdk.sync;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes.dex */
public class SyncConfig implements Parcelable, Serializable {
    public static final Parcelable.Creator<SyncConfig> CREATOR = new Parcelable.Creator<SyncConfig>() { // from class: com.netease.nimlib.sdk.sync.SyncConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SyncConfig createFromParcel(Parcel parcel) {
            return new SyncConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SyncConfig[] newArray(int i) {
            return new SyncConfig[i];
        }
    };
    private boolean enableSyncSuperTeamMember;
    private boolean enableSyncSuperTeamMemberUserInfo;
    private boolean enableSyncTeamMember;
    private boolean enableSyncTeamMemberUserInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SyncConfig(Builder builder) {
        this.enableSyncTeamMember = true;
        this.enableSyncTeamMemberUserInfo = true;
        this.enableSyncSuperTeamMember = true;
        this.enableSyncSuperTeamMemberUserInfo = true;
        if (builder == null) {
            return;
        }
        this.enableSyncTeamMember = builder.enableSyncTeamMember;
        this.enableSyncTeamMemberUserInfo = builder.enableSyncTeamMemberUserInfo;
        this.enableSyncSuperTeamMember = builder.enableSyncSuperTeamMember;
        this.enableSyncSuperTeamMemberUserInfo = builder.enableSyncSuperTeamMemberUserInfo;
    }

    public boolean isEnableSyncTeamMember() {
        return this.enableSyncTeamMember;
    }

    public boolean isEnableSyncSuperTeamMember() {
        return this.enableSyncSuperTeamMember;
    }

    public boolean isEnableSyncTeamMemberUserInfo() {
        return this.enableSyncTeamMemberUserInfo;
    }

    public boolean isEnableSyncSuperTeamMemberUserInfo() {
        return this.enableSyncSuperTeamMemberUserInfo;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private boolean enableSyncTeamMember = true;
        private boolean enableSyncTeamMemberUserInfo = true;
        private boolean enableSyncSuperTeamMember = true;
        private boolean enableSyncSuperTeamMemberUserInfo = true;

        public Builder setEnableSyncTeamMember(boolean z) {
            this.enableSyncTeamMember = z;
            return this;
        }

        public Builder setEnableSyncTeamMemberUserInfo(boolean z) {
            this.enableSyncTeamMemberUserInfo = z;
            return this;
        }

        public Builder setEnableSyncSuperTeamMember(boolean z) {
            this.enableSyncSuperTeamMember = z;
            return this;
        }

        public Builder setEnableSyncSuperTeamMemberUserInfo(boolean z) {
            this.enableSyncSuperTeamMemberUserInfo = z;
            return this;
        }

        public SyncConfig build() {
            return new SyncConfig(this);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.enableSyncTeamMember ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.enableSyncTeamMemberUserInfo ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.enableSyncSuperTeamMember ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.enableSyncSuperTeamMemberUserInfo ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel parcel) {
        this.enableSyncTeamMember = parcel.readByte() != 0;
        this.enableSyncTeamMemberUserInfo = parcel.readByte() != 0;
        this.enableSyncSuperTeamMember = parcel.readByte() != 0;
        this.enableSyncSuperTeamMemberUserInfo = parcel.readByte() != 0;
    }

    protected SyncConfig(Parcel parcel) {
        this.enableSyncTeamMember = true;
        this.enableSyncTeamMemberUserInfo = true;
        this.enableSyncSuperTeamMember = true;
        this.enableSyncSuperTeamMemberUserInfo = true;
        this.enableSyncTeamMember = parcel.readByte() != 0;
        this.enableSyncTeamMemberUserInfo = parcel.readByte() != 0;
        this.enableSyncSuperTeamMember = parcel.readByte() != 0;
        this.enableSyncSuperTeamMemberUserInfo = parcel.readByte() != 0;
    }
}
