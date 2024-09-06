package com.netease.nimlib.fusionstorage.crossplatform;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.netease.nimlib.biz.k;
import com.netease.nimlib.fusionstorage.crossplatform.defines.Credential;
import com.netease.nimlib.fusionstorage.crossplatform.defines.Policy;
import com.netease.nimlib.fusionstorage.crossplatform.defines.StorageProvider;
import com.netease.nimlib.o.f;
import defpackage.C$r8$backportedMethods$utility$String$2$joinArray;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class StorageListener implements IStorageListener {
    private final String TAG;
    private final Gson gson;
    private final WeakReference<StorageManager> storageManagerWeakReference;
    private final Set<String> requestingMixStoreAuthorizationCache = Collections.synchronizedSet(new HashSet());
    private final Map<String, b> credentialCacheMap = new HashMap();

    public StorageListener(StorageManager storageManager) {
        String str = storageManager.a;
        if (TextUtils.equals("SINGLE_INSTANCE_KEY", str)) {
            this.TAG = "StorageListener";
        } else {
            this.TAG = "StorageListener_" + str;
        }
        this.storageManagerWeakReference = new WeakReference<>(storageManager);
        this.gson = new Gson();
    }

    public void onPolicyDidUpdate(List<Policy> list, int i, int i2, long j) {
        com.netease.nimlib.log.b.d(this.TAG, String.format("onPolicyDidUpdate: %s %s %s %s", f.f(list), Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j)));
        savePolicyCache(new c(list, i, i2, j));
    }

    public void onPolicyWillExpire(int i) {
        com.netease.nimlib.log.b.d(this.TAG, String.format("onPolicyWillExpire: %s", Integer.valueOf(i)));
        d.a(this.storageManagerWeakReference);
    }

    public void onCredentialDidUpdate(int i, int i2, String str, List<Credential> list) {
        String str2 = this.TAG;
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = Integer.valueOf(i2);
        objArr[2] = str;
        objArr[3] = list == null ? Constants.NULL_VERSION_ID : Integer.valueOf(list.size());
        com.netease.nimlib.log.b.d(str2, String.format("onCredentialDidUpdate: %s %s %s %s", objArr));
        saveCredentialCache(i, str, new b(i2, list));
    }

    public void onCredentialWillExpire(int i, int i2, String str, int i3) {
        String requestingMixStoreAuthorizationKey = getRequestingMixStoreAuthorizationKey(i, Integer.valueOf(i2), str);
        if (this.requestingMixStoreAuthorizationCache.contains(requestingMixStoreAuthorizationKey)) {
            com.netease.nimlib.log.b.d(this.TAG, String.format("onCredentialWillExpire skip as requestingMixStoreAuthorizationCache: %s %s %s %s [ %s ]", Integer.valueOf(i), Integer.valueOf(i2), str, Integer.valueOf(i3), f.f(this.requestingMixStoreAuthorizationCache)));
            return;
        }
        com.netease.nimlib.log.b.d(this.TAG, String.format("onCredentialWillExpire invoke: %s %s %s %s [ %s ]", Integer.valueOf(i), Integer.valueOf(i2), str, Integer.valueOf(i3), f.f(this.requestingMixStoreAuthorizationCache)));
        appendMixStoreAuthorizationCache(requestingMixStoreAuthorizationKey);
        d.a(i, Integer.valueOf(i2), str, i3, this.storageManagerWeakReference, new WeakReference(this));
    }

    private static String getRequestingMixStoreAuthorizationKey(int i, Integer num, String str) {
        return C$r8$backportedMethods$utility$String$2$joinArray.join("#", new CharSequence[]{String.valueOf(i), String.valueOf(num), str});
    }

    private void appendMixStoreAuthorizationCache(String str) {
        this.requestingMixStoreAuthorizationCache.add(str);
    }

    public void appendMixStoreAuthorizationCache(int i, Integer num, String str) {
        appendMixStoreAuthorizationCache(getRequestingMixStoreAuthorizationKey(i, num, str));
    }

    public void markRequestMixStoreAuthorizationComplete(int i, Integer num, String str) {
        this.requestingMixStoreAuthorizationCache.remove(getRequestingMixStoreAuthorizationKey(i, num, str));
        com.netease.nimlib.log.b.d(this.TAG, String.format("markRequestMixStoreAuthorizationComplete: %s %s %s [ %s ]", Integer.valueOf(i), num, str, f.f(this.requestingMixStoreAuthorizationCache)));
    }

    public void OnCredentialRemoveAll(int i, int i2, String str) {
        clearCredentials();
    }

    public void onCredentialRemoved(int i, int i2, String str, Credential credential) {
        com.netease.nimlib.log.b.d(this.TAG, String.format("onCredentialRemoved: %s %s %s %s", Integer.valueOf(i), Integer.valueOf(i2), str, credential));
        b bVar = this.credentialCacheMap.get(generateCredentialKey(i, str));
        if (bVar != null && bVar.b() && bVar.a().remove(credential)) {
            saveCredentialCache(i, str, bVar);
        }
    }

    public void clearCredentials() {
        this.credentialCacheMap.clear();
        if (com.netease.nimlib.c.C() != null) {
            Iterator<Map.Entry<String, Long>> it = com.netease.nimlib.c.C().getNosTokenScene().entrySet().iterator();
            while (it.hasNext()) {
                String key = it.next().getKey();
                for (StorageProvider storageProvider : StorageProvider.values()) {
                    k.b().edit().remove(generateCredentialKey(storageProvider.getValue(), key)).commit();
                }
            }
        }
    }

    public c loadPolicyCache() {
        try {
            String string = k.b().getString("policyCache", null);
            if (TextUtils.isEmpty(string)) {
                com.netease.nimlib.log.b.d(this.TAG, String.format("loadPolicyCache: %s", string));
                return null;
            }
            c cVar = (c) this.gson.fromJson(string, c.class);
            com.netease.nimlib.log.b.d(this.TAG, String.format("loadPolicyCache: %s", cVar));
            return cVar;
        } catch (Exception e) {
            com.netease.nimlib.log.b.e(this.TAG, String.format("loadPolicyCache catch Exception", new Object[0]), e);
            return null;
        }
    }

    public void savePolicyCache(c cVar) {
        try {
            String json = this.gson.toJson(cVar);
            k.b().edit().putString("policyCache", json).apply();
            if (com.netease.nimlib.log.b.a()) {
                com.netease.nimlib.log.b.c(this.TAG, String.format("savePolicyCache %s", json));
            }
        } catch (Exception e) {
            com.netease.nimlib.log.b.e(this.TAG, String.format("savePolicyCache %s catch Exception", cVar), e);
        }
    }

    public b loadCredentialCache(int i, String str) {
        try {
            String generateCredentialKey = generateCredentialKey(i, str);
            String string = k.b().getString(generateCredentialKey, null);
            if (string == null) {
                com.netease.nimlib.log.b.e(this.TAG, String.format("loadCredentialCache %s %s: %s", Integer.valueOf(i), str, string));
                return null;
            }
            if (com.netease.nimlib.log.b.a()) {
                com.netease.nimlib.log.b.c(this.TAG, String.format("loadCredentialCache %s %s: %s", Integer.valueOf(i), str, string));
            }
            b bVar = (b) this.gson.fromJson(string, b.class);
            this.credentialCacheMap.put(generateCredentialKey, bVar);
            com.netease.nimlib.log.b.d(this.TAG, String.format("loadCredentialCache %s %s: %s", Integer.valueOf(i), str, bVar));
            return bVar;
        } catch (Exception e) {
            com.netease.nimlib.log.b.e(this.TAG, String.format("loadCredentialCache %s %s catch Exception", Integer.valueOf(i), str), e);
            return null;
        }
    }

    public void saveCredentialCache(int i, String str, b bVar) {
        try {
            String generateCredentialKey = generateCredentialKey(i, str);
            this.credentialCacheMap.put(generateCredentialKey, bVar);
            k.b().edit().putString(generateCredentialKey, this.gson.toJson(bVar)).apply();
            if (com.netease.nimlib.log.b.a()) {
                com.netease.nimlib.log.b.c(this.TAG, String.format("saveCredentialCache %s %s %s", Integer.valueOf(i), str, bVar));
            }
        } catch (Exception e) {
            com.netease.nimlib.log.b.e(this.TAG, String.format("saveCredentialCache %s %s %s catch Exception", Integer.valueOf(i), str, bVar), e);
        }
    }

    private String generateCredentialKey(int i, String str) {
        return "credentialCache_" + i + "_" + str;
    }
}
