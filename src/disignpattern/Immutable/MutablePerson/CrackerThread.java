package disignpattern.Immutable.MutablePerson;

public class CrackerThread extends Thread {
    private final MutablePerson mutablePerson;

    public CrackerThread(MutablePerson person) {
        this.mutablePerson = person;
    }

    @Override
    public void run() {
        while (true){
            ImmutablePerson immutablePerson = new ImmutablePerson(mutablePerson);

            if (!immutablePerson.getAddress().equals(immutablePerson.getAddress()))
            {
                System.out.println(currentThread().getName() + " ***** broken *****");
            }
        }
    }
}
