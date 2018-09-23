package designpattern.ActiveObject.Servant;

public class ScheduleThread extends Thread {
    private final ActivationQueue queue;

    public ScheduleThread(ActivationQueue queue) {
        this.queue = queue;
    }

    public void invoke(MethodRequest request)
    {
        queue.putRequest(request);
    }

    @Override
    public void run() {
        while (true)
        {
            MethodRequest request = queue.takeRequest();
            // 分别由MakeStringRequest和DisplayStringRequest调用
            request.execute();
        }
    }
}
