package www.kaiqigu.com;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.easyext.classes.AndroidEXTHelper;
import com.easyext.classes.JsonParser;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class VoiceHelper {
    private static String TAG = "voiceHelper";
    public JinQu activity;
    private RecognizerDialog iatDialog;
    private SpeechRecognizer mIat;
    private Toast mToast;
    public String platformName;
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    public Handler myHandler = new Handler() { // from class: www.kaiqigu.com.VoiceHelper.1
        AnonymousClass1() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
        }
    };
    private InitListener mInitListener = new InitListener() { // from class: www.kaiqigu.com.VoiceHelper.2
        AnonymousClass2() {
        }

        @Override // com.iflytek.cloud.InitListener
        public void onInit(int i) {
            Log.d(VoiceHelper.TAG, "SpeechRecognizer init() code = " + i);
        }
    };
    private RecognizerListener recognizerListener = new RecognizerListener() { // from class: www.kaiqigu.com.VoiceHelper.3
        @Override // com.iflytek.cloud.RecognizerListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
        }

        AnonymousClass3() {
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onBeginOfSpeech() {
            VoiceHelper.this.showTip("开始说话");
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onError(SpeechError speechError) {
            VoiceHelper.this.showTip(speechError.getPlainDescription(true));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onEndOfSpeech() {
            VoiceHelper.this.showTip("结束说话");
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onResult(RecognizerResult recognizerResult, boolean z) {
            Log.d(VoiceHelper.TAG, recognizerResult.getResultString());
            String parseIatResult = JsonParser.parseIatResult(recognizerResult.getResultString());
            VoiceHelper.this.showTip(parseIatResult);
            Log.e(VoiceHelper.TAG, "text - --- " + parseIatResult);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("voiceResult", parseIatResult);
                VoiceHelper.this.SendMessageWithParameters("voiceStart", jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onVolumeChanged(int i) {
            VoiceHelper.this.showTip("当前正在说话，音量大小：" + i);
        }
    };
    int ret = 0;
    private RecognizerDialogListener recognizerDialogListener = new RecognizerDialogListener() { // from class: www.kaiqigu.com.VoiceHelper.4
        @Override // com.iflytek.cloud.ui.RecognizerDialogListener
        public void onError(SpeechError speechError) {
        }

        AnonymousClass4() {
        }

        @Override // com.iflytek.cloud.ui.RecognizerDialogListener
        public void onResult(RecognizerResult recognizerResult, boolean z) {
            String parseIatResult = JsonParser.parseIatResult(recognizerResult.getResultString());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("voiceResult", parseIatResult);
                VoiceHelper.this.SendMessageWithParameters("voiceStart", jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public void showTip(String str) {
    }

    public void setParam() {
    }

    /* renamed from: www.kaiqigu.com.VoiceHelper$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends Handler {
        AnonymousClass1() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
        }
    }

    public void initPlatform() {
        AndroidEXTHelper.SetEXTRecieverEXT(this);
        SpeechUtility.createUtility(this.activity, "appid=54a9f611");
        Log.e("initPlatform", "SetEXTReciever");
        this.mIat = SpeechRecognizer.createRecognizer(this.activity, this.mInitListener);
        this.iatDialog = new RecognizerDialog(this.activity, this.mInitListener);
        this.mToast = Toast.makeText(this.activity, "", 0);
    }

    /* renamed from: www.kaiqigu.com.VoiceHelper$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements InitListener {
        AnonymousClass2() {
        }

        @Override // com.iflytek.cloud.InitListener
        public void onInit(int i) {
            Log.d(VoiceHelper.TAG, "SpeechRecognizer init() code = " + i);
        }
    }

    /* renamed from: www.kaiqigu.com.VoiceHelper$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 implements RecognizerListener {
        @Override // com.iflytek.cloud.RecognizerListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
        }

        AnonymousClass3() {
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onBeginOfSpeech() {
            VoiceHelper.this.showTip("开始说话");
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onError(SpeechError speechError) {
            VoiceHelper.this.showTip(speechError.getPlainDescription(true));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onEndOfSpeech() {
            VoiceHelper.this.showTip("结束说话");
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onResult(RecognizerResult recognizerResult, boolean z) {
            Log.d(VoiceHelper.TAG, recognizerResult.getResultString());
            String parseIatResult = JsonParser.parseIatResult(recognizerResult.getResultString());
            VoiceHelper.this.showTip(parseIatResult);
            Log.e(VoiceHelper.TAG, "text - --- " + parseIatResult);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("voiceResult", parseIatResult);
                VoiceHelper.this.SendMessageWithParameters("voiceStart", jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onVolumeChanged(int i) {
            VoiceHelper.this.showTip("当前正在说话，音量大小：" + i);
        }
    }

    public void voiceStart(JSONObject jSONObject) {
        this.mIat.setParameter("params", null);
        this.mIat.setParameter(SpeechConstant.APPID, "54a9f611");
        this.mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");
        this.mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        this.mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        this.mIat.setParameter(SpeechConstant.VAD_BOS, "4000");
        this.mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
        this.mIat.setParameter(SpeechConstant.VAD_EOS, "1000");
        this.mIat.setParameter(SpeechConstant.ASR_PTT, "true");
        this.mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/iflytek/wavaudio.pcm");
        this.mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        this.iatDialog.setListener(this.recognizerDialogListener);
        this.iatDialog.show();
    }

    /* renamed from: www.kaiqigu.com.VoiceHelper$4 */
    /* loaded from: classes.dex */
    class AnonymousClass4 implements RecognizerDialogListener {
        @Override // com.iflytek.cloud.ui.RecognizerDialogListener
        public void onError(SpeechError speechError) {
        }

        AnonymousClass4() {
        }

        @Override // com.iflytek.cloud.ui.RecognizerDialogListener
        public void onResult(RecognizerResult recognizerResult, boolean z) {
            String parseIatResult = JsonParser.parseIatResult(recognizerResult.getResultString());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("voiceResult", parseIatResult);
                VoiceHelper.this.SendMessageWithParameters("voiceStart", jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    protected void onDestroy() {
        this.mIat.cancel();
        this.mIat.destroy();
    }

    public void setActivity(JinQu jinQu) {
        this.activity = jinQu;
    }

    /* renamed from: www.kaiqigu.com.VoiceHelper$5 */
    /* loaded from: classes.dex */
    class AnonymousClass5 implements Runnable {
        final /* synthetic */ String val$method;
        final /* synthetic */ JSONObject val$params;

        AnonymousClass5(String str, JSONObject jSONObject) {
            r2 = str;
            r3 = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            AndroidEXTHelper.SendMessageWithParametersEXT(r2, r3);
        }
    }

    public void SendMessageWithParameters(String str, JSONObject jSONObject) {
        this.activity.runOnGLThread(new Runnable() { // from class: www.kaiqigu.com.VoiceHelper.5
            final /* synthetic */ String val$method;
            final /* synthetic */ JSONObject val$params;

            AnonymousClass5(String str2, JSONObject jSONObject2) {
                r2 = str2;
                r3 = jSONObject2;
            }

            @Override // java.lang.Runnable
            public void run() {
                AndroidEXTHelper.SendMessageWithParametersEXT(r2, r3);
            }
        });
    }
}
