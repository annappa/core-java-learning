package com.kscm.quartz.sched;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class OneTimeJobScheduler {
    public static void main(String[] args) throws Exception {
        // Create scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // Define the job
        JobDetail job = JobBuilder.newJob(OneTimeJob.class)
                .withIdentity("oneTimeJob", "group1")
                .build();

        // Trigger to run only once, 5 seconds from now
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("oneTimeTrigger", "group1")
                .startAt(new Date(System.currentTimeMillis() + 5000)) // delay 5 seconds
                .build();

        // Schedule the job
        scheduler.scheduleJob(job, trigger);

        // Start scheduler
        scheduler.start();

        // Optional: shutdown after job finishes
        Thread.sleep(7000); // wait enough time for the job to execute
        scheduler.shutdown();
    }
}
