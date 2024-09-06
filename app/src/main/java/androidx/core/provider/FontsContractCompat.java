package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.provider.SelfDestructiveThread;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class FontsContractCompat {
    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    public static final String PARCEL_FONT_RESULTS = "font_results";
    static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
    static final Object sLock = new Object();
    static final SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>> sPendingReplies = new SimpleArrayMap<>();
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() { // from class: androidx.core.provider.FontsContractCompat.5
        AnonymousClass5() {
        }

        @Override // java.util.Comparator
        public int compare(byte[] bArr, byte[] bArr2) {
            int i;
            int i2;
            if (bArr.length != bArr2.length) {
                i = bArr.length;
                i2 = bArr2.length;
            } else {
                for (int i3 = 0; i3 < bArr.length; i3++) {
                    if (bArr[i3] != bArr2[i3]) {
                        i = bArr[i3];
                        i2 = bArr2[i3];
                    }
                }
                return 0;
            }
            return i - i2;
        }
    };

    /* loaded from: classes.dex */
    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    /* loaded from: classes.dex */
    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
        public static final int RESULT_OK = 0;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface FontRequestFailReason {
        }

        public void onTypefaceRequestFailed(int i) {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }
    }

    private FontsContractCompat() {
    }

    static TypefaceResult getFontInternal(Context context, FontRequest fontRequest, int i) {
        try {
            FontFamilyResult fetchFonts = fetchFonts(context, null, fontRequest);
            if (fetchFonts.getStatusCode() == 0) {
                Typeface createFromFontInfo = TypefaceCompat.createFromFontInfo(context, null, fetchFonts.getFonts(), i);
                return new TypefaceResult(createFromFontInfo, createFromFontInfo != null ? 0 : -3);
            }
            return new TypefaceResult(null, fetchFonts.getStatusCode() == 1 ? -2 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new TypefaceResult(null, -1);
        }
    }

    /* loaded from: classes.dex */
    private static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(Typeface typeface, int i) {
            this.mTypeface = typeface;
            this.mResult = i;
        }
    }

    public static void resetCache() {
        sTypefaceCache.evictAll();
    }

    public static Typeface getFontSync(Context context, FontRequest fontRequest, ResourcesCompat.FontCallback fontCallback, Handler handler, boolean z, int i, int i2) {
        String str = fontRequest.getIdentifier() + "-" + i2;
        Typeface typeface = sTypefaceCache.get(str);
        if (typeface != null) {
            if (fontCallback != null) {
                fontCallback.onFontRetrieved(typeface);
            }
            return typeface;
        }
        if (z && i == -1) {
            TypefaceResult fontInternal = getFontInternal(context, fontRequest, i2);
            if (fontCallback != null) {
                if (fontInternal.mResult == 0) {
                    fontCallback.callbackSuccessAsync(fontInternal.mTypeface, handler);
                } else {
                    fontCallback.callbackFailAsync(fontInternal.mResult, handler);
                }
            }
            return fontInternal.mTypeface;
        }
        AnonymousClass1 anonymousClass1 = new Callable<TypefaceResult>() { // from class: androidx.core.provider.FontsContractCompat.1
            final /* synthetic */ Context val$context;
            final /* synthetic */ String val$id;
            final /* synthetic */ FontRequest val$request;
            final /* synthetic */ int val$style;

            AnonymousClass1(Context context2, FontRequest fontRequest2, int i22, String str2) {
                r1 = context2;
                r2 = fontRequest2;
                r3 = i22;
                r4 = str2;
            }

            @Override // java.util.concurrent.Callable
            public TypefaceResult call() throws Exception {
                TypefaceResult fontInternal2 = FontsContractCompat.getFontInternal(r1, r2, r3);
                if (fontInternal2.mTypeface != null) {
                    FontsContractCompat.sTypefaceCache.put(r4, fontInternal2.mTypeface);
                }
                return fontInternal2;
            }
        };
        if (z) {
            try {
                return ((TypefaceResult) sBackgroundThread.postAndWait(anonymousClass1, i)).mTypeface;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        AnonymousClass2 anonymousClass2 = fontCallback == null ? null : new SelfDestructiveThread.ReplyCallback<TypefaceResult>() { // from class: androidx.core.provider.FontsContractCompat.2
            final /* synthetic */ Handler val$handler;

            AnonymousClass2(Handler handler2) {
                r2 = handler2;
            }

            @Override // androidx.core.provider.SelfDestructiveThread.ReplyCallback
            public void onReply(TypefaceResult typefaceResult) {
                if (typefaceResult == null) {
                    ResourcesCompat.FontCallback.this.callbackFailAsync(1, r2);
                } else if (typefaceResult.mResult == 0) {
                    ResourcesCompat.FontCallback.this.callbackSuccessAsync(typefaceResult.mTypeface, r2);
                } else {
                    ResourcesCompat.FontCallback.this.callbackFailAsync(typefaceResult.mResult, r2);
                }
            }
        };
        synchronized (sLock) {
            ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>> arrayList = sPendingReplies.get(str2);
            if (arrayList != null) {
                if (anonymousClass2 != null) {
                    arrayList.add(anonymousClass2);
                }
                return null;
            }
            if (anonymousClass2 != null) {
                ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>> arrayList2 = new ArrayList<>();
                arrayList2.add(anonymousClass2);
                sPendingReplies.put(str2, arrayList2);
            }
            sBackgroundThread.postAndReply(anonymousClass1, new SelfDestructiveThread.ReplyCallback<TypefaceResult>() { // from class: androidx.core.provider.FontsContractCompat.3
                final /* synthetic */ String val$id;

                AnonymousClass3(String str2) {
                    r1 = str2;
                }

                @Override // androidx.core.provider.SelfDestructiveThread.ReplyCallback
                public void onReply(TypefaceResult typefaceResult) {
                    synchronized (FontsContractCompat.sLock) {
                        ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>> arrayList3 = FontsContractCompat.sPendingReplies.get(r1);
                        if (arrayList3 == null) {
                            return;
                        }
                        FontsContractCompat.sPendingReplies.remove(r1);
                        for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                            arrayList3.get(i3).onReply(typefaceResult);
                        }
                    }
                }
            });
            return null;
        }
    }

    /* renamed from: androidx.core.provider.FontsContractCompat$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements Callable<TypefaceResult> {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$id;
        final /* synthetic */ FontRequest val$request;
        final /* synthetic */ int val$style;

        AnonymousClass1(Context context2, FontRequest fontRequest2, int i22, String str2) {
            r1 = context2;
            r2 = fontRequest2;
            r3 = i22;
            r4 = str2;
        }

        @Override // java.util.concurrent.Callable
        public TypefaceResult call() throws Exception {
            TypefaceResult fontInternal2 = FontsContractCompat.getFontInternal(r1, r2, r3);
            if (fontInternal2.mTypeface != null) {
                FontsContractCompat.sTypefaceCache.put(r4, fontInternal2.mTypeface);
            }
            return fontInternal2;
        }
    }

    /* renamed from: androidx.core.provider.FontsContractCompat$2 */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements SelfDestructiveThread.ReplyCallback<TypefaceResult> {
        final /* synthetic */ Handler val$handler;

        AnonymousClass2(Handler handler2) {
            r2 = handler2;
        }

        @Override // androidx.core.provider.SelfDestructiveThread.ReplyCallback
        public void onReply(TypefaceResult typefaceResult) {
            if (typefaceResult == null) {
                ResourcesCompat.FontCallback.this.callbackFailAsync(1, r2);
            } else if (typefaceResult.mResult == 0) {
                ResourcesCompat.FontCallback.this.callbackSuccessAsync(typefaceResult.mTypeface, r2);
            } else {
                ResourcesCompat.FontCallback.this.callbackFailAsync(typefaceResult.mResult, r2);
            }
        }
    }

    /* renamed from: androidx.core.provider.FontsContractCompat$3 */
    /* loaded from: classes.dex */
    class AnonymousClass3 implements SelfDestructiveThread.ReplyCallback<TypefaceResult> {
        final /* synthetic */ String val$id;

        AnonymousClass3(String str2) {
            r1 = str2;
        }

        @Override // androidx.core.provider.SelfDestructiveThread.ReplyCallback
        public void onReply(TypefaceResult typefaceResult) {
            synchronized (FontsContractCompat.sLock) {
                ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>> arrayList3 = FontsContractCompat.sPendingReplies.get(r1);
                if (arrayList3 == null) {
                    return;
                }
                FontsContractCompat.sPendingReplies.remove(r1);
                for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                    arrayList3.get(i3).onReply(typefaceResult);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        public FontInfo(Uri uri, int i, int i2, boolean z, int i3) {
            this.mUri = (Uri) Preconditions.checkNotNull(uri);
            this.mTtcIndex = i;
            this.mWeight = i2;
            this.mItalic = z;
            this.mResultCode = i3;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public int getResultCode() {
            return this.mResultCode;
        }
    }

    /* loaded from: classes.dex */
    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        public FontFamilyResult(int i, FontInfo[] fontInfoArr) {
            this.mStatusCode = i;
            this.mFonts = fontInfoArr;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }
    }

    public static void requestFont(Context context, FontRequest fontRequest, FontRequestCallback fontRequestCallback, Handler handler) {
        requestFontInternal(context.getApplicationContext(), fontRequest, fontRequestCallback, handler);
    }

    /* renamed from: androidx.core.provider.FontsContractCompat$4 */
    /* loaded from: classes.dex */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ Context val$appContext;
        final /* synthetic */ FontRequestCallback val$callback;
        final /* synthetic */ Handler val$callerThreadHandler;
        final /* synthetic */ FontRequest val$request;

        AnonymousClass4(Context context, FontRequest fontRequest, Handler handler, FontRequestCallback fontRequestCallback) {
            r1 = context;
            r2 = fontRequest;
            r3 = handler;
            r4 = fontRequestCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                FontFamilyResult fetchFonts = FontsContractCompat.fetchFonts(r1, null, r2);
                if (fetchFonts.getStatusCode() != 0) {
                    int statusCode = fetchFonts.getStatusCode();
                    if (statusCode == 1) {
                        r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.2
                            AnonymousClass2() {
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                r4.onTypefaceRequestFailed(-2);
                            }
                        });
                        return;
                    } else if (statusCode == 2) {
                        r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.3
                            AnonymousClass3() {
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                r4.onTypefaceRequestFailed(-3);
                            }
                        });
                        return;
                    } else {
                        r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.4
                            RunnableC00004() {
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                r4.onTypefaceRequestFailed(-3);
                            }
                        });
                        return;
                    }
                }
                FontInfo[] fonts = fetchFonts.getFonts();
                if (fonts == null || fonts.length == 0) {
                    r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.5
                        AnonymousClass5() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            r4.onTypefaceRequestFailed(1);
                        }
                    });
                    return;
                }
                for (FontInfo fontInfo : fonts) {
                    if (fontInfo.getResultCode() != 0) {
                        int resultCode = fontInfo.getResultCode();
                        if (resultCode < 0) {
                            r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.6
                                AnonymousClass6() {
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    r4.onTypefaceRequestFailed(-3);
                                }
                            });
                            return;
                        } else {
                            r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.7
                                final /* synthetic */ int val$resultCode;

                                AnonymousClass7(int resultCode2) {
                                    r2 = resultCode2;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    r4.onTypefaceRequestFailed(r2);
                                }
                            });
                            return;
                        }
                    }
                }
                Typeface buildTypeface = FontsContractCompat.buildTypeface(r1, null, fonts);
                if (buildTypeface == null) {
                    r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.8
                        AnonymousClass8() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            r4.onTypefaceRequestFailed(-3);
                        }
                    });
                } else {
                    r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.9
                        final /* synthetic */ Typeface val$typeface;

                        AnonymousClass9(Typeface buildTypeface2) {
                            r2 = buildTypeface2;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            r4.onTypefaceRetrieved(r2);
                        }
                    });
                }
            } catch (PackageManager.NameNotFoundException unused) {
                r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.1
                    AnonymousClass1() {
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        r4.onTypefaceRequestFailed(-1);
                    }
                });
            }
        }

        /* renamed from: androidx.core.provider.FontsContractCompat$4$1 */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                r4.onTypefaceRequestFailed(-1);
            }
        }

        /* renamed from: androidx.core.provider.FontsContractCompat$4$2 */
        /* loaded from: classes.dex */
        class AnonymousClass2 implements Runnable {
            AnonymousClass2() {
            }

            @Override // java.lang.Runnable
            public void run() {
                r4.onTypefaceRequestFailed(-2);
            }
        }

        /* renamed from: androidx.core.provider.FontsContractCompat$4$3 */
        /* loaded from: classes.dex */
        class AnonymousClass3 implements Runnable {
            AnonymousClass3() {
            }

            @Override // java.lang.Runnable
            public void run() {
                r4.onTypefaceRequestFailed(-3);
            }
        }

        /* renamed from: androidx.core.provider.FontsContractCompat$4$4 */
        /* loaded from: classes.dex */
        class RunnableC00004 implements Runnable {
            RunnableC00004() {
            }

            @Override // java.lang.Runnable
            public void run() {
                r4.onTypefaceRequestFailed(-3);
            }
        }

        /* renamed from: androidx.core.provider.FontsContractCompat$4$5 */
        /* loaded from: classes.dex */
        class AnonymousClass5 implements Runnable {
            AnonymousClass5() {
            }

            @Override // java.lang.Runnable
            public void run() {
                r4.onTypefaceRequestFailed(1);
            }
        }

        /* renamed from: androidx.core.provider.FontsContractCompat$4$6 */
        /* loaded from: classes.dex */
        class AnonymousClass6 implements Runnable {
            AnonymousClass6() {
            }

            @Override // java.lang.Runnable
            public void run() {
                r4.onTypefaceRequestFailed(-3);
            }
        }

        /* renamed from: androidx.core.provider.FontsContractCompat$4$7 */
        /* loaded from: classes.dex */
        class AnonymousClass7 implements Runnable {
            final /* synthetic */ int val$resultCode;

            AnonymousClass7(int resultCode2) {
                r2 = resultCode2;
            }

            @Override // java.lang.Runnable
            public void run() {
                r4.onTypefaceRequestFailed(r2);
            }
        }

        /* renamed from: androidx.core.provider.FontsContractCompat$4$8 */
        /* loaded from: classes.dex */
        class AnonymousClass8 implements Runnable {
            AnonymousClass8() {
            }

            @Override // java.lang.Runnable
            public void run() {
                r4.onTypefaceRequestFailed(-3);
            }
        }

        /* renamed from: androidx.core.provider.FontsContractCompat$4$9 */
        /* loaded from: classes.dex */
        class AnonymousClass9 implements Runnable {
            final /* synthetic */ Typeface val$typeface;

            AnonymousClass9(Typeface buildTypeface2) {
                r2 = buildTypeface2;
            }

            @Override // java.lang.Runnable
            public void run() {
                r4.onTypefaceRetrieved(r2);
            }
        }
    }

    private static void requestFontInternal(Context context, FontRequest fontRequest, FontRequestCallback fontRequestCallback, Handler handler) {
        handler.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4
            final /* synthetic */ Context val$appContext;
            final /* synthetic */ FontRequestCallback val$callback;
            final /* synthetic */ Handler val$callerThreadHandler;
            final /* synthetic */ FontRequest val$request;

            AnonymousClass4(Context context2, FontRequest fontRequest2, Handler handler2, FontRequestCallback fontRequestCallback2) {
                r1 = context2;
                r2 = fontRequest2;
                r3 = handler2;
                r4 = fontRequestCallback2;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    FontFamilyResult fetchFonts = FontsContractCompat.fetchFonts(r1, null, r2);
                    if (fetchFonts.getStatusCode() != 0) {
                        int statusCode = fetchFonts.getStatusCode();
                        if (statusCode == 1) {
                            r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.2
                                AnonymousClass2() {
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    r4.onTypefaceRequestFailed(-2);
                                }
                            });
                            return;
                        } else if (statusCode == 2) {
                            r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.3
                                AnonymousClass3() {
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    r4.onTypefaceRequestFailed(-3);
                                }
                            });
                            return;
                        } else {
                            r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.4
                                RunnableC00004() {
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    r4.onTypefaceRequestFailed(-3);
                                }
                            });
                            return;
                        }
                    }
                    FontInfo[] fonts = fetchFonts.getFonts();
                    if (fonts == null || fonts.length == 0) {
                        r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.5
                            AnonymousClass5() {
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                r4.onTypefaceRequestFailed(1);
                            }
                        });
                        return;
                    }
                    for (FontInfo fontInfo : fonts) {
                        if (fontInfo.getResultCode() != 0) {
                            int resultCode2 = fontInfo.getResultCode();
                            if (resultCode2 < 0) {
                                r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.6
                                    AnonymousClass6() {
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        r4.onTypefaceRequestFailed(-3);
                                    }
                                });
                                return;
                            } else {
                                r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.7
                                    final /* synthetic */ int val$resultCode;

                                    AnonymousClass7(int resultCode22) {
                                        r2 = resultCode22;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        r4.onTypefaceRequestFailed(r2);
                                    }
                                });
                                return;
                            }
                        }
                    }
                    Typeface buildTypeface2 = FontsContractCompat.buildTypeface(r1, null, fonts);
                    if (buildTypeface2 == null) {
                        r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.8
                            AnonymousClass8() {
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                r4.onTypefaceRequestFailed(-3);
                            }
                        });
                    } else {
                        r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.9
                            final /* synthetic */ Typeface val$typeface;

                            AnonymousClass9(Typeface buildTypeface22) {
                                r2 = buildTypeface22;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                r4.onTypefaceRetrieved(r2);
                            }
                        });
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    r3.post(new Runnable() { // from class: androidx.core.provider.FontsContractCompat.4.1
                        AnonymousClass1() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            r4.onTypefaceRequestFailed(-1);
                        }
                    });
                }
            }

            /* renamed from: androidx.core.provider.FontsContractCompat$4$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements Runnable {
                AnonymousClass1() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    r4.onTypefaceRequestFailed(-1);
                }
            }

            /* renamed from: androidx.core.provider.FontsContractCompat$4$2 */
            /* loaded from: classes.dex */
            class AnonymousClass2 implements Runnable {
                AnonymousClass2() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    r4.onTypefaceRequestFailed(-2);
                }
            }

            /* renamed from: androidx.core.provider.FontsContractCompat$4$3 */
            /* loaded from: classes.dex */
            class AnonymousClass3 implements Runnable {
                AnonymousClass3() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    r4.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: androidx.core.provider.FontsContractCompat$4$4 */
            /* loaded from: classes.dex */
            class RunnableC00004 implements Runnable {
                RunnableC00004() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    r4.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: androidx.core.provider.FontsContractCompat$4$5 */
            /* loaded from: classes.dex */
            class AnonymousClass5 implements Runnable {
                AnonymousClass5() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    r4.onTypefaceRequestFailed(1);
                }
            }

            /* renamed from: androidx.core.provider.FontsContractCompat$4$6 */
            /* loaded from: classes.dex */
            class AnonymousClass6 implements Runnable {
                AnonymousClass6() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    r4.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: androidx.core.provider.FontsContractCompat$4$7 */
            /* loaded from: classes.dex */
            class AnonymousClass7 implements Runnable {
                final /* synthetic */ int val$resultCode;

                AnonymousClass7(int resultCode22) {
                    r2 = resultCode22;
                }

                @Override // java.lang.Runnable
                public void run() {
                    r4.onTypefaceRequestFailed(r2);
                }
            }

            /* renamed from: androidx.core.provider.FontsContractCompat$4$8 */
            /* loaded from: classes.dex */
            class AnonymousClass8 implements Runnable {
                AnonymousClass8() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    r4.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: androidx.core.provider.FontsContractCompat$4$9 */
            /* loaded from: classes.dex */
            class AnonymousClass9 implements Runnable {
                final /* synthetic */ Typeface val$typeface;

                AnonymousClass9(Typeface buildTypeface22) {
                    r2 = buildTypeface22;
                }

                @Override // java.lang.Runnable
                public void run() {
                    r4.onTypefaceRetrieved(r2);
                }
            }
        });
    }

    public static Typeface buildTypeface(Context context, CancellationSignal cancellationSignal, FontInfo[] fontInfoArr) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fontInfoArr, 0);
    }

    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
        HashMap hashMap = new HashMap();
        for (FontInfo fontInfo : fontInfoArr) {
            if (fontInfo.getResultCode() == 0) {
                Uri uri = fontInfo.getUri();
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, TypefaceCompatUtil.mmap(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public static FontFamilyResult fetchFonts(Context context, CancellationSignal cancellationSignal, FontRequest fontRequest) throws PackageManager.NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return new FontFamilyResult(1, null);
        }
        return new FontFamilyResult(0, getFontFromProvider(context, fontRequest, provider.authority, cancellationSignal));
    }

    public static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        }
        if (!resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
        List<byte[]> convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
        Collections.sort(convertToByteArrayList, sByteArrayComparator);
        List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
        for (int i = 0; i < certificates.size(); i++) {
            ArrayList arrayList = new ArrayList(certificates.get(i));
            Collections.sort(arrayList, sByteArrayComparator);
            if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                return resolveContentProvider;
            }
        }
        return null;
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    /* renamed from: androidx.core.provider.FontsContractCompat$5 */
    /* loaded from: classes.dex */
    class AnonymousClass5 implements Comparator<byte[]> {
        AnonymousClass5() {
        }

        @Override // java.util.Comparator
        public int compare(byte[] bArr, byte[] bArr2) {
            int i;
            int i2;
            if (bArr.length != bArr2.length) {
                i = bArr.length;
                i2 = bArr2.length;
            } else {
                for (int i3 = 0; i3 < bArr.length; i3++) {
                    if (bArr[i3] != bArr2[i3]) {
                        i = bArr[i3];
                        i2 = bArr2[i3];
                    }
                }
                return 0;
            }
            return i - i2;
        }
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x013a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static androidx.core.provider.FontsContractCompat.FontInfo[] getFontFromProvider(android.content.Context r23, androidx.core.provider.FontRequest r24, java.lang.String r25, android.os.CancellationSignal r26) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontsContractCompat.getFontFromProvider(android.content.Context, androidx.core.provider.FontRequest, java.lang.String, android.os.CancellationSignal):androidx.core.provider.FontsContractCompat$FontInfo[]");
    }
}
