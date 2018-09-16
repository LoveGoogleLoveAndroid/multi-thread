package designpattern.WorkerThread.Channel;

public class WorkerThreads extends Thread{
    private final Channel channel;

    public WorkerThreads(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true)
        {
            Request request = channel.takeRequest();
            request.execute();
        }
    }
}
