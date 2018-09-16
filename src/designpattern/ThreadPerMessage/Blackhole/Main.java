package designpattern.ThreadPerMessage.Blackhole;

public class Main {
    public static void main(String[] args) {
        System.out.println("main begin");
        Object object = new Object();
        Blackhole.enter(object);
        System.out.println("main end");
    }
}
