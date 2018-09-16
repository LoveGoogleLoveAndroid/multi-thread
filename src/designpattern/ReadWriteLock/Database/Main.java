package designpattern.ReadWriteLock.Database;

public class Main {
    public static void main(String[] args) {
        Database<String, String> database = new Database<>();

        new AssignThread(database, "Alice", "Alaska").start();
        new AssignThread(database, "Alice", "Australia").start();
        new AssignThread(database, "Bobby", "Brazil").start();
        new AssignThread(database, "Bobby", "Bulgaria").start();

        for (int i = 0; i < 50; i++) {
            new RetrieveThread(database, "Alice").start();
            new RetrieveThread(database, "Bobby").start();
        }

        // 这个延迟要长点，不然Bobby都连一次执行的机会都没有，导致读出来的是空
        try
        {
            Thread.sleep(20000);
        }
        catch (InterruptedException e)
        {

        }

        System.exit(0);
    }
}
