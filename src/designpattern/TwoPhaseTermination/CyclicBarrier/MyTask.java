package designpattern.TwoPhaseTermination.CyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MyTask implements Runnable {
    private static final int PHASE = 5;
    private final CyclicBarrier barrier;
    private final CountDownLatch latch;
    private final int count;
    private static final Random random = new Random(323222);

    public MyTask(CyclicBarrier barrier, CountDownLatch latch, int count) {
        this.barrier = barrier;
        this.latch = latch;
        this.count = count;
    }

    @Override
    public void run() {
        try
        {
            for (int i = 0; i < PHASE; i++) {
                doPhase(i);
                // 再次阻塞，等三个线程都执行一次之后，在主线程唤醒，执行一次Barrier Action
                barrier.await();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (BrokenBarrierException e)
        {
            e.printStackTrace();
        }
        finally {
            // 最后一个阶段PHASE执行后才会执行到这里，到时候3个线程一同连续执行此处3次，就可以唤醒主线程
            latch.countDown();
            System.out.println("MyTask run latch.getCount = " + latch.getCount());
        }
    }

    protected void doPhase(int phase)
    {
        String name = Thread.currentThread().getName();

        //System.out.println(name + ": MyTask begin.   count = " + count + ", phase = " + phase);
        try
        {
            Thread.sleep(random.nextInt(1000));
        }
        catch (InterruptedException e)
        {

        }
        finally {
            System.out.println(name + ": MyTask end.   count = " + count + ", phase = " + phase);
        }
    }
}
