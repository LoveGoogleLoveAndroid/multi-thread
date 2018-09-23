package designpattern.ActiveObject.Servant;

public class ActivationQueue {
    private static final int MAX_REQUEST = 100;
    private final MethodRequest[] requests;
    private int head;
    private int tail;
    private int count;

    public ActivationQueue() {
        this.requests = new MethodRequest[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    public synchronized void putRequest(MethodRequest request)
    {
        while (count >= MAX_REQUEST)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {

            }
        }

        requests[tail] = request;
        tail = (tail + 1) % requests.length;
        count++;
        notifyAll();
    }

    public synchronized MethodRequest takeRequest()
    {
        while (count <= 0)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {

            }
        }

        MethodRequest request = requests[head];
        head = (head + 1) % requests.length;
        count--;
        notifyAll();

        return request;
    }
}
