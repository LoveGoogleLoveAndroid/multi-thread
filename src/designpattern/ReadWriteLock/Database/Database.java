package designpattern.ReadWriteLock.Database;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database<K, V> {
    // 本身HashMap不是线程安全的，但是Database确是线程安全的
    private final Map<K, V> map = new HashMap<K, V>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true/* fair */);
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    // 20秒内使用 synchronized 可以读取大约455次
    // 455 retrieve: Bobby before
    // 20秒内使用 ReentrantReadWriteLock 可以读取大约2543次
    // 2543 retrieve: Bobby before

    public /*synchronized*/ void clear()
    {
        writeLock.lock();
        try{
            verySlowly();
            map.clear();
            }
        finally {
            writeLock.unlock();
        }
    }

    public /*synchronized */void assign(K key, V value)
    {
        writeLock.lock();
        try {
            verySlowly();
            map.put(key, value);
            System.out.println("database assign: key = " + key + ", value = " + value);
        }
        finally {
            writeLock.unlock();
        }
    }

    public /*synchronized*/ V retrieve(K key)
    {
        V value;

        readLock.lock();
        try {
            slowly();
            value = map.get(key);
            System.out.println("database retrieve: key = " + key + ", value = " + value);
        }
        finally {
            readLock.unlock();
        }
        return value;
    }

    private void slowly()
    {
        try
        {
            Thread.sleep(50);
        }
        catch (InterruptedException e)
        {

        }
    }

    private void verySlowly()
    {
        try
        {
            Thread.sleep(200);
        }
        catch (InterruptedException e)
        {

        }
    }
}
