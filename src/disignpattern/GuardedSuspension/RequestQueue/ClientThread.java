package disignpattern.GuardedSuspension.RequestQueue;

import java.util.Random;

public class ClientThread extends Thread {
    private final Random random;
    private final RequestQueue queue;

    public ClientThread(RequestQueue queue, String name, long seed) {
        super(name);
        this.random = new Random(seed);
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            Request request = new Request("No. " + i);
            System.out.println(Thread.currentThread().getName() + " requests " + request);
            queue.putRequest(request);

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
