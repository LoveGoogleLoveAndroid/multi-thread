package designpattern.GuardedSuspension.Talk;

import designpattern.GuardedSuspension.RequestQueue.Request;
import designpattern.GuardedSuspension.RequestQueue.RequestQueue;

public class Main {
    public static void main(String[] args) {
        RequestQueue queueInput = new RequestQueue();
        RequestQueue queueOutput = new RequestQueue();
        queueInput.putRequest(new Request("hello"));

        new TalkThread("alice", queueInput, queueOutput).start();
        new TalkThread("bobby", queueOutput, queueInput).start();
    }
}
