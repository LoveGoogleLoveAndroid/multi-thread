package designpattern.Immutable.SynchronizedList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {
        //List<Integer> list = new ArrayList<Integer>();
        //final List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());    // 这个没用哦
        final List<Integer> list = new CopyOnWriteArrayList<Integer>();

        new WriteThread(list).start();
        new ReadThread(list).start();
    }
}
