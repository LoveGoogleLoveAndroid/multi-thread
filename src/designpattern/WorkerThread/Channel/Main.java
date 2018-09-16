package designpattern.WorkerThread.Channel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        /*Channel channel = new Channel(5);
        channel.startWorkers();

        new ClientThread("Alice", channel).start();
        new ClientThread("Bobby", channel).start();
        new ClientThread("Chris", channel).start();*/
        ExecutorService service = Executors.newFixedThreadPool(5);
        try
        {
            new ClientThread("Alice", service).start();
            new ClientThread("Bobby", service).start();
            new ClientThread("Chris", service).start();

            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {

        }
        finally {
            service.shutdown();
        }
    }
}
