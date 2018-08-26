package Dynasty;

public class Hero extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started attack!");

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " continues attack!");
        }
        System.out.println(Thread.currentThread().getName() + " continues attack!");
        System.out.println(Thread.currentThread().getName() + " finished attack!");
    }
}
