package designpattern.ThreadPerMessage.Host;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {

    public static void main(String[] args) {
        System.out.println("main begin");

        //Host host = new Host(Executors.defaultThreadFactory());
        /*Host host = new Host(new Executor() {
            @Override
            public void execute(Runnable command) {
                new Thread(command).start();
            }
        });*/
        //ExecutorService service = Executors.newCachedThreadPool();
        //Host host = new Host(service);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        Host host = new Host(service);

        try {
            host.request(10, 'A');
            host.request(15, 'B');
            host.request(20, 'C');
        }
        finally {
            service.shutdown();
        }

        System.out.println("main end");
    }
}
