package designpattern.ThreadPerMessage.Host;

public class Host {
    private final Helper helper = new Helper();

    public void request(final int count, final char c)
    {
        System.out.println("Host request begin");

        new Thread()
        {
            @Override
            public void run() {
                helper.handle(count, c);
            }
        }.start();

        System.out.println("Host request end");
    }
}
