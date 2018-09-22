package designpattern.TwoPhaseTermination.CyclicBarrier;

import java.util.concurrent.*;

public class Main {
    private static final int THREAD_MAX = 3;

    public static void main(String[] args) {
        System.out.println("main begin.");
        ExecutorService service = Executors.newFixedThreadPool(THREAD_MAX);
        Runnable barrierAction = new Runnable() {
            @Override
            public void run() {
                // 每个阶段跑完之后执行一次此处
                System.out.println("Barrier Action!");
            }
        };

        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_MAX, barrierAction);
        CountDownLatch latch = new CountDownLatch(THREAD_MAX);

        try
        {
            for (int i = 0; i < THREAD_MAX; i++) {
                service.execute(new MyTask(cyclicBarrier, latch, i));
            }

            System.out.println("main await");
            latch.await();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally {
            System.out.println("main end.");
        }
    }
    /**
     *
     * main begin.
     main await
     pool-1-thread-1: MyTask end.   count = 0, phase = 0
     pool-1-thread-2: MyTask end.   count = 1, phase = 0
     pool-1-thread-3: MyTask end.   count = 2, phase = 0
     Barrier Action!
     pool-1-thread-1: MyTask end.   count = 0, phase = 1
     pool-1-thread-3: MyTask end.   count = 2, phase = 1
     pool-1-thread-2: MyTask end.   count = 1, phase = 1
     Barrier Action!
     pool-1-thread-3: MyTask end.   count = 2, phase = 2
     pool-1-thread-1: MyTask end.   count = 0, phase = 2
     pool-1-thread-2: MyTask end.   count = 1, phase = 2
     Barrier Action!
     pool-1-thread-3: MyTask end.   count = 2, phase = 3
     pool-1-thread-1: MyTask end.   count = 0, phase = 3
     pool-1-thread-2: MyTask end.   count = 1, phase = 3
     Barrier Action!
     pool-1-thread-2: MyTask end.   count = 1, phase = 4
     pool-1-thread-1: MyTask end.   count = 0, phase = 4
     pool-1-thread-3: MyTask end.   count = 2, phase = 4
     Barrier Action!
     MyTask run latch.getCount = 2
     MyTask run latch.getCount = 1
     main end.
     MyTask run latch.getCount = 0
     */
}
