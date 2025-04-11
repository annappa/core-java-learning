package custom;

import java.util.Date;
import java.util.List;

/**
 * A Scheduler provides the ability to schedule {@link AutomatedTask}s for execution.
 * AutomatedTasks may be scheduled to run immediately, at a specific time or recurring according to
 * a cron expression.
 * <p>
 * <b>NOTE:</b> Execution of tasks by a Scheduler will always happen in a separate thread from the
 * one in which they were scheduled and may happen in a separate JVM if the Scheduler is clustered,
 * so please make sure that all AutomatedTasks are coded in a manner that is robust with respect to
 * these possibilities.
 * 
 * @author ohutchison
 */
public interface Scheduler {

    /**
     * Indicated if the Scheduler is started. If a Scheduler is not started it will not execute
     * AutomatedTasks.
     */
    boolean isStarted();

    /**
     * Causes the Scheduler to start executing tasks.
     */
    void start();

    /**
     * Indicated if the Scheduler is paused. A paused Scheduler will not execute AutomatedTasks but
     * it will allow them to be scheduled for execution.
     */
    boolean isPaused();

    /**
     * Causes the Scheduler to pause executing tasks, this method may only be called on a started
     * Scheduler.
     * <p>
     * To restart a paused Scheduler simply call {@link #start()}.
     */
    void pause();

    /**
     * Indicated if the Scheduler is shutdown. A shutdown Scheduler will not execute AutomatedTasks
     * and will not allow for them to be scheduled for execution.
     */
    boolean isShutdown();

    /**
     * Causes a Scheduler to shutdown, any resources allocated by the Scheduler will also be freed.
     */
    void shutdown();

    /**
     * Schedules an AutomatedTask for immediate execution.
     */
    void schedule(AutomatedTask automatedTask);

    void schedule(AutomatedTask automatedTask, JobListenerType type);

    /**
     * Schedules an AutomatedTask for execution at the provided time.
     */
    void scheduleOnceOff(AutomatedTask automatedTask, Date runAt);

    void scheduleOnceOff(AutomatedTask automatedTask, Date date, JobListenerType type);

    /**
     * Schedules an AutomatedTask for execution at the provided time (today if the time has not
     * passed yet, otherwise tomorrow).
     *
     * @param hour
     *            the hour the task is to be run at (in 24-hour format)
     * @param minute
     *            the minute the task is to be run at.
     */
    void scheduleOnceOff(AutomatedTask automatedTask, int hour, int minute);

    /**
     * Schedules an AutomatedTask for recurring execution according to the provided cron expression.
     * <p>
     * See <a href="http://quartz.sourceforge.net/javadoc/org/quartz/CronTrigger.html">CronTrigger</a>
     * for a description of the format of cron expressions.
     */
    void scheduleRecurring(AutomatedTask automatedTask, String cronExpression);

    /**
     * Returns true is there is a task with the given name scheduled for execution in this
     * Scheduler.
     */
    boolean hasTask(String taskName);

    /**
     * Delete's the task with the given name from this Scheduler.
     */
    void deleteTask(String taskName);

    /**
     * Returns the list of all the tasks currently scheduled for execution in this Scheduler.
     */
    List<AutomatedTask> getAllTasks();

    /**
     * Returns the list of all the tasks currently executing in this Scheduler.
     */
    List<AutomatedTask> getExecutingTasks();
}