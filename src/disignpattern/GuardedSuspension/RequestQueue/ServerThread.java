package disignpattern.GuardedSuspension.RequestQueue;

import java.util.Random;

public class ServerThread extends Thread {
    private final Random random;
    private final RequestQueue queue;

    public ServerThread(RequestQueue queue, String name, long seed) {
        super(name);
        this.random = new Random(seed);
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            Request request = queue.getRequest();
            System.out.println(Thread.currentThread().getName() + " responses " + request);

            try
            {
                Thread.sleep(random.nextInt(100));
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
