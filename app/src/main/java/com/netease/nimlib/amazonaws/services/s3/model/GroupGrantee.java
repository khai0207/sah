package com.netease.nimlib.amazonaws.services.s3.model;

/* loaded from: classes.dex */
public enum GroupGrantee implements Grantee {
    AllUsers("http://acs.amazonaws.com/groups/global/AllUsers"),
    AuthenticatedUsers("http://acs.amazonaws.com/groups/global/AuthenticatedUsers"),
    LogDelivery("http://acs.amazonaws.com/groups/s3/LogDelivery");

    private String groupUri;

    @Override // com.netease.nimlib.amazonaws.services.s3.model.Grantee
    public String getTypeIdentifier() {
        return "uri";
    }

    GroupGrantee(String str) {
        this.groupUri = str;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.Grantee
    public String getIdentifier() {
        return this.groupUri;
    }

    @Override // com.netease.nimlib.amazonaws.services.s3.model.Grantee
    public void setIdentifier(String str) {
        throw new UnsupportedOperationException("Group grantees have preset identifiers that cannot be modified.");
    }

    @Override // java.lang.Enum
    public String toString() {
        return "GroupGrantee [" + this.groupUri + "]";
    }

    public static GroupGrantee parseGroupGrantee(String str) {
        for (GroupGrantee groupGrantee : values()) {
            if (groupGrantee.groupUri.equals(str)) {
                return groupGrantee;
            }
        }
        return null;
    }
}
