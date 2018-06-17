package threads;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.ArrayList;
import java.util.List;

public class Threads {

    public static void main(String[] args) throws InterruptedException {

        String[] workersNames = {"Ania", "Jan"};

        List<Worker> workers = new ArrayList<>();

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(workersNames.length);

        for (String name : workersNames) {
            Worker worker = new Worker(name, startSignal, doneSignal);

            worker.setListener(new DefaultWorkerListeners());

            addDefaultTasks(worker);

            worker.start();

            startSignal.countDown();

            workers.add(worker);

        }

        addDefaultTasks(workers.get(0));

        setTimeout(workers.get(0)::stop, 12 * 1000);

        doneSignal.await();

    }

    private static void addDefaultTasks(Worker worker) {

        worker.enqueueTask("100%", new Task() {
            @Override
            public void run(int taskNumber) throws InterruptedException {
                AtomicBoolean working = new AtomicBoolean(true);
                
                setTimeout(new Runnable() {
                    @Override
                    public void run() {
                        working.set(false);
                    }
                }, 10000);
                
                Random rng = new Random();
                double store = 1;
                
                while (working.get()) {
                    
                    if (Thread.currentThread().interrupted())
                        throw new InterruptedException("InterruptedException " + currentThread().getName());
                    
                    double r = rng.nextFloat();
                    double v = Math.sin(Math.cos(Math.sin(Math.cos(r))));
                    store *= v;
                }
            }
        });


        worker.enqueueTask("0%", new Task() {
            @Override
            public void run(int taskNumber) throws InterruptedException {
                try {
                    sleep(10000);
                } catch (InterruptedException ex) {
                    throw ex;
                }
            }
        });


        worker.enqueueTask("yield", new Task() {
            @Override
            public void run(int taskNumber) throws InterruptedException {
                AtomicBoolean working = new AtomicBoolean(true);
                setTimeout(() -> working.set(false), 10000);
                
                while (working.get()) {
                    if (Thread.currentThread().interrupted())
                        throw new InterruptedException("InterruptedException " + currentThread().getName());
                    
                    Thread.yield();
                }
            }
        });


        worker.enqueueTask("empty", new Task() {
            @Override
            public void run(int taskNumber) throws InterruptedException {
                try {
                    sleep(10000);
                } catch (InterruptedException ex) {
                    throw new InterruptedException("InterruptedException " + currentThread().getName());
                }
            }
        });


        worker.enqueueTask("empty", new Task() {
            @Override
            public void run(int taskNumber) throws InterruptedException {
                try {
                    sleep(10000);
                } catch (InterruptedException ex) {
                    throw new InterruptedException("InterruptedException " + currentThread().getName());
                }
            }
        });
    }

    private static void setTimeout(Runnable runnable, int delay) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(delay);
                    runnable.run();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

    private static class DefaultWorkerListeners implements WorkerListener {

        @Override
        public void onWorkerStarted() {
            System.out.println("Method: onWorkerStarted");
        }

        @Override
        public void onWorkerStopped() {
            System.out.println("Method: onWorkerStopped");
        }

        @Override
        public void onTaskStarted(int taskNumber, String taskName) {
            System.out.println("Task #" + taskNumber + ": " + taskName + " is started");
        }

        @Override
        public void onTaskCompleted(int taskNumber, String taskName) {
            System.out.println("Task #" + taskNumber + ": " + taskName + " is finished");
        }
    }

}
