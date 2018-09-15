package designpattern.ProducerConsumer.Exchanger;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ProduceThread extends Thread {
    private final Exchanger<char[]> exchanger;
    private char[] buffer = null;
    private char count = 0;
    private final Random random;

    public ProduceThread(Exchanger<char[]> exchanger, char[] buffer, long seed) {
        super("ProduceThread");
        this.exchanger = exchanger;
        this.buffer = buffer;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        try
        {
            while (true)
            {
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = nextChar();
                    System.out.println(Thread.currentThread().getName() + ": "+ buffer[i] + " ->");
                }

                System.out.println(Thread.currentThread().getName() + " before change");
                // exchange会抛出InterruptedException异常，开始消费者拿不到内容，等生产者写入内容到buffer后，才能和消费者空的buffer互换
                buffer = exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName() + " after change");
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private char nextChar() throws InterruptedException
    {
        char c = (char)('A' + count % 26);
        count++;

        Thread.sleep(random.nextInt(1000));
        return c;
    }
}
