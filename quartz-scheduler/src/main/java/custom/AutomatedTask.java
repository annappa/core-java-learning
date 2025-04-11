package custom;

import java.io.Serializable;

/**
 * Basic interface to be implemented by tasks that can be scheduled for
 * execution in a {@link Scheduler}.
 * 
 * @author ohutchison
 */
public interface AutomatedTask extends Runnable, Serializable {

    /**
     * The descriptive name of this Task. 
     * 
     * @return the name of the task. Name must be unique across a single {@link Scheduler}.
     */
    String getName();
}
