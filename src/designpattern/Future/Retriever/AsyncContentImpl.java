package designpattern.Future.Retriever;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class AsyncContentImpl extends FutureTask<SyncContentImpl> implements Content{
    /*private SyncContentImpl syncContent;
    private boolean ready = false;

    public synchronized void setContent(SyncContentImpl syncContent)
    {
        this.syncContent = syncContent;
        this.ready = true;
        notifyAll();
    }*/

    public AsyncContentImpl(Callable<SyncContentImpl> callable) {
        super(callable);
    }

    public /*synchronized*/ byte[] getBytes()
    {
        /*while (!ready)
        {
            try {
                wait();
            }
            catch (InterruptedException e)
            {

            }
        }*/
        byte[] bytes = null;
        try
        {
            bytes = get().getBytes();
        }
        catch (InterruptedException e)
        {

        }
        catch (ExecutionException e)
        {

        }

        //return syncContent.getBytes();
        return bytes;
    }
}
