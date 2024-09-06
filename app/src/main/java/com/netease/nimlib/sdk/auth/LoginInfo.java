package com.netease.nimlib.sdk.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.io.Serializable;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LoginInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<LoginInfo> CREATOR = new Parcelable.Creator<LoginInfo>() { // from class: com.netease.nimlib.sdk.auth.LoginInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LoginInfo createFromParcel(Parcel parcel) {
            return new LoginInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LoginInfo[] newArray(int i) {
            return new LoginInfo[i];
        }
    };
    private static final String KEY_ACCOUNT = "KEY_ACCOUNT";
    private static final String KEY_APP_KEY = "KEY_APP_KEY";
    private static final String KEY_AUTH_TYPE = "KEY_AUTH_TYPE";
    private static final String KEY_CUSTOM_CLIENT_TYPE = "KEY_CUSTOM_CLIENT_TYPE";
    private static final String KEY_LOGIN_EXT = "KEY_LOGIN_EXT";
    private static final String KEY_TOKEN = "KEY_TOKEN";
    private final String account;
    private String appKey;
    private int authType;
    private int customClientType;
    private String loginExt;
    private final String token;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LoginInfo(String str, String str2) {
        this.account = str == null ? null : str.toLowerCase();
        this.token = str2;
    }

    public LoginInfo(String str, String str2, String str3) {
        this(str, str2);
        this.appKey = str3;
    }

    public LoginInfo(String str, String str2, String str3, int i) {
        this(str, str2, str3);
        this.customClientType = i;
    }

    protected LoginInfo(Parcel parcel) {
        this.account = parcel.readString();
        this.token = parcel.readString();
        this.authType = parcel.readInt();
        this.loginExt = parcel.readString();
        this.appKey = parcel.readString();
        this.customClientType = parcel.readInt();
    }

    public String getAccount() {
        return this.account;
    }

    public String getToken() {
        return this.token;
    }

    public int getAuthType() {
        return this.authType;
    }

    public String getLoginExt() {
        return this.loginExt;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public int getCustomClientType() {
        return this.customClientType;
    }

    public boolean valid() {
        if (this.authType == 0) {
            return (TextUtils.isEmpty(this.account) || TextUtils.isEmpty(this.token)) ? false : true;
        }
        return !TextUtils.isEmpty(this.account);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LoginInfo loginInfo = (LoginInfo) obj;
        return this.authType == loginInfo.authType && this.customClientType == loginInfo.customClientType && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.account, loginInfo.account) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.token, loginInfo.token) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.loginExt, loginInfo.loginExt) && C$r8$backportedMethods$utility$Objects$2$equals.equals(this.appKey, loginInfo.appKey);
    }

    public String toString() {
        return "LoginInfo{account='" + this.account + "', authType=" + this.authType + ", customClientType=" + this.customClientType + '}';
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.account, this.token, Integer.valueOf(this.authType), this.loginExt, this.appKey, Integer.valueOf(this.customClientType)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.account);
        parcel.writeString(this.token);
        parcel.writeInt(this.authType);
        parcel.writeString(this.loginExt);
        parcel.writeString(this.appKey);
        parcel.writeInt(this.customClientType);
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(KEY_ACCOUNT, this.account);
            jSONObject.putOpt(KEY_TOKEN, this.token);
            jSONObject.putOpt(KEY_AUTH_TYPE, Integer.valueOf(this.authType));
            jSONObject.putOpt(KEY_LOGIN_EXT, this.loginExt);
            jSONObject.putOpt(KEY_APP_KEY, this.appKey);
            jSONObject.putOpt(KEY_CUSTOM_CLIENT_TYPE, Integer.valueOf(this.customClientType));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static LoginInfo fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString(KEY_ACCOUNT, null);
        String optString2 = jSONObject.optString(KEY_TOKEN, null);
        int optInt = jSONObject.optInt(KEY_AUTH_TYPE);
        String optString3 = jSONObject.optString(KEY_LOGIN_EXT, null);
        String optString4 = jSONObject.optString(KEY_APP_KEY, null);
        return new LoginInfoBuilder(optString, optString2, optInt, optString3).withAppKey(optString4).withCustomClientType(jSONObject.optInt(KEY_CUSTOM_CLIENT_TYPE)).build();
    }

    /* loaded from: classes.dex */
    public static final class LoginInfoBuilder {
        private String account;
        private String appKey;
        private int authType;
        private int customClientType;
        private String loginExt;
        private String token;

        public LoginInfoBuilder(String str, String str2, int i, String str3) {
            this.account = str;
            this.token = str2;
            this.authType = i;
            this.loginExt = str3;
        }

        public static LoginInfoBuilder loginInfoDefault(String str, String str2) {
            return new LoginInfoBuilder(str, str2, 0, "");
        }

        public static LoginInfoBuilder loginInfoDynamic(String str, String str2) {
            return new LoginInfoBuilder(str, str2, 1, "");
        }

        public static LoginInfoBuilder loginInfoThirdParty(String str, String str2, String str3) {
            return new LoginInfoBuilder(str, str2, 2, str3);
        }

        public static LoginInfoBuilder loginInfoThirdPartyDynamic(String str, String str2) {
            return new LoginInfoBuilder(str, str2, 2, "");
        }

        public LoginInfoBuilder withAppKey(String str) {
            this.appKey = str;
            return this;
        }

        public LoginInfoBuilder withCustomClientType(int i) {
            this.customClientType = i;
            return this;
        }

        public LoginInfoBuilder withAuthType(int i) {
            this.authType = i;
            return this;
        }

        public LoginInfoBuilder withLoginExt(String str) {
            this.loginExt = str;
            return this;
        }

        public LoginInfo build() {
            LoginInfo loginInfo = new LoginInfo(this.account, this.token);
            loginInfo.authType = this.authType;
            loginInfo.loginExt = this.loginExt;
            loginInfo.appKey = this.appKey;
            loginInfo.customClientType = this.customClientType;
            return loginInfo;
        }
    }
}
