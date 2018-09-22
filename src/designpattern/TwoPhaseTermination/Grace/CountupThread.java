package designpattern.TwoPhaseTermination.Grace;

public class CountupThread extends Thread {
    private long count = 0;
    private volatile boolean isRunning = true;
    private long start = 0;

    // 由外部调用，发出终止请求
    public void shutdownRequest()
    {
        isRunning = false;
        start = System.currentTimeMillis();
        // 发出终止请求，1 线程变成中断状态，反应为"状态"，2 也会抛出InterruptedException的异常，反应为"控制"，通常情况下是结果1，如果线程正在sleep，wait，或者join，就是结果2
        // 结果1和结果2是可以互相转换的
        // 如果发出终止请求时，线程正在sleep，wait，或者join，就会抛出InterruptedException的异常，即使抛出了异常，finally里的终止行为也会尽快的执行
        // 如果发出终止请求时，线程正在sleep，线程也不会开始终止处理，这样程序的响应性会下降，调用interrupt可以中断sleep
        // 如果发出终止请求时，线程正在wait，线程也不会从等待队列出来，必须使用interrupt对线程下达中断wait的指示
        // 如果调用interrupt，doShutdown: duration = 0 milli seconds，但是会CountupThread run: got a InterruptedException
        // 如果不调用interrupt，doShutdown: duration = 31 milli seconds
        interrupt();
    }

    // 是否已经发出终止请求
    public boolean isShutdownRequested()
    {
        return !isRunning;
    }

    // 处理耗时工作
    private void doWork() throws InterruptedException
    {
        count++;
        System.out.println("doWork: count = " + count);
        Thread.sleep(500);
    }

    // 终止处理
    private void doShutdown()
    {
        System.out.println("doShutdown: count = " + count);
        System.out.println("doShutdown: duration = " + (System.currentTimeMillis() - start) + " milli seconds");
    }

    @Override
    public void run() {
        try
        {
            while (isRunning)
            {
                doWork();
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("CountupThread run: got a InterruptedException");
        }
        finally {
            doShutdown();
        }
    }
}
