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
                String cake = table.take();
                //System.out.println(cake + " eaten by " + Thread.currentThread().getName());
                Thread.sleep(random.nextInt(1000));
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
