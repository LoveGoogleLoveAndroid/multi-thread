package designpattern.ProducerConsumer.Cake;

public class Table {
    private final String[] buffer;
    private int head;
    private int tail;
    private int count;

    public Table(int count) {
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
        System.out.println(Thread.currentThread().getName() + " puts " + cake + ", count = " + count);
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
        System.out.println(Thread.currentThread().getName() + " takes " + cake + ", count = " + count);
        return cake;
    }
}
