package disignpattern.GuardedSuspension.Talk;

import disignpattern.GuardedSuspension.RequestQueue.Request;
import disignpattern.GuardedSuspension.RequestQueue.RequestQueue;

public class TalkThread extends Thread {
    private final RequestQueue input;
    private final RequestQueue output;

    public TalkThread(String name, RequestQueue input, RequestQueue output) {
        super(name);
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " begin.");

        for (int i = 0; i < 10; i++) {
            Request request1 = input.getRequest();
            System.out.println(Thread.currentThread().getName() + " gets " + request1);

            Request request2 = new Request(request1.getName() + " responses!");
            System.out.println(Thread.currentThread().getName() + " puts " + request2);
            output.putRequest(request2);
        }

        System.out.println(Thread.currentThread().getName() + " end.");
    }
}
