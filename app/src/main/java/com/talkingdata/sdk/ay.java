package com.talkingdata.sdk;

import android.os.Parcel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: td */
/* loaded from: classes.dex */
public class ay extends File {
    public final String a;

    public static String a(String str) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(str));
        } catch (Throwable unused) {
        }
        try {
            String str2 = "";
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                sb.append(str2);
                sb.append(readLine);
                str2 = "\n";
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
            } catch (Throwable unused2) {
            }
            return sb2;
        } catch (Throwable unused3) {
            bufferedReader2 = bufferedReader;
            try {
                String sb3 = sb.toString();
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable unused4) {
                    }
                }
                return sb3;
            } catch (Throwable th) {
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable unused5) {
                    }
                }
                throw th;
            }
        }
    }

    protected ay(String str) {
        super(str);
        this.a = a(str);
    }

    protected ay(Parcel parcel) {
        super(parcel.readString());
        this.a = parcel.readString();
    }

    @Override // java.io.File
    public long length() {
        return this.a.length();
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    public static class c extends ay {
        private String[] b;

        public static c a(int i) {
            try {
                return new c(String.format("/proc/%d/stat", Integer.valueOf(i)));
            } catch (Throwable unused) {
                return null;
            }
        }

        private c(String str) {
            super(str);
            this.b = new String[0];
            try {
                this.b = this.a.split("\\s+");
            } catch (Throwable unused) {
            }
        }

        private c(Parcel parcel) {
            super(parcel);
            this.b = new String[0];
            try {
                this.b = parcel.createStringArray();
            } catch (Throwable unused) {
            }
        }

        public long a() {
            try {
                return Long.parseLong(this.b[21]);
            } catch (Throwable unused) {
                return 0L;
            }
        }

        public String b() {
            try {
                return this.b[1].replace("(", "").replace(")", "");
            } catch (Throwable unused) {
                return "";
            }
        }

        public char c() {
            try {
                return this.b[2].charAt(0);
            } catch (Throwable unused) {
                return (char) 0;
            }
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    static class d extends ay {
        public static d a(int i) {
            try {
                return new d(String.format("/proc/%d/status", Integer.valueOf(i)));
            } catch (Throwable unused) {
                return null;
            }
        }

        private d(String str) {
            super(str);
        }

        private d(Parcel parcel) {
            super(parcel);
        }

        public String b(String str) {
            try {
                for (String str2 : this.a.split("\n")) {
                    if (str2.startsWith(str + ":")) {
                        return str2.split(str + ":")[1].trim();
                    }
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }

        public int a() {
            try {
                return Integer.parseInt(b("Uid").split("\\s+")[0]);
            } catch (Throwable unused) {
                return -1;
            }
        }

        public int b() {
            try {
                return Integer.parseInt(b("Gid").split("\\s+")[0]);
            } catch (Throwable unused) {
                return -1;
            }
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    static class a extends ay {
        public ArrayList b;

        public static a a(int i) {
            try {
                return new a(String.format("/proc/%d/cgroup", Integer.valueOf(i)));
            } catch (Throwable unused) {
                return null;
            }
        }

        private a(String str) {
            super(str);
            try {
                this.b = new ArrayList();
                for (String str2 : this.a.split("\n")) {
                    try {
                        this.b.add(new b(str2));
                    } catch (Throwable unused) {
                    }
                }
            } catch (Throwable unused2) {
            }
        }

        public b b(String str) {
            try {
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    b bVar = (b) it.next();
                    for (String str2 : bVar.b.split(",")) {
                        if (str2.equals(str)) {
                            return bVar;
                        }
                    }
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* compiled from: td */
    /* loaded from: classes.dex */
    static class b {
        protected int a;
        protected String b;
        protected String c;

        protected b(String str) {
            try {
                String[] split = str.split(":");
                this.a = Integer.parseInt(split[0]);
                this.b = split[1];
                this.c = split[2];
            } catch (Throwable unused) {
            }
        }

        protected b(Parcel parcel) {
            try {
                this.a = parcel.readInt();
                this.b = parcel.readString();
                this.c = parcel.readString();
            } catch (Throwable unused) {
            }
        }
    }
}
