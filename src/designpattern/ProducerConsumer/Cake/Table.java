package designpattern.ProducerConsumer.Cake;

import java.util.concurrent.ArrayBlockingQueue;

public class Table /*extends ArrayBlockingQueue<String>*/{
    private final String[] buffer;
    private int head;
    private int tail;
    private int count;

    public Table(int count) {
        //super(count);
        this.buffer = new String[count];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    public synchronized void put(String cake) throws InterruptedException
    {
        while (count >= buffer.length)
        {
            wait();
        }

        buffer[tail] = cake;
        tail = (tail + 1) % buffer.length;
        count++;
        notifyAll();
        //super.put(cake);
        System.out.println(Thread.currentThread().getName() + " puts " + cake/* + ", count = " + count*/);
    }

    public synchronized String take() throws InterruptedException
    {
        while (count <= 0)
        {
            wait();
        }

        String cake = buffer[head];
        head = (head + 1) % buffer.length;
        count--;
        notifyAll();
        //String cake = super.take();
        System.out.println(Thread.currentThread().getName() + " takes " + cake/* + ", count = " + count*/);
        return cake;
    }

    public synchronized void clear()
    {

        this.head = 0;
        this.tail = 0;
        this.count = 0;
        notifyAll();

        //super.clear();
    }
}
