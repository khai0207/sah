package com.unionpay.tsmservice.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.AppID;

/* loaded from: classes.dex */
public class AppDetail implements Parcelable {
    public static final Parcelable.Creator<AppDetail> CREATOR = new Parcelable.Creator<AppDetail>() { // from class: com.unionpay.tsmservice.data.AppDetail.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppDetail createFromParcel(Parcel parcel) {
            return new AppDetail(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppDetail[] newArray(int i) {
            return new AppDetail[i];
        }
    };
    private String mAppApplyId;
    private String mAppDesc;
    private AppID mAppID;
    private String mAppIcon;
    private String mAppName;
    private String mAppProviderAgreement;
    private String mAppProviderLogo;
    private String mAppProviderName;
    private String mApplyMode;
    private long mDownloadTimes;
    private String mPublishData;
    private String mPublishStatus;
    private String mRechargeLowerLimit;
    private String mRechargeMode;
    private String mServicePhone;
    private AppStatus mStatus;
    private String mUpAgreement;

    public AppDetail() {
        this.mAppName = "";
        this.mAppIcon = "";
        this.mAppDesc = "";
        this.mAppProviderLogo = "";
        this.mAppProviderName = "";
        this.mAppProviderAgreement = "";
        this.mUpAgreement = "";
        this.mApplyMode = "";
        this.mServicePhone = "";
        this.mDownloadTimes = 0L;
        this.mPublishData = "";
        this.mPublishStatus = "";
        this.mRechargeMode = "";
        this.mRechargeLowerLimit = "";
    }

    public AppDetail(Parcel parcel) {
        this.mAppName = "";
        this.mAppIcon = "";
        this.mAppDesc = "";
        this.mAppProviderLogo = "";
        this.mAppProviderName = "";
        this.mAppProviderAgreement = "";
        this.mUpAgreement = "";
        this.mApplyMode = "";
        this.mServicePhone = "";
        this.mDownloadTimes = 0L;
        this.mPublishData = "";
        this.mPublishStatus = "";
        this.mRechargeMode = "";
        this.mRechargeLowerLimit = "";
        this.mAppID = (AppID) parcel.readParcelable(AppID.class.getClassLoader());
        this.mAppName = parcel.readString();
        this.mAppIcon = parcel.readString();
        this.mAppDesc = parcel.readString();
        this.mAppProviderLogo = parcel.readString();
        this.mAppProviderName = parcel.readString();
        this.mAppProviderAgreement = parcel.readString();
        this.mUpAgreement = parcel.readString();
        this.mApplyMode = parcel.readString();
        this.mServicePhone = parcel.readString();
        this.mDownloadTimes = parcel.readLong();
        this.mPublishData = parcel.readString();
        this.mPublishStatus = parcel.readString();
        this.mRechargeMode = parcel.readString();
        this.mRechargeLowerLimit = parcel.readString();
        this.mAppApplyId = parcel.readString();
        this.mStatus = (AppStatus) parcel.readParcelable(AppStatus.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAppApplyId() {
        return this.mAppApplyId;
    }

    public String getAppDesc() {
        return this.mAppDesc;
    }

    public AppID getAppID() {
        return this.mAppID;
    }

    public String getAppIcon() {
        return this.mAppIcon;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public String getAppProviderAgreement() {
        return this.mAppProviderAgreement;
    }

    public String getAppProviderLogo() {
        return this.mAppProviderLogo;
    }

    public String getAppProviderName() {
        return this.mAppProviderName;
    }

    public String getApplyMode() {
        return this.mApplyMode;
    }

    public long getDownloadTimes() {
        return this.mDownloadTimes;
    }

    public String getPublishData() {
        return this.mPublishData;
    }

    public String getPublishStatus() {
        return this.mPublishStatus;
    }

    public String getRechargeLowerLimit() {
        return this.mRechargeLowerLimit;
    }

    public String getRechargeMode() {
        return this.mRechargeMode;
    }

    public String getServicePhone() {
        return this.mServicePhone;
    }

    public AppStatus getStatus() {
        return this.mStatus;
    }

    public String getUpAgreement() {
        return this.mUpAgreement;
    }

    public void setAppApplyId(String str) {
        this.mAppApplyId = str;
    }

    public void setAppDesc(String str) {
        this.mAppDesc = str;
    }

    public void setAppID(AppID appID) {
        this.mAppID = appID;
    }

    public void setAppIcon(String str) {
        this.mAppIcon = str;
    }

    public void setAppName(String str) {
        this.mAppName = str;
    }

    public void setAppProviderAgreement(String str) {
        this.mAppProviderAgreement = str;
    }

    public void setAppProviderLogo(String str) {
        this.mAppProviderLogo = str;
    }

    public void setAppProviderName(String str) {
        this.mAppProviderName = str;
    }

    public void setApplyMode(String str) {
        this.mApplyMode = str;
    }

    public void setDownloadTimes(long j) {
        this.mDownloadTimes = j;
    }

    public void setPublishData(String str) {
        this.mPublishData = str;
    }

    public void setPublishStatus(String str) {
        this.mPublishStatus = str;
    }

    public void setRechargeLowerLimit(String str) {
        this.mRechargeLowerLimit = str;
    }

    public void setRechargeMode(String str) {
        this.mRechargeMode = str;
    }

    public void setServicePhone(String str) {
        this.mServicePhone = str;
    }

    public void setStatus(AppStatus appStatus) {
        this.mStatus = appStatus;
    }

    public void setUpAgreement(String str) {
        this.mUpAgreement = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAppID, i);
        parcel.writeString(this.mAppName);
        parcel.writeString(this.mAppIcon);
        parcel.writeString(this.mAppDesc);
        parcel.writeString(this.mAppProviderLogo);
        parcel.writeString(this.mAppProviderName);
        parcel.writeString(this.mAppProviderAgreement);
        parcel.writeString(this.mUpAgreement);
        parcel.writeString(this.mApplyMode);
        parcel.writeString(this.mServicePhone);
        parcel.writeLong(this.mDownloadTimes);
        parcel.writeString(this.mPublishData);
        parcel.writeString(this.mPublishStatus);
        parcel.writeString(this.mRechargeMode);
        parcel.writeString(this.mRechargeLowerLimit);
        parcel.writeString(this.mAppApplyId);
        parcel.writeParcelable(this.mStatus, i);
    }
}
