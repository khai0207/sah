package com.android.pc.ioc.download;

import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import com.alipay.sdk.m.h.a;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.db.sqlite.WhereBuilder;
import com.android.pc.ioc.event.EventBus;
import com.android.pc.ioc.internet.InternetConfig;
import com.android.pc.ioc.update.NotificationHelper;
import com.android.pc.util.Handler_File;
import com.netease.nimlib.amazonaws.http.HttpHeader;
import com.netease.nimlib.amazonaws.mobileconnectors.s3.transferutility.TransferService;
import com.netease.nimlib.amazonaws.services.s3.Headers;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class FileLoaderManager {
    private static String proxyPassword = null;
    private static int proxyPort = 0;
    private static String proxyServer = null;
    private static String proxyUser = null;
    private static final int stuts_finish = 0;
    private static final int stuts_start = 1;
    private static boolean useProxy;
    private static HashMap<String, Boolean> loadingMap = new HashMap<>();
    private static String UA = InternetConfig.UA;
    private static int blockSize = 4096;
    static EventBus eventBus = EventBus.getDefault();
    private static HashMap<String, FileEntity> fileEntities = new HashMap<>();
    private static Handler handler = new Handler() { // from class: com.android.pc.ioc.download.FileLoaderManager.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            List<ThreadEntity> threadsEntities;
            FileEntity fileEntity = (FileEntity) message.obj;
            int i = message.what;
            if (i == 0) {
                FileLoaderManager.loadingMap.remove(fileEntity.getUrl());
                FileLoaderManager.sendMsg(fileEntity, 3, 100);
                return;
            }
            if (i != 1) {
                return;
            }
            synchronized (FileLoaderManager.loadingMap) {
                if (!((Boolean) FileLoaderManager.loadingMap.get(fileEntity.getUrl())).booleanValue()) {
                    FileLoaderManager.loadingMap.remove(fileEntity.getUrl());
                    FileLoaderManager.sendMsg(fileEntity, 2, 0);
                    return;
                }
                fileEntity.setSucess(false);
                if ((fileEntity.getId() != 0 && !fileEntity.isAgain()) || fileEntity.getLength() <= 0 || !fileEntity.isRange()) {
                    if (fileEntity.getLength() <= 0 || !fileEntity.isRange()) {
                        new DownloadSingleThread(fileEntity).start();
                        return;
                    }
                    if (fileEntity.getId() == 0 || fileEntity.getLength() <= 0 || !fileEntity.isRange() || (threadsEntities = fileEntity.getThreadsEntities()) == null) {
                        return;
                    }
                    int size = threadsEntities.size();
                    DownloadThreadGroup downloadThreadGroup = new DownloadThreadGroup("multi threads continue", fileEntity);
                    for (int i2 = 0; i2 < size; i2++) {
                        ThreadEntity threadEntity = threadsEntities.get(i2);
                        downloadThreadGroup.setEntity(threadEntity);
                        if (threadEntity.getLoad() + threadEntity.getStart() != threadEntity.getEnd()) {
                            new DownloadThread(downloadThreadGroup, threadEntity, fileEntity, i2, threadEntity.getStart(), threadEntity.getEnd()).start();
                        }
                    }
                    downloadThreadGroup.start();
                    return;
                }
                int threads = fileEntity.getThreads();
                long length = fileEntity.getLength();
                long j = threads;
                long j2 = length / j;
                long j3 = length % j;
                ThreadEntity.delete(fileEntity.getId());
                DownloadThreadGroup downloadThreadGroup2 = new DownloadThreadGroup("multi threads reload", fileEntity);
                int i3 = 0;
                while (i3 < threads) {
                    long j4 = i3 * j2;
                    int i4 = i3 + 1;
                    long j5 = i4 * j2;
                    if (threads > 1 && i3 == threads - 1 && j3 > 0) {
                        j5 += j3;
                    }
                    long j6 = j5;
                    ThreadEntity threadEntity2 = new ThreadEntity();
                    threadEntity2.setStart(j4);
                    threadEntity2.setEnd(j6);
                    threadEntity2.setLoad(0L);
                    threadEntity2.setFileEntity(fileEntity);
                    downloadThreadGroup2.setEntity(threadEntity2);
                    new DownloadThread(downloadThreadGroup2, threadEntity2, fileEntity, i3, j4, j6).start();
                    downloadThreadGroup2 = downloadThreadGroup2;
                    i3 = i4;
                }
                DownloadThreadGroup downloadThreadGroup3 = downloadThreadGroup2;
                if (threads > 0) {
                    downloadThreadGroup3.start();
                }
            }
        }
    };

    public static String getProxyUser() {
        return proxyUser;
    }

    public static void setProxyUser(String str) {
        proxyUser = str;
    }

    public static String getProxyPassword() {
        return proxyPassword;
    }

    public static void setProxyPassword(String str) {
        proxyPassword = str;
    }

    public static String getProxyServer() {
        return proxyServer;
    }

    public static void setProxyServer(String str) {
        proxyServer = str;
    }

    public static int getProxyPort() {
        return proxyPort;
    }

    public static void setProxyPort(int i) {
        proxyPort = i;
    }

    public static boolean isUseProxy() {
        return useProxy;
    }

    public static void setUseProxy(boolean z) {
        useProxy = z;
    }

    public static void download(String str, String str2) {
        download(str, str2, 1, false, null);
    }

    public static void download(String str) {
        download(str, null, 1, false, null);
    }

    public static void download(String str, String str2, NotfiEntity notfiEntity) {
        download(str, str2, 1, false, notfiEntity);
    }

    public static void download(String str, NotfiEntity notfiEntity) {
        download(str, null, 1, false, notfiEntity);
    }

    public static void download(String str, String str2, int i) {
        download(str, str2, i, false, null);
    }

    public static void download(String str, int i) {
        download(str, null, i, false, null);
    }

    public static void download(String str, String str2, int i, NotfiEntity notfiEntity) {
        download(str, str2, i, false, notfiEntity);
    }

    public static void download(String str, int i, NotfiEntity notfiEntity) {
        download(str, null, i, false, notfiEntity);
    }

    public static void downloadUpdate(String str, String str2) {
        download(str, str2, 1, true, null);
    }

    public static void downloadUpdate(String str) {
        download(str, null, 1, true, null);
    }

    public static void downloadUpdate(String str, String str2, NotfiEntity notfiEntity) {
        download(str, str2, 1, true, notfiEntity);
    }

    public static void downloadUpdate(String str, NotfiEntity notfiEntity) {
        download(str, null, 1, true, notfiEntity);
    }

    public static void downloadUpdate(String str, String str2, int i) {
        download(str, str2, i, true, null);
    }

    public static void downloadUpdate(String str, int i) {
        download(str, null, i, true, null);
    }

    public static void downloadUpdate(String str, String str2, int i, NotfiEntity notfiEntity) {
        download(str, str2, i, true, notfiEntity);
    }

    public static void downloadUpdate(String str, int i, NotfiEntity notfiEntity) {
        download(str, null, i, true, notfiEntity);
    }

    public static Set<String> getLoadingUrl() {
        return fileEntities.keySet();
    }

    public static boolean showNotif(String str, NotfiEntity notfiEntity) {
        if (!fileEntities.containsKey(str)) {
            return false;
        }
        FileEntity fileEntity = fileEntities.get(str);
        fileEntity.setNotfi(notfiEntity);
        NotificationHelper notificationHelper = new NotificationHelper(Ioc.getIoc().getApplication(), notfiEntity.getLayout_id(), notfiEntity.getIcon_id(), notfiEntity.getProgress_id(), notfiEntity.getProgress_txt_id(), notfiEntity.getClazz());
        fileEntity.setHelper(notificationHelper);
        if (fileEntity.isRange()) {
            notificationHelper.initNotif();
            return true;
        }
        notificationHelper.downNotification("下载中......");
        return true;
    }

    public static void hideNotif(String str) {
        if (fileEntities.containsKey(str)) {
            FileEntity fileEntity = fileEntities.get(str);
            fileEntity.setNotfi(null);
            NotificationHelper helper = fileEntity.getHelper();
            fileEntity.setHelper(null);
            helper.cancel();
        }
    }

    public static void hideNotif() {
        ((NotificationManager) Ioc.getIoc().getApplication().getSystemService(TransferService.INTENT_KEY_NOTIFICATION)).cancelAll();
    }

    public static void stop(String str) {
        if (loadingMap.containsKey(str)) {
            loadingMap.put(str, false);
        }
    }

    public static List<FileEntity> getAllDownload() {
        return FileEntity.getAllEntity();
    }

    public static List<FileEntity> getAllFinishDownload() {
        return FileEntity.getAllFinishEntity();
    }

    public static List<FileEntity> getAllFailureDownload() {
        return FileEntity.getAllFailureEntity();
    }

    public static void clearByUrl(String str) {
        Ioc.getIoc().getDb().delete(FileEntity.class, WhereBuilder.b(" url ", " = ", str));
    }

    public static void clearHistory() {
        Ioc.getIoc().getDb().deleteAll(FileEntity.class);
        Ioc.getIoc().getDb().deleteAll(ThreadEntity.class);
    }

    private static void download(String str, final String str2, int i, boolean z, NotfiEntity notfiEntity) {
        if (loadingMap.containsKey(str)) {
            return;
        }
        loadingMap.put(str, true);
        if (str2 == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(Handler_File.getExternalCacheDir(Ioc.getIoc().getApplication(), "files").getPath());
            sb.append("/file_");
            sb.append((System.currentTimeMillis() + "").substring(4));
            str2 = sb.toString();
        }
        File file = new File(str2);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        final FileEntity entityByUrl = FileEntity.getEntityByUrl(str);
        if (entityByUrl == null) {
            entityByUrl = new FileEntity();
            entityByUrl.setUrl(str);
            entityByUrl.setPath(str2);
            entityByUrl.setThreads(i);
        }
        fileEntities.put(str, entityByUrl);
        if (notfiEntity != null) {
            NotificationHelper notificationHelper = new NotificationHelper(Ioc.getIoc().getApplication(), notfiEntity.getLayout_id(), notfiEntity.getIcon_id(), notfiEntity.getProgress_id(), notfiEntity.getProgress_txt_id(), notfiEntity.getClazz());
            notificationHelper.initNotif();
            entityByUrl.setHelper(notificationHelper);
        }
        entityByUrl.setNotfi(notfiEntity);
        entityByUrl.setUpdate(z);
        File file2 = new File(entityByUrl.getPath());
        if (!file2.exists() || file2.length() == 0) {
            entityByUrl.setAgain(true);
        }
        Handler_File.makeDirs(str2);
        if (entityByUrl.getPath().equals(str2) && entityByUrl.isSucess && !entityByUrl.isAgain()) {
            entityByUrl.setNotfi(null);
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 0;
            obtainMessage.obj = entityByUrl;
            handler.sendMessage(obtainMessage);
            return;
        }
        if (!entityByUrl.getPath().equals(str2) && !entityByUrl.isAgain()) {
            final File file3 = new File(str2);
            final File file4 = new File(entityByUrl.getPath());
            File parentFile = file3.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            new Thread(new Runnable() { // from class: com.android.pc.ioc.download.FileLoaderManager.2
                @Override // java.lang.Runnable
                public void run() {
                    Handler_File.copyFile(file4, file3);
                    entityByUrl.setPath(str2);
                    entityByUrl.update();
                    if (entityByUrl.isSucess()) {
                        entityByUrl.setNotfi(null);
                        Message obtainMessage2 = FileLoaderManager.handler.obtainMessage();
                        obtainMessage2.what = 0;
                        obtainMessage2.obj = entityByUrl;
                        FileLoaderManager.handler.sendMessage(obtainMessage2);
                        return;
                    }
                    new Thread(new GetLengthThread(entityByUrl)).start();
                }
            }).start();
            return;
        }
        new Thread(new GetLengthThread(entityByUrl)).start();
    }

    /* loaded from: classes.dex */
    private static class GetLengthThread implements Runnable {
        private FileEntity fileEntity;

        public GetLengthThread(FileEntity fileEntity) {
            this.fileEntity = fileEntity;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!((Boolean) FileLoaderManager.loadingMap.get(this.fileEntity.getUrl())).booleanValue()) {
                    FileLoaderManager.sendMsg(this.fileEntity, 2, 0);
                    FileLoaderManager.loadingMap.remove(this.fileEntity.getUrl());
                    return;
                }
                FileLoaderManager.sendMsg(this.fileEntity, 1, 0);
                HttpURLConnection httpConnection = FileLoaderManager.getHttpConnection(0L, this.fileEntity.getUrl());
                String str = null;
                Map<String, List<String>> headerFields = httpConnection.getHeaderFields();
                for (String str2 : headerFields.keySet()) {
                    if ("location".equalsIgnoreCase(str2)) {
                        str = headerFields.get(str2).get(0);
                    }
                }
                if (str == null) {
                    str = httpConnection.getURL().toString();
                }
                if (str.startsWith(a.q) && !str.equals(this.fileEntity.getUrl())) {
                    httpConnection.disconnect();
                    this.fileEntity.setReal_url(str);
                    httpConnection = FileLoaderManager.getHttpConnection(0L, str);
                    Ioc.getIoc().getLogger().i("下载的文件是重定向地址,正在获取真实地址......");
                }
                int contentLength = httpConnection.getContentLength();
                if (contentLength <= 0) {
                    Ioc.getIoc().getLogger().w("服务器不能返回文件大小，采用单线程下载");
                    this.fileEntity.setThreads(1);
                }
                this.fileEntity.setRange(true);
                if (httpConnection.getHeaderField(Headers.CONTENT_RANGE) == null) {
                    Ioc.getIoc().getLogger().w("服务器不支持断点续传");
                    this.fileEntity.setRange(false);
                }
                httpConnection.disconnect();
                File file = new File(this.fileEntity.getPath());
                if (contentLength > 0 && file.getParentFile().getFreeSpace() < contentLength) {
                    Ioc.getIoc().getLogger().e("磁盘空间不够");
                    FileLoaderManager.sendMsg(this.fileEntity, 2, 0);
                    FileLoaderManager.loadingMap.remove(this.fileEntity.getUrl());
                    return;
                }
                if (contentLength > 0 && this.fileEntity.getLength() > 0 && this.fileEntity.getLength() != contentLength) {
                    this.fileEntity.setAgain(true);
                }
                this.fileEntity.setLength(contentLength);
                if (contentLength <= 10240) {
                    this.fileEntity.setThreads(1);
                }
                Message obtainMessage = FileLoaderManager.handler.obtainMessage();
                obtainMessage.what = 1;
                obtainMessage.obj = this.fileEntity;
                FileLoaderManager.handler.sendMessage(obtainMessage);
            } catch (Exception unused) {
                if (this.fileEntity.getNotfi() != null) {
                    NotfiEntity notfi = this.fileEntity.getNotfi();
                    NotificationHelper notificationHelper = new NotificationHelper(Ioc.getIoc().getApplication(), notfi.getLayout_id(), notfi.getIcon_id(), notfi.getProgress_id(), notfi.getProgress_txt_id(), notfi.getClazz());
                    notificationHelper.initNotif();
                    this.fileEntity.setHelper(notificationHelper);
                }
                FileLoaderManager.sendMsg(this.fileEntity, 2, 0);
                FileLoaderManager.loadingMap.remove(this.fileEntity.getUrl());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HttpURLConnection getHttpConnection(long j, String str) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        HttpURLConnection.setFollowRedirects(false);
        if (useProxy) {
            httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyServer, proxyPort)));
            if (proxyUser != null && proxyPassword != null) {
                String str2 = new String(Base64.encode(new String(proxyUser + ":" + proxyPassword).getBytes(), 0));
                StringBuilder sb = new StringBuilder();
                sb.append("Basic ");
                sb.append(str2);
                httpURLConnection.setRequestProperty("Proxy-Authorization", sb.toString());
            }
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setRequestProperty(HttpHeader.USER_AGENT, UA);
        if (j >= 0) {
            httpURLConnection.setRequestProperty("RANGE", "bytes=" + j + "-");
        }
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode >= 200 && responseCode < 400) {
            return httpURLConnection;
        }
        loadingMap.remove(str);
        throw new IOException("服务器返回无效信息:" + responseCode);
    }

    /* loaded from: classes.dex */
    private static class DownloadThreadGroup extends ThreadGroup {
        private FileEntity entity;
        public List<ThreadEntity> entitysList;

        public DownloadThreadGroup(String str, FileEntity fileEntity) {
            super(str);
            this.entitysList = new ArrayList();
            this.entity = fileEntity;
        }

        /* loaded from: classes.dex */
        private class DownLoad extends Thread {
            private DownLoad() {
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                int i = -1;
                while (DownloadThreadGroup.this.activeCount() > 0) {
                    DownloadThreadGroup.this.entity.setLoadedLength(DownloadThreadGroup.this.getSize());
                    int loadedLength = (int) ((DownloadThreadGroup.this.entity.getLoadedLength() * 100) / DownloadThreadGroup.this.entity.getLength());
                    if (i != loadedLength) {
                        FileLoaderManager.sendMsg(DownloadThreadGroup.this.entity, 0, loadedLength);
                    }
                    Ioc.getIoc().getDb().saveOrUpdateAll(DownloadThreadGroup.this.entitysList);
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception unused) {
                    }
                    i = loadedLength;
                }
                long size = DownloadThreadGroup.this.getSize();
                DownloadThreadGroup.this.entity.setSucess(true);
                if (size != DownloadThreadGroup.this.entity.getLength()) {
                    FileLoaderManager.sendMsg(DownloadThreadGroup.this.entity, 2, 0);
                    DownloadThreadGroup.this.entity.setSucess(false);
                } else {
                    FileLoaderManager.sendMsg(DownloadThreadGroup.this.entity, 3, (int) ((size * 100) / DownloadThreadGroup.this.entity.getLength()));
                }
                FileLoaderManager.loadingMap.remove(DownloadThreadGroup.this.entity.getUrl());
                Ioc.getIoc().getDb().saveOrUpdateAll(DownloadThreadGroup.this.entitysList);
                Ioc.getIoc().getDb().update(DownloadThreadGroup.this.entity);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long getSize() {
            int size = this.entitysList.size();
            long j = 0;
            for (int i = 0; i < size; i++) {
                j += this.entitysList.get(i).getLoad();
            }
            return j;
        }

        public void setEntity(ThreadEntity threadEntity) {
            this.entitysList.add(threadEntity);
        }

        public void start() {
            if (this.entity.getNotfi() != null) {
                NotfiEntity notfi = this.entity.getNotfi();
                NotificationHelper notificationHelper = new NotificationHelper(Ioc.getIoc().getApplication(), notfi.getLayout_id(), notfi.getIcon_id(), notfi.getProgress_id(), notfi.getProgress_txt_id(), notfi.getClazz());
                notificationHelper.initNotif();
                this.entity.setHelper(notificationHelper);
            }
            new DownLoad().start();
        }
    }

    /* loaded from: classes.dex */
    private static class DownloadThread extends Thread {
        private long blockBegin;
        private long blockEnd;
        private RandomAccessFile destFile;
        private FileEntity entity;
        private long loading;
        private long offset;
        private ThreadEntity threadEntity;

        public DownloadThread(DownloadThreadGroup downloadThreadGroup, ThreadEntity threadEntity, FileEntity fileEntity, int i, long j, long j2) {
            super(downloadThreadGroup, "downloadThread-" + i);
            this.blockBegin = 0L;
            this.blockEnd = 0L;
            this.blockBegin = j;
            this.blockEnd = j2;
            this.entity = fileEntity;
            this.threadEntity = threadEntity;
            long load = threadEntity.getLoad();
            this.loading = load;
            this.offset = j + load;
            try {
                this.destFile = new RandomAccessFile(fileEntity.getPath(), "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            BufferedInputStream bufferedInputStream = null;
            try {
                try {
                    this.destFile.seek(this.offset * 1);
                    HttpURLConnection httpConnection = FileLoaderManager.getHttpConnection(this.offset, this.entity.getReal_url() != null ? this.entity.getReal_url() : this.entity.getUrl());
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(httpConnection.getInputStream());
                    try {
                        byte[] bArr = new byte[FileLoaderManager.blockSize];
                        while (this.offset < this.blockEnd && FileLoaderManager.loadingMap.containsKey(this.entity.getUrl()) && ((Boolean) FileLoaderManager.loadingMap.get(this.entity.getUrl())).booleanValue()) {
                            int read = bufferedInputStream2.read(bArr);
                            long j = this.loading + read;
                            this.loading = j;
                            long j2 = this.blockBegin + j;
                            this.offset = j2;
                            if (j2 > this.blockEnd) {
                                this.loading = this.blockEnd - this.blockBegin;
                            }
                            this.destFile.write(bArr, 0, read);
                            this.threadEntity.setLoad(this.loading);
                        }
                        Ioc.getIoc().getLogger().d("---------------------------------------------------------------------");
                        Ioc.getIoc().getLogger().d(getName() + "开始：" + this.blockBegin + "下载了：" + this.loading + "结束：" + this.blockEnd);
                        Ioc.getIoc().getLogger().d("---------------------------------------------------------------------");
                        httpConnection.disconnect();
                        try {
                            bufferedInputStream2.close();
                        } catch (Exception unused) {
                        }
                        try {
                            if (this.destFile != null) {
                                this.destFile.close();
                            }
                        } catch (Exception unused2) {
                        }
                    } catch (Exception e) {
                        e = e;
                        bufferedInputStream = bufferedInputStream2;
                        e.printStackTrace();
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Exception unused3) {
                            }
                        }
                        try {
                            if (this.destFile != null) {
                                this.destFile.close();
                            }
                        } catch (Exception unused4) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedInputStream = bufferedInputStream2;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Exception unused5) {
                            }
                        }
                        try {
                            if (this.destFile != null) {
                                this.destFile.close();
                            }
                        } catch (Exception unused6) {
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* loaded from: classes.dex */
    private static class DownloadSingleThread extends Thread {
        private RandomAccessFile destFile;
        private FileEntity entity;
        private long readCount;

        public DownloadSingleThread(FileEntity fileEntity) {
            this.entity = fileEntity;
            try {
                this.destFile = new RandomAccessFile(fileEntity.getPath(), "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                if (fileEntity.getNotfi() != null) {
                    NotificationHelper helper = fileEntity.getHelper();
                    helper.downShowNotification("下载失败");
                    fileEntity.setHelper(helper);
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            BufferedInputStream bufferedInputStream = null;
            try {
                try {
                    this.destFile.seek(0L);
                    HttpURLConnection httpConnection = FileLoaderManager.getHttpConnection(-1L, this.entity.getReal_url() != null ? this.entity.getReal_url() : this.entity.getUrl());
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(httpConnection.getInputStream());
                    try {
                        if (this.entity.getNotfi() != null && this.entity.getLength() == 0) {
                            NotfiEntity notfi = this.entity.getNotfi();
                            NotificationHelper notificationHelper = new NotificationHelper(Ioc.getIoc().getApplication(), notfi.getLayout_id(), notfi.getIcon_id(), notfi.getProgress_id(), notfi.getProgress_txt_id(), notfi.getClazz());
                            notificationHelper.downNotification("下载中...");
                            this.entity.setHelper(notificationHelper);
                        }
                        byte[] bArr = new byte[FileLoaderManager.blockSize];
                        int i = -1;
                        while (true) {
                            int read = bufferedInputStream2.read(bArr);
                            if (read <= 0) {
                                FileLoaderManager.sendMsg(this.entity, 3, 100);
                                if (this.entity.getNotfi() != null) {
                                    NotificationHelper helper = this.entity.getHelper();
                                    if (this.entity.isUpdate()) {
                                        helper.notifyUpdateFinish(new File(this.entity.getPath()));
                                    } else {
                                        helper.downShowNotification("下载成功");
                                        this.entity.setHelper(helper);
                                    }
                                }
                                FileLoaderManager.loadingMap.remove(this.entity.getUrl());
                                this.entity.setSucess(true);
                                Ioc.getIoc().getDb().saveOrUpdate(this.entity);
                                Ioc.getIoc().getLogger().d("---------------------------------------------------------------------");
                                Ioc.getIoc().getLogger().d("单线程下载:" + this.readCount);
                                Ioc.getIoc().getLogger().d("---------------------------------------------------------------------");
                                httpConnection.disconnect();
                                FileLoaderManager.loadingMap.remove(this.entity.getUrl());
                                try {
                                    bufferedInputStream2.close();
                                } catch (Exception unused) {
                                }
                                try {
                                    if (this.destFile != null) {
                                        this.destFile.close();
                                        return;
                                    }
                                    return;
                                } catch (Exception unused2) {
                                    return;
                                }
                            }
                            if (!((Boolean) FileLoaderManager.loadingMap.get(this.entity.getUrl())).booleanValue()) {
                                FileLoaderManager.sendMsg(this.entity, 2, 0);
                                if (this.entity.getNotfi() != null) {
                                    NotificationHelper helper2 = this.entity.getHelper();
                                    helper2.downShowNotification("下载失败");
                                    this.entity.setHelper(helper2);
                                }
                                this.entity.setSucess(false);
                                Ioc.getIoc().getDb().saveOrUpdate(this.entity);
                                FileLoaderManager.loadingMap.remove(this.entity.getUrl());
                                try {
                                    bufferedInputStream2.close();
                                } catch (Exception unused3) {
                                }
                                try {
                                    if (this.destFile != null) {
                                        this.destFile.close();
                                        return;
                                    }
                                    return;
                                } catch (Exception unused4) {
                                    return;
                                }
                            }
                            this.readCount += read;
                            this.destFile.write(bArr, 0, read);
                            this.entity.setLoadedLength(this.readCount);
                            int loadedLength = (int) ((this.entity.getLoadedLength() * 100) / this.entity.getLength());
                            if (i != loadedLength) {
                                FileLoaderManager.sendMsg(this.entity, 0, loadedLength);
                            }
                            i = loadedLength;
                        }
                    } catch (Exception unused5) {
                        bufferedInputStream = bufferedInputStream2;
                        FileLoaderManager.sendMsg(this.entity, 2, 100);
                        FileLoaderManager.loadingMap.remove(this.entity.getUrl());
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Exception unused6) {
                            }
                        }
                        try {
                            if (this.destFile != null) {
                                this.destFile.close();
                            }
                        } catch (Exception unused7) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedInputStream = bufferedInputStream2;
                        FileLoaderManager.loadingMap.remove(this.entity.getUrl());
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Exception unused8) {
                            }
                        }
                        try {
                            if (this.destFile != null) {
                                this.destFile.close();
                            }
                        } catch (Exception unused9) {
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception unused10) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendMsg(FileEntity fileEntity, int i, int i2) {
        FileResultEntity fileResultEntity = new FileResultEntity();
        fileResultEntity.setRange(fileEntity.isRange());
        fileResultEntity.setUrl(fileEntity.getUrl());
        fileResultEntity.setLoadedLength(fileEntity.getLoadedLength());
        fileResultEntity.setFile(new File(fileEntity.getPath()));
        fileResultEntity.setStatus(i);
        fileResultEntity.setLength(fileEntity.getLength());
        fileResultEntity.setProgress(i2);
        eventBus.post(fileResultEntity);
        if (i == 3 || i == 2) {
            fileEntities.remove(fileEntity.getUrl());
        }
        if (fileEntity.getNotfi() == null && fileEntity.isUpdate() && i == 3) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setDataAndType(Uri.fromFile(fileResultEntity.getFile()), "application/vnd.android.package-archive");
            Ioc.getIoc().getApplication().getApplicationContext().startActivity(intent);
        }
        if (fileEntity.getNotfi() != null) {
            NotificationHelper helper = fileEntity.getHelper();
            if (i == 0) {
                helper.refreshProgress(i2);
                return;
            }
            if (i == 2) {
                helper.downShowNotification("下载失败");
            } else {
                if (i != 3) {
                    return;
                }
                if (fileEntity.isUpdate()) {
                    helper.notifyUpdateFinish(new File(fileEntity.getPath()));
                } else {
                    helper.downShowNotification("下载成功");
                }
            }
        }
    }
}
