package com.netease.nimlib.session.a;

import android.os.Handler;
import android.util.Pair;
import com.netease.nimlib.amazonaws.services.s3.internal.Constants;
import com.netease.nimlib.biz.c.i.b;
import com.netease.nimlib.biz.d.i.l;
import com.netease.nimlib.biz.e.j.n;
import com.netease.nimlib.biz.i;
import com.netease.nimlib.i.k;
import com.netease.nimlib.o.f;
import com.netease.nimlib.o.w;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.GetMessageDirectionEnum;
import com.netease.nimlib.sdk.msg.model.GetMessagesDynamicallyParam;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.netease.nimlib.session.IMMessageImpl;
import com.netease.nimlib.session.MsgDBHelper;
import com.netease.nimlib.session.a.b;
import com.netease.nimlib.session.g;
import com.netease.nimlib.session.j;
import com.netease.nimlib.session.y;
import defpackage.C$r8$backportedMethods$utility$Objects$2$equals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: SessionReliableHelper.java */
/* loaded from: classes.dex */
public class c implements b.a {
    private static final c a = new c();
    private e b;
    private final Map<String, d> c = new HashMap();
    private final Map<String, b> d = new HashMap();
    private final Map<String, IMMessageImpl> g = new HashMap();
    private volatile boolean e = false;
    private final Set<String> f = new HashSet();
    private final Handler h = com.netease.nimlib.c.b.a.c().a();
    private volatile boolean i = false;

    /* compiled from: SessionReliableHelper.java */
    /* loaded from: classes.dex */
    public interface a<T, S> {
        S onCallback(T t);
    }

    public static c a() {
        return a;
    }

    private c() {
    }

    public synchronized void b() {
        com.netease.nimlib.log.b.d("SessionReliableHelper", "function: onCallLogin");
        e();
    }

    public synchronized void c() {
        com.netease.nimlib.log.b.d("SessionReliableHelper", "function: onCallLogout");
        e();
    }

    public synchronized void a(e eVar) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: onSyncReliableInfo %s", eVar));
        this.b = eVar;
        this.d.clear();
        if (this.b == null) {
            com.netease.nimlib.log.b.d("SessionReliableHelper", "get null syncReliableInfo on sync reliable info");
            return;
        }
        y a2 = this.b.a();
        if (a2 == null) {
            a2 = y.a();
        }
        com.netease.nimlib.push.e.a(a2.b());
        Map<String, f> c = this.b.c();
        if (c == null) {
            com.netease.nimlib.log.b.d("SessionReliableHelper", "get null syncSessionReliableInfos on sync reliable info");
            return;
        }
        for (f fVar : c.values()) {
            if (fVar == null) {
                com.netease.nimlib.log.b.e("SessionReliableHelper", "get null SyncSessionReliableInfo on sync reliable info");
            } else {
                String a3 = j.a(fVar.c(), fVar.b());
                b bVar = new b(fVar.b(), fVar.c(), this);
                this.d.put(a3, bVar);
                if (!fVar.p()) {
                    bVar.d();
                }
                if (!fVar.q()) {
                    bVar.b();
                }
            }
        }
    }

    public synchronized void a(List<com.netease.nimlib.push.packet.b.c> list, com.netease.nimlib.session.a.a aVar) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", "function: onReceivedSyncMessage");
        c(d(list), aVar);
    }

    public synchronized void b(List<IMMessageImpl> list, com.netease.nimlib.session.a.a aVar) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: onSavedSyncMessage(messages: size=%s, source: %s)", Integer.valueOf(com.netease.nimlib.o.f.e(list)), aVar));
        d(b(list), aVar);
    }

    @Override // com.netease.nimlib.session.a.b.a
    public synchronized void a(String str, SessionTypeEnum sessionTypeEnum) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: onAllSyncMessageSaved(sessionId: %s, sessionType: %s)", str, sessionTypeEnum));
        if (this.b == null) {
            com.netease.nimlib.log.b.e("SessionReliableHelper", "all sync message saved without reliable information");
            return;
        }
        f a2 = this.b.a(str, sessionTypeEnum);
        if (a2 == null) {
            com.netease.nimlib.log.b.e("SessionReliableHelper", String.format("all sync message saved without reliable information of (%s, %s)", str, sessionTypeEnum));
            return;
        }
        d a3 = a2.a();
        if (!a3.a()) {
            if (a2.o()) {
                com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("all sync message saved with only next of (%s, %s)", str, sessionTypeEnum));
            } else {
                com.netease.nimlib.log.b.e("SessionReliableHelper", String.format("all sync message saved without start information of (%s, %s)", str, sessionTypeEnum));
            }
        } else {
            if (a3.b() && c(a3)) {
                a(str, sessionTypeEnum, a3);
            }
        }
    }

    public synchronized void a(long j) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: onSyncDone(timestamp: %s)", Long.valueOf(j)));
        if (this.b != null) {
            this.b.a(j);
        }
    }

    public synchronized void a(IMMessageImpl iMMessageImpl) {
        com.netease.nimlib.log.b.c("SessionReliableHelper", String.format("function: onReceivedMessage(message: %s)", IMMessageImpl.toStringSimple(iMMessageImpl)));
        c(iMMessageImpl);
    }

    public synchronized void b(IMMessageImpl iMMessageImpl) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: onSendMessageDone(message: %s)", IMMessageImpl.toStringSimple(iMMessageImpl)));
        c(iMMessageImpl);
    }

    public synchronized void a(final GetMessagesDynamicallyParam getMessagesDynamicallyParam, final k kVar) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: onShouldGetMessagesFromServer(param: %s)", getMessagesDynamicallyParam));
        final com.netease.nimlib.biz.d.i.k kVar2 = new com.netease.nimlib.biz.d.i.k(getMessagesDynamicallyParam.getSessionId(), getMessagesDynamicallyParam.getSessionType(), getMessagesDynamicallyParam.getFromTime(), getMessagesDynamicallyParam.getToTime(), getMessagesDynamicallyParam.getAnchorServerId(), getMessagesDynamicallyParam.getLimit(), GetMessageDirectionEnum.BACKWARD.equals(getMessagesDynamicallyParam.getDirection()), true, null, true, null, false);
        i.a().a(new com.netease.nimlib.biz.g.b(kVar2) { // from class: com.netease.nimlib.session.a.c.1
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar) {
                com.netease.nimlib.biz.c.i.b.a(aVar, kVar2, new b.a() { // from class: com.netease.nimlib.session.a.c.1.1
                    @Override // com.netease.nimlib.biz.c.i.b.a
                    public void a(int i, ArrayList<IMMessageImpl> arrayList, boolean z, Throwable th) {
                        if (th != null || i != 200) {
                            kVar.b(new com.netease.nimlib.session.c(false, c.this.c(getMessagesDynamicallyParam))).b();
                            return;
                        }
                        if (getMessagesDynamicallyParam.getDirection() != GetMessageDirectionEnum.BACKWARD && com.netease.nimlib.o.f.d(arrayList)) {
                            Collections.reverse(arrayList);
                        }
                        kVar2.a(kVar);
                        c.this.a(kVar2, getMessagesDynamicallyParam, arrayList, kVar);
                    }
                });
            }
        });
    }

    public synchronized void a(com.netease.nimlib.biz.d.i.k kVar, final GetMessagesDynamicallyParam getMessagesDynamicallyParam, List<IMMessageImpl> list, k kVar2) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", "function: onSavedMessagesFromServer");
        if (kVar == null) {
            com.netease.nimlib.log.b.e("SessionReliableHelper", "cancel handle messages from server, request is null");
            return;
        }
        if (kVar.j() == null) {
            com.netease.nimlib.log.b.d("SessionReliableHelper", "cancel handle messages from server, request has no attachment(transaction)");
            return;
        }
        MsgTypeEnum[] f = kVar.f();
        if (f != null && f.length > 0) {
            com.netease.nimlib.log.b.d("SessionReliableHelper", "cancel handle messages from server, should be not continuously");
            return;
        }
        d a2 = a(getMessagesDynamicallyParam, list);
        if (a2 != null) {
            c(a2);
        }
        if (kVar2 != null && getMessagesDynamicallyParam != null) {
            final Set c = com.netease.nimlib.o.f.c(list, true, new f.a() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$TEtVtgv-E2oQCupOdmu_YYCQjtQ
                @Override // com.netease.nimlib.o.f.a
                public final Object transform(Object obj) {
                    String f2;
                    f2 = c.f((IMMessageImpl) obj);
                    return f2;
                }
            });
            ArrayList<IMMessage> c2 = c(getMessagesDynamicallyParam);
            final ArrayList arrayList = new ArrayList(com.netease.nimlib.o.f.e(c2));
            if (!com.netease.nimlib.o.f.c((Collection) c2)) {
                com.netease.nimlib.o.f.a((Collection) c2, new f.a() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$Ql1Z580wp8VxKR7whlN-Q3FmtUA
                    @Override // com.netease.nimlib.o.f.a
                    public final Object transform(Object obj) {
                        Boolean a3;
                        a3 = c.a(c, arrayList, (IMMessage) obj);
                        return a3;
                    }
                });
                if (!com.netease.nimlib.o.f.c((Collection) c2)) {
                    final d b = b(getMessagesDynamicallyParam);
                    final d c3 = c(getMessagesDynamicallyParam.getSessionId(), getMessagesDynamicallyParam.getSessionType());
                    if (b != null || c3 != null) {
                        com.netease.nimlib.o.f.a((Collection) c2, new f.a() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$1bB9iDzw7eL4qVYkMXmrXkXx9QU
                            @Override // com.netease.nimlib.o.f.a
                            public final Object transform(Object obj) {
                                Boolean a3;
                                a3 = c.a(d.this, c3, arrayList, (IMMessage) obj);
                                return a3;
                            }
                        });
                    }
                    if (!com.netease.nimlib.o.f.c((Collection) c2)) {
                        com.netease.nimlib.o.f.a((Collection) c2, new f.a() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$QaaS0XZh933h79ZSjLSq4BjevGY
                            @Override // com.netease.nimlib.o.f.a
                            public final Object transform(Object obj) {
                                Boolean a3;
                                a3 = c.a(arrayList, (IMMessage) obj);
                                return a3;
                            }
                        });
                    }
                }
            }
            com.netease.nimlib.o.f.b(arrayList, new Comparator() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$ixxZCofqLvl9fKr3z-pY5v5bfds
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int a3;
                    a3 = c.a(GetMessagesDynamicallyParam.this, (IMMessage) obj, (IMMessage) obj2);
                    return a3;
                }
            });
            kVar2.b(new com.netease.nimlib.session.c(true, arrayList)).b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String f(IMMessageImpl iMMessageImpl) {
        if (iMMessageImpl == null) {
            return null;
        }
        return iMMessageImpl.getUuid();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean a(Set set, ArrayList arrayList, IMMessage iMMessage) {
        if (iMMessage == null) {
            return true;
        }
        if (set.contains(iMMessage.getUuid())) {
            arrayList.add(iMMessage);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean a(d dVar, d dVar2, ArrayList arrayList, IMMessage iMMessage) {
        d a2;
        if (iMMessage == null || (a2 = d.a(iMMessage.getSessionId(), iMMessage.getSessionType(), iMMessage.getTime(), iMMessage.getServerId(), iMMessage.getUuid())) == null) {
            return true;
        }
        boolean z = false;
        if (dVar != null && dVar.a(a2)) {
            z = true;
        }
        boolean z2 = (dVar2 == null || a2.i() <= dVar2.i()) ? z : true;
        if (z2) {
            arrayList.add(iMMessage);
        }
        return Boolean.valueOf(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean a(ArrayList arrayList, IMMessage iMMessage) {
        if (iMMessage == null) {
            return true;
        }
        if (iMMessage.getServerId() == 0) {
            arrayList.add(iMMessage);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int a(GetMessagesDynamicallyParam getMessagesDynamicallyParam, IMMessage iMMessage, IMMessage iMMessage2) {
        int i = (iMMessage.getTime() > iMMessage2.getTime() ? 1 : (iMMessage.getTime() == iMMessage2.getTime() ? 0 : -1));
        return getMessagesDynamicallyParam.getDirection() != GetMessageDirectionEnum.BACKWARD ? -i : i;
    }

    public synchronized void b(String str, SessionTypeEnum sessionTypeEnum) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: onToClearMessages(sessionId: %s, sessionType: %s)", str, sessionTypeEnum));
        String a2 = j.a(sessionTypeEnum, str);
        this.c.remove(a2);
        this.f.add(a2);
        MsgDBHelper.removeSessionReliableInfo(str, sessionTypeEnum);
    }

    public synchronized void d() {
        com.netease.nimlib.log.b.d("SessionReliableHelper", "function: onToClearAllMessages()");
        this.c.clear();
        this.e = true;
        MsgDBHelper.removeAllSessionReliableInfo();
    }

    public synchronized d c(String str, SessionTypeEnum sessionTypeEnum) {
        com.netease.nimlib.log.b.b("SessionReliableHelper", String.format("function: getCurrentSessionReliableInfo(sessionId: %s, sessionType: %s)", str, sessionTypeEnum));
        return this.c.get(j.a(sessionTypeEnum, str));
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004f, code lost:
    
        if (r10.getToTime() > r0.i()) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean a(com.netease.nimlib.sdk.msg.model.GetMessagesDynamicallyParam r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            java.lang.String r0 = "SessionReliableHelper"
            java.lang.String r1 = "function: needCheckHasLastMessage(param: %s)"
            r2 = 1
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L57
            r4 = 0
            r3[r4] = r10     // Catch: java.lang.Throwable -> L57
            java.lang.String r1 = java.lang.String.format(r1, r3)     // Catch: java.lang.Throwable -> L57
            com.netease.nimlib.log.b.d(r0, r1)     // Catch: java.lang.Throwable -> L57
            if (r10 == 0) goto L55
            boolean r0 = r10.isValid()     // Catch: java.lang.Throwable -> L57
            if (r0 != 0) goto L1b
            goto L55
        L1b:
            java.lang.String r0 = r10.getSessionId()     // Catch: java.lang.Throwable -> L57
            com.netease.nimlib.sdk.msg.constant.SessionTypeEnum r1 = r10.getSessionType()     // Catch: java.lang.Throwable -> L57
            com.netease.nimlib.sdk.msg.model.GetMessageDirectionEnum r3 = r10.getDirection()     // Catch: java.lang.Throwable -> L57
            java.lang.String r0 = com.netease.nimlib.session.j.a(r1, r0)     // Catch: java.lang.Throwable -> L57
            java.util.Map<java.lang.String, com.netease.nimlib.session.a.d> r1 = r9.c     // Catch: java.lang.Throwable -> L57
            java.lang.Object r0 = r1.get(r0)     // Catch: java.lang.Throwable -> L57
            com.netease.nimlib.session.a.d r0 = (com.netease.nimlib.session.a.d) r0     // Catch: java.lang.Throwable -> L57
            if (r0 != 0) goto L37
            monitor-exit(r9)
            return r2
        L37:
            com.netease.nimlib.sdk.msg.model.GetMessageDirectionEnum r1 = com.netease.nimlib.sdk.msg.model.GetMessageDirectionEnum.BACKWARD     // Catch: java.lang.Throwable -> L57
            if (r3 == r1) goto L52
            long r5 = r10.getToTime()     // Catch: java.lang.Throwable -> L57
            r7 = 0
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 == 0) goto L53
            long r5 = r10.getToTime()     // Catch: java.lang.Throwable -> L57
            long r0 = r0.i()     // Catch: java.lang.Throwable -> L57
            int r10 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r10 <= 0) goto L52
            goto L53
        L52:
            r2 = 0
        L53:
            monitor-exit(r9)
            return r2
        L55:
            monitor-exit(r9)
            return r4
        L57:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netease.nimlib.session.a.c.a(com.netease.nimlib.sdk.msg.model.GetMessagesDynamicallyParam):boolean");
    }

    public synchronized d b(GetMessagesDynamicallyParam getMessagesDynamicallyParam) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: getParentInfoOfAnchor(%s)", getMessagesDynamicallyParam));
        IMMessageImpl iMMessageImpl = (IMMessageImpl) getMessagesDynamicallyParam.getAnchor();
        d a2 = d.a(iMMessageImpl.getSessionId(), iMMessageImpl.getSessionType(), iMMessageImpl.getTime(), iMMessageImpl.getServerId(), iMMessageImpl.getUuid());
        if (a2 == null && (a2 = d.a(iMMessageImpl.getSessionId(), iMMessageImpl.getSessionType(), iMMessageImpl.getTime())) == null) {
            return null;
        }
        return a(a2);
    }

    public synchronized ArrayList<IMMessage> a(GetMessagesDynamicallyParam getMessagesDynamicallyParam, d dVar) {
        long max;
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: getReliableMessagesFromDatabase(param: %s, parentOfAnchor: %s)", getMessagesDynamicallyParam, dVar));
        if (getMessagesDynamicallyParam != null && dVar != null) {
            IMMessageImpl iMMessageImpl = (IMMessageImpl) getMessagesDynamicallyParam.getAnchor();
            QueryDirectionEnum queryDirectionEnum = getMessagesDynamicallyParam.getDirection() == GetMessageDirectionEnum.BACKWARD ? QueryDirectionEnum.QUERY_NEW : QueryDirectionEnum.QUERY_OLD;
            if (getMessagesDynamicallyParam.getDirection() == GetMessageDirectionEnum.BACKWARD) {
                max = Math.min(getMessagesDynamicallyParam.getToTime(), dVar.l());
                if (max <= 0) {
                    max = Math.max(getMessagesDynamicallyParam.getToTime(), dVar.l());
                }
            } else {
                max = Math.max(getMessagesDynamicallyParam.getFromTime(), dVar.i());
            }
            if (queryDirectionEnum == QueryDirectionEnum.QUERY_OLD && iMMessageImpl.getMessageId() <= 0) {
                iMMessageImpl.setTime(Math.max(0L, iMMessageImpl.getTime() - 1));
            } else if (queryDirectionEnum == QueryDirectionEnum.QUERY_NEW && iMMessageImpl.getMessageId() <= 0) {
                max = Math.max(0L, max - 1);
            }
            return MsgDBHelper.queryMessageListEx(null, iMMessageImpl, max, queryDirectionEnum, getMessagesDynamicallyParam.getLimit(), queryDirectionEnum == QueryDirectionEnum.QUERY_NEW);
        }
        return new ArrayList<>();
    }

    public synchronized ArrayList<IMMessage> c(GetMessagesDynamicallyParam getMessagesDynamicallyParam) {
        long fromTime;
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: getMessagesFromDatabase(param: %s)", getMessagesDynamicallyParam));
        if (getMessagesDynamicallyParam == null) {
            return new ArrayList<>();
        }
        IMMessageImpl iMMessageImpl = (IMMessageImpl) getMessagesDynamicallyParam.getAnchor();
        QueryDirectionEnum queryDirectionEnum = getMessagesDynamicallyParam.getDirection() == GetMessageDirectionEnum.BACKWARD ? QueryDirectionEnum.QUERY_NEW : QueryDirectionEnum.QUERY_OLD;
        if (queryDirectionEnum == QueryDirectionEnum.QUERY_OLD && iMMessageImpl.getMessageId() <= 0) {
            iMMessageImpl.setTime(iMMessageImpl.getTime() - 1);
        }
        if (getMessagesDynamicallyParam.getDirection() == GetMessageDirectionEnum.BACKWARD) {
            fromTime = Math.max(0L, getMessagesDynamicallyParam.getToTime());
            if (iMMessageImpl.getMessageId() <= 0) {
                fromTime = Math.max(0L, fromTime - 1);
            }
        } else {
            fromTime = getMessagesDynamicallyParam.getFromTime();
        }
        return MsgDBHelper.queryMessageListEx(null, iMMessageImpl, fromTime, queryDirectionEnum, getMessagesDynamicallyParam.getLimit(), queryDirectionEnum == QueryDirectionEnum.QUERY_NEW);
    }

    public synchronized void a(final String str, final SessionTypeEnum sessionTypeEnum, final a<IMMessage, Void> aVar) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: getLastMessage(sessionId: %s, sessionType: %s, callback: %s)", str, sessionTypeEnum, aVar));
        i.a().a(new com.netease.nimlib.biz.g.b(new l(com.netease.nimlib.o.f.a(new Pair(sessionTypeEnum, str)))) { // from class: com.netease.nimlib.session.a.c.2
            @Override // com.netease.nimlib.biz.g.b, com.netease.nimlib.biz.g.c
            public void a(com.netease.nimlib.biz.e.a aVar2) {
                if (!(aVar2 instanceof n) || !aVar2.n()) {
                    a aVar3 = aVar;
                    if (aVar3 != null) {
                        aVar3.onCallback(null);
                        return;
                    }
                    return;
                }
                List<com.netease.nimlib.push.packet.b.c> a2 = ((n) aVar2).a();
                if (com.netease.nimlib.o.f.c((Collection) a2)) {
                    a aVar4 = aVar;
                    if (aVar4 != null) {
                        aVar4.onCallback(null);
                        return;
                    }
                    return;
                }
                boolean z = false;
                com.netease.nimlib.push.packet.b.c cVar = a2.get(0);
                long e = cVar.e(3);
                String c = cVar.c(4);
                long e2 = cVar.e(5);
                IMMessage queryMessageByUuid = MsgDBHelper.queryMessageByUuid(c);
                if (queryMessageByUuid != null && queryMessageByUuid.getServerId() == e && queryMessageByUuid.getTime() == e2) {
                    z = true;
                }
                if (z) {
                    if (((d) c.this.c.get(j.a(sessionTypeEnum, str))) == null) {
                        new d(str, sessionTypeEnum).a(e2, e, c);
                    }
                }
                a aVar5 = aVar;
                if (aVar5 != null) {
                    aVar5.onCallback(z ? queryMessageByUuid : null);
                }
            }
        });
    }

    private void a(String str, SessionTypeEnum sessionTypeEnum, d dVar) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: putCurrentSessionReliableInfo(sessionId: %s, sessionType: %s, info: %s)", str, sessionTypeEnum, dVar));
        this.c.put(j.a(sessionTypeEnum, str), dVar);
    }

    private d a(d dVar) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: getParentInfo(info: %s)", dVar));
        List<d> queryParentInfos = MsgDBHelper.queryParentInfos(dVar);
        if (com.netease.nimlib.o.f.c((Collection) queryParentInfos)) {
            return null;
        }
        int e = com.netease.nimlib.o.f.e(queryParentInfos);
        d dVar2 = queryParentInfos.get(0);
        if (e > 1) {
            com.netease.nimlib.log.b.e("SessionReliableHelper", String.format("get more than one(%s) parent infos with %s", Integer.valueOf(e), dVar));
            Iterator<d> it = queryParentInfos.iterator();
            while (it.hasNext()) {
                dVar2.b(it.next());
            }
            c(dVar2);
            queryParentInfos = MsgDBHelper.queryParentInfos(dVar);
        }
        if (com.netease.nimlib.o.f.c((Collection) queryParentInfos)) {
            return null;
        }
        if (com.netease.nimlib.o.f.e(queryParentInfos) > 1) {
            com.netease.nimlib.log.b.e("SessionReliableHelper", String.format("fixed parentInfos' size(%s) is still unexpected", Integer.valueOf(e)));
        }
        return queryParentInfos.get(0);
    }

    private void c(IMMessageImpl iMMessageImpl) {
        com.netease.nimlib.log.b.c("SessionReliableHelper", String.format("function: recordOnlineMessageDone(message: %s)", IMMessageImpl.toStringSimple(iMMessageImpl)));
        if (iMMessageImpl == null) {
            return;
        }
        this.g.put(j.a(iMMessageImpl.getSessionType(), iMMessageImpl.getSessionId()), iMMessageImpl);
        if (this.i) {
            return;
        }
        this.i = true;
        this.h.postDelayed(new Runnable() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$_M8x-TmRLPMQcKoKwGdbemyu0bo
            @Override // java.lang.Runnable
            public final void run() {
                c.this.f();
            }
        }, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        try {
            synchronized (a()) {
                if (this.g.isEmpty()) {
                    com.netease.nimlib.log.b.e("SessionReliableHelper", "on timer without new message");
                    return;
                }
                HashMap hashMap = new HashMap(this.g);
                this.g.clear();
                this.i = false;
                a(hashMap);
            }
        } catch (Throwable th) {
            com.netease.nimlib.log.b.f("SessionReliableHelper", "recordOnlineMessageDone on exception. " + th);
        }
    }

    private List<Pair<SessionTypeEnum, String>> b(List<IMMessageImpl> list) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", "function: getSessionListFromMessages");
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return new ArrayList(0);
        }
        return com.netease.nimlib.o.f.b(com.netease.nimlib.o.f.a((Collection) list, true, (f.a) new f.a() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$kS8FXNWkaENJYnWitdrfthOXp9o
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                String e;
                e = c.e((IMMessageImpl) obj);
                return e;
            }
        }).keySet(), true, new f.a() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$NoUkbD5aDyEYRWR4EEi6EQlHF6M
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                Pair b;
                b = c.b((String) obj);
                return b;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String e(IMMessageImpl iMMessageImpl) {
        if (iMMessageImpl == null) {
            return null;
        }
        return j.a(iMMessageImpl.getSessionType(), iMMessageImpl.getSessionId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Pair b(String str) {
        if (w.a((CharSequence) str)) {
            return null;
        }
        return j.d(str);
    }

    private List<b> c(List<Pair<SessionTypeEnum, String>> list) {
        com.netease.nimlib.log.b.b("SessionReliableHelper", "function: getSavedSyncMessageSituations: " + com.netease.nimlib.o.f.f(list));
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return new ArrayList(0);
        }
        return com.netease.nimlib.o.f.b(list, true, new f.a() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$Gh_JSAOGcPSVVZQJl3ap863oqn4
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                b a2;
                a2 = c.this.a((Pair) obj);
                return a2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ b a(Pair pair) {
        if (pair == null || pair.first == null || w.a((CharSequence) pair.second)) {
            com.netease.nimlib.log.b.d("SessionReliableHelper", "get null element when getting saved sync message situations");
            return null;
        }
        String a2 = j.a((SessionTypeEnum) pair.first, (String) pair.second);
        b bVar = this.d.get(a2);
        if (bVar != null) {
            return bVar;
        }
        com.netease.nimlib.log.b.e("SessionReliableHelper", String.format("session(%s) does not exist when getting saved sync message situations", a2));
        return null;
    }

    private List<Pair<SessionTypeEnum, String>> d(List<com.netease.nimlib.push.packet.b.c> list) {
        com.netease.nimlib.log.b.b("SessionReliableHelper", "function: getSessionListFromMessageProperties");
        if (com.netease.nimlib.o.f.c((Collection) list)) {
            return new ArrayList(0);
        }
        return com.netease.nimlib.o.f.b(com.netease.nimlib.o.f.a((Collection) list, true, (f.a) new f.a() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$jJ36IsqQgAaQCxEFqdfHJONhWvg
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                String a2;
                a2 = c.a((com.netease.nimlib.push.packet.b.c) obj);
                return a2;
            }
        }).keySet(), true, new f.a() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$qLCnEHrFEzKw8MrZw853-m35nsY
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                Pair a2;
                a2 = c.a((String) obj);
                return a2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String a(com.netease.nimlib.push.packet.b.c cVar) {
        if (cVar == null) {
            return null;
        }
        return j.a(SessionTypeEnum.typeOfValue(cVar.d(0)), g.b(cVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Pair a(String str) {
        if (w.a((CharSequence) str)) {
            return null;
        }
        return j.d(str);
    }

    private void c(List<Pair<SessionTypeEnum, String>> list, com.netease.nimlib.session.a.a aVar) {
        com.netease.nimlib.log.b.b("SessionReliableHelper", String.format("function: markReceivedSyncMessage(sessions: %s, source: %s)", com.netease.nimlib.o.f.f(list), aVar));
        List<b> c = c(list);
        if (com.netease.nimlib.o.f.c((Collection) c)) {
            return;
        }
        for (b bVar : c) {
            if (bVar != null) {
                int i = AnonymousClass3.a[aVar.ordinal()];
                if (i == 1) {
                    bVar.c();
                } else if (i == 2) {
                    bVar.a();
                }
            }
        }
    }

    /* compiled from: SessionReliableHelper.java */
    /* renamed from: com.netease.nimlib.session.a.c$3, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[com.netease.nimlib.session.a.a.values().length];
            a = iArr;
            try {
                iArr[com.netease.nimlib.session.a.a.ROAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[com.netease.nimlib.session.a.a.OFFLINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void d(List<Pair<SessionTypeEnum, String>> list, com.netease.nimlib.session.a.a aVar) {
        com.netease.nimlib.log.b.b("SessionReliableHelper", String.format("function: markSavedSyncMessage(sessions: %s, source: %s)", com.netease.nimlib.o.f.f(list), aVar));
        List<b> c = c(list);
        if (com.netease.nimlib.o.f.c((Collection) c)) {
            return;
        }
        for (b bVar : c) {
            if (bVar != null) {
                int i = AnonymousClass3.a[aVar.ordinal()];
                if (i == 1) {
                    bVar.d();
                } else if (i == 2) {
                    bVar.b();
                }
            }
        }
    }

    private synchronized void a(Map<String, IMMessageImpl> map) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: handleNewMessageFromServer(message: %s)", com.netease.nimlib.o.f.a(map.values(), ", ", new f.a() { // from class: com.netease.nimlib.session.a.-$$Lambda$qW8vxP-5wbSdtkPJ0pg1Ab0FVrM
            @Override // com.netease.nimlib.o.f.a
            public final Object transform(Object obj) {
                return IMMessageImpl.toStringSimple((IMMessageImpl) obj);
            }
        })));
        e eVar = this.b;
        if (!map.isEmpty() && eVar != null) {
            for (IMMessageImpl iMMessageImpl : map.values()) {
                if (iMMessageImpl != null) {
                    String sessionId = iMMessageImpl.getSessionId();
                    SessionTypeEnum sessionType = iMMessageImpl.getSessionType();
                    if (!w.a((CharSequence) sessionId) && sessionType != null) {
                        d dVar = this.c.get(j.a(sessionType, sessionId));
                        long b = eVar.b();
                        if (dVar != null) {
                            dVar.b(iMMessageImpl.getTime(), iMMessageImpl.getServerId(), iMMessageImpl.getUuid());
                            if (c(dVar)) {
                                a(sessionId, sessionType, dVar);
                            }
                        } else if (b > 0) {
                            d dVar2 = new d(sessionId, sessionType);
                            dVar2.a(b, 0L, (String) null);
                            dVar2.b(iMMessageImpl.getTime(), iMMessageImpl.getServerId(), iMMessageImpl.getUuid());
                            if (c(dVar2)) {
                                a(sessionId, sessionType, dVar2);
                            }
                        }
                    }
                    return;
                }
            }
        }
    }

    private d a(GetMessagesDynamicallyParam getMessagesDynamicallyParam, List<IMMessageImpl> list) {
        IMMessage iMMessage;
        SessionTypeEnum sessionType;
        String str;
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: getReliableInfoFromServerMessages(param: %s, messages: %s)", getMessagesDynamicallyParam, a(list)));
        if ((getMessagesDynamicallyParam == null || !getMessagesDynamicallyParam.isValid()) && com.netease.nimlib.o.f.c((Collection) list)) {
            return null;
        }
        if (getMessagesDynamicallyParam != null && getMessagesDynamicallyParam.isValid()) {
            str = getMessagesDynamicallyParam.getSessionId();
            sessionType = getMessagesDynamicallyParam.getSessionType();
        } else {
            if (!com.netease.nimlib.o.f.d(list) || (iMMessage = (IMMessage) com.netease.nimlib.o.f.e(list, new f.a() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$GIsA55EyillS3Ud2Ri0eaS40mF8
                @Override // com.netease.nimlib.o.f.a
                public final Object transform(Object obj) {
                    Boolean d;
                    d = c.d((IMMessageImpl) obj);
                    return d;
                }
            })) == null) {
                return null;
            }
            String sessionId = iMMessage.getSessionId();
            sessionType = iMMessage.getSessionType();
            str = sessionId;
        }
        d dVar = new d(str, sessionType);
        d a2 = d.a(list, true);
        d a3 = d.a(getMessagesDynamicallyParam);
        if (a3 == null || getMessagesDynamicallyParam.getAnchorServerId() > 0 || w.b((CharSequence) getMessagesDynamicallyParam.getAnchorClientId())) {
            return a2;
        }
        if (com.netease.nimlib.o.f.c((Collection) list) || a2 == null) {
            if (getMessagesDynamicallyParam.getFromTime() <= 0 || getMessagesDynamicallyParam.getToTime() <= 0) {
                return null;
            }
            return a3;
        }
        GetMessageDirectionEnum direction = getMessagesDynamicallyParam.getDirection();
        if (a3.a() && a3.b() && getMessagesDynamicallyParam.getLimit() > 0 && com.netease.nimlib.o.f.e(list) < getMessagesDynamicallyParam.getLimit()) {
            a3.b(a2);
            return a3;
        }
        if (direction == GetMessageDirectionEnum.BACKWARD && getMessagesDynamicallyParam.getFromTime() > 0) {
            if (a3.i() < a2.i()) {
                dVar.a(a3.i(), a3.g(), a3.h());
            } else {
                dVar.a(a2.i(), a2.g(), a2.h());
            }
            dVar.b(a2.l(), a2.j(), a2.k());
            return dVar;
        }
        if (direction != GetMessageDirectionEnum.FORWARD || getMessagesDynamicallyParam.getToTime() <= 0) {
            return a2;
        }
        dVar.a(a2.i(), a2.g(), a2.h());
        if (a3.l() > a2.l()) {
            dVar.b(a3.l(), a3.j(), a3.k());
        } else {
            dVar.b(a2.l(), a2.j(), a2.k());
        }
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean d(IMMessageImpl iMMessageImpl) {
        return Boolean.valueOf(iMMessageImpl != null);
    }

    private synchronized void e() {
        com.netease.nimlib.log.b.d("SessionReliableHelper", "function: reset");
        this.b = null;
        this.c.clear();
        this.d.clear();
        this.f.clear();
        this.g.clear();
    }

    private boolean b(d dVar) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: canMergeReliableInfo(info: %s)", dVar));
        return (this.e || dVar == null || !dVar.a() || !dVar.b() || this.f.contains(dVar.c())) ? false : true;
    }

    private synchronized boolean c(final d dVar) {
        com.netease.nimlib.log.b.d("SessionReliableHelper", String.format("function: mergeSessionReliableInfo(info: %s)", dVar));
        if (!b(dVar)) {
            return false;
        }
        List<d> queryMayOverLappedInfos = MsgDBHelper.queryMayOverLappedInfos(dVar);
        if (com.netease.nimlib.o.f.d(queryMayOverLappedInfos)) {
            final ArrayList arrayList = new ArrayList();
            com.netease.nimlib.o.f.f(queryMayOverLappedInfos, new f.a() { // from class: com.netease.nimlib.session.a.-$$Lambda$c$NdfuvDSsWkIZm1n0dukF8CdK9Lc
                @Override // com.netease.nimlib.o.f.a
                public final Object transform(Object obj) {
                    Boolean a2;
                    a2 = c.a(d.this, arrayList, (d) obj);
                    return a2;
                }
            });
            MsgDBHelper.removeSessionReliableInfo(arrayList);
        }
        long saveSessionReliableInfo = MsgDBHelper.saveSessionReliableInfo(dVar);
        if (saveSessionReliableInfo <= 0) {
            return false;
        }
        dVar.a(Long.valueOf(saveSessionReliableInfo));
        if (C$r8$backportedMethods$utility$Objects$2$equals.equals(MsgDBHelper.queryLastSessionReliableInfo(dVar.e(), dVar.f()).d(), dVar.d())) {
            a(dVar.e(), dVar.f(), dVar);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean a(d dVar, List list, d dVar2) {
        Long d;
        if (dVar2 != null && (d = dVar2.d()) != null && dVar.b(dVar2)) {
            list.add(d);
        }
        return true;
    }

    String a(List<IMMessageImpl> list) {
        return list == null ? Constants.NULL_VERSION_ID : list.isEmpty() ? "[]" : String.format("from %s to %s", IMMessageImpl.toStringSimple(list.get(0)), IMMessageImpl.toStringSimple(list.get(list.size() - 1)));
    }
}
