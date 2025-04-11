package custom;
/**
 * Convenient abstract base class for implementations of {@link AutomatedTask}. Provides
 * implementation for getName methods simple way to create a tasks with a unique name.
 *
 * @author ohutchison
 */
public abstract class SimpleAutomatedTask implements AutomatedTask {
    // serialVersionUID chosen to match that in the 8.9 release (which had no ID) to freeze version
    // and prevent deserialization problems
    private static final long serialVersionUID = 8955777363206166909L;

    private final String name;

    /**
     * Creates a new SimpleAutomatedTask.
     *
     * @param name the name for this task
     * @param makeNameUnique if the name should be appended with a GUID to make it unique.
     */
    protected SimpleAutomatedTask(String name, boolean makeNameUnique) {
        this.name = makeNameUnique ? name + ' ' + GuidGenerator.getGuid() : name;
    }

    public String getName() {
        return name;
    }
}
