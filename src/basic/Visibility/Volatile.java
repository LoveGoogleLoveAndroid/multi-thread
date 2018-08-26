package Visibility;

import java.util.concurrent.locks.ReentrantLock;

public class Volatile {
    private int number = 0;
    private ReentrantLock lock = new ReentrantLock();

    private int getNumber()
    {
        return number;
    }

    // 1. 读取number的值  2. 将number加1  3. 写入最新的number的值
    // 线程A读取number的值    比如5
    // 线程B读取number的值               5
    // 线程B执行+1                      6
    // 线程B写入最新的值                 6
    // 线程A执行+1            6
    // 线程A写入最新的值       6
    // 两个线程两次+1操作，其中一次的写操作未及时更新，导致只+了一次
    private void increase()
    {
        /*
        synchronized (this) {
            number++;
        }*/
        lock.lock();

        try {
            number++;
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final Volatile vola = new Volatile();

        for (int i = 0; i <100 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    vola.increase();
                }
            }).start();
        }
        System.out.println("main thread .");

        // 如果还有子线程在运行，主线程就让出CPU
        while (Thread.activeCount() > 2)
        {
            System.out.println("Thread.activeCount() = " + Thread.activeCount());
            Thread.yield();
        }

        System.out.println("number = " + vola.getNumber());
    }
}
