package designpattern.Immutable.SynchTrial;

public class Main {
    private static final long CALL_COUNT = 10000000L;

    public static void main(String[] args) {
        trial("NotSynch", CALL_COUNT, new NotSynch());
        trial("Synch", CALL_COUNT, new Synch());
    }

    private static void trial(String msg, long count, Object object)
    {
        System.out.println(msg + " begin");

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < CALL_COUNT; i++) {
            object.toString();
        }
        System.out.println(msg + " end");
        System.out.println("Elapsed duration: " + (System.currentTimeMillis() - startTime));
    }
}
