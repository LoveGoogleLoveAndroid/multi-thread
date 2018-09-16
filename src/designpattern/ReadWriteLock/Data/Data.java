package designpattern.ReadWriteLock.Data;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Data {
    private final char[] buffer;
    //private final ReadWriteLocks lock = new ReadWriteLocks();
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true/* fair */);
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Data(int size) {
        this.buffer = new char[size];

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException
    {
        //lock.readLock();
        readLock.lock();

        try {
            return doRead();
        }
        finally {
            //lock.readUnlock();
            readLock.unlock();
        }
    }

    public void write(char c) throws InterruptedException
    {
        //lock.writeLock();
        writeLock.lock();

        try {
            doWrite(c);
        }
        finally {
            //lock.writeUnlock();
            writeLock.unlock();
        }
    }

    private char[] doRead()
    {
        char[] newBuf = new char[buffer.length];

        for (int i = 0; i < buffer.length; i++) {
            newBuf[i] = buffer[i];
        }

        slowly();

        return newBuf;
    }

    private void doWrite(char c)
    {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly();
        }
    }

    private void slowly()
    {
        try{
            Thread.sleep(50);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
