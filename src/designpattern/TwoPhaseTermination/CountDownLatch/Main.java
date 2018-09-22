package designpattern.TwoPhaseTermination.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main{
    private static final int THREAD_MAX = 10;
    private static final int THREAD_POOL_MAX = 3;

    public static void main(String[] args) {
        System.out.println("main begin.");
        ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_MAX);
        CountDownLatch latch = new CountDownLatch(THREAD_MAX);

        try
        {
            for (int i = 0; i < THREAD_MAX; i++) {
                service.execute(new MyTask(latch, i));
            }
            System.out.println("main wait.");
            // 阻塞主线程，直到countdown为0才会唤醒
            latch.await();
        }
        catch (InterruptedException e)
        {
            System.out.println("main got a InterruptedException");
        }
        finally {
            System.out.println("main end.");
            service.shutdown();
        }
    }
}
