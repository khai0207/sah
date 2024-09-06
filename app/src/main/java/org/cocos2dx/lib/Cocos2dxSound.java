package org.cocos2dx.lib;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Semaphore;

/* loaded from: classes.dex */
public class Cocos2dxSound {
    private static final int INVALID_SOUND_ID = -1;
    private static final int INVALID_STREAM_ID = -1;
    private static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 5;
    private static final int SOUND_PRIORITY = 1;
    private static final int SOUND_QUALITY = 5;
    private static final float SOUND_RATE = 1.0f;
    private static final String TAG = "Cocos2dxSound";
    private final Context mContext;
    private float mLeftVolume;
    private float mRightVolume;
    private Semaphore mSemaphore;
    private SoundPool mSoundPool;
    private int mStreamIdSyn;
    private final HashMap<String, ArrayList<Integer>> mPathStreamIDsMap = new HashMap<>();
    private final HashMap<String, Integer> mPathSoundIDMap = new HashMap<>();
    private final ArrayList<SoundInfoForLoadedCompleted> mEffecToPlayWhenLoadedArray = new ArrayList<>();

    public Cocos2dxSound(Context context) {
        this.mContext = context;
        initData();
    }

    private void initData() {
        SoundPool soundPool = new SoundPool(5, 3, 5);
        this.mSoundPool = soundPool;
        soundPool.setOnLoadCompleteListener(new OnLoadCompletedListener());
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        this.mSemaphore = new Semaphore(0, true);
    }

    public int preloadEffect(String str) {
        Integer num = this.mPathSoundIDMap.get(str);
        if (num == null) {
            num = Integer.valueOf(createSoundIDFromAsset(str));
            if (num.intValue() != -1) {
                this.mPathSoundIDMap.put(str, num);
            }
        }
        return num.intValue();
    }

    public void unloadEffect(String str) {
        ArrayList<Integer> arrayList = this.mPathStreamIDsMap.get(str);
        if (arrayList != null) {
            Iterator<Integer> it = arrayList.iterator();
            while (it.hasNext()) {
                this.mSoundPool.stop(it.next().intValue());
            }
        }
        this.mPathStreamIDsMap.remove(str);
        Integer num = this.mPathSoundIDMap.get(str);
        if (num != null) {
            this.mSoundPool.unload(num.intValue());
            this.mPathSoundIDMap.remove(str);
        }
    }

    public int playEffect(String str, boolean z) {
        int i;
        Integer num = this.mPathSoundIDMap.get(str);
        if (num != null) {
            return doPlayEffect(str, num.intValue(), z);
        }
        Integer valueOf = Integer.valueOf(preloadEffect(str));
        if (valueOf.intValue() == -1) {
            return -1;
        }
        synchronized (this.mSoundPool) {
            this.mEffecToPlayWhenLoadedArray.add(new SoundInfoForLoadedCompleted(str, valueOf.intValue(), z));
            try {
                this.mSemaphore.acquire();
                i = this.mStreamIdSyn;
            } catch (Exception unused) {
                return -1;
            }
        }
        return i;
    }

    public void stopEffect(int i) {
        this.mSoundPool.stop(i);
        for (String str : this.mPathStreamIDsMap.keySet()) {
            if (this.mPathStreamIDsMap.get(str).contains(Integer.valueOf(i))) {
                this.mPathStreamIDsMap.get(str).remove(this.mPathStreamIDsMap.get(str).indexOf(Integer.valueOf(i)));
                return;
            }
        }
    }

    public void pauseEffect(int i) {
        this.mSoundPool.pause(i);
    }

    public void resumeEffect(int i) {
        this.mSoundPool.resume(i);
    }

    public void pauseAllEffects() {
        this.mSoundPool.autoPause();
    }

    public void resumeAllEffects() {
        if (this.mPathStreamIDsMap.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<String, ArrayList<Integer>>> it = this.mPathStreamIDsMap.entrySet().iterator();
        while (it.hasNext()) {
            Iterator<Integer> it2 = it.next().getValue().iterator();
            while (it2.hasNext()) {
                this.mSoundPool.resume(it2.next().intValue());
            }
        }
    }

    public void stopAllEffects() {
        if (!this.mPathStreamIDsMap.isEmpty()) {
            Iterator<Map.Entry<String, ArrayList<Integer>>> it = this.mPathStreamIDsMap.entrySet().iterator();
            while (it.hasNext()) {
                Iterator<Integer> it2 = it.next().getValue().iterator();
                while (it2.hasNext()) {
                    this.mSoundPool.stop(it2.next().intValue());
                }
            }
        }
        this.mPathStreamIDsMap.clear();
    }

    public float getEffectsVolume() {
        return (this.mLeftVolume + this.mRightVolume) / 2.0f;
    }

    public void setEffectsVolume(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > SOUND_RATE) {
            f = SOUND_RATE;
        }
        this.mRightVolume = f;
        this.mLeftVolume = f;
        if (this.mPathStreamIDsMap.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<String, ArrayList<Integer>>> it = this.mPathStreamIDsMap.entrySet().iterator();
        while (it.hasNext()) {
            Iterator<Integer> it2 = it.next().getValue().iterator();
            while (it2.hasNext()) {
                this.mSoundPool.setVolume(it2.next().intValue(), this.mLeftVolume, this.mRightVolume);
            }
        }
    }

    public void end() {
        this.mSoundPool.release();
        this.mPathStreamIDsMap.clear();
        this.mPathSoundIDMap.clear();
        this.mEffecToPlayWhenLoadedArray.clear();
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        initData();
    }

    public int createSoundIDFromAsset(String str) {
        int i;
        try {
            if (str.startsWith("/")) {
                i = this.mSoundPool.load(str, 0);
            } else {
                i = this.mSoundPool.load(this.mContext.getAssets().openFd(str), 0);
            }
        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
            i = -1;
        }
        if (i == 0) {
            return -1;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int doPlayEffect(String str, int i, boolean z) {
        int play = this.mSoundPool.play(i, this.mLeftVolume, this.mRightVolume, 1, z ? -1 : 0, SOUND_RATE);
        ArrayList<Integer> arrayList = this.mPathStreamIDsMap.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mPathStreamIDsMap.put(str, arrayList);
        }
        arrayList.add(Integer.valueOf(play));
        return play;
    }

    /* loaded from: classes.dex */
    public class SoundInfoForLoadedCompleted {
        public boolean isLoop;
        public String path;
        public int soundID;

        public SoundInfoForLoadedCompleted(String str, int i, boolean z) {
            this.path = str;
            this.soundID = i;
            this.isLoop = z;
        }
    }

    /* loaded from: classes.dex */
    public class OnLoadCompletedListener implements SoundPool.OnLoadCompleteListener {
        public OnLoadCompletedListener() {
        }

        @Override // android.media.SoundPool.OnLoadCompleteListener
        public void onLoadComplete(SoundPool soundPool, int i, int i2) {
            if (i2 == 0) {
                Iterator it = Cocos2dxSound.this.mEffecToPlayWhenLoadedArray.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    SoundInfoForLoadedCompleted soundInfoForLoadedCompleted = (SoundInfoForLoadedCompleted) it.next();
                    if (i == soundInfoForLoadedCompleted.soundID) {
                        Cocos2dxSound cocos2dxSound = Cocos2dxSound.this;
                        cocos2dxSound.mStreamIdSyn = cocos2dxSound.doPlayEffect(soundInfoForLoadedCompleted.path, soundInfoForLoadedCompleted.soundID, soundInfoForLoadedCompleted.isLoop);
                        Cocos2dxSound.this.mEffecToPlayWhenLoadedArray.remove(soundInfoForLoadedCompleted);
                        break;
                    }
                }
            } else {
                Cocos2dxSound.this.mStreamIdSyn = -1;
            }
            Cocos2dxSound.this.mSemaphore.release();
        }
    }
}
