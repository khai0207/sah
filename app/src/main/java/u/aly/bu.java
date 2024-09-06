package u.aly;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: HelperUtils.java */
/* loaded from: classes.dex */
public class bu {
    public static final String a = System.getProperty("line.separator");
    private static final String b = "helper";

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                stringBuffer.append(String.format("%02X", Byte.valueOf(b2)));
            }
            return stringBuffer.toString();
        } catch (Exception unused) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
        }
    }

    public static String b(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                stringBuffer.append(Integer.toHexString(b2 & 255));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            bv.c(b, "getMD5 error", e);
            return "";
        }
    }

    public static String a(File file) {
        byte[] bArr = new byte[1024];
        try {
            if (!file.isFile()) {
                return "";
            }
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    messageDigest.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return String.format("%1$032x", new BigInteger(1, messageDigest.digest()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(Context context, long j) {
        if (j < 1000) {
            return ((int) j) + "B";
        }
        if (j < 1000000) {
            StringBuilder sb = new StringBuilder();
            double d = (float) j;
            Double.isNaN(d);
            sb.append(Math.round(d / 1000.0d));
            sb.append("K");
            return sb.toString();
        }
        if (j < 1000000000) {
            DecimalFormat decimalFormat = new DecimalFormat("#0.0");
            StringBuilder sb2 = new StringBuilder();
            double d2 = (float) j;
            Double.isNaN(d2);
            sb2.append(decimalFormat.format(d2 / 1000000.0d));
            sb2.append("M");
            return sb2.toString();
        }
        DecimalFormat decimalFormat2 = new DecimalFormat("#0.00");
        StringBuilder sb3 = new StringBuilder();
        double d3 = (float) j;
        Double.isNaN(d3);
        sb3.append(decimalFormat2.format(d3 / 1.0E9d));
        sb3.append("G");
        return sb3.toString();
    }

    public static String c(String str) {
        try {
            long longValue = Long.valueOf(str).longValue();
            if (longValue < ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) {
                return ((int) longValue) + "B";
            }
            if (longValue < 1048576) {
                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                StringBuilder sb = new StringBuilder();
                double d = (float) longValue;
                Double.isNaN(d);
                sb.append(decimalFormat.format(d / 1024.0d));
                sb.append("K");
                return sb.toString();
            }
            if (longValue < Constants.GB) {
                DecimalFormat decimalFormat2 = new DecimalFormat("#0.00");
                StringBuilder sb2 = new StringBuilder();
                double d2 = (float) longValue;
                Double.isNaN(d2);
                sb2.append(decimalFormat2.format(d2 / 1048576.0d));
                sb2.append("M");
                return sb2.toString();
            }
            DecimalFormat decimalFormat3 = new DecimalFormat("#0.00");
            StringBuilder sb3 = new StringBuilder();
            double d3 = (float) longValue;
            Double.isNaN(d3);
            sb3.append(decimalFormat3.format(d3 / 1.073741824E9d));
            sb3.append("G");
            return sb3.toString();
        } catch (NumberFormatException unused) {
            return str;
        }
    }

    public static void a(Context context, String str) {
        context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str));
    }

    public static boolean b(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean d(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean e(String str) {
        if (d(str)) {
            return false;
        }
        String lowerCase = str.trim().toLowerCase(Locale.US);
        return lowerCase.startsWith("http://") || lowerCase.startsWith("https://");
    }

    public static String a() {
        return a(new Date());
    }

    public static String a(Date date) {
        return date == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
    }

    public static String a(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[1024];
        StringWriter stringWriter = new StringWriter();
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (-1 != read) {
                stringWriter.write(cArr, 0, read);
            } else {
                return stringWriter.toString();
            }
        }
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static void a(File file, byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
        } finally {
            a(fileOutputStream);
        }
    }

    public static void a(File file, String str) throws IOException {
        a(file, str.getBytes());
    }

    public static void c(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception unused) {
            }
        }
    }
}
