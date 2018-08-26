package disignpattern.GuardedSuspension.RequestQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RequestQueue {
    //private final Queue<Request> queue = new LinkedList<Request>();
    private final BlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    public /*synchronized */Request getRequest()
    {
        Request request = null;
        //while (queue.peek() == null)
        {
            try {
                //wait();
                // take 已经实现了互斥处理
                request = queue.take();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        //return queue.remove();
        return request;
    }

    public /*synchronized */void putRequest(Request request)
    {
        //queue.offer(request);
        //notifyAll();
        try
        {
            // put 已经实现了互斥处理
            queue.put(request);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
