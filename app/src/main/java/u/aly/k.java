package u.aly;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: UTDIdTracker.java */
/* loaded from: classes.dex */
public class k extends a {
    private static final String a = "utdid";
    private static final String b = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final Pattern c = Pattern.compile("UTDID\">([^<]+)");
    private Context d;

    public k(Context context) {
        super("utdid");
        this.d = context;
    }

    @Override // u.aly.a
    public String f() {
        return g();
    }

    private String g() {
        File h = h();
        if (h != null && h.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(h);
                try {
                    return b(bu.a(fileInputStream));
                } finally {
                    bu.c(fileInputStream);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String b(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = c.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private File h() {
        if (bt.a(this.d, b) && Environment.getExternalStorageState().equals("mounted")) {
            try {
                return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
