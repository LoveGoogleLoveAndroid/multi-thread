package designpattern.ReadWriteLock.Database;

import java.util.concurrent.atomic.AtomicInteger;

public class RetrieveThread extends Thread{
    private final Database<String, String> database;
    private final String key;
    private static final AtomicInteger counter = new AtomicInteger(0);

    public RetrieveThread(Database<String, String> database, String key) {
        this.database = database;
        this.key = key;
    }

    @Override
    public void run() {
        while (true)
        {
            int count = counter.incrementAndGet();
            System.out.println(count + " retrieve: " + key + " before");
            String value = database.retrieve(key);
            System.out.println(count + " retrieve: " + key + " -> " + value);
        }
    }
}
