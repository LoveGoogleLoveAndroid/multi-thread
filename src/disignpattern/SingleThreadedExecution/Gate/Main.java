package disignpattern.SingleThreadedExecution.Gate;

public class Main {

    public static void main(String[] args) {
        System.out.println("Testing gate:");

        Gate gate = new Gate();
        new UserThread("Alice", "Alaska", gate).start();
        new UserThread("Bobby", "Brazil", gate).start();
    }
}
