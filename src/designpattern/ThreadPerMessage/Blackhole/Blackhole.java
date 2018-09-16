package designpattern.ThreadPerMessage.Blackhole;

public class Blackhole {
    public static void enter(Object object)
    {
        System.out.println("step 1");
        magic(object);
        System.out.println("step 2");

        synchronized (object)
        {
            System.out.println("step 3, should never be reached");
        }
    }

    private static void magic(final Object object)
    {
        Thread thread = new Thread(){
            @Override
            public void run() {
                synchronized (object)
                {
                    synchronized (this)
                    {
                        this.setName("Locked");
                        this.notifyAll();
                    }
                    System.out.println("enter endless loop");
                    while (true)
                    {
                        //
                    }
                }
            }
        };

        synchronized (thread)
        {
            thread.setName("thread");
            thread.start();

            while (thread.getName().equals("thread"))
            {
                System.out.println("thread enter waiting");
                try
                {
                    thread.wait();
                }
                catch (InterruptedException e)
                {

                }
            }
        }
    }
}
