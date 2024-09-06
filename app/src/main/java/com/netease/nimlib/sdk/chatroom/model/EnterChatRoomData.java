package com.netease.nimlib.sdk.chatroom.model;

import android.text.TextUtils;
import com.netease.nimlib.sdk.antispam.model.AntiSpamConfig;
import java.util.Map;

/* loaded from: classes.dex */
public class EnterChatRoomData {
    private String account;
    private AntiSpamConfig antiSpamConfig;
    private String appKey;
    private String avatar;

    @Deprecated
    private ChatRoomAuthProvider chatRoomAuthProvider;
    private ChatRoomSpatialLocation chatRoomSpatialLocation;
    private Map<String, Object> extension;
    private boolean independentMode;
    private ChatRoomIndependentCallback independentModeCallback;
    private boolean isAnonymousMode;
    private Integer loginAuthType;
    private String loginExt;
    private String nick;
    private Map<String, Object> notifyExtension;
    private String notifyTargetTags;
    private String roomId;
    private String tags;
    private String token;

    public String getRoomId() {
        return this.roomId;
    }

    public void setRoomId(String str) {
        this.roomId = str;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String str) {
        this.nick = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public Map<String, Object> getExtension() {
        return this.extension;
    }

    public void setExtension(Map<String, Object> map) {
        this.extension = map;
    }

    public Map<String, Object> getNotifyExtension() {
        return this.notifyExtension;
    }

    public void setNotifyExtension(Map<String, Object> map) {
        this.notifyExtension = map;
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

    public boolean isIndependentMode() {
        return this.independentMode;
    }

    public boolean isAnonymousMode() {
        return this.isAnonymousMode;
    }

    public String getAccount() {
        return this.account;
    }

    public String getToken() {
        return this.token;
    }

    public Integer getLoginAuthType() {
        return this.loginAuthType;
    }

    public void setLoginAuthType(Integer num) {
        this.loginAuthType = num;
    }

    public String getLoginExt() {
        return this.loginExt;
    }

    public void setLoginExt(String str) {
        this.loginExt = str;
    }

    public ChatRoomSpatialLocation getChatRoomSpatialLocation() {
        return this.chatRoomSpatialLocation;
    }

    public void setChatRoomSpatialLocation(ChatRoomSpatialLocation chatRoomSpatialLocation) {
        this.chatRoomSpatialLocation = chatRoomSpatialLocation;
    }

    @Deprecated
    public ChatRoomAuthProvider getChatRoomAuthProvider() {
        return this.chatRoomAuthProvider;
    }

    @Deprecated
    public void setChatRoomAuthProvider(ChatRoomAuthProvider chatRoomAuthProvider) {
        this.chatRoomAuthProvider = chatRoomAuthProvider;
    }

    public ChatRoomIndependentCallback getIndependentModeCallback() {
        return this.independentModeCallback;
    }

    public AntiSpamConfig getAntiSpamConfig() {
        return this.antiSpamConfig;
    }

    public void setAntiSpamConfig(AntiSpamConfig antiSpamConfig) {
        this.antiSpamConfig = antiSpamConfig;
    }

    public void setIndependentMode(ChatRoomIndependentCallback chatRoomIndependentCallback, String str, String str2) {
        this.independentMode = true;
        this.independentModeCallback = chatRoomIndependentCallback;
        this.account = str;
        this.token = str2;
        this.isAnonymousMode = TextUtils.isEmpty(str);
    }

    public void setIndependentMode(ChatRoomIndependentCallback chatRoomIndependentCallback, String str, String str2, boolean z) {
        this.independentMode = true;
        this.independentModeCallback = chatRoomIndependentCallback;
        this.account = str;
        this.token = str2;
        this.isAnonymousMode = z;
    }

    public EnterChatRoomData(String str) {
        this.roomId = str;
    }

    public boolean isValid() {
        if (TextUtils.isEmpty(this.roomId)) {
            return false;
        }
        try {
            Long.parseLong(this.roomId);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
