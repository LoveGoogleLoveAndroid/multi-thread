package designpattern.ProducerConsumer.Wait;

public class Main {

    public static void main(String[] args) {
        System.out.println("begin");
        try {
            Something.method(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("end");
    }
}
