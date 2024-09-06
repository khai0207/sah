package okhttp3;

import java.io.IOException;

/* loaded from: classes.dex */
public interface Authenticator {
    public static final Authenticator NONE = new Authenticator() { // from class: okhttp3.Authenticator.1
        @Override // okhttp3.Authenticator
        public Request authenticate(Route route, Response response) {
            return null;
        }

        AnonymousClass1() {
        }
    };

    Request authenticate(Route route, Response response) throws IOException;

    /* renamed from: okhttp3.Authenticator$1 */
    /* loaded from: classes.dex */
    static class AnonymousClass1 implements Authenticator {
        @Override // okhttp3.Authenticator
        public Request authenticate(Route route, Response response) {
            return null;
        }

        AnonymousClass1() {
        }
    }
}
