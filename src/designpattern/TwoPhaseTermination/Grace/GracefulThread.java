package designpattern.TwoPhaseTermination.Grace;

public class GracefulThread extends Thread {
    private volatile boolean isRunning = true;

    public final void shutdownRequest() {
        isRunning = false;
        interrupt();
    }

    public final boolean isShutdownRequested()
    {
        return !isRunning;
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

        }
        finally {
            doShutdown();
        }
    }

    protected void doWork() throws InterruptedException
    {

    }

    protected void doShutdown()
    {

    }
}
