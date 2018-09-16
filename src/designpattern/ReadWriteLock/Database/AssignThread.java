package designpattern.ReadWriteLock.Database;

import java.util.Random;

public class AssignThread extends Thread {
    private static Random random = new Random(100000);
    private final Database<String, String> database;
    private final String key;
    private final String value;

    public AssignThread(Database<String, String> database, String key, String value) {
        this.database = database;
        this.key = key;
        this.value = value;
    }

    @Override
    public void run() {
        while (true)
        {
            System.out.println(Thread.currentThread().getName() + " assigns: key = " + key + ", value = " + value);
            database.assign(key, value);
            slowly();
        }
    }

    private void slowly()
    {
        try
        {
            Thread.sleep(random.nextInt(100));
        }
        catch (InterruptedException e)
        {

        }
    }
}
