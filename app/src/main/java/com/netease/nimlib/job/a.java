package com.netease.nimlib.job;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import com.netease.nimlib.c;
import com.netease.nimlib.o.n;
import java.util.Iterator;
import java.util.List;

/* compiled from: JobCore.java */
/* loaded from: classes.dex */
public class a {
    private b a;

    /* compiled from: JobCore.java */
    /* renamed from: com.netease.nimlib.job.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0037a {
        static final a a = new a();
    }

    private a() {
        this.a = new b();
    }

    public static a a() {
        return C0037a.a;
    }

    public void a(Context context) {
        if (c.H() || c.s() || c.G() || Build.VERSION.SDK_INT < 21) {
            return;
        }
        try {
            if (this.a.a(context)) {
                c(context);
                com.netease.nimlib.log.b.d("JobCore", "job core startup success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            com.netease.nimlib.log.b.f("JobCore", "job core startup failed, e=" + e.getMessage());
        }
    }

    public void b(Context context) {
        if (c.H() || c.s() || c.G() || Build.VERSION.SDK_INT < 21) {
            return;
        }
        com.netease.nimlib.log.b.d("JobCore", "job core shutdown");
        try {
            e(context);
        } catch (Exception e) {
            e.printStackTrace();
            com.netease.nimlib.log.b.f("JobCore", "job core shutdown failed, e=" + e.getMessage());
        }
    }

    private void c(Context context) throws Exception {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler == null) {
            return;
        }
        List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
        if (!allPendingJobs.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("pending job ids=[");
            Iterator<JobInfo> it = allPendingJobs.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getId());
                sb.append(";");
            }
            sb.append("]");
            com.netease.nimlib.log.b.c("JobCore", sb.toString());
        }
        for (JobInfo jobInfo : d(context)) {
            int schedule = jobScheduler.schedule(jobInfo);
            if (schedule > 0) {
                com.netease.nimlib.log.b.d("JobCore", "schedule job success, job id=" + jobInfo.getId());
            } else {
                com.netease.nimlib.log.b.f("JobCore", "schedule job failed, job id=" + jobInfo.getId() + ", error code=" + schedule);
            }
        }
    }

    private JobInfo[] d(Context context) {
        ComponentName componentName = new ComponentName(context, (Class<?>) NIMJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, componentName);
        builder.setPeriodic(180000L);
        JobInfo.Builder builder2 = new JobInfo.Builder(1, componentName);
        builder2.setRequiredNetworkType(1);
        builder2.setPeriodic(300000L);
        JobInfo.Builder builder3 = new JobInfo.Builder(2, componentName);
        builder3.setRequiresCharging(true);
        builder3.setOverrideDeadline(1200000L);
        return new JobInfo[]{builder.build(), builder2.build(), builder3.build()};
    }

    private void e(Context context) {
        JobScheduler jobScheduler;
        if (this.a.a(context) && (jobScheduler = (JobScheduler) context.getSystemService("jobscheduler")) != null) {
            jobScheduler.cancel(0);
            jobScheduler.cancel(1);
            jobScheduler.cancel(2);
            com.netease.nimlib.log.b.d("JobCore", "cancel all scheduled jobs");
        }
    }

    /* compiled from: JobCore.java */
    /* loaded from: classes.dex */
    private static class b {
        private boolean a;
        private boolean b;

        private b() {
            this.a = false;
            this.b = false;
        }

        public boolean a(Context context) {
            if (this.a) {
                return this.b;
            }
            boolean a = n.a(context, NIMJobService.class);
            this.b = a;
            this.a = true;
            return a;
        }
    }
}
