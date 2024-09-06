package org.cocos2dx.lib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CCImage_richlabel {
    private static final int HORIZONTALALIGN_CENTER = 3;
    private static final int HORIZONTALALIGN_LEFT = 1;
    private static final int HORIZONTALALIGN_RIGHT = 2;
    private static final char TAG_END = ']';
    private static final char TAG_START = '[';
    private static final int VERTICALALIGN_BOTTOM = 2;
    private static final int VERTICALALIGN_CENTER = 3;
    private static final int VERTICALALIGN_TOP = 1;

    /* loaded from: classes.dex */
    private enum SpanType {
        UNKNOWN,
        COLOR,
        FONT,
        SIZE,
        BOLD,
        ITALIC,
        UNDERLINE,
        IMAGE,
        LINK
    }

    /* loaded from: classes.dex */
    private enum TagParseState {
        READY,
        START_TAG,
        CLOSE_TAG,
        EQUAL,
        SUCCESS,
        FAIL
    }

    private static native String nativeFullPathForFilename(String str);

    private static native void nativeInitBitmapDC(int i, int i2, byte[] bArr);

    private static native void nativeResetBitmapDC();

    private static native void nativeSaveLinkMeta(int i, int i2, float f, float f2, float f3, float f4, int i3);

    private static native void nativeSaveShadowStrokePadding(float f, float f2);

    /* loaded from: classes.dex */
    static final class LinkMeta {
        float height;
        int normalBgColor;
        int selectedBgColor;
        int tag;
        float width;
        float x;
        float y;

        LinkMeta() {
        }
    }

    /* loaded from: classes.dex */
    static final class Span {
        public boolean close;
        public int color;
        public String fontName;
        public float fontSize;
        public float height;
        public String imageName;
        int normalBgColor;
        public float offsetY;
        public int pos;
        public float scaleX;
        public float scaleY;
        int selectedBgColor;
        public SpanType type;
        public float width;

        Span() {
        }
    }

    /* loaded from: classes.dex */
    static final class TagParseResult {
        boolean close;
        int dataEnd;
        int dataStart;
        int endTagPos;
        SpanType type;

        TagParseResult() {
        }
    }

    /* loaded from: classes.dex */
    static class CustomTypefaceSpan extends TypefaceSpan {
        private final Typeface newType;

        public CustomTypefaceSpan(String str, Typeface typeface) {
            super(str);
            this.newType = typeface;
        }

        @Override // android.text.style.TypefaceSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            applyCustomTypeFace(textPaint, this.newType);
        }

        @Override // android.text.style.TypefaceSpan, android.text.style.MetricAffectingSpan
        public void updateMeasureState(TextPaint textPaint) {
            applyCustomTypeFace(textPaint, this.newType);
        }

        private void applyCustomTypeFace(Paint paint, Typeface typeface) {
            Typeface typeface2 = paint.getTypeface();
            int style = (typeface2 == null ? 0 : typeface2.getStyle()) & (typeface.getStyle() ^ (-1));
            if ((style & 1) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((style & 2) != 0) {
                paint.setTextSkewX(-0.25f);
            }
            paint.setTypeface(typeface);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x042d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void createRichLabelBitmap(java.lang.String r27, java.lang.String r28, int r29, float r30, float r31, float r32, int r33, int r34, int r35, boolean r36, float r37, float r38, int r39, float r40, boolean r41, float r42, float r43, float r44, float r45, float r46, boolean r47) {
        /*
            Method dump skipped, instructions count: 1142
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.lib.CCImage_richlabel.createRichLabelBitmap(java.lang.String, java.lang.String, int, float, float, float, int, int, int, boolean, float, float, int, float, boolean, float, float, float, float, float, boolean):void");
    }

    private static void renderEmbededImages(Canvas canvas, StaticLayout staticLayout, String str, List<Span> list, Map<String, Bitmap> map) {
        if (map.isEmpty()) {
            return;
        }
        int lineCount = staticLayout.getLineCount();
        PointF[] pointFArr = new PointF[lineCount];
        for (int i = 0; i < lineCount; i++) {
            pointFArr[i] = new PointF();
            pointFArr[i].x = staticLayout.getLineLeft(i);
            pointFArr[i].y = staticLayout.getLineBaseline(i);
        }
        Point[] pointArr = new Point[lineCount];
        for (int i2 = 0; i2 < lineCount; i2++) {
            pointArr[i2] = new Point();
            pointArr[i2].x = staticLayout.getLineStart(i2);
            pointArr[i2].y = staticLayout.getLineEnd(i2);
        }
        for (int i3 = 0; i3 < lineCount; i3++) {
            for (int i4 = pointArr[i3].x; i4 < pointArr[i3].y; i4++) {
                if (str.charAt(i4) == 65532) {
                    float primaryHorizontal = staticLayout.getPrimaryHorizontal(i4);
                    Iterator<Span> it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Span next = it.next();
                            if (next.type == SpanType.IMAGE && !next.close && next.pos == i4) {
                                Bitmap bitmap = map.get(next.imageName);
                                if (bitmap != null) {
                                    canvas.drawBitmap(bitmap, primaryHorizontal + pointFArr[i3].x, (pointFArr[i3].y - next.offsetY) - bitmap.getHeight(), (Paint) null);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void extractLinkMeta(StaticLayout staticLayout, List<Span> list, float f) {
        int i;
        Point[] pointArr;
        float primaryHorizontal;
        float primaryHorizontal2;
        StaticLayout staticLayout2 = staticLayout;
        int lineCount = staticLayout.getLineCount();
        float height = staticLayout.getHeight();
        PointF[] pointFArr = new PointF[lineCount];
        for (int i2 = 0; i2 < lineCount; i2++) {
            pointFArr[i2] = new PointF();
            pointFArr[i2].x = staticLayout2.getLineLeft(i2) / f;
            pointFArr[i2].y = (height - staticLayout2.getLineBaseline(i2)) / f;
        }
        Point[] pointArr2 = new Point[lineCount];
        for (int i3 = 0; i3 < lineCount; i3++) {
            pointArr2[i3] = new Point();
            pointArr2[i3].x = staticLayout2.getLineStart(i3);
            pointArr2[i3].y = staticLayout2.getLineEnd(i3);
        }
        LinkMeta linkMeta = new LinkMeta();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (Span span : list) {
            if (span.type == SpanType.LINK) {
                if (!span.close) {
                    i4 = span.pos;
                    linkMeta.normalBgColor = span.normalBgColor;
                    linkMeta.selectedBgColor = span.selectedBgColor;
                } else {
                    int i8 = span.pos;
                    int i9 = 0;
                    while (true) {
                        if (i9 >= lineCount) {
                            break;
                        }
                        if (i4 >= pointArr2[i9].x && i4 < pointArr2[i9].y) {
                            i5 = i9;
                        }
                        if (i8 >= pointArr2[i9].x && i8 < pointArr2[i9].y) {
                            i7 = i9;
                            break;
                        }
                        i9++;
                    }
                    int i10 = i5;
                    while (i10 <= i7) {
                        float f2 = (-staticLayout2.getLineAscent(i10)) / f;
                        float lineDescent = staticLayout2.getLineDescent(i10) / f;
                        int i11 = i10 < i7 ? pointArr2[i10].y : i8;
                        if (i10 > i5) {
                            primaryHorizontal = staticLayout2.getLineLeft(i10);
                        } else {
                            primaryHorizontal = staticLayout2.getPrimaryHorizontal(i4);
                        }
                        float f3 = primaryHorizontal / f;
                        if (i10 < i7) {
                            primaryHorizontal2 = staticLayout2.getLineRight(i10);
                        } else {
                            primaryHorizontal2 = staticLayout2.getPrimaryHorizontal(i11);
                        }
                        linkMeta.x = f3;
                        linkMeta.y = pointFArr[i10].y - lineDescent;
                        linkMeta.width = (primaryHorizontal2 / f) - linkMeta.x;
                        linkMeta.height = lineDescent + f2;
                        linkMeta.tag = i6;
                        nativeSaveLinkMeta(linkMeta.normalBgColor, linkMeta.selectedBgColor, linkMeta.x, linkMeta.y, linkMeta.width, linkMeta.height, linkMeta.tag);
                        i10++;
                        staticLayout2 = staticLayout;
                        i4 = i11;
                        lineCount = lineCount;
                        pointArr2 = pointArr2;
                    }
                    i = lineCount;
                    pointArr = pointArr2;
                    i6++;
                    i5 = i10;
                    staticLayout2 = staticLayout;
                    lineCount = i;
                    pointArr2 = pointArr;
                }
            }
            i = lineCount;
            pointArr = pointArr2;
            staticLayout2 = staticLayout;
            lineCount = i;
            pointArr2 = pointArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c2 A[ADDED_TO_REGION, EDGE_INSN: B:26:0x00c2->B:20:0x00c2 BREAK  A[LOOP:0: B:2:0x0018->B:25:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static org.cocos2dx.lib.CCImage_richlabel.TagParseResult checkTag(java.lang.String r9, int r10) {
        /*
            org.cocos2dx.lib.CCImage_richlabel$TagParseResult r0 = new org.cocos2dx.lib.CCImage_richlabel$TagParseResult
            r0.<init>()
            org.cocos2dx.lib.CCImage_richlabel$SpanType r1 = org.cocos2dx.lib.CCImage_richlabel.SpanType.UNKNOWN
            r0.type = r1
            org.cocos2dx.lib.CCImage_richlabel$TagParseState r1 = org.cocos2dx.lib.CCImage_richlabel.TagParseState.READY
            r2 = 0
            r0.close = r2
            int r3 = r9.length()
            r0.endTagPos = r3
            r4 = -1
            r0.dataStart = r4
            r4 = r10
        L18:
            if (r4 >= r3) goto Lc2
            int[] r5 = org.cocos2dx.lib.CCImage_richlabel.AnonymousClass1.$SwitchMap$org$cocos2dx$lib$CCImage_richlabel$TagParseState
            int r6 = r1.ordinal()
            r5 = r5[r6]
            r6 = 1
            if (r5 == r6) goto Laa
            r7 = 2
            r8 = 93
            if (r5 == r7) goto L55
            r6 = 3
            if (r5 == r6) goto L40
            r6 = 4
            if (r5 == r6) goto L32
            goto Lba
        L32:
            char r5 = r9.charAt(r4)
            if (r5 != r8) goto L52
            org.cocos2dx.lib.CCImage_richlabel$TagParseState r1 = org.cocos2dx.lib.CCImage_richlabel.TagParseState.SUCCESS
            r0.endTagPos = r4
            r0.dataEnd = r4
            goto Lba
        L40:
            char r5 = r9.charAt(r4)
            if (r5 != r8) goto L52
            org.cocos2dx.lib.CCImage_richlabel$TagParseState r1 = org.cocos2dx.lib.CCImage_richlabel.TagParseState.SUCCESS
            r0.endTagPos = r4
            org.cocos2dx.lib.CCImage_richlabel$SpanType r5 = checkTagName(r9, r2, r4)
            r0.type = r5
            goto Lba
        L52:
            int r4 = r4 + 1
            goto Lba
        L55:
            int r5 = r10 + 1
            if (r4 != r5) goto L66
            char r5 = r9.charAt(r4)
            r7 = 47
            if (r5 != r7) goto L66
            org.cocos2dx.lib.CCImage_richlabel$TagParseState r1 = org.cocos2dx.lib.CCImage_richlabel.TagParseState.CLOSE_TAG
            r0.close = r6
            goto Lb4
        L66:
            char r5 = r9.charAt(r4)
            r6 = 61
            if (r5 != r6) goto L7c
            org.cocos2dx.lib.CCImage_richlabel$TagParseState r1 = org.cocos2dx.lib.CCImage_richlabel.TagParseState.EQUAL
            int r5 = r4 + 1
            org.cocos2dx.lib.CCImage_richlabel$SpanType r4 = checkTagName(r9, r2, r4)
            r0.type = r4
            r0.dataStart = r5
        L7a:
            r4 = r5
            goto Lba
        L7c:
            char r5 = r9.charAt(r4)
            if (r5 != r8) goto L95
            org.cocos2dx.lib.CCImage_richlabel$TagParseState r1 = org.cocos2dx.lib.CCImage_richlabel.TagParseState.SUCCESS
            r0.endTagPos = r4
            r0.dataEnd = r4
            org.cocos2dx.lib.CCImage_richlabel$SpanType r5 = r0.type
            org.cocos2dx.lib.CCImage_richlabel$SpanType r6 = org.cocos2dx.lib.CCImage_richlabel.SpanType.UNKNOWN
            if (r5 != r6) goto Lba
            org.cocos2dx.lib.CCImage_richlabel$SpanType r5 = checkTagName(r9, r2, r4)
            r0.type = r5
            goto Lba
        L95:
            char r5 = r9.charAt(r4)
            r6 = 32
            if (r5 != r6) goto L52
            org.cocos2dx.lib.CCImage_richlabel$TagParseState r1 = org.cocos2dx.lib.CCImage_richlabel.TagParseState.EQUAL
            int r5 = r4 + 1
            org.cocos2dx.lib.CCImage_richlabel$SpanType r4 = checkTagName(r9, r2, r4)
            r0.type = r4
            r0.dataStart = r5
            goto L7a
        Laa:
            char r1 = r9.charAt(r4)
            r5 = 91
            if (r1 != r5) goto Lb8
            org.cocos2dx.lib.CCImage_richlabel$TagParseState r1 = org.cocos2dx.lib.CCImage_richlabel.TagParseState.START_TAG
        Lb4:
            int r4 = r4 + 1
            r2 = r4
            goto Lba
        Lb8:
            org.cocos2dx.lib.CCImage_richlabel$TagParseState r1 = org.cocos2dx.lib.CCImage_richlabel.TagParseState.FAIL
        Lba:
            org.cocos2dx.lib.CCImage_richlabel$TagParseState r5 = org.cocos2dx.lib.CCImage_richlabel.TagParseState.FAIL
            if (r1 == r5) goto Lc2
            org.cocos2dx.lib.CCImage_richlabel$TagParseState r5 = org.cocos2dx.lib.CCImage_richlabel.TagParseState.SUCCESS
            if (r1 != r5) goto L18
        Lc2:
            org.cocos2dx.lib.CCImage_richlabel$TagParseState r9 = org.cocos2dx.lib.CCImage_richlabel.TagParseState.SUCCESS
            if (r1 == r9) goto Lca
            org.cocos2dx.lib.CCImage_richlabel$SpanType r9 = org.cocos2dx.lib.CCImage_richlabel.SpanType.UNKNOWN
            r0.type = r9
        Lca:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.lib.CCImage_richlabel.checkTag(java.lang.String, int):org.cocos2dx.lib.CCImage_richlabel$TagParseResult");
    }

    /* renamed from: org.cocos2dx.lib.CCImage_richlabel$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$SpanType;
        static final /* synthetic */ int[] $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$TagParseState;

        static {
            int[] iArr = new int[TagParseState.values().length];
            $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$TagParseState = iArr;
            try {
                iArr[TagParseState.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$TagParseState[TagParseState.START_TAG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$TagParseState[TagParseState.CLOSE_TAG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$TagParseState[TagParseState.EQUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[SpanType.values().length];
            $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$SpanType = iArr2;
            try {
                iArr2[SpanType.COLOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$SpanType[SpanType.FONT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$SpanType[SpanType.BOLD.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$SpanType[SpanType.ITALIC.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$SpanType[SpanType.SIZE.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$SpanType[SpanType.UNDERLINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$SpanType[SpanType.IMAGE.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$cocos2dx$lib$CCImage_richlabel$SpanType[SpanType.LINK.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0010, code lost:
    
        if (r9 != 5) goto L75;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static org.cocos2dx.lib.CCImage_richlabel.SpanType checkTagName(java.lang.String r7, int r8, int r9) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.lib.CCImage_richlabel.checkTagName(java.lang.String, int, int):org.cocos2dx.lib.CCImage_richlabel$SpanType");
    }

    private static String buildSpan(String str, List<Span> list) {
        List<Span> list2;
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean z = false;
        while (i < length) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '[':
                    Span span = new Span();
                    TagParseResult checkTag = checkTag(str, i);
                    if (checkTag.type != SpanType.UNKNOWN && (!z || (checkTag.close && checkTag.type == SpanType.IMAGE))) {
                        span.type = checkTag.type;
                        span.close = checkTag.close;
                        span.pos = sb.length();
                        if (checkTag.close) {
                            if (z) {
                                sb.append((char) 65532);
                                span.pos++;
                                list2 = list;
                                z = false;
                            }
                            list2 = list;
                        } else {
                            int i2 = AnonymousClass1.$SwitchMap$org$cocos2dx$lib$CCImage_richlabel$SpanType[span.type.ordinal()];
                            if (i2 == 1) {
                                span.color = parseColor(str, checkTag.dataStart, checkTag.dataEnd - checkTag.dataStart);
                            } else if (i2 == 2) {
                                span.fontName = str.substring(checkTag.dataStart, checkTag.dataEnd);
                            } else if (i2 == 5) {
                                try {
                                    span.fontSize = Integer.parseInt(str.substring(checkTag.dataStart, checkTag.dataEnd));
                                } catch (NumberFormatException unused) {
                                    span.fontSize = 16.0f;
                                }
                            } else if (i2 == 7) {
                                String[] split = str.substring(checkTag.dataStart, checkTag.dataEnd).split(" ");
                                span.imageName = split[0];
                                span.scaleY = 1.0f;
                                span.scaleX = 1.0f;
                                span.height = 0.0f;
                                span.width = 0.0f;
                                span.offsetY = 0.0f;
                                if (split.length > 1) {
                                    for (int i3 = 1; i3 < split.length; i3++) {
                                        String[] split2 = split[i3].split("=");
                                        if (split2.length == 2) {
                                            try {
                                                if ("scale".equals(split2[0])) {
                                                    float parseFloat = Float.parseFloat(split2[1]);
                                                    span.scaleY = parseFloat;
                                                    span.scaleX = parseFloat;
                                                } else if ("scalex".equals(split2[0])) {
                                                    span.scaleX = Float.parseFloat(split2[1]);
                                                } else if ("scaley".equals(split2[0])) {
                                                    span.scaleY = Float.parseFloat(split2[1]);
                                                } else if ("w".equals(split2[0])) {
                                                    span.width = Float.parseFloat(split2[1]);
                                                } else if ("h".equals(split2[0])) {
                                                    span.height = Float.parseFloat(split2[1]);
                                                } else if ("offsety".equals(split2[0])) {
                                                    span.offsetY = Float.parseFloat(split2[1]);
                                                }
                                            } catch (NumberFormatException unused2) {
                                            }
                                        }
                                    }
                                }
                                list2 = list;
                                z = true;
                            } else if (i2 == 8) {
                                for (String str2 : str.substring(checkTag.dataStart, checkTag.dataEnd).split(" ")) {
                                    String[] split3 = str2.split("=");
                                    if (split3.length == 2) {
                                        if ("bg".equals(split3[0])) {
                                            span.normalBgColor = parseColor(split3[1], 0, split3[1].length());
                                        } else if ("bg_click".equals(split3[0])) {
                                            span.selectedBgColor = parseColor(split3[1], 0, split3[1].length());
                                        }
                                    }
                                }
                            }
                            list2 = list;
                        }
                        list2.add(span);
                        i = checkTag.endTagPos;
                        break;
                    }
                    break;
                case '\\':
                    if (i < length - 1) {
                        int i4 = i + 1;
                        char charAt2 = str.charAt(i4);
                        if (charAt2 == '[' || charAt2 == ']') {
                            sb.append(charAt2);
                            i = i4;
                            break;
                        }
                    } else {
                        sb.append(charAt);
                        break;
                    }
                    break;
                case ']':
                    break;
                default:
                    if (!z) {
                        sb.append(charAt);
                        break;
                    } else {
                        continue;
                    }
            }
            i++;
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003b, code lost:
    
        if (r0 != null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x003d, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0046, code lost:
    
        if (r0 == null) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Bitmap createSpanImage(org.cocos2dx.lib.CCImage_richlabel.Span r4) {
        /*
            java.lang.String r0 = r4.imageName
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            if (r0 == 0) goto La
            return r1
        La:
            java.lang.String r0 = r4.imageName
            java.lang.String r2 = "/"
            boolean r0 = r0.startsWith(r2)
            if (r0 == 0) goto L1c
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L45
            java.lang.String r2 = r4.imageName     // Catch: java.lang.Throwable -> L45
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L45
            goto L37
        L1c:
            android.content.res.AssetManager r0 = org.cocos2dx.lib.Cocos2dxHelper.getAssetManager()     // Catch: java.lang.Throwable -> L45
            java.lang.String r2 = r4.imageName     // Catch: java.lang.Throwable -> L45
            java.lang.String r2 = nativeFullPathForFilename(r2)     // Catch: java.lang.Throwable -> L45
            java.lang.String r3 = "assets/"
            boolean r3 = r2.startsWith(r3)     // Catch: java.lang.Throwable -> L45
            if (r3 == 0) goto L33
            r3 = 7
            java.lang.String r2 = r2.substring(r3)     // Catch: java.lang.Throwable -> L45
        L33:
            java.io.InputStream r0 = r0.open(r2)     // Catch: java.lang.Throwable -> L45
        L37:
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r0)     // Catch: java.lang.Throwable -> L43
            if (r0 == 0) goto L49
        L3d:
            r0.close()     // Catch: java.io.IOException -> L41
            goto L49
        L41:
            goto L49
        L43:
            goto L46
        L45:
            r0 = r1
        L46:
            if (r0 == 0) goto L49
            goto L3d
        L49:
            if (r1 == 0) goto L82
            float r0 = r4.width
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L55
            float r0 = r4.width
            goto L5e
        L55:
            int r0 = r1.getWidth()
            float r0 = (float) r0
            float r3 = r4.scaleX
            float r0 = r0 * r3
        L5e:
            int r0 = (int) r0
            float r0 = (float) r0
            float r3 = r4.height
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 == 0) goto L6a
            float r4 = r4.height
            int r4 = (int) r4
            goto L74
        L6a:
            int r2 = r1.getHeight()
            float r2 = (float) r2
            float r4 = r4.scaleY
            float r2 = r2 * r4
            int r4 = (int) r2
        L74:
            float r4 = (float) r4
            int r0 = (int) r0
            int r4 = (int) r4
            r2 = 1
            android.graphics.Bitmap r4 = android.graphics.Bitmap.createScaledBitmap(r1, r0, r4, r2)
            if (r4 == r1) goto L81
            r1.recycle()
        L81:
            r1 = r4
        L82:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.lib.CCImage_richlabel.createSpanImage(org.cocos2dx.lib.CCImage_richlabel$Span):android.graphics.Bitmap");
    }

    private static int parseColor(String str, int i, int i2) {
        int i3;
        int i4;
        int i5 = i2 + i;
        int i6 = 0;
        while (i < i5) {
            i6 <<= 4;
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                if (charAt < 'a' || charAt > 'f') {
                    if (charAt >= 'A' && charAt <= 'F') {
                        i3 = charAt - 'A';
                    }
                    i++;
                } else {
                    i3 = charAt - 'a';
                }
                i4 = i3 + 10;
            } else {
                i4 = charAt - '0';
            }
            i6 |= i4;
            i++;
        }
        return i6;
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

    private static void initNativeObject(Bitmap bitmap) {
        byte[] pixels = getPixels(bitmap);
        if (pixels == null) {
            return;
        }
        nativeInitBitmapDC(bitmap.getWidth(), bitmap.getHeight(), pixels);
    }
}
