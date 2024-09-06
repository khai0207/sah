package com.netease.nimlib.amazonaws.services.cognitoidentity.model;

import com.alipay.sdk.m.q.h;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public class DeleteIdentitiesResult implements Serializable {
    private List<UnprocessedIdentityId> unprocessedIdentityIds;

    public List<UnprocessedIdentityId> getUnprocessedIdentityIds() {
        return this.unprocessedIdentityIds;
    }

    public void setUnprocessedIdentityIds(Collection<UnprocessedIdentityId> collection) {
        if (collection == null) {
            this.unprocessedIdentityIds = null;
        } else {
            this.unprocessedIdentityIds = new ArrayList(collection);
        }
    }

    public DeleteIdentitiesResult withUnprocessedIdentityIds(UnprocessedIdentityId... unprocessedIdentityIdArr) {
        if (getUnprocessedIdentityIds() == null) {
            this.unprocessedIdentityIds = new ArrayList(unprocessedIdentityIdArr.length);
        }
        for (UnprocessedIdentityId unprocessedIdentityId : unprocessedIdentityIdArr) {
            this.unprocessedIdentityIds.add(unprocessedIdentityId);
        }
        return this;
    }

    public DeleteIdentitiesResult withUnprocessedIdentityIds(Collection<UnprocessedIdentityId> collection) {
        setUnprocessedIdentityIds(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getUnprocessedIdentityIds() != null) {
            sb.append("UnprocessedIdentityIds: " + getUnprocessedIdentityIds());
        }
        sb.append(h.d);
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getUnprocessedIdentityIds() == null ? 0 : getUnprocessedIdentityIds().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteIdentitiesResult)) {
            return false;
        }
        DeleteIdentitiesResult deleteIdentitiesResult = (DeleteIdentitiesResult) obj;
        if ((deleteIdentitiesResult.getUnprocessedIdentityIds() == null) ^ (getUnprocessedIdentityIds() == null)) {
            return false;
        }
        return deleteIdentitiesResult.getUnprocessedIdentityIds() == null || deleteIdentitiesResult.getUnprocessedIdentityIds().equals(getUnprocessedIdentityIds());
    }
}
