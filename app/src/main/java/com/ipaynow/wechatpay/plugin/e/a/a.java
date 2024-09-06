package com.ipaynow.wechatpay.plugin.e.a;

import android.util.Pair;

/* loaded from: classes.dex */
public final class a {
    public static Pair e(Object obj) {
        int length;
        int length2;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (obj instanceof int[][]) {
            int[][] iArr = (int[][]) obj;
            length = iArr.length;
            length2 = length == 0 ? 0 : iArr[0].length;
            int length3 = iArr.length;
            while (i < length3) {
                sb.append(f(iArr[i]).second + "\n");
                i++;
            }
        } else if (obj instanceof byte[][]) {
            byte[][] bArr = (byte[][]) obj;
            length = bArr.length;
            length2 = length == 0 ? 0 : bArr[0].length;
            int length4 = bArr.length;
            while (i < length4) {
                sb.append(f(bArr[i]).second + "\n");
                i++;
            }
        } else if (obj instanceof short[][]) {
            short[][] sArr = (short[][]) obj;
            length = sArr.length;
            length2 = length == 0 ? 0 : sArr[0].length;
            int length5 = sArr.length;
            while (i < length5) {
                sb.append(f(sArr[i]).second + "\n");
                i++;
            }
        } else if (obj instanceof long[][]) {
            long[][] jArr = (long[][]) obj;
            length = jArr.length;
            length2 = length == 0 ? 0 : jArr[0].length;
            int length6 = jArr.length;
            while (i < length6) {
                sb.append(f(jArr[i]).second + "\n");
                i++;
            }
        } else if (obj instanceof float[][]) {
            float[][] fArr = (float[][]) obj;
            length = fArr.length;
            length2 = length == 0 ? 0 : fArr[0].length;
            int length7 = fArr.length;
            while (i < length7) {
                sb.append(f(fArr[i]).second + "\n");
                i++;
            }
        } else if (obj instanceof double[][]) {
            double[][] dArr = (double[][]) obj;
            length = dArr.length;
            length2 = length == 0 ? 0 : dArr[0].length;
            int length8 = dArr.length;
            while (i < length8) {
                sb.append(f(dArr[i]).second + "\n");
                i++;
            }
        } else if (obj instanceof boolean[][]) {
            boolean[][] zArr = (boolean[][]) obj;
            length = zArr.length;
            length2 = length == 0 ? 0 : zArr[0].length;
            int length9 = zArr.length;
            while (i < length9) {
                sb.append(f(zArr[i]).second + "\n");
                i++;
            }
        } else if (obj instanceof char[][]) {
            char[][] cArr = (char[][]) obj;
            length = cArr.length;
            length2 = length == 0 ? 0 : cArr[0].length;
            int length10 = cArr.length;
            while (i < length10) {
                sb.append(f(cArr[i]).second + "\n");
                i++;
            }
        } else {
            Object[][] objArr = (Object[][]) obj;
            length = objArr.length;
            length2 = length == 0 ? 0 : objArr[0].length;
            int length11 = objArr.length;
            while (i < length11) {
                sb.append(f(objArr[i]).second + "\n");
                i++;
            }
        }
        return Pair.create(Pair.create(Integer.valueOf(length), Integer.valueOf(length2)), sb.toString());
    }

    public static Pair f(Object obj) {
        int length;
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            length = iArr.length;
            int length2 = iArr.length;
            while (i < length2) {
                sb.append(String.valueOf(iArr[i]) + ",\t");
                i++;
            }
        } else if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            length = bArr.length;
            int length3 = bArr.length;
            while (i < length3) {
                sb.append(String.valueOf((int) bArr[i]) + ",\t");
                i++;
            }
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            length = sArr.length;
            int length4 = sArr.length;
            while (i < length4) {
                sb.append(String.valueOf((int) sArr[i]) + ",\t");
                i++;
            }
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            length = jArr.length;
            int length5 = jArr.length;
            while (i < length5) {
                sb.append(String.valueOf(jArr[i]) + ",\t");
                i++;
            }
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            length = fArr.length;
            int length6 = fArr.length;
            while (i < length6) {
                sb.append(String.valueOf(fArr[i]) + ",\t");
                i++;
            }
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            length = dArr.length;
            int length7 = dArr.length;
            while (i < length7) {
                sb.append(String.valueOf(dArr[i]) + ",\t");
                i++;
            }
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            length = zArr.length;
            int length8 = zArr.length;
            while (i < length8) {
                sb.append(String.valueOf(zArr[i]) + ",\t");
                i++;
            }
        } else if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            length = cArr.length;
            int length9 = cArr.length;
            while (i < length9) {
                sb.append(String.valueOf(cArr[i]) + ",\t");
                i++;
            }
        } else {
            Object[] objArr = (Object[]) obj;
            length = objArr.length;
            int length10 = objArr.length;
            while (i < length10) {
                sb.append(String.valueOf(b.g(objArr[i])) + ",\t");
                i++;
            }
        }
        return Pair.create(Integer.valueOf(length), sb.replace(sb.length() - 2, sb.length(), "]").toString());
    }
}
