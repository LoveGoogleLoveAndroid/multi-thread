package designpattern.ReadWriteLock.Database;

import java.util.HashMap;
import java.util.Map;

public class Database<K, V> {
    // 本身HashMap不是线程安全的，但是Database确是线程安全的
    private final Map<K, V> map = new HashMap<K, V>();

    public synchronized void clear()
    {
        verySlowly();
        map.clear();
    }

    public synchronized void assign(K key, V value)
    {
        verySlowly();
        map.put(key, value);
        System.out.println("database assign: key = " + key + ", value = " + value);
    }

    public synchronized V retrieve(K key)
    {
         slowly();
         V value = map.get(key);
         System.out.println("database retrieve: key = " + key + ", value = " + value);
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
