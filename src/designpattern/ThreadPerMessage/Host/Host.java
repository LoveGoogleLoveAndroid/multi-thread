package designpattern.ThreadPerMessage.Host;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Host {
    private final Helper helper = new Helper();
    /*private final ThreadFactory factory;

    public Host(ThreadFactory factory) {
        this.factory = factory;
    }
    private final Executor executor;

    public Host(Executor executor) {
        this.executor = executor;
    }*/
    private final ScheduledExecutorService service;

    public Host(ScheduledExecutorService service) {
        this.service = service;
    }

    public void request(final int count, final char c)
    {
        System.out.println("Host request begin");

        /*factory.newThread(new Runnable() {
              @Override
              public void run() {
                  helper.handle(count, c);
              }
          }
        ).start();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                helper.handle(count, c);
            }
        });*/

        service.schedule(new Runnable() {
            @Override
            public void run() {
                helper.handle(count, c);
            }
        }, 3L, TimeUnit.SECONDS);

        System.out.println("Host request end");
    }
}
