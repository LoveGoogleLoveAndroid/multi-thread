package basic.Daemon;

import java.util.Scanner;

public class DaemonThread {
    public static void main(String[] args) {
        System.out.println("enter main thread" + Thread.currentThread().getName());
        Thread thread = new Thread(new DaomonRunnable());
        // 如果不设置为守护线程，则主线程退出后，该线程还会继续执行下去
        // 设置为守护线程后，则主线程退出后，守护线程在接收到输入操作后会立即退出，导致数据没有写完整
        thread.setDaemon(true);
        thread.start();

        Scanner scanner = new Scanner(System.in);
        // 主线程进入阻塞状态，一旦有输入，则退出阻塞状态
        scanner.next();

        System.out.println("exist main thread" + Thread.currentThread().getName());
    }
}

