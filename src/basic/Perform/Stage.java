package Perform;

public class Stage {

    public static void main(String[] args) {
        Actor actor = new Actor();
        actor.setName("sky");
        actor.start();

        Thread actress = new Thread(new Actress());
        actress.setName("lotus");
        actress.start();
    }
}
