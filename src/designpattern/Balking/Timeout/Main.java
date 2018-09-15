package designpattern.Balking.Timeout;

import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) {
        Host host = new Host(5000);

        try
        {
            System.out.println("Execute begin.");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        // 可以在另一个线程设置守护条件状态
                        //host.setExecutable(true);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();

            host.execute();
        }
        catch (TimeoutException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
