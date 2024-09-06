package com.netease.nim.highavailable.lava.base.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public interface MultipartWriter {
    void addPart(PrintWriter printWriter, OutputStream outputStream) throws IOException;
}
