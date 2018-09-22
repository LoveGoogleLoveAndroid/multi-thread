package designpattern.TwoPhaseTermination.Grace;

public class Main {
    public static void main(String[] args) {
        System.out.println("main begin.");
        try
        {
            CountupThread thread = new CountupThread();
            thread.start();

            Thread.sleep(5000);

            System.out.println("main shutdownRequest");
            thread.shutdownRequest();

            System.out.println("main join begin");
            thread.join();
            System.out.println("main join end");
        }
        catch (InterruptedException e)
        {
            System.out.println("main got a InterruptedException");
            e.printStackTrace();
        }

        System.out.println("main end.");
    }
}
