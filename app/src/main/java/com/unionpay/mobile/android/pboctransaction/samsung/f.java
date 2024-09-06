package com.unionpay.mobile.android.pboctransaction.samsung;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.kqg.main.constant.KV;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.tsmservice.UPTsmAddon;
import com.unionpay.tsmservice.data.Amount;
import com.unionpay.tsmservice.data.AppStatus;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams;
import com.unionpay.tsmservice.request.CloseChannelRequestParams;
import com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams;
import com.unionpay.tsmservice.request.GetSeAppListRequestParams;
import com.unionpay.tsmservice.request.InitRequestParams;
import com.unionpay.tsmservice.request.OpenChannelRequestParams;
import com.unionpay.tsmservice.request.SendApduRequestParams;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class f implements com.unionpay.mobile.android.pboctransaction.c {
    public static boolean e = false;
    public static boolean f = false;
    private InitRequestParams B;
    private SendApduRequestParams C;
    private Context j;
    private com.unionpay.mobile.android.pboctransaction.b k;
    private UPTsmAddon l;
    String a = "19999741583305435775450371903957622252895007857586703985696530069777024392884287211670048223494223356836139331264745305488035196420545843046674853984852305228918004888414759300445308845681087472809487791392726663269247999482633231304479943902981311338709709401000108625221857486530967716878328228310703650408575058288784573884262229674701501842066793928002725038356122707147929560460157457327696696471785787505023643000687928051333648369477362945785046976181683285277919023274376124429148429078602516462345014971452220809120399264066736558357572250447243744965533695780751271768398207631002867947152625578881506566297";
    String b = "65537";
    String c = "5929703506495688276130676968213384164609348882017291684789802337394713840702726472221198819456433069055388915357817202968369194525956730949539025096963015440180443916974948318765778051794088998339276397676916425744003507605582286608745438301704836361482343765671805403004194772735755889141443700570750608527755694790475628670051863813384800013641474007746161600969180035295709344887092512089121125289090881005234379649440422346798246278284328310221953743757037875834557694749810951089453346522229122216198442376081589767583019062954875861469699069474707285206935898628020341168773624455554331118138151051364372906861";
    String d = "UnionPay";
    private final String h = "A0000003334355502D4D4F42494C45";
    private final int i = 10000;
    private Handler m = null;
    private int n = 0;
    private final int o = 8;
    private boolean p = false;
    private String q = "-1";
    private String r = "";
    private boolean s = false;
    private String t = null;

    /* renamed from: u, reason: collision with root package name */
    private boolean f49u = false;
    private String v = "-1";
    private String w = "-1";
    private String x = "";
    private String y = "";
    String g = "";
    private final Handler.Callback z = new g(this);
    private final Handler A = new Handler(this.z);
    private final UPTsmAddon.UPTsmConnectionListener D = new h(this);

    static /* synthetic */ void a(f fVar, int i, String str) {
        if (i == 1000) {
            fVar.a(false);
            return;
        }
        switch (i) {
            case 1011:
                j.c("uppay", "open channel fail");
                fVar.q = null;
                fVar.r = "";
                fVar.p = true;
                return;
            case 1012:
                j.c("uppay", "apdu fail");
                fVar.A.removeMessages(3);
                fVar.s = true;
                return;
            case 1013:
                j.c("uppay", "close channel fail");
                return;
            case 1014:
                fVar.m.sendMessage(fVar.A.obtainMessage(8, null));
                return;
            case 1015:
                j.c("uppay-spay", "get spay list call back");
                fVar.m.sendMessage(fVar.m.obtainMessage(KV.INIT_ERROR, str));
                return;
            case 1016:
                j.c("uppay-spay", "check spay support fail");
                f = false;
                e = true;
                return;
            default:
                return;
        }
    }

    private void a(String str, String str2) {
        SendApduRequestParams sendApduRequestParams = new SendApduRequestParams();
        this.C = sendApduRequestParams;
        sendApduRequestParams.setChannel(str2);
        this.C.setHexApdu(str);
        this.C.setReserve(g());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        com.unionpay.mobile.android.pboctransaction.b bVar = this.k;
        if (bVar != null) {
            if (z) {
                bVar.a();
            } else {
                bVar.b();
            }
        }
    }

    static /* synthetic */ boolean c(f fVar) {
        fVar.p = true;
        return true;
    }

    static /* synthetic */ boolean d(f fVar) {
        fVar.f49u = true;
        return true;
    }

    static /* synthetic */ boolean d(String str) {
        return str == null || str.length() <= 16 || AppStatus.APPLY.equalsIgnoreCase(str.substring(14, 16));
    }

    static /* synthetic */ boolean e(f fVar) {
        fVar.s = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.B == null) {
            InitRequestParams initRequestParams = new InitRequestParams();
            this.B = initRequestParams;
            initRequestParams.setSignature(g());
            this.B.setReserve(g());
        }
        try {
            int init = this.l.init(this.B, new e(1000, this.A));
            if (init != 0) {
                this.A.sendMessage(Message.obtain(this.A, 1, 1000, 0, ""));
            }
            j.c("uppay", "ret = " + init);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    private String g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constant.KEY_SIGNATURE, this.g);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        this.r = "";
        try {
            OpenChannelRequestParams openChannelRequestParams = new OpenChannelRequestParams();
            openChannelRequestParams.setAppAID(str);
            openChannelRequestParams.setReserve(g());
            if (this.l.openChannel(openChannelRequestParams, new e(1011, this.A)) != 0) {
                this.A.sendMessage(Message.obtain(this.A, 1, 1011, 0, ""));
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        do {
        } while (!this.p);
        if ("A0000003334355502D4D4F42494C45".equals(str)) {
            this.v = this.q;
        } else {
            this.w = this.q;
        }
        this.p = false;
        return this.r;
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final ArrayList<com.unionpay.mobile.android.model.c> a(com.unionpay.mobile.android.pboctransaction.d dVar) {
        if (this.l == null) {
            return null;
        }
        try {
            if (com.unionpay.mobile.android.model.b.aw && com.unionpay.mobile.android.model.b.av && com.unionpay.mobile.android.model.b.bi) {
                GetCardInfoBySpayRequestParams getCardInfoBySpayRequestParams = new GetCardInfoBySpayRequestParams();
                Amount amount = new Amount();
                amount.setProductPrice(this.x);
                String e2 = com.unionpay.mobile.android.pboctransaction.e.e(this.y);
                if (!TextUtils.isEmpty(e2)) {
                    amount.setCurrencyType(e2);
                }
                getCardInfoBySpayRequestParams.setAmount(amount);
                getCardInfoBySpayRequestParams.setReserve(g());
                int cardInfoBySamsungPay = this.l.getCardInfoBySamsungPay(getCardInfoBySpayRequestParams, new e(1015, this.A));
                if (cardInfoBySamsungPay != 0) {
                    this.A.sendMessage(Message.obtain(this.A, 1, 1015, 0, ""));
                }
                j.c("uppay", "readList: " + cardInfoBySamsungPay);
            } else {
                GetSeAppListRequestParams getSeAppListRequestParams = new GetSeAppListRequestParams();
                getSeAppListRequestParams.setReserve(g());
                if (this.l.getSEAppList(getSeAppListRequestParams, new e(1014, this.A)) != 0) {
                    this.A.sendMessage(Message.obtain(this.A, 1, 1014, 0, ""));
                }
            }
        } catch (RemoteException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        j.c("uppay", "readList");
        return null;
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void a() {
        UPTsmAddon uPTsmAddon = this.l;
        if (uPTsmAddon != null) {
            uPTsmAddon.removeConnectionListener(this.D);
            this.l.unbind();
        }
    }

    public final void a(Handler handler) {
        this.m = handler;
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void a(com.unionpay.mobile.android.pboctransaction.b bVar, Context context) {
        this.k = bVar;
        this.j = context;
        try {
            this.g = a.a(KeyFactory.getInstance(com.alipay.sdk.m.j.d.a).generatePrivate(new RSAPrivateKeySpec(new BigInteger(this.a), new BigInteger(this.c))), this.d);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
        }
        UPTsmAddon uPTsmAddon = UPTsmAddon.getInstance(context);
        this.l = uPTsmAddon;
        uPTsmAddon.addConnectionListener(this.D);
        j.c("uppay-spay", "type se  bind service");
        UPTsmAddon uPTsmAddon2 = this.l;
        if (uPTsmAddon2 != null && !uPTsmAddon2.isConnected()) {
            j.c("uppay", "bind service");
            this.l.bind();
            return;
        }
        UPTsmAddon uPTsmAddon3 = this.l;
        if (uPTsmAddon3 == null || !uPTsmAddon3.isConnected()) {
            return;
        }
        j.c("uppay", "tem service already connected");
        f();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:1|(1:3)(11:(1:28)|5|6|7|(1:9)|11|(2:23|12)|17|(1:19)|20|21)|4|5|6|7|(0)|11|(3:14|23|12)|24|17|(0)|20|21) */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0052, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0053, code lost:
    
        r9.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0044 A[Catch: RemoteException -> 0x0052, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x0052, blocks: (B:7:0x0031, B:9:0x0044), top: B:6:0x0031 }] */
    @Override // com.unionpay.mobile.android.pboctransaction.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] a(byte[] r8, int r9) {
        /*
            r7 = this;
            r0 = 0
            r7.t = r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "--->"
            r1.<init>(r2)
            java.lang.String r2 = com.unionpay.mobile.android.pboctransaction.e.a(r8)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "uppay"
            com.unionpay.mobile.android.utils.j.a(r2, r1)
            r1 = 1
            if (r9 != 0) goto L27
            java.lang.String r8 = com.unionpay.mobile.android.pboctransaction.e.a(r8)
            java.lang.String r9 = r7.w
        L23:
            r7.a(r8, r9)
            goto L30
        L27:
            if (r9 != r1) goto L30
            java.lang.String r8 = com.unionpay.mobile.android.pboctransaction.e.a(r8)
            java.lang.String r9 = r7.v
            goto L23
        L30:
            r8 = 0
            com.unionpay.tsmservice.UPTsmAddon r9 = r7.l     // Catch: android.os.RemoteException -> L52
            com.unionpay.tsmservice.request.SendApduRequestParams r3 = r7.C     // Catch: android.os.RemoteException -> L52
            com.unionpay.mobile.android.pboctransaction.samsung.e r4 = new com.unionpay.mobile.android.pboctransaction.samsung.e     // Catch: android.os.RemoteException -> L52
            android.os.Handler r5 = r7.A     // Catch: android.os.RemoteException -> L52
            r6 = 1012(0x3f4, float:1.418E-42)
            r4.<init>(r6, r5)     // Catch: android.os.RemoteException -> L52
            int r9 = r9.sendApdu(r3, r4)     // Catch: android.os.RemoteException -> L52
            if (r9 == 0) goto L56
            android.os.Handler r9 = r7.A     // Catch: android.os.RemoteException -> L52
            android.os.Handler r3 = r7.A     // Catch: android.os.RemoteException -> L52
            java.lang.String r4 = ""
            android.os.Message r1 = android.os.Message.obtain(r3, r1, r6, r8, r4)     // Catch: android.os.RemoteException -> L52
            r9.sendMessage(r1)     // Catch: android.os.RemoteException -> L52
            goto L56
        L52:
            r9 = move-exception
            r9.printStackTrace()
        L56:
            android.os.Handler r9 = r7.A
            r1 = 3
            android.os.Message r1 = android.os.Message.obtain(r9, r1)
            r3 = 10000(0x2710, double:4.9407E-320)
            r9.sendMessageDelayed(r1, r3)
        L62:
            java.lang.String r9 = r7.t
            if (r9 != 0) goto L6a
            boolean r9 = r7.s
            if (r9 == 0) goto L62
        L6a:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r1 = "mApduResult: "
            r9.<init>(r1)
            java.lang.String r1 = r7.t
            r9.append(r1)
            java.lang.String r1 = ",mApduReturn:"
            r9.append(r1)
            boolean r1 = r7.s
            r9.append(r1)
            java.lang.String r9 = r9.toString()
            com.unionpay.mobile.android.utils.j.a(r2, r9)
            java.lang.String r9 = r7.t
            if (r9 == 0) goto La0
            byte[] r0 = com.unionpay.mobile.android.pboctransaction.e.a(r9)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r1 = "ret1 <---"
            r9.<init>(r1)
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            com.unionpay.mobile.android.utils.j.a(r2, r9)
        La0:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r1 = "<---"
            r9.<init>(r1)
            java.lang.String r1 = r7.t
            r9.append(r1)
            java.lang.String r9 = r9.toString()
            com.unionpay.mobile.android.utils.j.a(r2, r9)
            r7.s = r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "ret2 <---"
            r8.<init>(r9)
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            com.unionpay.mobile.android.utils.j.a(r2, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.samsung.f.a(byte[], int):byte[]");
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void b() {
    }

    public final void b(String str) {
        this.x = str;
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void c() {
        String str = this.v;
        if (str != null && !"-1".equals(str)) {
            try {
                CloseChannelRequestParams closeChannelRequestParams = new CloseChannelRequestParams();
                closeChannelRequestParams.setChannel(this.v);
                closeChannelRequestParams.setReserve(g());
                if (this.l.closeChannel(closeChannelRequestParams, new e(1013, this.A)) != 0) {
                    this.A.sendMessage(Message.obtain(this.A, 1, 1013, 0, ""));
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            do {
            } while (!this.f49u);
            this.v = "-1";
            this.f49u = false;
        }
        String str2 = this.w;
        if (str2 == null || "-1".equals(str2)) {
            return;
        }
        try {
            CloseChannelRequestParams closeChannelRequestParams2 = new CloseChannelRequestParams();
            closeChannelRequestParams2.setChannel(this.w);
            closeChannelRequestParams2.setReserve(g());
            if (this.l.closeChannel(closeChannelRequestParams2, new e(1013, this.A)) != 0) {
                this.A.sendMessage(Message.obtain(this.A, 1, 1013, 0, ""));
            }
        } catch (RemoteException e3) {
            e3.printStackTrace();
        }
        do {
        } while (!this.f49u);
        this.w = "-1";
        this.f49u = false;
    }

    public final void c(String str) {
        this.y = str;
    }

    @Override // com.unionpay.mobile.android.pboctransaction.c
    public final void d() {
    }

    public final boolean e() {
        CheckSSamsungPayRequestParams checkSSamsungPayRequestParams = new CheckSSamsungPayRequestParams();
        checkSSamsungPayRequestParams.setReserve(g());
        try {
            j.c("uppay", "getSpaySupported");
            int checkSSamsungPay = this.l.checkSSamsungPay(checkSSamsungPayRequestParams, new e(1016, this.A));
            j.c("uppay", "retcheck = " + checkSSamsungPay);
            if (checkSSamsungPay != 0) {
                return false;
            }
            do {
            } while (!e);
            e = false;
            return f;
        } catch (RemoteException unused) {
            return false;
        }
    }
}
