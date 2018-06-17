package threads;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class Worker
        implements Runnable {

    private String workerName;
    private boolean working;
    private Map<Integer, Task> tasks;
    private Map<Integer, String> tasksName;
    private int tasksCounter;
    private Thread thread;
    private WorkerListener workerListener;
    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;

    public Worker(String workerName, CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.workerName = workerName;
        this.working = false;
        this.tasksCounter = 0;
        this.tasks = new LinkedHashMap<>();
        this.tasksName = new HashMap<>();

        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public synchronized void enqueueTask(String taskName, Task task) {
        int id = ++tasksCounter;
        this.tasks.put(id, task);
        this.tasksName.put(id, taskName);
    }

    public boolean isStarted() {
        return this.working;
    }

    public boolean isWorking() {
        return this.isStarted() && !tasks.isEmpty();
    }
    
    public void setListener(WorkerListener workerListener) {
        this.workerListener = workerListener;
    }

    public void start() {
        if (this.isStarted()) {
            return;
        }
        this.working = true;

        thread = new Thread(this, "Worker" + this.workerName + "Thread");
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Worker " + this.workerName + " Thread");

        try {
            startSignal.await();
        } catch (InterruptedException e) {
        }

        if (this.workerListener != null) {
            this.workerListener.onWorkerStarted();
        }

        while (this.isWorking()) {
            this.dequeueTask();
        }

        if (this.workerListener != null) {
            this.workerListener.onWorkerStopped();
        }

        doneSignal.countDown();
    }

    public void stop() {
        if (!this.isStarted()) {
            return;
        }

        this.working = false;
        thread.interrupt();
    }

    private synchronized void dequeueTask() {
        if (this.tasks.isEmpty()) {
            return;
        }

        Map.Entry<Integer, Task> taskEntry = this.tasks.entrySet().iterator().next();

        int taskID = taskEntry.getKey();
        String taskName = tasksName.get(taskID);

        this.tasks.remove(taskID);
        this.tasksName.remove(taskID);

        try {

            workerListener.onTaskStarted(taskID, taskName);
            taskEntry.getValue().run(taskID);
            workerListener.onTaskCompleted(taskID, taskName);
        } catch (InterruptedException e) {
            //System.out.println("interrupt! \n" + e.getMessage());
        }
    }

}
