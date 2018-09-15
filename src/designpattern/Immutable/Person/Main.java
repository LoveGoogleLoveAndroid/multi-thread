package designpattern.Immutable.Person;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Alice", "Alaska");

        PersonThread thread1 = new PersonThread(person);
        thread1.start();
        PersonThread thread2 = new PersonThread(person);
        thread2.start();
        PersonThread thread3 = new PersonThread(person);
        thread3.start();

        try
        {
            Thread.sleep(10);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        thread1.setRunning(false);
        thread2.setRunning(false);
        thread3.setRunning(false);
    }
}
