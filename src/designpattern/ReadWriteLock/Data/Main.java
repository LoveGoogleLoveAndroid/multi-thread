package designpattern.ReadWriteLock.Data;

public class Main {
    public static void main(String[] args) {
        Data data = new Data(10);
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();

        new WriteThread(data, "ABCDEFGHIJK").start();
        new WriteThread(data, "abcdefghijk").start();
    }
}
