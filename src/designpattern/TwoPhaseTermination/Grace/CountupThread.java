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
