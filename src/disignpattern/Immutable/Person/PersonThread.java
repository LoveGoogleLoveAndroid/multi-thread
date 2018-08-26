package disignpattern.Immutable.Person;

public class PersonThread extends Thread {
    private final Person person;
    private volatile boolean isRunning = true;

    public PersonThread(Person person) {
        this.person = person;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": begin");
        while (isRunning)
        {
            System.out.println(Thread.currentThread().getName() + ": " + person);
        }
        System.out.println(Thread.currentThread().getName() + ": end");
    }
}
