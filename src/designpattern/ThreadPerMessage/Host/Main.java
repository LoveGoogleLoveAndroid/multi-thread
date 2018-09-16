package designpattern.ThreadPerMessage.Host;

public class Main {

    public static void main(String[] args) {
        System.out.println("main begin");

        Host host = new Host();
        host.request(10, 'A');
        host.request(15, 'B');
        host.request(20, 'C');

        System.out.println("main end");
    }
}
