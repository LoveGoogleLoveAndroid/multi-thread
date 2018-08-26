package disignpattern.GuardedSuspension.Talk;

import disignpattern.GuardedSuspension.RequestQueue.Request;
import disignpattern.GuardedSuspension.RequestQueue.RequestQueue;

public class Main {
    public static void main(String[] args) {
        RequestQueue queueInput = new RequestQueue();
        RequestQueue queueOutput = new RequestQueue();
        queueInput.putRequest(new Request("hello"));

        new TalkThread("alice", queueInput, queueOutput).start();
        new TalkThread("bobby", queueOutput, queueInput).start();
    }
}
