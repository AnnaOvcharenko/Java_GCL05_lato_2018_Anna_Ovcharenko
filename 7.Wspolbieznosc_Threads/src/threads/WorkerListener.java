
package threads;

public interface WorkerListener {
    
    
    void onWorkerStarted();

    void onWorkerStopped();

    void onTaskStarted(int taskNumber, String taskName);

    void onTaskCompleted(int taskNumber, String taskName); 
    
}
