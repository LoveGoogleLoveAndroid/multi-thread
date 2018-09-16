package designpattern.ReadWriteLock.Data;

import java.util.Random;

public class WriteThread extends Thread {
    private static final Random random = new Random();
    private final Data data;
    private final String filter;
    private int index = 0;

    public WriteThread(Data data, String filter) {
        this.data = data;
        this.filter = filter;
    }

    @Override
    public void run() {
        try
        {
            while (true)
            {
                char c = nextChar();
                data.write(c);
                System.out.println(Thread.currentThread().getName() + " writes " + c + " successfully");
                Thread.sleep(random.nextInt(1000));
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private char nextChar()
    {
        char c = filter.charAt(index);
        index++;

        if (index >= filter.length())
        {
            index = 0;
        }

        return c;
    }
}
