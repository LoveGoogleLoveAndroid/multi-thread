package basic.Dynasty;

public class ArmyRunnable implements Runnable {

    // 保证每次都是从内存中读取最新的值
    volatile boolean keepRunning = true;

    @Override
    public void run() {
        while (keepRunning)
        {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " attacked " + i + " times");

                // 如果加yield，让出CPU，可以使得两个线程大体上呈现交替执行的，但偶尔一个线程还是会执行多次，即下次谁进攻还不确定
                // 如果不加yield，则一个线程可能连续进攻3次，或者进攻10次，另一个线程才会执行
                Thread.yield();
            }
        }

        System.out.println(Thread.currentThread().getName() + " attack finished!");
    }
}
