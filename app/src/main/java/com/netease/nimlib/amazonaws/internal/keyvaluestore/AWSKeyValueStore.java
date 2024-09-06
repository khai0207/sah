package com.netease.nimlib.amazonaws.internal.keyvaluestore;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.netease.nimlib.amazonaws.logging.Log;
import com.netease.nimlib.amazonaws.logging.LogFactory;
import com.netease.nimlib.amazonaws.util.Base64;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes.dex */
public class AWSKeyValueStore {
    private static final int AWS_KEY_VALUE_STORE_VERSION = 1;
    private static final String CHARSET_NAME = "UTF-8";
    private static final String CIPHER_AES_GCM_NOPADDING = "AES/GCM/NoPadding";
    private static final int CIPHER_AES_GCM_NOPADDING_IV_LENGTH_IN_BYTES = 12;
    private static final int CIPHER_AES_GCM_NOPADDING_TAG_LENGTH_LENGTH_IN_BITS = 128;
    static final String SHARED_PREFERENCES_DATA_IDENTIFIER_SUFFIX = ".encrypted";
    static final String SHARED_PREFERENCES_ENCRYPTION_KEY_NAMESPACE_SUFFIX = ".encryptionkey";
    static final String SHARED_PREFERENCES_IV_SUFFIX = ".iv";
    static final String SHARED_PREFERENCES_STORE_VERSION_SUFFIX = ".keyvaluestoreversion";
    private Map<String, String> cache;
    Context context;
    private boolean isPersistenceEnabled;
    KeyProvider keyProvider;
    private SecureRandom secureRandom = new SecureRandom();
    SharedPreferences sharedPreferencesForData;
    SharedPreferences sharedPreferencesForEncryptionMaterials;
    private final String sharedPreferencesName;
    private static final Log logger = LogFactory.getLog((Class<?>) AWSKeyValueStore.class);
    static Map<String, HashMap<String, String>> cacheFactory = new HashMap();

    private static Map<String, String> getCacheForKey(String str) {
        if (cacheFactory.containsKey(str)) {
            return cacheFactory.get(str);
        }
        HashMap<String, String> hashMap = new HashMap<>();
        cacheFactory.put(str, hashMap);
        return hashMap;
    }

    public AWSKeyValueStore(Context context, String str, boolean z) {
        this.cache = getCacheForKey(str);
        this.sharedPreferencesName = str;
        this.context = context;
        setPersistenceEnabled(z);
    }

    public synchronized void setPersistenceEnabled(boolean z) {
        try {
            boolean z2 = this.isPersistenceEnabled;
            this.isPersistenceEnabled = z;
            if (z && !z2) {
                this.sharedPreferencesForData = this.context.getSharedPreferences(this.sharedPreferencesName, 0);
                this.sharedPreferencesForEncryptionMaterials = this.context.getSharedPreferences(this.sharedPreferencesName + SHARED_PREFERENCES_ENCRYPTION_KEY_NAMESPACE_SUFFIX, 0);
                initKeyProviderBasedOnAPILevel();
                logger.info("Detected Android API Level = " + Build.VERSION.SDK_INT);
                logger.info("Creating the AWSKeyValueStore with key for sharedPreferencesForData = " + this.sharedPreferencesName);
                onMigrateFromNoEncryption();
            } else if (!z) {
                logger.info("Persistence is disabled. Data will be accessed from memory.");
            }
            if (!z && z2) {
                this.sharedPreferencesForData.edit().clear().apply();
            }
        } catch (Exception e) {
            logger.error("Error in enabling persistence for " + this.sharedPreferencesName, e);
        }
    }

    public synchronized boolean contains(String str) {
        if (this.isPersistenceEnabled) {
            if (this.cache.containsKey(str)) {
                return true;
            }
            return this.sharedPreferencesForData.contains(getDataKeyUsedInPersistentStore(str));
        }
        return this.cache.containsKey(str);
    }

    public synchronized String get(String str) {
        if (str == null) {
            return null;
        }
        if (!this.cache.containsKey(str) && this.isPersistenceEnabled) {
            String dataKeyUsedInPersistentStore = getDataKeyUsedInPersistentStore(str);
            Key retrieveEncryptionKey = retrieveEncryptionKey(getEncryptionKeyAlias());
            if (retrieveEncryptionKey == null) {
                logger.warn("Error in retrieving the decryption key used to decrypt the data from the persistent store. Returning null for the requested dataKey = " + str);
                return null;
            }
            if (!this.sharedPreferencesForData.contains(dataKeyUsedInPersistentStore)) {
                return null;
            }
            try {
                if (Integer.parseInt(this.sharedPreferencesForData.getString(dataKeyUsedInPersistentStore + SHARED_PREFERENCES_STORE_VERSION_SUFFIX, null)) != 1) {
                    logger.error("The version of the data read from SharedPreferences for " + str + " does not match the version of the store.");
                    return null;
                }
                String decrypt = decrypt(retrieveEncryptionKey, getInitializationVector(dataKeyUsedInPersistentStore), this.sharedPreferencesForData.getString(dataKeyUsedInPersistentStore, null));
                this.cache.put(str, decrypt);
                return decrypt;
            } catch (Exception e) {
                logger.warn("Error in retrieving value for dataKey = " + str, e);
                remove(str);
                return null;
            }
        }
        return this.cache.get(str);
    }

    public synchronized void put(String str, String str2) {
        byte[] generateInitializationVector;
        if (str == null) {
            logger.error("dataKey is null.");
            return;
        }
        this.cache.put(str, str2);
        if (this.isPersistenceEnabled) {
            if (str2 == null) {
                logger.debug("Value is null. Removing the data, IV and version from SharedPreferences");
                this.cache.remove(str);
                remove(str);
                return;
            }
            String dataKeyUsedInPersistentStore = getDataKeyUsedInPersistentStore(str);
            String encryptionKeyAlias = getEncryptionKeyAlias();
            Key retrieveEncryptionKey = retrieveEncryptionKey(encryptionKeyAlias);
            if (retrieveEncryptionKey == null) {
                logger.warn("No encryption key found for encryptionKeyAlias: " + encryptionKeyAlias);
                retrieveEncryptionKey = generateEncryptionKey(encryptionKeyAlias);
                if (retrieveEncryptionKey == null) {
                    logger.warn("Error in generating the encryption key for encryptionKeyAlias: " + encryptionKeyAlias + " used to encrypt the data before storing. Skipping persisting the data in the persistent store.");
                    return;
                }
            }
            try {
                generateInitializationVector = generateInitializationVector();
            } catch (Exception e) {
                logger.error("Error in storing value for dataKey = " + str + ". This data has not been stored in the persistent store.", e);
            }
            if (generateInitializationVector == null) {
                throw new Exception("The generated IV for dataKey = " + str + " is null.");
            }
            String encrypt = encrypt(retrieveEncryptionKey, getAlgorithmParameterSpecForIV(generateInitializationVector), str2);
            String encodeAsString = Base64.encodeAsString(generateInitializationVector);
            if (encodeAsString == null) {
                throw new Exception("Error in Base64 encoding the IV for dataKey = " + str);
            }
            this.sharedPreferencesForData.edit().putString(dataKeyUsedInPersistentStore, encrypt).putString(dataKeyUsedInPersistentStore + SHARED_PREFERENCES_IV_SUFFIX, encodeAsString).putString(dataKeyUsedInPersistentStore + SHARED_PREFERENCES_STORE_VERSION_SUFFIX, String.valueOf(1)).apply();
        }
    }

    public synchronized void remove(String str) {
        this.cache.remove(str);
        if (this.isPersistenceEnabled) {
            String dataKeyUsedInPersistentStore = getDataKeyUsedInPersistentStore(str);
            this.sharedPreferencesForData.edit().remove(dataKeyUsedInPersistentStore).remove(dataKeyUsedInPersistentStore + SHARED_PREFERENCES_IV_SUFFIX).remove(dataKeyUsedInPersistentStore + SHARED_PREFERENCES_STORE_VERSION_SUFFIX).apply();
        }
    }

    public synchronized void clear() {
        this.cache.clear();
        if (this.isPersistenceEnabled) {
            this.sharedPreferencesForData.edit().clear().apply();
        }
    }

    private String encrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, String str) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_AES_GCM_NOPADDING);
            cipher.init(1, key, algorithmParameterSpec);
            return Base64.encodeAsString(cipher.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            logger.error("Error in encrypting data. ", e);
            return null;
        }
    }

    private String decrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, String str) {
        try {
            byte[] decode = Base64.decode(str);
            Cipher cipher = Cipher.getInstance(CIPHER_AES_GCM_NOPADDING);
            cipher.init(2, key, algorithmParameterSpec);
            return new String(cipher.doFinal(decode), "UTF-8");
        } catch (Exception e) {
            logger.error("Error in decrypting data. ", e);
            return null;
        }
    }

    private AlgorithmParameterSpec getInitializationVector(String str) throws Exception {
        String str2 = str + SHARED_PREFERENCES_IV_SUFFIX;
        if (!this.sharedPreferencesForData.contains(str2)) {
            throw new Exception("Initialization vector for " + str + " is missing from the SharedPreferences.");
        }
        String string = this.sharedPreferencesForData.getString(str2, null);
        if (string == null) {
            throw new Exception("Cannot read the initialization vector for " + str + " from SharedPreferences.");
        }
        byte[] decode = Base64.decode(string);
        if (decode == null || decode.length == 0) {
            throw new Exception("Cannot base64 decode the initialization vector for " + str + " read from SharedPreferences.");
        }
        return getAlgorithmParameterSpecForIV(decode);
    }

    private byte[] generateInitializationVector() {
        byte[] bArr = new byte[12];
        this.secureRandom.nextBytes(bArr);
        return bArr;
    }

    private AlgorithmParameterSpec getAlgorithmParameterSpecForIV(byte[] bArr) {
        if (Build.VERSION.SDK_INT >= 23) {
            return new GCMParameterSpec(128, bArr);
        }
        return new IvParameterSpec(bArr);
    }

    private synchronized Key retrieveEncryptionKey(String str) {
        try {
        } catch (KeyNotFoundException e) {
            logger.warn(e);
            logger.info("Deleting the encryption key identified by the keyAlias: " + str);
            this.keyProvider.deleteKey(str);
            return null;
        }
        return this.keyProvider.retrieveKey(str);
    }

    synchronized Key generateEncryptionKey(String str) {
        try {
        } catch (KeyNotGeneratedException e) {
            logger.error("Encryption Key cannot be generated successfully.", e);
            return null;
        }
        return this.keyProvider.generateKey(str);
    }

    private String getDataKeyUsedInPersistentStore(String str) {
        if (str == null) {
            return null;
        }
        return str + SHARED_PREFERENCES_DATA_IDENTIFIER_SUFFIX;
    }

    private String getEncryptionKeyAlias() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.sharedPreferencesName + ".aesKeyStoreAlias";
        }
        if (Build.VERSION.SDK_INT >= 18) {
            return this.sharedPreferencesName + ".rsaKeyStoreAlias";
        }
        if (Build.VERSION.SDK_INT >= 10) {
            return "AesGcmNoPaddingEncryption10-encryption-key";
        }
        logger.error("API Level " + String.valueOf(Build.VERSION.SDK_INT) + " not supported by the SDK. Setting persistence to false.");
        this.isPersistenceEnabled = false;
        return null;
    }

    private void initKeyProviderBasedOnAPILevel() {
        if (Build.VERSION.SDK_INT >= 23) {
            this.keyProvider = new KeyProvider23();
            return;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            this.keyProvider = new KeyProvider18(this.context, this.sharedPreferencesForEncryptionMaterials);
            return;
        }
        if (Build.VERSION.SDK_INT >= 10) {
            this.keyProvider = new KeyProvider10(this.sharedPreferencesForEncryptionMaterials);
            return;
        }
        logger.error("API Level " + String.valueOf(Build.VERSION.SDK_INT) + " not supported by the SDK. Setting persistence to false.");
        this.isPersistenceEnabled = false;
    }

    private void onMigrateFromNoEncryption() {
        Map<String, ?> all = this.sharedPreferencesForData.getAll();
        for (String str : all.keySet()) {
            if (!str.endsWith(SHARED_PREFERENCES_DATA_IDENTIFIER_SUFFIX) && !str.endsWith(SHARED_PREFERENCES_IV_SUFFIX) && !str.endsWith(SHARED_PREFERENCES_STORE_VERSION_SUFFIX)) {
                if (all.get(str) instanceof Long) {
                    put(str, String.valueOf(Long.valueOf(this.sharedPreferencesForData.getLong(str, 0L))));
                } else if (all.get(str) instanceof String) {
                    put(str, this.sharedPreferencesForData.getString(str, null));
                } else if (all.get(str) instanceof Float) {
                    put(str, String.valueOf(Float.valueOf(this.sharedPreferencesForData.getFloat(str, 0.0f))));
                } else if (all.get(str) instanceof Boolean) {
                    put(str, String.valueOf(Boolean.valueOf(this.sharedPreferencesForData.getBoolean(str, false))));
                } else if (all.get(str) instanceof Integer) {
                    put(str, String.valueOf(Integer.valueOf(this.sharedPreferencesForData.getInt(str, 0))));
                } else if (all.get(str) instanceof Set) {
                    Set set = (Set) all.get(str);
                    StringBuilder sb = new StringBuilder();
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        sb.append((String) it.next());
                        if (it.hasNext()) {
                            sb.append(",");
                        }
                    }
                    put(str, sb.toString());
                }
                this.sharedPreferencesForData.edit().remove(str).apply();
            }
        }
    }
}
