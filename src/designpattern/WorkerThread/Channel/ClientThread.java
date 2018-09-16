package designpattern.WorkerThread.Channel;

import java.util.Random;
import java.util.concurrent.ExecutorService;

public class ClientThread extends Thread {
    //private final Channel channel;
    private final ExecutorService service;
    private final static Random random = new Random();

    /*public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }*/

    public ClientThread(String name, ExecutorService service) {
        super(name);
        this.service = service;
    }

    @Override
    public void run() {
        try
        {
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                //channel.putRequest(request);
                service.execute(request);
                Thread.sleep(random.nextInt(200));
            }
        }
        catch (InterruptedException e)
        {

        }
    }
}
