package disignpattern.Immutable.MutablePerson;

public class Main {

    public static void main(String[] args) {
        MutablePerson person = new MutablePerson("start", "start");

        new CrackerThread(person).start();
        new CrackerThread(person).start();
        new CrackerThread(person).start();

        for (int i = 0; true; i++) {
            person.setPerson("" + i, "" + i);
        }
    }
}
