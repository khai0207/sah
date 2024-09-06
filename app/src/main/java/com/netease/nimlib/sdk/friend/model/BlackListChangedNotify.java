package com.netease.nimlib.sdk.friend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class BlackListChangedNotify implements Serializable {
    private List<String> addedAccounts = new ArrayList();
    private List<String> removedAccounts = new ArrayList();

    public BlackListChangedNotify(List<String> list, List<String> list2) {
        if (list != null && !list.isEmpty()) {
            this.addedAccounts.addAll(list);
        }
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        this.removedAccounts.addAll(list2);
    }

    public BlackListChangedNotify(String str, String str2) {
        if (str != null) {
            this.addedAccounts.add(str);
        }
        if (str2 != null) {
            this.removedAccounts.add(str2);
        }
    }

    public List<String> getAddedAccounts() {
        return this.addedAccounts;
    }

    public List<String> getRemovedAccounts() {
        return this.removedAccounts;
    }
}
