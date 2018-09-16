package designpattern.ThreadPerMessage.Host;

import java.util.concurrent.ThreadFactory;

public class Host {
    private final Helper helper = new Helper();
    private final ThreadFactory factory;

    public Host(ThreadFactory factory) {
        this.factory = factory;
    }

    public void request(final int count, final char c)
    {
        System.out.println("Host request begin");

        factory.newThread(new Runnable() {
              @Override
              public void run() {
                  helper.handle(count, c);
              }
          }
        ).start();

        System.out.println("Host request end");
    }
}
