package com.iflytek.cloud.b;

import android.os.Environment;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.speech.TextUnderstanderAidl;

/* loaded from: classes.dex */
public class b {
    public static String a = "wtime";
    public static final String b = Environment.getExternalStorageDirectory().getPath() + "/msc/ist/audio/";
    public static final String[][] c = {new String[]{"surl", "server_url"}, new String[]{"besturl_search", "search_best_url"}, new String[]{"bsts", "search_best_url"}, new String[]{"asr_sch", "sch"}, new String[]{TextUnderstanderAidl.SCENE, "scn"}, new String[]{SpeechConstant.ASR_NOMATCH_ERROR, "asr_nme"}, new String[]{SpeechConstant.ASR_PTT, "ptt"}, new String[]{SpeechConstant.RESULT_TYPE, "rst"}, new String[]{SpeechConstant.SEARCH_AREA, "area"}, new String[]{SpeechConstant.VAD_BOS, "vad_timeout"}, new String[]{"bos", "vad_timeout"}, new String[]{SpeechConstant.VAD_EOS, "vad_speech_tail", "eos"}, new String[]{"eos", "vad_speech_tail", "eos"}, new String[]{SpeechConstant.ASR_AUDIO_PATH, "aap"}, new String[]{SpeechConstant.TTS_BUFFER_TIME, "tbt"}, new String[]{SpeechConstant.TTS_AUDIO_PATH, "tap"}, new String[]{SpeechConstant.SUBJECT, "sub"}, new String[]{SpeechConstant.DATA_TYPE, "dtt"}, new String[]{SpeechConstant.ASR_NBEST, "nbest"}, new String[]{SpeechConstant.ASR_WBEST, "wbest"}, new String[]{SpeechConstant.VOICE_NAME, "vcn"}, new String[]{SpeechConstant.BACKGROUND_SOUND, "bgs"}, new String[]{SpeechConstant.TEXT_ENCODING, "tte"}};
}
