package designpattern.ProducerConsumer.Cake;


import java.util.Random;

public class EatThread extends Thread {
    private final Random random;
    private final Table table;

    public EatThread(String name, long seed, Table table) {
        super(name);
        this.random = new Random(seed);
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 加这个好像没啥用
                /*
                if (Thread.currentThread().isInterrupted())
                {
                    System.out.println(Thread.currentThread().getName() + " has interrupted");
                    return;
                }*/
                String cake = table.take();
                //System.out.println(cake + " eaten by " + Thread.currentThread().getName());
                Thread.sleep(random.nextInt(1000));
            }
        }
        catch (InterruptedException e)
        {
            // sleep的时候在外部如果调用interrupt停止线程，会导致该异常，在此时停止线程
            //Thread.interrupted();
            System.out.println(Thread.currentThread().getName() + " has interrupted");
            this.interrupt();
            //e.printStackTrace();
        }
    }
}
