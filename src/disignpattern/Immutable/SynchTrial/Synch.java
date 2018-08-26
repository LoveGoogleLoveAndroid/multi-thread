package disignpattern.Immutable.SynchTrial;

public class Synch {
    private final String name = "Synch";

    @Override
    public synchronized String toString() {
        return "Synch{" +
                "name='" + name + '\'' +
                '}';
    }
}
