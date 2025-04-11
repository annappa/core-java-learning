/*
import custom.AutomatedTask;
import custom.GuidGenerator;
import custom.JobListenerType;
import org.quartz.*;
import org.quartz.Scheduler;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
package org.joda.time;

*/
/**
 * Implementation of Scheduler that delegates to a Quartz {@link Scheduler}.
 *
 * @author annrames
 *//*

@SuppressWarnings({"PMD.ExcessivePublicCount", "checkstyle:CyclomaticComplexity"})
public class QuartzScheduler implements custom.Scheduler {

    private static final int MAXIMUM_JOB_NAME_LENGTH = 256;

    */
/**
     * A map of Scheduler and the set of jobs that are currently running
     *//*

    private static final Map<Scheduler, Set<String>> JOB_QUEUE = new HashMap<Scheduler, Set<String>>();
    private static final String SCHEDULER_EXCEPTION_MESSAGE = "Problem scheduling AutomatedTask";

    private final Scheduler scheduler;

    public QuartzScheduler() {
        SchedulerFactory schedFact = new StdSchedulerFactory();
        try {
            scheduler = schedFact.getScheduler();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    private QuartzSchedulerState state = new NotYetStartedState();

    public Scheduler getQuartzScheduler() {
        return scheduler;
    }


    public synchronized boolean isStarted() {
        return getState().isStarted();
    }

    public synchronized void start() {
        //LOG.info(format("Start scheduler %s", name));
        getState().start();
    }

    */
/**
     * Schedules a task for immediate execution by Quartz scheduler
     * @param automatedTask the task to be executed immediately in one-shot
     *//*

    @Override
    public void schedule(AutomatedTask automatedTask, JobListenerType jobListenerType) {
        try {
            JobDetail jobDetail = getJobDetail(automatedTask, false);
            scheduler.addJob(jobDetail, true, true);
            scheduler.getListenerManager().addJobListenerMatcher(jobListenerType.name(), KeyMatcher.keyEquals(jobDetail.getKey()));
            scheduler.triggerJob(jobDetail.getKey());
        } catch (SchedulerException e) {
            throw new custom.SchedulerException("Problem configuring Quartz Scheduler", e);
        }
    }

    //@ManagedAttribute(description = "Is the scheduler paused")
    public synchronized boolean isPaused() {
        return getState().isPaused();
    }

    //@ManagedOperation(description = "Pause the scheduler")
    public synchronized void pause() {
        //LOG.info(format("Pause scheduler %s", name));
        getState().pause();
    }

    //@ManagedAttribute(description = "Is the scheduler shutdown")
    public synchronized boolean isShutdown() {
        return getState().isShutdown();
    }

    public synchronized void shutdown() {
        getState().shutdown();
    }

    */
/**
     * Schedules a task for immediate execution by Quartz scheduler
     *
     * @param automatedTask the task to be executed immediately in one-shot
     *//*

    public void schedule(AutomatedTask automatedTask) {
        try {
            JobDetail jobDetail = getJobDetail(automatedTask, false);
            // Job needs to be stored before triggering
            scheduler.addJob(jobDetail, true, true);
            scheduler.triggerJob(jobDetail.getKey());
        } catch (Exception e) {
            throw new custom.SchedulerException("Problem configuring Quartz Scheduler", e);
        }
    }

   */
/* @Override
    public void scheduleOnceOff(AutomatedTask automatedTask, Date runAt, JobListenerType jobListenerType) {
        try {
            Trigger trigger = getOneOffTrigger(runAt);
            JobDetail jobDetail = getJobDetail(automatedTask, false);
            scheduler.getListenerManager().addJobListenerMatcher(jobListenerType.name(), KeyMatcher.keyEquals(jobDetail.getKey()));
            scheduleJobDetail(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new custom.SchedulerException("Problem configuring Quartz Scheduler", e);
        }
    }*//*


    */
/*public void scheduleOnceOff(AutomatedTask automatedTask, Date runAt) {
        Trigger trigger = getOneOffTrigger(runAt);
        scheduleJobDetail(getJobDetail(automatedTask, false), trigger);
    }*//*


    */
/*public void scheduleOnceOff(AutomatedTask automatedTask, int hour, int minute) {
        DateTime now = new DateTime();
        MutableDateTime runAt = new MutableDateTime();
        runAt.setTime(hour, minute, 0, 0);
        if (now.isAfter(runAt)) {
            runAt.addDays(1);
        }

        scheduleOnceOff(automatedTask, runAt.toDate());
    }*//*


    */
/*public void scheduleOnceOff(AutomatedTask automatedTask, Date runAt, boolean requestsRecovery, int priority) {
        Trigger trigger = getOneOffTrigger(runAt, priority);
        scheduleJobDetail(getJobDetail(automatedTask, requestsRecovery), trigger);
    }*//*


    */
/*private JobDetail getJobDetail(final AutomatedTask automatedTask, boolean requestsRecovery) {
        return createTaskExecutingJobDetail(automatedTask, false, requestsRecovery);
    }*//*


    protected SimpleTrigger getOneOffTrigger(Date runAt, int triggerPriority) {
        return newTrigger()
                .withIdentity("One Off [" + runAt.toString() + "] " + GuidGenerator.getGuid())
                .startAt(runAt)
                .withSchedule(simpleSchedule().withMisfireHandlingInstructionIgnoreMisfires())
                .withPriority(triggerPriority)
                .build();
    }

    protected SimpleTrigger getOneOffTrigger(Date runAt) {
        return getOneOffTrigger(runAt, Trigger.DEFAULT_PRIORITY);
    }

    protected void scheduleJobDetail(JobDetail jobDetail, Trigger trigger) {
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new custom.SchedulerException(SCHEDULER_EXCEPTION_MESSAGE, e);
        }
    }

    protected JobDetail findJobDetail(String taskName) {
        try {
            return scheduler.getJobDetail(JobKey.jobKey(taskName));
        } catch (SchedulerException e) {
            throw new custom.SchedulerException(
                    "There was a problem locating the JobDetail for task [" + taskName + "].", e);
        }
    }

    */
/*protected JobDetail createTaskExecutingJobDetail(AutomatedTask automatedTask, boolean concurrent, boolean requestsRecovery) {
        if (automatedTask.getName().length() > MAXIMUM_JOB_NAME_LENGTH) {
            throw new IllegalArgumentException("AutomatedTask name [" + automatedTask.getName()
                    + "] is too long; must be less than " + MAXIMUM_JOB_NAME_LENGTH + " chars.");
        }
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(TaskExecutingJob.TASK_KEY, automatedTask);
        // Default job builder behavior: requestRecovery set to false, durability set to false
        // The notion (property) of “volatility” of jobs and triggers has been eliminated in quartz 2
        return newJob(!concurrent ? TaskExecutingJob.class : StatefulTaskExecutingJob.class)
                .withIdentity(automatedTask.getName())
                .usingJobData(jobDataMap)
                .requestRecovery(requestsRecovery)
                .build();
    }*//*


    protected QuartzSchedulerState getState() {
        return state;
    }

    protected void setState(QuartzSchedulerState state) {
        this.state = state;
    }

    protected void transitionToStartedState() {
        try {
            scheduler.start();
            setState(new StartedState());
        } catch (SchedulerException e) {
            throw new custom.SchedulerException(
                    "There was a problem starting the scheduler", e);
        }
    }

    protected void transitionToPausedState() {
        try {
            scheduler.standby();
            setState(new PausedState());
        } catch (SchedulerException e) {
            throw new custom.SchedulerException(
                    "There was a problem pausing the scheduler", e);
        }
    }

    protected void transitionToShutdownState() {
        try {
            scheduler.shutdown(true);
            setState(new ShutdownState());
        } catch (SchedulerException e) {
            //LOG.warn("Problem shutting down scheduler", e);
        }
    }

    protected interface QuartzSchedulerState {
        boolean isStarted();

        boolean isPaused();

        boolean isShutdown();

        void start();

        void pause();

        void shutdown();
    }

    private static class ShutdownState implements QuartzSchedulerState {

        public boolean isStarted() {
            return false;
        }

        public boolean isPaused() {
            return false;
        }

        public boolean isShutdown() {
            return true;
        }

        public void start() {
            throw new IllegalStateException(
                    "Scheduler can not be restarted once it has been shutdown.");
        }

        public void pause() {
            throw new IllegalStateException("Scheduler can not be paused as it has been shutdown.");
        }

        public void shutdown() {
            throw new IllegalStateException("Scheduler is already shutdown.");
        }
    }

    protected class NotYetStartedState implements QuartzSchedulerState {

        public boolean isStarted() {
            return false;
        }

        public boolean isPaused() {
            return false;
        }

        public boolean isShutdown() {
            return false;
        }

        public void start() {
            transitionToStartedState();
        }

        public void pause() {
            throw new IllegalStateException("Scheduler must be started before it can be paused.");
        }

        public void shutdown() {
            transitionToShutdownState();
        }
    }

    protected class StartedState implements QuartzSchedulerState {

        public boolean isStarted() {
            return true;
        }

        public boolean isPaused() {
            return false;
        }

        public boolean isShutdown() {
            return false;
        }

        public void start() {
            throw new IllegalStateException("Scheduler is already started.");
        }

        public void pause() {
            transitionToPausedState();
        }

        public void shutdown() {
            transitionToShutdownState();
        }

    }

    protected class PausedState implements QuartzSchedulerState {

        public boolean isStarted() {
            return true;
        }

        public boolean isPaused() {
            return true;
        }

        public boolean isShutdown() {
            return false;
        }

        public void pause() {
            throw new IllegalStateException("Scheduler is already paused.");
        }

        public void start() {
            transitionToStartedState();
        }

        public void shutdown() {
            transitionToShutdownState();
        }

    }

}*/
