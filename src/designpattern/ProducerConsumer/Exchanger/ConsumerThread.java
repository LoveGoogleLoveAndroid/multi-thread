package designpattern.ProducerConsumer.Exchanger;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ConsumerThread extends Thread {
    private final Exchanger<char[]> exchanger;
    private char[] buffer = null;
    private final Random random;

    public ConsumerThread(Exchanger<char[]> exchanger, char[] buffer, long seed) {
        super("ConsumerThread");
        this.exchanger = exchanger;
        this.buffer = buffer;
        this.random = new Random(seed);
    }

    /*
    *
    *   ConsumerThread before exchange
        ProduceThread: A ->
        ProduceThread: B ->
        ProduceThread: C ->
        ProduceThread: D ->
        ProduceThread: E ->
        ProduceThread before change
        ProduceThread after change
        ConsumerThread after exchange

        ConsumerThread before exchange
        ProduceThread: J ->
        ProduceThread before change
        ProduceThread after change
        ConsumerThread after exchange
*/

    @Override
    public void run() {
        try {
            while (true)
            {
                System.out.println(Thread.currentThread().getName() + " before exchange");
                // exchange会抛出InterruptedException异常，等生产者将buffer填充并且调用了exchange后，就可以取到内容，然后把自己的空的buffer再传给生产者
                buffer = exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName() + " after exchange");

                for (int i = 0; i < buffer.length; i++) {
                    System.out.println(Thread.currentThread().getName() + ": -> " + buffer[i]);
                    Thread.sleep(random.nextInt(1000));
                }
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
