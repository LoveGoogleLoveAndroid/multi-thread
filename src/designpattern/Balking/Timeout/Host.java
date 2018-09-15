package designpattern.Balking.Timeout;

import java.util.concurrent.TimeoutException;

public class Host {
    private final long timeout;
    private boolean ready = false;

    public Host(long timeout) {
        this.timeout = timeout;
    }

    // 在外部调用此方法，可满足守护条件
    public synchronized void setExecutable(boolean on)
    {
        ready = on;
        notifyAll();
        System.out.println("wake up!");
    }

    public synchronized void execute() throws TimeoutException, InterruptedException
    {
        long start = System.currentTimeMillis();
        while (!ready)
        {
            long now = System.currentTimeMillis();
            long rest = timeout - (now - start);

            if (rest <= 0)
            {
                throw new TimeoutException("now - start = " + (now - start) + ", timeout = " + timeout);
            }

            // 未满足守护条件，等待
            System.out.println("I am waiting");
            wait(rest);
            System.out.println("I wake up");
        }

        doExecute();
    }

    private void doExecute()
    {
        System.out.println(Thread.currentThread().getName() + " calls doExecute.");
    }
}
