package okhttp3.internal.tls;

import javax.security.auth.x500.X500Principal;

/* loaded from: classes.dex */
final class DistinguishedNameParser {
    private int beg;
    private char[] chars;
    private int cur;
    private final String dn;
    private int end;
    private final int length;
    private int pos;

    public DistinguishedNameParser(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.dn = name;
        this.length = name.length();
    }

    private String nextAT() {
        while (true) {
            int i = this.pos;
            if (i >= this.length || this.chars[i] != ' ') {
                break;
            }
            this.pos = i + 1;
        }
        int i2 = this.pos;
        if (i2 == this.length) {
            return null;
        }
        this.beg = i2;
        this.pos = i2 + 1;
        while (true) {
            int i3 = this.pos;
            if (i3 >= this.length) {
                break;
            }
            char[] cArr = this.chars;
            if (cArr[i3] == '=' || cArr[i3] == ' ') {
                break;
            }
            this.pos = i3 + 1;
        }
        int i4 = this.pos;
        if (i4 >= this.length) {
            throw new IllegalStateException("Unexpected end of DN: " + this.dn);
        }
        this.end = i4;
        if (this.chars[i4] == ' ') {
            while (true) {
                int i5 = this.pos;
                if (i5 >= this.length) {
                    break;
                }
                char[] cArr2 = this.chars;
                if (cArr2[i5] == '=' || cArr2[i5] != ' ') {
                    break;
                }
                this.pos = i5 + 1;
            }
            char[] cArr3 = this.chars;
            int i6 = this.pos;
            if (cArr3[i6] != '=' || i6 == this.length) {
                throw new IllegalStateException("Unexpected end of DN: " + this.dn);
            }
        }
        this.pos++;
        while (true) {
            int i7 = this.pos;
            if (i7 >= this.length || this.chars[i7] != ' ') {
                break;
            }
            this.pos = i7 + 1;
        }
        int i8 = this.end;
        int i9 = this.beg;
        if (i8 - i9 > 4) {
            char[] cArr4 = this.chars;
            if (cArr4[i9 + 3] == '.' && (cArr4[i9] == 'O' || cArr4[i9] == 'o')) {
                char[] cArr5 = this.chars;
                int i10 = this.beg;
                if (cArr5[i10 + 1] == 'I' || cArr5[i10 + 1] == 'i') {
                    char[] cArr6 = this.chars;
                    int i11 = this.beg;
                    if (cArr6[i11 + 2] == 'D' || cArr6[i11 + 2] == 'd') {
                        this.beg += 4;
                    }
                }
            }
        }
        char[] cArr7 = this.chars;
        int i12 = this.beg;
        return new String(cArr7, i12, this.end - i12);
    }

    private String quotedAV() {
        int i = this.pos + 1;
        this.pos = i;
        this.beg = i;
        this.end = i;
        while (true) {
            int i2 = this.pos;
            if (i2 == this.length) {
                throw new IllegalStateException("Unexpected end of DN: " + this.dn);
            }
            char[] cArr = this.chars;
            if (cArr[i2] == '\"') {
                this.pos = i2 + 1;
                while (true) {
                    int i3 = this.pos;
                    if (i3 >= this.length || this.chars[i3] != ' ') {
                        break;
                    }
                    this.pos = i3 + 1;
                }
                char[] cArr2 = this.chars;
                int i4 = this.beg;
                return new String(cArr2, i4, this.end - i4);
            }
            if (cArr[i2] == '\\') {
                cArr[this.end] = getEscaped();
            } else {
                cArr[this.end] = cArr[i2];
            }
            this.pos++;
            this.end++;
        }
    }

    private String hexAV() {
        int i = this.pos;
        if (i + 4 >= this.length) {
            throw new IllegalStateException("Unexpected end of DN: " + this.dn);
        }
        this.beg = i;
        this.pos = i + 1;
        while (true) {
            int i2 = this.pos;
            if (i2 == this.length) {
                break;
            }
            char[] cArr = this.chars;
            if (cArr[i2] == '+' || cArr[i2] == ',' || cArr[i2] == ';') {
                break;
            }
            if (cArr[i2] == ' ') {
                this.end = i2;
                this.pos = i2 + 1;
                while (true) {
                    int i3 = this.pos;
                    if (i3 >= this.length || this.chars[i3] != ' ') {
                        break;
                    }
                    this.pos = i3 + 1;
                }
            } else {
                if (cArr[i2] >= 'A' && cArr[i2] <= 'F') {
                    cArr[i2] = (char) (cArr[i2] + ' ');
                }
                this.pos++;
            }
        }
        this.end = this.pos;
        int i4 = this.end;
        int i5 = this.beg;
        int i6 = i4 - i5;
        if (i6 < 5 || (i6 & 1) == 0) {
            throw new IllegalStateException("Unexpected end of DN: " + this.dn);
        }
        int i7 = i6 / 2;
        byte[] bArr = new byte[i7];
        int i8 = i5 + 1;
        for (int i9 = 0; i9 < i7; i9++) {
            bArr[i9] = (byte) getByte(i8);
            i8 += 2;
        }
        return new String(this.chars, this.beg, i6);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a7, code lost:
    
        return new java.lang.String(r1, r2, r8.cur - r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String escapedAV() {
        /*
            r8 = this;
            int r0 = r8.pos
            r8.beg = r0
            r8.end = r0
        L6:
            int r0 = r8.pos
            int r1 = r8.length
            if (r0 < r1) goto L19
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.chars
            int r2 = r8.beg
            int r3 = r8.end
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L19:
            char[] r1 = r8.chars
            char r2 = r1[r0]
            r3 = 44
            r4 = 43
            r5 = 59
            r6 = 32
            if (r2 == r6) goto L60
            if (r2 == r5) goto L53
            r5 = 92
            if (r2 == r5) goto L40
            if (r2 == r4) goto L53
            if (r2 == r3) goto L53
            int r2 = r8.end
            int r3 = r2 + 1
            r8.end = r3
            char r3 = r1[r0]
            r1[r2] = r3
            int r0 = r0 + 1
            r8.pos = r0
            goto L6
        L40:
            int r0 = r8.end
            int r2 = r0 + 1
            r8.end = r2
            char r2 = r8.getEscaped()
            r1[r0] = r2
            int r0 = r8.pos
            int r0 = r0 + 1
            r8.pos = r0
            goto L6
        L53:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.chars
            int r2 = r8.beg
            int r3 = r8.end
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L60:
            int r2 = r8.end
            r8.cur = r2
            int r0 = r0 + 1
            r8.pos = r0
            int r0 = r2 + 1
            r8.end = r0
            r1[r2] = r6
        L6e:
            int r0 = r8.pos
            int r1 = r8.length
            if (r0 >= r1) goto L87
            char[] r1 = r8.chars
            char r2 = r1[r0]
            if (r2 != r6) goto L87
            int r2 = r8.end
            int r7 = r2 + 1
            r8.end = r7
            r1[r2] = r6
            int r0 = r0 + 1
            r8.pos = r0
            goto L6e
        L87:
            int r0 = r8.pos
            int r1 = r8.length
            if (r0 == r1) goto L9b
            char[] r1 = r8.chars
            char r2 = r1[r0]
            if (r2 == r3) goto L9b
            char r2 = r1[r0]
            if (r2 == r4) goto L9b
            char r0 = r1[r0]
            if (r0 != r5) goto L6
        L9b:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.chars
            int r2 = r8.beg
            int r3 = r8.cur
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.tls.DistinguishedNameParser.escapedAV():java.lang.String");
    }

    private char getEscaped() {
        int i = this.pos + 1;
        this.pos = i;
        if (i == this.length) {
            throw new IllegalStateException("Unexpected end of DN: " + this.dn);
        }
        char c = this.chars[i];
        if (c != ' ' && c != '%' && c != '\\' && c != '_' && c != '\"' && c != '#') {
            switch (c) {
                case '*':
                case '+':
                case ',':
                    break;
                default:
                    switch (c) {
                        case ';':
                        case '<':
                        case '=':
                        case '>':
                            break;
                        default:
                            return getUTF8();
                    }
            }
        }
        return this.chars[this.pos];
    }

    private char getUTF8() {
        int i;
        int i2;
        int i3 = getByte(this.pos);
        this.pos++;
        if (i3 < 128) {
            return (char) i3;
        }
        if (i3 < 192 || i3 > 247) {
            return '?';
        }
        if (i3 <= 223) {
            i2 = i3 & 31;
            i = 1;
        } else if (i3 <= 239) {
            i = 2;
            i2 = i3 & 15;
        } else {
            i = 3;
            i2 = i3 & 7;
        }
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = this.pos + 1;
            this.pos = i5;
            if (i5 == this.length || this.chars[i5] != '\\') {
                return '?';
            }
            int i6 = i5 + 1;
            this.pos = i6;
            int i7 = getByte(i6);
            this.pos++;
            if ((i7 & 192) != 128) {
                return '?';
            }
            i2 = (i2 << 6) + (i7 & 63);
        }
        return (char) i2;
    }

    private int getByte(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 >= this.length) {
            throw new IllegalStateException("Malformed DN: " + this.dn);
        }
        char c = this.chars[i];
        if (c >= '0' && c <= '9') {
            i2 = c - '0';
        } else if (c >= 'a' && c <= 'f') {
            i2 = c - 'W';
        } else {
            if (c < 'A' || c > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.dn);
            }
            i2 = c - '7';
        }
        char c2 = this.chars[i4];
        if (c2 >= '0' && c2 <= '9') {
            i3 = c2 - '0';
        } else if (c2 >= 'a' && c2 <= 'f') {
            i3 = c2 - 'W';
        } else {
            if (c2 < 'A' || c2 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.dn);
            }
            i3 = c2 - '7';
        }
        return (i2 << 4) + i3;
    }

    public String findMostSpecific(String str) {
        String quotedAV;
        this.pos = 0;
        this.beg = 0;
        this.end = 0;
        this.cur = 0;
        this.chars = this.dn.toCharArray();
        String nextAT = nextAT();
        if (nextAT == null) {
            return null;
        }
        do {
            int i = this.pos;
            if (i == this.length) {
                return null;
            }
            char c = this.chars[i];
            if (c == '\"') {
                quotedAV = quotedAV();
            } else if (c == '#') {
                quotedAV = hexAV();
            } else {
                quotedAV = (c == '+' || c == ',' || c == ';') ? "" : escapedAV();
            }
            if (str.equalsIgnoreCase(nextAT)) {
                return quotedAV;
            }
            int i2 = this.pos;
            if (i2 >= this.length) {
                return null;
            }
            char[] cArr = this.chars;
            if (cArr[i2] != ',' && cArr[i2] != ';' && cArr[i2] != '+') {
                throw new IllegalStateException("Malformed DN: " + this.dn);
            }
            this.pos++;
            nextAT = nextAT();
        } while (nextAT != null);
        throw new IllegalStateException("Malformed DN: " + this.dn);
    }
}
