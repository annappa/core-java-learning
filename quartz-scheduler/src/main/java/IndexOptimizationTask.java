import custom.SimpleAutomatedTask;

public class IndexOptimizationTask extends SimpleAutomatedTask {

    public IndexOptimizationTask() {
        super(IndexOptimizationTask.class.getSimpleName(), true);
    }

    @Override
    public void run() {
        System.out.println("Running index optimization task");
        System.out.println("Completed index optimization");
    }
}
