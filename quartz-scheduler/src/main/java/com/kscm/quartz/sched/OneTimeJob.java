package com.kscm.quartz.sched;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class OneTimeJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Running one-time task: " + System.currentTimeMillis());
    }
}
