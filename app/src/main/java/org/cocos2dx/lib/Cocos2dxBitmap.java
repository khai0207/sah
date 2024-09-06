package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;

/* loaded from: classes.dex */
public class Cocos2dxBitmap {
    private static final int HORIZONTALALIGN_CENTER = 3;
    private static final int HORIZONTALALIGN_LEFT = 1;
    private static final int HORIZONTALALIGN_RIGHT = 2;
    private static final int VERTICALALIGN_BOTTOM = 2;
    private static final int VERTICALALIGN_CENTER = 3;
    private static final int VERTICALALIGN_TOP = 1;
    private static Context sContext;

    private static native void nativeInitBitmapDC(int i, int i2, byte[] bArr);

    public static void setContext(Context context) {
        sContext = context;
    }

    public static void createTextBitmap(String str, String str2, int i, int i2, int i3, int i4) {
        createTextBitmapShadowStroke(str, str2, i, 1.0f, 1.0f, 1.0f, i2, i3, i4, false, 0.0f, 0.0f, 0.0f, false, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    /*  JADX ERROR: NullPointerException in pass: LoopRegionVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.use(jadx.core.dex.instructions.args.RegisterArg)" because "ssaVar" is null
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:493)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        */
    public static void createTextBitmapShadowStroke(java.lang.String r18, java.lang.String r19, int r20, float r21, float r22, float r23, int r24, int r25, int r26, boolean r27, float r28, float r29, float r30, boolean r31, float r32, float r33, float r34, float r35) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.lib.Cocos2dxBitmap.createTextBitmapShadowStroke(java.lang.String, java.lang.String, int, float, float, float, int, int, int, boolean, float, float, float, boolean, float, float, float, float):void");
    }

    private static Paint newPaint(String str, int i, int i2) {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setTextSize(i);
        paint.setAntiAlias(true);
        if (str.endsWith(".ttf")) {
            try {
                paint.setTypeface(Cocos2dxTypefaces.get(sContext, str));
            } catch (Exception unused) {
                Log.e("Cocos2dxBitmap", "error to create ttf type face: " + str);
                paint.setTypeface(Typeface.create(str, 0));
            }
        } else {
            paint.setTypeface(Typeface.create(str, 0));
        }
        if (i2 == 2) {
            paint.setTextAlign(Paint.Align.RIGHT);
        } else if (i2 == 3) {
            paint.setTextAlign(Paint.Align.CENTER);
        } else {
            paint.setTextAlign(Paint.Align.LEFT);
        }
        return paint;
    }

    private static TextProperty computeTextProperty(String str, int i, int i2, Paint paint) {
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int ceil = (int) Math.ceil(fontMetricsInt.bottom - fontMetricsInt.top);
        String[] splitString = splitString(str, i, i2, paint);
        if (i == 0) {
            int i3 = 0;
            for (String str2 : splitString) {
                int ceil2 = (int) Math.ceil(paint.measureText(str2, 0, str2.length()));
                if (ceil2 > i3) {
                    i3 = ceil2;
                }
            }
            i = i3;
        }
        return new TextProperty(i, ceil, splitString);
    }

    private static int computeX(String str, int i, int i2) {
        if (i2 == 2) {
            return i;
        }
        if (i2 != 3) {
            return 0;
        }
        return i / 2;
    }

    private static int computeY(Paint.FontMetricsInt fontMetricsInt, int i, int i2, int i3) {
        int i4;
        int i5;
        int i6 = -fontMetricsInt.top;
        if (i <= i2) {
            return i6;
        }
        if (i3 == 1) {
            return -fontMetricsInt.top;
        }
        if (i3 == 2) {
            i4 = -fontMetricsInt.top;
            i5 = i - i2;
        } else {
            if (i3 != 3) {
                return i6;
            }
            i4 = -fontMetricsInt.top;
            i5 = (i - i2) / 2;
        }
        return i4 + i5;
    }

    private static String[] splitString(String str, int i, int i2, Paint paint) {
        String[] split = str.split("\\n");
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int ceil = i2 / ((int) Math.ceil(fontMetricsInt.bottom - fontMetricsInt.top));
        int i3 = 0;
        if (i != 0) {
            LinkedList linkedList = new LinkedList();
            int length = split.length;
            while (i3 < length) {
                String str2 = split[i3];
                if (((int) Math.ceil(paint.measureText(str2))) > i) {
                    linkedList.addAll(divideStringWithMaxWidth(str2, i, paint));
                } else {
                    linkedList.add(str2);
                }
                if (ceil > 0 && linkedList.size() >= ceil) {
                    break;
                }
                i3++;
            }
            if (ceil > 0 && linkedList.size() > ceil) {
                while (linkedList.size() > ceil) {
                    linkedList.removeLast();
                }
            }
            String[] strArr = new String[linkedList.size()];
            linkedList.toArray(strArr);
            return strArr;
        }
        if (i2 == 0 || split.length <= ceil) {
            return split;
        }
        LinkedList linkedList2 = new LinkedList();
        while (i3 < ceil) {
            linkedList2.add(split[i3]);
            i3++;
        }
        String[] strArr2 = new String[linkedList2.size()];
        linkedList2.toArray(strArr2);
        return strArr2;
    }

    private static LinkedList<String> divideStringWithMaxWidth(String str, int i, Paint paint) {
        int length = str.length();
        LinkedList<String> linkedList = new LinkedList<>();
        int i2 = 1;
        int i3 = 0;
        while (i2 <= length) {
            int ceil = (int) Math.ceil(paint.measureText(str, i3, i2));
            if (ceil >= i) {
                int lastIndexOf = str.substring(0, i2).lastIndexOf(" ");
                if (lastIndexOf != -1 && lastIndexOf > i3) {
                    linkedList.add(str.substring(i3, lastIndexOf));
                    i2 = lastIndexOf + 1;
                } else if (ceil > i) {
                    linkedList.add(str.substring(i3, i2 - 1));
                    i2--;
                } else {
                    linkedList.add(str.substring(i3, i2));
                }
                while (i2 < length && str.charAt(i2) == ' ') {
                    i2++;
                }
                i3 = i2;
            }
            i2++;
        }
        if (i3 < length) {
            linkedList.add(str.substring(i3));
        }
        return linkedList;
    }

    private static String refactorString(String str) {
        if (str.compareTo("") == 0) {
            return " ";
        }
        StringBuilder sb = new StringBuilder(str);
        int i = 0;
        for (int indexOf = sb.indexOf("\n"); indexOf != -1; indexOf = sb.indexOf("\n", i)) {
            if (indexOf == 0 || sb.charAt(indexOf - 1) == '\n') {
                sb.insert(i, " ");
                i = indexOf + 2;
            } else {
                i = indexOf + 1;
            }
            if (i > sb.length() || indexOf == sb.length()) {
                break;
            }
        }
        return sb.toString();
    }

    private static void initNativeObject(Bitmap bitmap) {
        byte[] pixels = getPixels(bitmap);
        if (pixels == null) {
            return;
        }
        nativeInitBitmapDC(bitmap.getWidth(), bitmap.getHeight(), pixels);
    }

    private static byte[] getPixels(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        byte[] bArr = new byte[bitmap.getWidth() * bitmap.getHeight() * 4];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.nativeOrder());
        bitmap.copyPixelsToBuffer(wrap);
        return bArr;
    }

    private static int getFontSizeAccordingHeight(int i) {
        Paint paint = new Paint();
        Rect rect = new Rect();
        paint.setTypeface(Typeface.DEFAULT);
        boolean z = false;
        int i2 = 1;
        while (!z) {
            paint.setTextSize(i2);
            paint.getTextBounds("SghMNy", 0, 6, rect);
            i2++;
            if (i - rect.height() <= 2) {
                z = true;
            }
        }
        return i2;
    }

    private static String getStringWithEllipsis(String str, float f, float f2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setTextSize(f2);
        return TextUtils.ellipsize(str, textPaint, f, TextUtils.TruncateAt.END).toString();
    }

    /* loaded from: classes.dex */
    private static class TextProperty {
        private final int mHeightPerLine;
        private final String[] mLines;
        private final int mMaxWidth;
        private final int mTotalHeight;

        TextProperty(int i, int i2, String[] strArr) {
            this.mMaxWidth = i;
            this.mHeightPerLine = i2;
            this.mTotalHeight = i2 * strArr.length;
            this.mLines = strArr;
        }
    }
}
