package designpattern.ProducerConsumer.Exchanger;

import java.util.concurrent.Exchanger;

public class Main {
    private static final int MAX_BUFFER_SIZE = 5;

    public static void main(String[] args) {
        Exchanger<char[]> exchanger = new Exchanger<>();
        char[] buffer1 = new char[MAX_BUFFER_SIZE];
        char[] buffer2 = new char[MAX_BUFFER_SIZE];

        new ProduceThread(exchanger, buffer1, 1000).start();
        new ConsumerThread(exchanger, buffer2, 2000).start();
    }
}
