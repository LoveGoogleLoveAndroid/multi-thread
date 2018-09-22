package designpattern.TwoPhaseTermination.CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MyTask implements Runnable {
    private final CountDownLatch latch;
    private final int count;
    private static final Random random = new Random(314222);

    public MyTask(CountDownLatch latch, int count) {
        this.latch = latch;
        this.count = count;
    }

    @Override
    public void run() {
        doTask();
        // 每执行一次就会减一，直到为0
        latch.countDown();
        System.out.println("MyTask run latch.getCount = " + latch.getCount());
    }

    protected void doTask()
    {
        String name = Thread.currentThread().getName();
        //System.out.println(name + " :MyTask begin count = " + count);
        try
        {
            Thread.sleep(random.nextInt(1000));
        }
        catch (InterruptedException e)
        {

        }
        finally {
            System.out.println(name + " :MyTask end count = " + count);
        }
    }
}
