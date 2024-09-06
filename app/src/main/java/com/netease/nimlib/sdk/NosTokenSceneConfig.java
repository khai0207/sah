package com.netease.nimlib.sdk;

import android.text.TextUtils;
import com.netease.nimlib.NimNosSceneKeyConstant;
import java.util.HashMap;

/* loaded from: classes.dex */
public class NosTokenSceneConfig {
    private static final long DAY_SECOND = 86400;
    private static final int DEFAULT_CUSTOM_SCENE_COUNT = 10;
    private static final int MIN_CUSTOM_SCENE_COUNT = 10;
    public static final long NEVER_EXPIRE = 0;
    private static int sDefaultSceneCount;
    private HashMap<String, Long> mNosTokenScene;
    private int maxCustomNosSceneCount = 10;
    private static final HashMap<String, Long> DEFAULT_SCENE = new HashMap<>();
    private static NosTokenSceneConfig sDefault = new NosTokenSceneConfig();

    static {
        DEFAULT_SCENE.put(NimNosSceneKeyConstant.NIM_DEFAULT_PROFILE, 0L);
        DEFAULT_SCENE.put(NimNosSceneKeyConstant.NIM_DEFAULT_IM, 0L);
        DEFAULT_SCENE.put(NimNosSceneKeyConstant.NIM_SYSTEM_NOS_SCENE, 0L);
        DEFAULT_SCENE.put(NimNosSceneKeyConstant.NIM_SECURITY_PREFIX, 0L);
        sDefaultSceneCount = DEFAULT_SCENE.size();
    }

    public NosTokenSceneConfig() {
        HashMap<String, Long> hashMap = new HashMap<>();
        this.mNosTokenScene = hashMap;
        hashMap.putAll(DEFAULT_SCENE);
    }

    public int getMaxCustomNosSceneCount() {
        return this.maxCustomNosSceneCount;
    }

    public void updateDefaultProfileSceneExpireTime(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("expireTimeByDay must >= 0");
        }
        this.mNosTokenScene.put(NimNosSceneKeyConstant.NIM_DEFAULT_PROFILE, Long.valueOf(i * DAY_SECOND));
    }

    public void updateDefaultIMSceneExpireTime(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("expireTimeByDay must >= 0");
        }
        this.mNosTokenScene.put(NimNosSceneKeyConstant.NIM_DEFAULT_IM, Long.valueOf(i * DAY_SECOND));
    }

    public NosTokenSceneConfig appendCustomScene(String str, int i) {
        if (i < 0 || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("sceneKey must not empty and expireTimeByDay must >= 0");
        }
        if (this.mNosTokenScene.size() - sDefaultSceneCount >= this.maxCustomNosSceneCount) {
            throw new IllegalStateException("the custom scene count must <= " + this.maxCustomNosSceneCount);
        }
        if (NimNosSceneKeyConstant.NIM_SYSTEM_NOS_SCENE.equals(str)) {
            throw new IllegalArgumentException("the \"nim_system_nos_scene\" scene cannot be modified");
        }
        this.mNosTokenScene.put(str, Long.valueOf(i * DAY_SECOND));
        return this;
    }

    public HashMap<String, Long> getNosTokenScene() {
        HashMap<String, Long> hashMap = new HashMap<>(this.mNosTokenScene.size());
        hashMap.putAll(this.mNosTokenScene);
        return hashMap;
    }

    public static NosTokenSceneConfig defaultConfig() {
        NosTokenSceneConfig nosTokenSceneConfig = sDefault;
        nosTokenSceneConfig.mNosTokenScene = DEFAULT_SCENE;
        return nosTokenSceneConfig;
    }
}
