package com.iflytek.msc;

import java.io.FileDescriptor;

/* loaded from: classes.dex */
public class MSC {
    private static boolean a = false;

    public static final native int DebugLog(boolean z);

    public static final native int QHCRDataWrite(char[] cArr, byte[] bArr, byte[] bArr2, int i, int i2);

    public static final native int QHCRFini();

    public static final native byte[] QHCRGetResult(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QHCRInit(byte[] bArr);

    public static final native int QHCRLogEvent(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native char[] QHCRSessionBegin(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QHCRSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QISEAudioWrite(char[] cArr, byte[] bArr, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISEFini();

    public static final native int QISEGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QISEGetResult(char[] cArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QISEInit(byte[] bArr);

    public static final native char[] QISESessionBegin(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISESessionEnd(char[] cArr, byte[] bArr);

    public static final native int QISETextPut(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native int QISRAudioWrite(char[] cArr, byte[] bArr, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISRBuildGrammar(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, String str, Object obj);

    public static final native int QISRFini();

    public static final native int QISRGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QISRGetResult(char[] cArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QISRGrammarActivate(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native int QISRInit(byte[] bArr);

    public static final native int QISRLogEvent(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native int QISRRegisterNotify(char[] cArr, String str, String str2, String str3, Object obj);

    public static final native char[] QISRSessionBegin(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISRSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QISRSetParam(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native int QISRUpdateLexicon(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, String str, Object obj);

    public static final native byte[] QISRUploadData(char[] cArr, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, MSCSessionInfo mSCSessionInfo);

    public static final native int QISVAudioWrite(char[] cArr, char[] cArr2, byte[] bArr, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISVFini();

    public static final native int QISVGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QISVGetResult(char[] cArr, char[] cArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISVInit(byte[] bArr);

    public static final native char[] QISVQueDelModel(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISVQueDelModelRelease(char[] cArr);

    public static final native char[] QISVSessionBegin(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISVSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QIVWAudioWrite(char[] cArr, byte[] bArr, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final native int QIVWRegisterNotify(char[] cArr, String str, Object obj);

    public static final native char[] QIVWSessionBegin(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QIVWSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QMSPDownload(byte[] bArr, byte[] bArr2, Object obj);

    public static final native byte[] QMSPDownloadData(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QMSPGetParam(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QMSPLogOut();

    public static final native int QMSPLogin(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static final native int QMSPRegisterNotify(String str, String str2);

    public static final native byte[] QMSPSearch(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QMSPSetParam(byte[] bArr, byte[] bArr2);

    public static final native byte[] QMSPUploadData(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QTTSAudioGet(char[] cArr, MSCSessionInfo mSCSessionInfo);

    public static final native char[] QTTSAudioInfo(char[] cArr);

    public static final native int QTTSFini();

    public static final native int QTTSGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QTTSInit(byte[] bArr);

    public static final native char[] QTTSSessionBegin(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QTTSSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QTTSTextPut(char[] cArr, byte[] bArr);

    public static final native int UMSPLogin(byte[] bArr, byte[] bArr2, byte[] bArr3, Object obj);

    public static final native int getFileDescriptorFD(FileDescriptor fileDescriptor);

    public static boolean isLoaded() {
        return a;
    }

    public static boolean loadLibrary(String str) {
        boolean z = a;
        if (z) {
            return z;
        }
        try {
            System.loadLibrary(str);
            a = true;
        } catch (UnsatisfiedLinkError unused) {
            a = false;
        }
        return a;
    }
}
