package u.aly;

import org.json.JSONArray;

/* compiled from: UPage.java */
/* loaded from: classes.dex */
public class aj extends bi {
    public aj() {
    }

    public aj(JSONArray jSONArray) throws Exception {
        a(jSONArray);
    }

    public aj(String str) throws Exception {
        a(new JSONArray(str));
    }

    private void a(JSONArray jSONArray) throws Exception {
        a(jSONArray.getString(0));
        a(jSONArray.getInt(1));
    }
}
