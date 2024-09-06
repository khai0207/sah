package com.netease.nimlib.sdk.nos.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class NosThumbParam implements Serializable {
    public int height;
    public ThumbType thumb = ThumbType.Crop;
    public int width;

    /* loaded from: classes.dex */
    public enum ThumbType implements Serializable {
        Internal,
        Crop,
        External
    }
}
