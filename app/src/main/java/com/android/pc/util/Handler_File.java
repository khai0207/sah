package com.android.pc.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.android.pc.ioc.app.Ioc;
import com.android.pc.ioc.internet.FastHttp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class Handler_File {
    public static final String FILE_EXTENSION_SEPARATOR = ".";

    public static StringBuilder readFile(String str) {
        File file = new File(str);
        StringBuilder sb = new StringBuilder("");
        BufferedReader bufferedReader = null;
        if (!file.isFile()) {
            return null;
        }
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            if (!sb.toString().equals("")) {
                                sb.append(FastHttp.LINEND);
                            }
                            sb.append(readLine);
                        } else {
                            bufferedReader2.close();
                            try {
                                bufferedReader2.close();
                                return sb;
                            } catch (IOException e) {
                                throw new RuntimeException("IOException occurred. ", e);
                            }
                        }
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader = bufferedReader2;
                        throw new RuntimeException("IOException occurred. ", e);
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                throw new RuntimeException("IOException occurred. ", e3);
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e = e4;
        }
    }

    public static boolean writeFile(String str, String str2, boolean z) {
        FileWriter fileWriter = null;
        try {
            try {
                FileWriter fileWriter2 = new FileWriter(str, z);
                try {
                    fileWriter2.write(str2);
                    fileWriter2.close();
                    try {
                        fileWriter2.close();
                        return true;
                    } catch (IOException e) {
                        throw new RuntimeException("IOException occurred. ", e);
                    }
                } catch (IOException e2) {
                    e = e2;
                    throw new RuntimeException("IOException occurred. ", e);
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (IOException e3) {
                            throw new RuntimeException("IOException occurred. ", e3);
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean writeFile(String str, InputStream inputStream) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read != -1) {
                            fileOutputStream2.write(bArr, 0, read);
                        } else {
                            fileOutputStream2.flush();
                            try {
                                fileOutputStream2.close();
                                inputStream.close();
                                return true;
                            } catch (IOException e) {
                                throw new RuntimeException("IOException occurred. ", e);
                            }
                        }
                    }
                } catch (FileNotFoundException e2) {
                    e = e2;
                    throw new RuntimeException("FileNotFoundException occurred. ", e);
                } catch (IOException e3) {
                    e = e3;
                    throw new RuntimeException("IOException occurred. ", e);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            inputStream.close();
                        } catch (IOException e4) {
                            throw new RuntimeException("IOException occurred. ", e4);
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
            } catch (IOException e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static List<String> readFileToList(String str) {
        File file = new File(str);
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = null;
        try {
            if (!file.isFile()) {
                return null;
            }
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            arrayList.add(readLine);
                        } else {
                            bufferedReader2.close();
                            try {
                                bufferedReader2.close();
                                return arrayList;
                            } catch (IOException e) {
                                throw new RuntimeException("IOException occurred. ", e);
                            }
                        }
                    } catch (IOException e2) {
                        e = e2;
                        throw new RuntimeException("IOException occurred. ", e);
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                throw new RuntimeException("IOException occurred. ", e3);
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getFileNameWithoutExtension(String str) {
        if (Handler_String.isEmpty(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(".");
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf2 == -1) {
            return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
        }
        if (lastIndexOf == -1) {
            return str.substring(lastIndexOf2 + 1);
        }
        return lastIndexOf2 < lastIndexOf ? str.substring(lastIndexOf2 + 1, lastIndexOf) : str.substring(lastIndexOf2 + 1);
    }

    public static String getFileName(String str) {
        int lastIndexOf;
        return (Handler_String.isEmpty(str) || (lastIndexOf = str.lastIndexOf(File.separator)) == -1) ? str : str.substring(lastIndexOf + 1);
    }

    public static String getFolderName(String str) {
        if (Handler_String.isEmpty(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf == -1 ? "" : str.substring(0, lastIndexOf);
    }

    public static String getFileExtension(String str) {
        if (Handler_String.isBlank(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(".");
        return (lastIndexOf != -1 && str.lastIndexOf(File.separator) < lastIndexOf) ? str.substring(lastIndexOf + 1) : "";
    }

    public static boolean makeDirs(String str) {
        String folderName = getFolderName(str);
        if (Handler_String.isEmpty(folderName)) {
            return false;
        }
        File file = new File(folderName);
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        return file.mkdirs();
    }

    public static boolean makeFolders(String str) {
        return makeDirs(str);
    }

    public static boolean isFileExist(String str) {
        if (Handler_String.isBlank(str)) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.isFile();
    }

    public static boolean isFolderExist(String str) {
        if (Handler_String.isBlank(str)) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.isDirectory();
    }

    public static boolean deleteFile(String str) {
        if (Handler_String.isBlank(str)) {
            return true;
        }
        File file = new File(str);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                file2.delete();
            } else if (file2.isDirectory()) {
                deleteFile(file2.getAbsolutePath());
            }
        }
        return file.delete();
    }

    public static long getFileSize(String str) {
        if (Handler_String.isBlank(str)) {
            return -1L;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            return file.length();
        }
        return -1L;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x005a A[Catch: IOException -> 0x005d, TRY_LEAVE, TryCatch #7 {IOException -> 0x005d, blocks: (B:39:0x0055, B:33:0x005a), top: B:38:0x0055 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void copyFile(java.io.File r4, java.io.File r5) {
        /*
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3d
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3d
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3d
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3d
            r5 = 5120(0x1400, float:7.175E-42)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
        L19:
            int r0 = r1.read(r5)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r3 = -1
            if (r0 == r3) goto L25
            r3 = 0
            r2.write(r5, r3, r0)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            goto L19
        L25:
            r2.flush()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r4.delete()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r4.deleteOnExit()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r1.close()     // Catch: java.io.IOException -> L51
        L31:
            r2.close()     // Catch: java.io.IOException -> L51
            goto L51
        L35:
            r4 = move-exception
            goto L3b
        L37:
            r4 = move-exception
            goto L3f
        L39:
            r4 = move-exception
            r2 = r0
        L3b:
            r0 = r1
            goto L53
        L3d:
            r4 = move-exception
            r2 = r0
        L3f:
            r0 = r1
            goto L46
        L41:
            r4 = move-exception
            r2 = r0
            goto L53
        L44:
            r4 = move-exception
            r2 = r0
        L46:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L52
            if (r0 == 0) goto L4e
            r0.close()     // Catch: java.io.IOException -> L51
        L4e:
            if (r2 == 0) goto L51
            goto L31
        L51:
            return
        L52:
            r4 = move-exception
        L53:
            if (r0 == 0) goto L58
            r0.close()     // Catch: java.io.IOException -> L5d
        L58:
            if (r2 == 0) goto L5d
            r2.close()     // Catch: java.io.IOException -> L5d
        L5d:
            goto L5f
        L5e:
            throw r4
        L5f:
            goto L5e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.pc.util.Handler_File.copyFile(java.io.File, java.io.File):void");
    }

    public static File getExternalCacheDir(Context context, String str) {
        File file = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File file2 = new File(new File(new File(file, context.getPackageName()), "cache"), str);
        if (!file2.exists()) {
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException e) {
                Log.w("创建目录", "Can't create \".nomedia\" file in application external cache directory", e);
            }
            if (!file2.mkdirs()) {
                Log.w("创建目录", "Unable to create external cache directory");
                return null;
            }
        }
        return file2;
    }

    public static void write(File file, String str) {
        BufferedWriter bufferedWriter = null;
        try {
            try {
                try {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file), 1024);
                    try {
                        bufferedWriter2.write(str);
                        bufferedWriter2.flush();
                        bufferedWriter2.close();
                    } catch (IOException e) {
                        e = e;
                        bufferedWriter = bufferedWriter2;
                        Ioc.getIoc().getLogger().d("write " + file.getAbsolutePath() + " data failed!");
                        e.printStackTrace();
                        if (bufferedWriter != null) {
                            bufferedWriter.flush();
                            bufferedWriter.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.flush();
                                bufferedWriter.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x004b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getAsString(java.io.File r4) {
        /*
            boolean r0 = r4.exists()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            java.lang.String r4 = ""
        L14:
            java.lang.String r2 = r0.readLine()     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L47
            if (r2 == 0) goto L2a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L47
            r3.<init>()     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L47
            r3.append(r4)     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L47
            r3.append(r2)     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L47
            java.lang.String r4 = r3.toString()     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L47
            goto L14
        L2a:
            r0.close()     // Catch: java.io.IOException -> L2e
            goto L32
        L2e:
            r0 = move-exception
            r0.printStackTrace()
        L32:
            return r4
        L33:
            r4 = move-exception
            goto L39
        L35:
            r4 = move-exception
            goto L49
        L37:
            r4 = move-exception
            r0 = r1
        L39:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L47
            if (r0 == 0) goto L46
            r0.close()     // Catch: java.io.IOException -> L42
            goto L46
        L42:
            r4 = move-exception
            r4.printStackTrace()
        L46:
            return r1
        L47:
            r4 = move-exception
            r1 = r0
        L49:
            if (r1 == 0) goto L53
            r1.close()     // Catch: java.io.IOException -> L4f
            goto L53
        L4f:
            r0 = move-exception
            r0.printStackTrace()
        L53:
            goto L55
        L54:
            throw r4
        L55:
            goto L54
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.pc.util.Handler_File.getAsString(java.io.File):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0044 A[Catch: IOException -> 0x0047, TRY_LEAVE, TryCatch #0 {IOException -> 0x0047, blocks: (B:35:0x003f, B:29:0x0044), top: B:34:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x003f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void saveObject(java.lang.String r3, java.io.Serializable r4) {
        /*
            r0 = 0
            com.android.pc.ioc.app.Ioc r1 = com.android.pc.ioc.app.Ioc.getIoc()     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e
            android.app.Application r1 = r1.getApplication()     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e
            r2 = 0
            java.io.FileOutputStream r3 = r1.openFileOutput(r3, r2)     // Catch: java.lang.Throwable -> L2b java.lang.Exception -> L2e
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L27
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L27
            r1.writeObject(r4)     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
            if (r3 == 0) goto L1b
            r3.close()     // Catch: java.io.IOException -> L3b
        L1b:
            r1.close()     // Catch: java.io.IOException -> L3b
            goto L3b
        L1f:
            r4 = move-exception
            goto L25
        L21:
            r4 = move-exception
            goto L29
        L23:
            r4 = move-exception
            r1 = r0
        L25:
            r0 = r3
            goto L3d
        L27:
            r4 = move-exception
            r1 = r0
        L29:
            r0 = r3
            goto L30
        L2b:
            r4 = move-exception
            r1 = r0
            goto L3d
        L2e:
            r4 = move-exception
            r1 = r0
        L30:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L3c
            if (r0 == 0) goto L38
            r0.close()     // Catch: java.io.IOException -> L3b
        L38:
            if (r1 == 0) goto L3b
            goto L1b
        L3b:
            return
        L3c:
            r4 = move-exception
        L3d:
            if (r0 == 0) goto L42
            r0.close()     // Catch: java.io.IOException -> L47
        L42:
            if (r1 == 0) goto L47
            r1.close()     // Catch: java.io.IOException -> L47
        L47:
            goto L49
        L48:
            throw r4
        L49:
            goto L48
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.pc.util.Handler_File.saveObject(java.lang.String, java.io.Serializable):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0047 A[Catch: IOException -> 0x004a, TRY_LEAVE, TryCatch #0 {IOException -> 0x004a, blocks: (B:36:0x0042, B:31:0x0047), top: B:35:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> T getObject(java.lang.String r4) {
        /*
            r0 = 0
            com.android.pc.ioc.app.Ioc r1 = com.android.pc.ioc.app.Ioc.getIoc()     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2e
            android.app.Application r1 = r1.getApplication()     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2e
            java.io.FileInputStream r4 = r1.openFileInput(r4)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2e
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L26
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L26
            java.lang.Object r0 = r1.readObject()     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L3f
            if (r4 == 0) goto L1b
            r4.close()     // Catch: java.io.IOException -> L1e
        L1b:
            r1.close()     // Catch: java.io.IOException -> L1e
        L1e:
            return r0
        L1f:
            r2 = move-exception
            goto L31
        L21:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L40
        L26:
            r2 = move-exception
            r1 = r0
            goto L31
        L29:
            r4 = move-exception
            r1 = r0
            r0 = r4
            r4 = r1
            goto L40
        L2e:
            r2 = move-exception
            r4 = r0
            r1 = r4
        L31:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L3f
            if (r4 == 0) goto L39
            r4.close()     // Catch: java.io.IOException -> L3e
        L39:
            if (r1 == 0) goto L3e
            r1.close()     // Catch: java.io.IOException -> L3e
        L3e:
            return r0
        L3f:
            r0 = move-exception
        L40:
            if (r4 == 0) goto L45
            r4.close()     // Catch: java.io.IOException -> L4a
        L45:
            if (r1 == 0) goto L4a
            r1.close()     // Catch: java.io.IOException -> L4a
        L4a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.pc.util.Handler_File.getObject(java.lang.String):java.lang.Object");
    }
}
