package designpattern.ProducerConsumer.Cake;

public class Main {
    public static void main(String[] args) {
        Table table = new Table(3);
        new MakerThread("MakerThread-1", 1000, table).start();
        new MakerThread("MakerThread-2", 2000, table).start();
        new MakerThread("MakerThread-3", 3000, table).start();
        new EatThread("EatThread-1", 1001, table).start();
        new EatThread("EatThread-2", 2001, table).start();
        new EatThread("EatThread-3", 3001, table).start();
    }
}
