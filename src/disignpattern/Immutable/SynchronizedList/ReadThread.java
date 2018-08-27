package disignpattern.Immutable.SynchronizedList;

import java.util.List;

public class ReadThread extends Thread {
    private final List<Integer> list;

    public ReadThread(List<Integer> list) {
        super("ReaderThread");
        this.list = list;
    }

    @Override
    public void run() {
        //synchronized (list) {
            while (true) {
                for (int n : list) {
                    System.out.println(n);
                }
            }
        //}
    }
}
