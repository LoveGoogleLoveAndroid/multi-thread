package basic.Window;

public class Window {
    private static final int THREAD_COUNT = 5;

    public static void main(String[] args) {
        TicketThread ticketThread = new TicketThread();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(ticketThread, "Window_" + i);
            thread.start();
        }
    }

}
