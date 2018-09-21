package designpattern.Future.FutureData;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureData extends FutureTask<RealData> implements Data {
    /*private RealData realData = null;
    private boolean ready = false;


    public synchronized void setRealData(RealData realData)
    {
        System.out.println(Thread.currentThread().getName() + " FutureData setRealData ready = " + ready);
        if (ready)
        {
            return;
        }

        this.realData = realData;
        this.ready = true;
        notifyAll();
    }*/

    public FutureData(Callable<RealData> callable) {
        super(callable);
    }

    @Override
    public synchronized String getContent() {
        System.out.println(Thread.currentThread().getName() + " FutureData getContent");
        /*while (!ready)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }*/
        String string = null;
        try
        {
             string = get().getContent();
        }
        catch (InterruptedException e)
        {

        }
        catch (ExecutionException e)
        {

        }

        return string;
        //return realData.getContent();
    }
}
