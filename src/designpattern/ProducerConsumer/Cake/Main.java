package designpattern.ProducerConsumer.Cake;

public class Main {
    public static void main(String[] args) {
        Table table = new Table(3);
        Thread[] threads = {
                new MakerThread("MakerThread-1", 1000, table),
                new MakerThread("MakerThread-2", 2000, table),
                new MakerThread("MakerThread-3", 3000, table),

                new EatThread("EatThread-1", 1001, table),
                new EatThread("EatThread-2", 2001, table),
                new EatThread("EatThread-3", 3001, table),

                new ClearThread("ClearThread", table),
        };

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {

        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].interrupt();
        }
    }
}
