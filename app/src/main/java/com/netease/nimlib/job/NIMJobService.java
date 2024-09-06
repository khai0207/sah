package com.netease.nimlib.job;

import android.app.job.JobParameters;
import android.app.job.JobService;
import com.netease.nimlib.c;
import com.netease.nimlib.log.b;

/* loaded from: classes.dex */
public class NIMJobService extends JobService {
    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (c.H() || c.s() || c.G()) {
            return;
        }
        c.a("NIMJobService");
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        if (c.H() || c.s() || c.G()) {
            return false;
        }
        try {
            b.d("JobCore", "onStartJob " + jobParameters.getJobId());
        } catch (Throwable unused) {
        }
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        if (c.H() || c.s() || c.G()) {
            return false;
        }
        try {
            b.d("JobCore", "onStopJob " + jobParameters.getJobId());
        } catch (Throwable unused) {
        }
        return false;
    }
}
